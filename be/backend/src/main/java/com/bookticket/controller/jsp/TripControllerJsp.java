package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Request.TripRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.OrderOnline;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.pojo.Vehicle;
import com.bookticket.service.IncreasedPriceService;
import com.bookticket.service.RouteService;
import com.bookticket.service.TicketService;
import com.bookticket.service.TripService;
import com.bookticket.service.UserService;
import com.bookticket.service.VehicleService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class TripControllerJsp {

    @Autowired
    private TripService tripService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private IncreasedPriceService increasedPriceService;

    @GetMapping("/admin/trip")
    public String getTrips(@RequestParam Map<String, String> params, Model model) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TripRequest> trips = tripService.getAdminTrips(params);
        model.addAttribute("trips", trips);
        if (!trips.isEmpty()) {
            model.addAttribute("totalPage", trips.get(0).getTotalPage());
        }

        return "trip";
    }

    @GetMapping("/employee/trip")
    public String getTripsForEmployee(@RequestParam Map<String, String> params, Model model) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TripRequest> trips = tripService.getAdminTrips(params);
        model.addAttribute("trips", trips);
        if (!trips.isEmpty()) {
            model.addAttribute("totalPage", trips.get(0).getTotalPage());
        }

        return "tripEmployeeView";
    }

    @GetMapping("/driver/trip")
    public String getTripsForDriver(@RequestParam Map<String, String> params, Model model) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        String driverId = "";
        org.springframework.security.core.Authentication auth
                = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();

            if (principal instanceof User) {
                User user = (User) principal;
                driverId = user.getId();
            }
        }

        List<TripRequest> trips = tripService.getTripsByDriverId(params, driverId);
        model.addAttribute("trips", trips);
        if (!trips.isEmpty()) {
            model.addAttribute("totalPage", trips.get(0).getTotalPage());
        }

        return "tripDriverView";
    }

    @GetMapping("/admin/trip/add")
    public String newTrip(Model model) {
        model.addAttribute("addTripModel", new TripRequest());

        return "addTrip";
    }

    @PostMapping("/admin/trip/add")
    public String addTrip(@ModelAttribute(value = "addTripModel") @Valid TripRequest p,
            BindingResult rs,
            Model model) throws ParseException {

        // Formaing departureTime
//      System.out.println("Time: " + p.getDepartureTime()); 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        String departureTime = p.getDepartureTime();
        departureTime = departureTime.replace("T", " ");
        departureTime = departureTime.concat(":00");
        Date formatDepartureTime = dateFormat.parse(departureTime);

        // Formating arrivalTime
//      System.out.println("Time: " + p.getArrivalTime()); 
        String arrivalTime = p.getArrivalTime();
        arrivalTime = arrivalTime.replace("T", " ");
        arrivalTime = arrivalTime.concat(":00");
        Date formatArrivalTime = dateFormat.parse(arrivalTime);

        if (formatDepartureTime.compareTo(formatArrivalTime) >= 0) {
            rs.rejectValue("departureTime", "departureTime.lessThanArrivalTime",
                    "Departure time must be less than Arrival Time");
            return "addTrip";
        }
        Date now = new Date();
        if (formatDepartureTime.compareTo(now) <= 0) {
            rs.rejectValue("departureTime", "departureTime.greatterThanNow",
                    "Departure time must be greater than Now Time");
            return "addTrip";
        }

        // Formating price
        String price = p.getPrice();

        if (!price.matches("\\d+")) {
            rs.rejectValue("price", "price.isNumberic",
                    "Price must be numberic");
            return "addTrip";
        }

        Double formatPrice = Double.valueOf(price);

        String driverId = p.getDriverName();
        Integer routeId = Integer.valueOf(p.getRouteName());
        Integer vehicleId = Integer.valueOf(p.getSeatCapacity());

        User driver = userService.getUserById(driverId);
        Route route = routeService.getRouteById(routeId);
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        Trip trip = new Trip();
        trip.setDepartureTime(formatDepartureTime);
        trip.setArrivalTime(formatArrivalTime);
        trip.setPrice(formatPrice);
        trip.setDriverId(driver);
        trip.setRouteId(route);
        trip.setVehicleId(vehicle);
        trip.setIsActive(Short.valueOf("1"));

        if (this.tripService.addTrip(trip)) {
            return "redirect:/admin/trip";
        }

        return "addTrip";
    }

    @GetMapping("/admin/trip/{id}")
    public String editTrip(@PathVariable(value = "id") Integer id,
            Model model) {

        Trip trip = this.tripService.getTripById(id);

        // Formaing departureTime
//      System.out.println("Time: " + p.getDepartureTime()); 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        String departureTime = dateFormat.format(trip.getDepartureTime());

        // Formaing arrivalTime
        String arrivalTime = dateFormat.format(trip.getArrivalTime());

        // Formating price
        String price = trip.getPrice().toString();

        TripRequest tripRequest = new TripRequest();
        tripRequest.setId(id);
        tripRequest.setDepartureTime(departureTime);
        tripRequest.setArrivalTime(arrivalTime);
        tripRequest.setPrice(price);
        tripRequest.setDriverId(trip.getDriverId().getId());
        tripRequest.setDriverName(trip.getDriverId().getName());
        tripRequest.setRouteId(trip.getRouteId().getId());
        tripRequest.setRouteName(trip.getRouteId().getName());
        tripRequest.setVehicleId(trip.getVehicleId().getId());
        tripRequest.setSeatCapacity(String.valueOf(trip.getVehicleId().getSeatCapacity()));

        model.addAttribute("Trip", tripRequest);
        return "editTrip";
    }

    @PostMapping("/admin/trip")
    public String editTrip(@ModelAttribute(value = "Trip") @Valid TripRequest p,
            BindingResult rs,
            Model model) throws ParseException {

        // Formaing departureTime
//      System.out.println("Time: " + p.getDepartureTime()); 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        String departureTime = p.getDepartureTime();
        departureTime = departureTime.replace("T", " ");
        departureTime = departureTime.concat(":00");
        Date formatDepartureTime = dateFormat.parse(departureTime);

        // Formating arrivalTime
//      System.out.println("Time: " + p.getArrivalTime()); 
        String arrivalTime = p.getArrivalTime();
        arrivalTime = arrivalTime.replace("T", " ");
        arrivalTime = arrivalTime.concat(":00");
        Date formatArrivalTime = dateFormat.parse(arrivalTime);

        if (formatDepartureTime.compareTo(formatArrivalTime) >= 0) {
            rs.rejectValue("departureTime", "departureTime.lessThanArrivalTime",
                    "Departure time must be less than Arrival Time");
            model.addAttribute("departureError", "Departure time must be less than Arrival Time");
            return "redirect:/admin/trip/" + p.getId();
        }

        Date now = new Date();
        if (formatDepartureTime.compareTo(now) <= 0) {
            rs.rejectValue("departureTime", "departureTime.greatterThanNow",
                    "Departure time must be less than Arrival Time");
            model.addAttribute("departureError", "Departure time must be less than Arrival Time");
            return "redirect:/admin/trip/" + p.getId();
        }

        // Formating price
        String price = p.getPrice();

        if (!price.matches("\\d+")) {
            rs.rejectValue("price", "price.isNumberic",
                    "Price must be numberic");
            model.addAttribute("priceError", "Price must be numeric");
            return "redirect:/admin/trip/" + p.getId();
        }

        Double formatPrice = Double.valueOf(price);

        String driverId = p.getDriverName();
        Integer routeId = Integer.valueOf(p.getRouteName());
        Integer vehicleId = Integer.valueOf(p.getSeatCapacity());

        User driver = userService.getUserById(driverId);
        Route route = routeService.getRouteById(routeId);
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        Trip trip = new Trip();
        trip.setId(p.getId());
        trip.setDepartureTime(formatDepartureTime);
        trip.setArrivalTime(formatArrivalTime);
        trip.setPrice(formatPrice);
        trip.setDriverId(driver);
        trip.setRouteId(route);
        trip.setVehicleId(vehicle);
        trip.setIsActive(Short.valueOf("1"));

        if (this.tripService.editTrip(trip)) {
            return "redirect:/admin/trip";
        }

        return "redirect:/admin/trip/" + p.getId();
    }

    @PutMapping("admin/trip/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable(value = "id") Integer id) {
        Trip t = this.tripService.getTripById(id);

        t.setIsActive(Short.valueOf("0"));

        this.tripService.deleteTrip(t);
    }

    @GetMapping("admin/trip/addTicket/{id}")
    public String newTicketInTrip(Model model, @PathVariable(value = "id") Integer id) {

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTripId(id);
//               TicketRequest ticketRequest = new TicketRequest();
        Trip trip = this.tripService.getTripById(id);
//        ticketRequest.setTripId(id);
        long longStartDate = trip.getDepartureTime().getTime();
        IncreasedPrice increase = this.increasedPriceService.checkIncreasePrice(longStartDate);
        ticketRequest.setEventName(increase.getEventName());
        ticketRequest.setIncreasedPercentage(increase.getIncreasedPercentage());
        ticketRequest.setIncreasePriceId(increase.getId());

        model.addAttribute("addTicketInTrip", ticketRequest);

        return "addTicketInTrip";
    }

    @PostMapping("admin/trip/addTicket/{id}")
    public String addTicketInTrip(HttpServletResponse response,
            Model model, @ModelAttribute(value = "addTicketInTrip") TicketRequest ticketRequest
    ) throws DocumentException, IOException {

        String employeeId = "";
        org.springframework.security.core.Authentication auth
                = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();

            if (principal instanceof User) {
                User user = (User) principal;
                employeeId = user.getId();
            }
        }

        User employee = this.userService.getUserById(employeeId);
        ticketRequest.setEmpId(employeeId);
        Trip trip = this.tripService.getTripById(ticketRequest.getTripId());
        IncreasedPrice increasedPrice = this.increasedPriceService.getIncreasedPriceById(Integer.valueOf(ticketRequest.getIncreasePriceId().toString()));
        Double price = trip.getPrice() + (trip.getPrice() * (ticketRequest.getIncreasedPercentage() / 100.0));
        ticketRequest.setPrice(price.toString());
        Date now = new Date();

        List<Short> seats = this.ticketService.getAllSeatTicketByTripId(ticketRequest.getTripId());

        Short chooseSeat = Short.valueOf(ticketRequest.getSeat().toString());
        //check seat 
        for (Short seat : seats) {
            if (chooseSeat == seat) {
                model.addAttribute("seatError", "Seat is already existed");
                return "redirect:/admin/trip/addTicket/" + ticketRequest.getTripId();
            }
        }

        Short maxSeat = trip.getVehicleId().getSeatCapacity();
        if (chooseSeat > maxSeat || chooseSeat <= 0) {
            model.addAttribute("seatError", "That Seat doesn't exist");
            return "redirect:/admin/trip/addTicket/" + ticketRequest.getTripId();
        }

        long startTime = trip.getDepartureTime().getTime();
        long nowTime = now.getTime() + (2 * 3600 * 1000);
        if (startTime <= nowTime) {
            model.addAttribute("timeError", "Trip already to depart");
            return "redirect:/employee/trip/" + ticketRequest.getTripId();
        }

