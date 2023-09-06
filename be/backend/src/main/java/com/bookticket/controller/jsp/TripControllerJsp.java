package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Request.TripRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Ticket;
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
        if (trips.size() != 0) {
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
        if (trips.size() != 0) {
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
        if (trips.size() != 0) {
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

        if (formatDepartureTime.compareTo(formatArrivalTime ) >= 0) {
            rs.rejectValue("departureTime", "departureTime.lessThanArrivalTime", 
                    "Departure time must be less than Arrival Time");
            return "addTrip";
        }
        Date now = new Date();  
        if(formatDepartureTime.compareTo(now) <= 0){
            rs.rejectValue("departureTime", "departureTime.greatterThanNow", 
                    "Departure time must be greater than Now Time");
            return "addTrip";
        }
        
        
        // Formating price
        String price = p.getPrice();
        
        if(!price.matches("\\d+")){
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

        if (formatDepartureTime.compareTo(formatArrivalTime ) >= 0) {
            rs.rejectValue("departureTime", "departureTime.lessThanArrivalTime", 
                    "Departure time must be less than Arrival Time");
            model.addAttribute("departureError", "Departure time must be less than Arrival Time");
            return "redirect:/admin/trip/" + p.getId();
        }
        
        Date now = new Date();  
        if(formatDepartureTime.compareTo(now) <= 0){
            rs.rejectValue("departureTime", "departureTime.greatterThanNow", 
                    "Departure time must be less than Arrival Time");
            model.addAttribute("departureError", "Departure time must be less than Arrival Time");
            return "redirect:/admin/trip/" + p.getId();
        }
        
        
        // Formating price
        String price = p.getPrice();
        
        if(!price.matches("\\d+")){
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
    public void deleteTrip(@PathVariable(value = "id") Integer id){
        Trip t = this.tripService.getTripById(id);
        
        t.setIsActive(Short.valueOf("0"));
        
        this.tripService.deleteTrip(t);
    }
    
    @GetMapping("admin/trip/addTicket/{id}")
    public String newTicketInTrip(Model model, @PathVariable(value = "id") Integer id){
        
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTripId(id);
        
        model.addAttribute("addTicketInTrip", ticketRequest);
        
        return "addTicketInTrip";
    }
    @PostMapping("admin/trip/addTicket/{id}")
    public String addTicketInTrip(Model model, @ModelAttribute(value = "addTicketInTrip") TicketRequest ticketRequest){
        
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
         Trip trip = this.tripService.getTripById(ticketRequest.getTripId());
         IncreasedPrice increasedPrice = this.increasedPriceService.getIncreasedPriceById(Integer.valueOf(ticketRequest.getIncreasePrice()));
         Double price = trip.getPrice() + (trip.getPrice() * (increasedPrice.getIncreasedPercentage()/100.0));
         Date now = new Date();
         
         List<Short> seats = this.ticketService.getAllSeatTicketByTripId(3);
         
         Short chooseSeat = Short.valueOf(ticketRequest.getSeat().toString());
         //check seat 
         for(Short seat : seats){
            if(chooseSeat == seat){
                model.addAttribute("seatError", "Seat is already existed");
                return "redirect:/admin/trip/addTicket/" + ticketRequest.getTripId();
            }
         }
         
         Short maxSeat = trip.getVehicleId().getSeatCapacity();
         if(chooseSeat > maxSeat || chooseSeat <= 0){
            model.addAttribute("seatError", "That Seat doesn't exist");
            return "redirect:/admin/trip/addTicket/" + ticketRequest.getTripId();
         }
         
         long startTime = trip.getDepartureTime().getTime();
         long nowTime = now.getTime()+ (2 * 3600 * 1000);
           if(startTime <= nowTime)
           {
                model.addAttribute("timeError", "Trip already to depart");
                return "redirect:/employee/trip/" + ticketRequest.getTripId();
           }
           
         Ticket ticket = new Ticket();
         ticket.setName(ticketRequest.getUserName());
         ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString())); // 
         ticket.setTripId(trip);
         ticket.setIncreasedPriceId(increasedPrice);
         ticket.setPrice(price);
         ticket.setDate(now);
         ticket.setEmployeeId(employee);
         ticket.setType("off");
         ticket.setIsActive(Short.valueOf("1"));
         
         if(this.ticketService.addOffTicket(ticket)){
            return "redirect:/admin/trip/" + ticketRequest.getTripId();
         }
         
        return "redirect:/admin/trip/addTicket/" + ticketRequest.getTripId();
    }
    
    @ModelAttribute
    public void getTripInfo(Model model){
        model.addAttribute("tripInfo", this.tripService.getTripInfo());
    }
    
    @GetMapping("employee/trip/{id}")
    public String newTicketInTripForEmployee(Model model, @PathVariable(value = "id") Integer id){
        
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTripId(id);
        
        model.addAttribute("addTicketInTrip", ticketRequest);
        
        return "addTicketInTripEmployeeView";
    }
    @PostMapping("employee/trip/{id}")
    public String addTicketInTripForEmployee(Model model, @ModelAttribute(value = "addTicketInTrip") TicketRequest ticketRequest){
        
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
         Trip trip = this.tripService.getTripById(ticketRequest.getTripId());
         IncreasedPrice increasedPrice = this.increasedPriceService.getIncreasedPriceById(Integer.valueOf(ticketRequest.getIncreasePrice()));
         Double price = trip.getPrice() + (trip.getPrice() * (increasedPrice.getIncreasedPercentage()/100.0));
         Date now = new Date();
         
         List<Short> seats = this.ticketService.getAllSeatTicketByTripId(3);
         
    
         long startTime = trip.getDepartureTime().getTime();
         long nowTime = now.getTime()+ (2 * 3600 * 1000);
           if(startTime <= nowTime)
           {
                model.addAttribute("timeError", "Trip already to depart");
                return "redirect:/employee/trip/" + ticketRequest.getTripId();
           }
           
         Short chooseSeat = Short.valueOf(ticketRequest.getSeat().toString());
         
         //check seat 
         for(Short seat : seats){
            if(chooseSeat == seat){
                model.addAttribute("seatError", "Seat is already existed");
                return "redirect:/employee/trip/" + ticketRequest.getTripId();
            }
         }
         
         Short maxSeat = trip.getVehicleId().getSeatCapacity();
         if(chooseSeat > maxSeat || chooseSeat <= 0){
            model.addAttribute("seatError", "That Seat doesn't exist");
            return "redirect:/employee/trip/" + ticketRequest.getTripId();
         }
         
         Ticket ticket = new Ticket();
         ticket.setName(ticketRequest.getUserName());
         ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString())); // 
         ticket.setTripId(trip);
         ticket.setIncreasedPriceId(increasedPrice);
         ticket.setPrice(price);
         ticket.setDate(now);
         ticket.setEmployeeId(employee);
         ticket.setType("off");
         ticket.setIsActive(Short.valueOf("1"));
         
         if(this.ticketService.addOffTicket(ticket)){
            return "redirect:/employee/trip";
         }
         
        return "redirect:/employee/trip/" + ticketRequest.getTripId();
    }
}