//        Ticket ticket = new Ticket();
//        ticket.setName(ticketRequest.getUserName());
//        ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString())); // 
//        ticket.setTripId(trip);
//        ticket.setIncreasedPriceId(increasedPrice);
//        ticket.setPrice(price);
//        ticket.setDate(now);
//        ticket.setEmployeeId(employee);
//        ticket.setIsGet(Short.valueOf("1"));
//        ticket.setType("off");
//        ticket.setIsActive(Short.valueOf("1"));
        Integer idTic = this.ticketService.addOffTicket2(ticketRequest);
        if (idTic != -1) {
//            return "redirect:/admin/trip/addTicket/exportPdf/" + ticketRequest.getTripId() + "/" + ticket.getId();
        }

        return "redirect:/admin/trip/addTicket/" + ticketRequest.getTripId();
    }

    @GetMapping("admin/trip/addTicket/exportPdf/{tripId}/{ticketId}")
    public String getExportPdf(@PathVariable("tripId") Integer tripId, @PathVariable("ticketId") Integer ticketId, Model model) {

//        Ticket ticket = this.ticketService.getTicketById(ticketId);
        OrderOnline ticket = this.ticketService.getOrderByTicket2Id(ticketId);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(ticket.getTicketId().getId());
        ticketRequest.setUserName(ticket.getTicketId().getCusName());
        ticketRequest.setSelectSeat(Integer.valueOf(ticket.getTicketId().getSeat()));
        ticketRequest.setSeat(Integer.valueOf(ticket.getTicketId().getSeat()));
        ticketRequest.setPrice(ticket.getTicketId().getPrice().toString());
        ticketRequest.setPayment(ticket.getPayment().toString());
        ticketRequest.setIncreasePrice(ticket.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(ticket.getTicketId().getTripId().getId());
        ticketRequest.setRoute(ticket.getTicketId().getTripId().getRouteId().getName());
        ticketRequest.setDate(ticket.getOrderDate().toString());
        ticketRequest.setEmployee(ticket.getEmpId().getName());

        model.addAttribute("ticket", ticketRequest);

        return "pdfAdmin";
    }

    @PostMapping("admin/trip/addTicket/exportPdf/{tripId}/{ticketId}")
    public String postExportPdf(HttpServletResponse response,
            @ModelAttribute(value = "ticket") TicketRequest ticketRequest) throws DocumentException, IOException {

//        Ticket ticket = this.ticketService.getTicketById(ticketRequest.getId());
        OrderOnline ticket = this.ticketService.getOrderByTicket2Id(ticketRequest.getId());

        // Create a new Document for the PDF
        Document document = new Document();

        // Set the response content type and headers for PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=TicketFor" + ticket.getTicketId().getCusName()+ ".pdf");

        // Get the OutputStream to write the PDF content to the response
        OutputStream out = response.getOutputStream();
        PdfWriter.getInstance(document, out);

        // Open the document for writing
        document.open();

        // Add data to the PDF document
        document.add(new Paragraph("Name: " + ticket.getTicketId().getCusName()));
        document.add(new Paragraph("License Plate: " + ticket.getTicketId().getTripId().getVehicleId().getLicensePlate()));
        document.add(new Paragraph("Seat: " + ticket.getTicketId().getSeat()));
        document.add(new Paragraph("Event: " + ticket.getIncreasedPriceId().getEventName()));
        document.add(new Paragraph("Route: " + ticket.getTicketId().getTripId().getRouteId().getName()));
        document.add(new Paragraph("Departure time: " + ticket.getTicketId().getTripId().getDepartureTime()));
        document.add(new Paragraph("Arrival time: " + ticket.getTicketId().getTripId().getArrivalTime()));
        document.add(new Paragraph("Date: " + ticket.getOrderDate()));
        document.add(new Paragraph("Employee Email: " + ticket.getEmpId().getEmail()));
        document.add(new Paragraph("Employee Name: " + ticket.getEmpId().getName()));

        // Close the document
        document.close();

        // Flush and close the OutputStream
        out.flush();
        out.close();

        return "trip";
    }

    @ModelAttribute
    public void getTripInfo(Model model) {
        model.addAttribute("tripInfo", this.tripService.getTripInfo());
    }

    @GetMapping("employee/trip/{id}")
    public String newTicketInTripForEmployee(Model model, @PathVariable(value = "id") Integer id) {

        TicketRequest ticketRequest = new TicketRequest();
        Trip trip = this.tripService.getTripById(id);
        ticketRequest.setTripId(id);
        long longStartDate = trip.getDepartureTime().getTime();
        IncreasedPrice increase = this.increasedPriceService.checkIncreasePrice(longStartDate);
        ticketRequest.setEventName(increase.getEventName());
        ticketRequest.setIncreasedPercentage(increase.getIncreasedPercentage());
        ticketRequest.setIncreasePriceId(increase.getId());

        model.addAttribute("addTicketInTrip", ticketRequest);

        return "addTicketInTripEmployeeView";
    }

    @PostMapping("employee/trip/{id}")
    public String addTicketInTripForEmployee(HttpServletResponse response,
            Model model, @ModelAttribute(value = "addTicketInTrip") TicketRequest ticketRequest)
            throws IOException, DocumentException {

//        System.out.println("----------------------------------------------------check ne");
//        System.out.println(ticketRequest.getEventName());
//                System.out.println(ticketRequest.getIncreasedPercentage());
//
//        System.out.println(ticketRequest.getSeat());
//        System.out.println(ticketRequest.getTicType());
//
//
//        return "redirect:/employee/trip/" + ticketRequest.getTripId();
        String employeeId = "";
        org.springframework.security.core.Authentication auth
                = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();

            if (principal instanceof User) {
                User user = (User) principal;
                employeeId = user.getId();
            }
        }

        User employee = this.userService.getUserById(employeeId);
        ticketRequest.setEmpId(employeeId);
        Trip trip = this.tripService.getTripById(ticketRequest.getTripId());
        IncreasedPrice increasedPrice = this.increasedPriceService.getIncreasedPriceById(Integer.valueOf(ticketRequest.getIncreasePriceId().toString()));
        Double price = trip.getPrice() + (trip.getPrice() * (ticketRequest.getIncreasedPercentage() / 100.0));
        ticketRequest.setPrice(price.toString());
        Date now = new Date();

        List<Short> seats = this.ticketService.getAllSeatTicketByTripId(ticketRequest.getTripId());

        long startTime = trip.getDepartureTime().getTime();
        long nowTime = now.getTime() + (2 * 3600 * 1000);
        if (startTime <= nowTime) {
            model.addAttribute("timeError", "Trip already to depart");
            return "redirect:/employee/trip/" + ticketRequest.getTripId();
        }

        Short chooseSeat = Short.valueOf(ticketRequest.getSeat().toString());

        //check seat 
        for (Short seat : seats) {
            if (chooseSeat == seat) {
                model.addAttribute("seatError", "Seat is already existed");
                return "redirect:/employee/trip/" + ticketRequest.getTripId();
            }
        }

        Short maxSeat = trip.getVehicleId().getSeatCapacity();
        if (chooseSeat > maxSeat || chooseSeat <= 0) {
            model.addAttribute("seatError", "That Seat doesn't exist");
            return "redirect:/employee/trip/" + ticketRequest.getTripId();
        }

//        Ticket ticket = new Ticket();
//        ticket.setName(ticketRequest.getUserName());
//        ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString())); // 
//        ticket.setTripId(trip);
//        ticket.setIncreasedPriceId(increasedPrice);
//        ticket.setPrice(price);
//        ticket.setDate(now);
//        ticket.setEmployeeId(employee);
//        ticket.setIsGet(Short.valueOf("1"));
//        ticket.setType("off");
//        ticket.setIsActive(Short.valueOf("1"));
        Integer idTic = this.ticketService.addOffTicket2(ticketRequest);
        if (idTic != -1) {

            Integer ticketId = idTic;
//            return "redirect:/employee/trip/exportPdf/" + ticketRequest.getTripId() + "/" + ticketId;
            return "redirect:/employee/trip/" + ticketRequest.getTripId();

        }

        return "redirect:/employee/trip/" + ticketRequest.getTripId();
    }

    @GetMapping("employee/trip/exportPdf/{tripId}/{ticketId}")
    public String getExportPdfForEmployee(@PathVariable("tripId") Integer tripId, @PathVariable("ticketId") Integer ticketId, Model model) {

//        Ticket ticket = this.ticketService.getTicketById(ticketId);
        OrderOnline ticket = this.ticketService.getOrderByTicket2Id(ticketId);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(ticket.getId());
        ticketRequest.setUserName(ticket.getTicketId().getCusName());
        ticketRequest.setSelectSeat(Integer.valueOf(ticket.getTicketId().getSeat()));
        ticketRequest.setSeat(Integer.valueOf(ticket.getTicketId().getSeat()));
        ticketRequest.setPrice(ticket.getPrice().toString());
        ticketRequest.setPayment(ticket.getPayment().toString());
        ticketRequest.setIncreasePrice(ticket.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(ticket.getTicketId().getTripId().getId());
        ticketRequest.setRoute(ticket.getTicketId().getTripId().getRouteId().getName());
        ticketRequest.setDate(ticket.getOrderDate().toString());
        ticketRequest.setEmployee(ticket.getEmpId().getName());

        model.addAttribute("ticket", ticketRequest);

        return "pdfEmployee";
    }

    @PostMapping("employee/trip/exportPdf/{tripId}/{ticketId}")
    public String postExportPdfForEmployee(HttpServletResponse response,
            @ModelAttribute(value = "ticket") TicketRequest ticketRequest) throws DocumentException, IOException {

//        Ticket ticket = this.ticketService.getTicketById(ticketRequest.getId());
                OrderOnline ticket = this.ticketService.getOrderByTicket2Id(ticketRequest.getId());

        // Create a new Document for the PDF
        Document document = new Document();

        // Set the response content type and headers for PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=TicketFor" + ticket.getTicketId().getCusName()+ ".pdf");

        // Get the OutputStream to write the PDF content to the response
        OutputStream out = response.getOutputStream();
        PdfWriter.getInstance(document, out);

        // Open the document for writing
        document.open();

        // Add data to the PDF document
        document.add(new Paragraph("Name: " + ticket.getTicketId().getCusName()));
        document.add(new Paragraph("License Plate: " + ticket.getTicketId().getTripId().getVehicleId().getLicensePlate()));
        document.add(new Paragraph("Seat: " + ticket.getTicketId().getSeat()));
        document.add(new Paragraph("Event: " + ticket.getIncreasedPriceId().getEventName()));
        document.add(new Paragraph("Route: " + ticket.getTicketId().getTripId().getRouteId().getName()));
        document.add(new Paragraph("Departure time: " + ticket.getTicketId().getTripId().getDepartureTime()));
        document.add(new Paragraph("Arrival time: " + ticket.getTicketId().getTripId().getArrivalTime()));
        document.add(new Paragraph("Date: " + ticket.getOrderDate()));
        document.add(new Paragraph("Employee Email: " + ticket.getEmpId().getEmail()));
        document.add(new Paragraph("Employee Name: " + ticket.getEmpId().getName()));

        // Close the document
        document.close();

        // Flush and close the OutputStream
        out.flush();
        out.close();

        return "trip";
    }

}
