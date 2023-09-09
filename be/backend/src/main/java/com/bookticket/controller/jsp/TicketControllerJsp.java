package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.OrderOnline;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Ticket2;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.service.IncreasedPriceService;
import com.bookticket.service.TicketService;
import com.bookticket.service.TripService;
import com.bookticket.service.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author vegar
 */
@Controller
@ControllerAdvice
public class TicketControllerJsp {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private IncreasedPriceService increasedPriceService;
    @Autowired
    private TripService tripService;

    @GetMapping("/admin/onlTicket")
    public String getOnlTickets(@RequestParam Map<String, String> params, Model model) {
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
        
        List<TicketRequest> tickets = ticketService.getOnlTickets(params);

        model.addAttribute("tickets", tickets);
        if (!tickets.isEmpty()) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "onlTicket";
    }

    @GetMapping("/employee/onlTicket")
    public String getOnlTicketsForEmployee(@RequestParam Map<String, String> params, Model model) {
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TicketRequest> tickets = ticketService.getOnlTickets(params);

        model.addAttribute("tickets", tickets);
        if (tickets.size() != 0) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "onlTicketEmployeeView";
    }

    @GetMapping("/admin/onlTicket/{id}")
    public String onlTicketDetail(Model model, @PathVariable(value = "id") Integer id) {
        Ticket ticket = this.ticketService.getTicketById(id);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setUserName(ticket.getUserId().getEmail());
        ticketRequest.setSelectSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setPayment(ticket.getPayment());
        ticketRequest.setIncreasePrice(ticket.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(ticket.getTripId().getId());
        ticketRequest.setRoute(ticket.getTripId().getRouteId().getName());
        ticketRequest.setDate(ticket.getDate().toString());
        ticketRequest.setEmployee(ticket.getEmployeeId().getName());

        model.addAttribute("ticket", ticketRequest);

        return "editOnlTicket";
    }

    @PostMapping("/admin/onlTicket")
    public String editOnlTicket(Model model,
            @ModelAttribute(value = "ticket") @Valid TicketRequest ticketRequest, BindingResult rs) throws ParseException {

        // formating date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        String date = ticketRequest.getDate();
        date = date.replace("T", " ");
        date = date.concat(":00");
        Date formatDate = dateFormat.parse(date);

        User customer = this.userService.getUserById(ticketRequest.getUserName());
        IncreasedPrice increasePrice = this.increasedPriceService.getIncreasedPriceById(Integer.valueOf(ticketRequest.getIncreasePrice()));
        Trip trip = this.tripService.getTripById(Integer.valueOf(ticketRequest.getRoute().toString()));
        User employee = this.userService.getUserById(ticketRequest.getEmployee());
        //get all exist Seat in trip
        List<Short> existSeatList = this.ticketService.getAllSeatTicketByTripId(Integer.valueOf(ticketRequest.getRoute()));

        Short selectSeat = Short.valueOf(ticketRequest.getSelectSeat().toString());
        Short seat = Short.valueOf(ticketRequest.getSeat().toString());
        for (Short existSeat : existSeatList) {

            if (seat.equals(existSeat) && !seat.equals(selectSeat)) {
                rs.rejectValue("seat", "seat.noSeatEqual",
                        "Seat is already existed");
                model.addAttribute("seatError", "Seat is already existed");
                return "redirect:/admin/onlTicket/" + ticketRequest.getId();
            }
        }

        Short maxSeat = trip.getVehicleId().getSeatCapacity();
        if (seat > maxSeat || seat <= 0) {
            model.addAttribute("seatError", "That Seat doesn't exist");
            return "redirect:/admin/offTicket/" + ticketRequest.getId();
        }

        Double price = trip.getPrice() + (trip.getPrice() * (increasePrice.getIncreasedPercentage() / 100.0));

        Ticket ticket = new Ticket();
        ticket.setId(ticketRequest.getId());
        ticket.setUserId(customer);
        ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString()));
        ticket.setDate(formatDate);
        ticket.setPrice(price);
        ticket.setPayment(ticketRequest.getPayment());
        ticket.setIncreasedPriceId(increasePrice);
        ticket.setEmployeeId(employee);
        ticket.setTripId(trip);
        ticket.setType("onl");
        ticket.setIsActive(Short.valueOf("1"));

        if (this.ticketService.editOnlTicket(ticket)) {
            return "redirect:/admin/onlTicket";
        }

        return "redirect:/admin/onlTicket/" + ticketRequest.getId();
    }

    @GetMapping("/admin/offTicket")
    public String getOffTickets(@RequestParam Map<String, String> params, Model model) {
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TicketRequest> tickets = ticketService.getOffTickets(params);

        model.addAttribute("tickets", tickets);
        if (tickets.size() != 0) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "offTicket";
    }

    @GetMapping("/employee/offTicket")
    public String getOffTicketsForEmployee(@RequestParam Map<String, String> params, Model model) {
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TicketRequest> tickets = ticketService.getOffTickets(params);

        model.addAttribute("tickets", tickets);
        if (tickets.size() != 0) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "offTicketEmployeeView";
    }

    @GetMapping("/admin/offTicket/{id}")
    public String offTicketDetail(Model model, @PathVariable(value = "id") Integer id) {

        Ticket ticket = this.ticketService.getTicketById(id);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setUserName(ticket.getName());
        ticketRequest.setSelectSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setPayment(ticket.getPayment());
        ticketRequest.setIncreasePrice(ticket.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(ticket.getTripId().getId());
        ticketRequest.setRoute(ticket.getTripId().getRouteId().getName());
        ticketRequest.setDate(ticket.getDate().toString());
        ticketRequest.setEmployee(ticket.getEmployeeId().getName());

        model.addAttribute("ticket", ticketRequest);
        return "editOffTicket";
    }

    @PostMapping("/admin/offTicket")
    public String editOffTicket(Model model,
            @ModelAttribute(value = "ticket") @Valid TicketRequest ticketRequest, BindingResult rs) throws ParseException {

        // formating date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        String date = ticketRequest.getDate();
        date = date.replace("T", " ");
        date = date.concat(":00");
        Date formatDate = dateFormat.parse(date);

        IncreasedPrice increasePrice = this.increasedPriceService.getIncreasedPriceById(Integer.valueOf(ticketRequest.getIncreasePrice()));
        Trip trip = this.tripService.getTripById(Integer.valueOf(ticketRequest.getRoute().toString()));
        User employee = this.userService.getUserById(ticketRequest.getEmployee());
        //get all exist Seat in trip
        List<Short> existSeatList = this.ticketService.getAllSeatTicketByTripId(Integer.valueOf(ticketRequest.getRoute()));

        Short selectSeat = Short.valueOf(ticketRequest.getSelectSeat().toString());
        Short seat = Short.valueOf(ticketRequest.getSeat().toString());
        for (Short existSeat : existSeatList) {
            if (seat.equals(existSeat) && !seat.equals(selectSeat)) {
                rs.rejectValue("seat", "seat.noSeatEqual",
                        "Seat is already existed");
                model.addAttribute("seatError", "Seat is already existed");
                return "redirect:/admin/offTicket/" + ticketRequest.getId();
            }
        }

        Short maxSeat = trip.getVehicleId().getSeatCapacity();
        if (seat >= maxSeat || seat <= 0) {
            model.addAttribute("seatError", "That Seat doesn't exist");
            return "redirect:/admin/offTicket/" + ticketRequest.getId();
        }

        Double price = trip.getPrice() + (trip.getPrice() * (increasePrice.getIncreasedPercentage() / 100.0));

        Ticket ticket = new Ticket();
        ticket.setId(ticketRequest.getId());
        ticket.setName(ticketRequest.getUserName());
        ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString()));
        ticket.setDate(formatDate);
        ticket.setPrice(price);
        ticket.setPayment(ticketRequest.getPayment());
        ticket.setIncreasedPriceId(increasePrice);
        ticket.setEmployeeId(employee);
        ticket.setTripId(trip);
        ticket.setIsGet(Short.valueOf("1"));
        ticket.setType("off");
        ticket.setIsActive(Short.valueOf("1"));

        if (this.ticketService.editOffTicket(ticket)) {
            return "redirect:/admin/offTicket";
        }

        return "redirect:/admin/offTicket/" + ticketRequest.getId();
    }

    @PutMapping("admin/offTicket/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffTicket(@PathVariable(value = "id") Integer id) {
        Ticket t = this.ticketService.getTicketById(id);

        t.setIsActive(Short.valueOf("0"));

        this.ticketService.deleteTicket(t);
    }

    @PutMapping("admin/onlTicket/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOnlTicket(@PathVariable(value = "id") Integer id) {
        Ticket t = this.ticketService.getTicketById(id);

        t.setIsActive(Short.valueOf("0"));

        this.ticketService.deleteTicket(t);
    }
    
    @GetMapping("admin/onlTicket/confirm/{id}")
    public String goToConfirmTicket(@PathVariable(value = "id") Integer id, Model model){
        Ticket2 ticket = this.ticketService.getTicket2ById(id);
        OrderOnline order = this.ticketService.getOrderByTicket2Id(id);

        
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(ticket.getId());
        ticketRequest.setUserName(ticket.getCusName());
        ticketRequest.setSelectSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setPrice(ticket.getPrice().toString());
        ticketRequest.setPayment(order.getPayment().toString());
        ticketRequest.setIncreasePrice(order.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(ticket.getTripId().getId());
        ticketRequest.setRoute(ticket.getTripId().getRouteId().getName());
        ticketRequest.setDate(order.getOrderDate().toString());
        ticketRequest.setEmployee(order.getEmpId().getName());
        ticketRequest.setIsGet(ticket.getIsGet());
       
        
        model.addAttribute("ticket", ticketRequest);
        return "confirmTicket";
    }
    @PostMapping("admin/onlTicket/confirm/{id}")
    public String confirmTicket(HttpServletResponse response,
            @ModelAttribute(value = "ticket") TicketRequest ticketRequest) throws DocumentException, IOException{
        
        Ticket ticket = this.ticketService.getTicketById(ticketRequest.getId());

        ticket.setIsGet(Short.valueOf("1"));
        
        if(this.ticketService.editOnlTicket(ticket)){
        // Create a new Document for the PDF
        Document document = new Document();

        // Set the response content type and headers for PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=TicketFor" + ticket.getName() + ".pdf");

        // Get the OutputStream to write the PDF content to the response
        OutputStream out = response.getOutputStream();
        PdfWriter.getInstance(document, out);

        
        // Open the document for writing
        document.open();

        // Add data to the PDF document
        document.add(new Paragraph("Name: " + ticket.getUserId().getName()));
        document.add(new Paragraph("License Plate: " + ticket.getTripId().getVehicleId().getLicensePlate()));
        document.add(new Paragraph("Seat: " + ticket.getSeat()));
        document.add(new Paragraph("Event: " + ticket.getIncreasedPriceId().getEventName()));
        document.add(new Paragraph("Route: " + ticket.getTripId().getRouteId().getName()));
        document.add(new Paragraph("Departure time: " + ticket.getTripId().getDepartureTime()));
        document.add(new Paragraph("Arrival time: " + ticket.getTripId().getArrivalTime()));
        document.add(new Paragraph("Date: " + ticket.getDate()));
        document.add(new Paragraph("Employee Email: " + ticket.getEmployeeId().getEmail()));
        document.add(new Paragraph("Employee Name: " + ticket.getEmployeeId().getName()));

        // Close the document
        document.close();

        // Flush and close the OutputStream
        out.flush();
        out.close();
        }
        return "onlTicket";
    }
    
    @GetMapping("employee/onlTicket/confirm/{id}")
    public String goToConfirmTicketForEmployee(@PathVariable(value = "id") Integer id, Model model){
        Ticket ticket = this.ticketService.getTicketById(id);
        
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(ticket.getId());
        ticketRequest.setUserName(ticket.getUserId().getName());
        ticketRequest.setSelectSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setSeat(Integer.valueOf(ticket.getSeat()));
        ticketRequest.setPrice(ticket.getPrice().toString());
        ticketRequest.setPayment(ticket.getPayment());
        ticketRequest.setIncreasePrice(ticket.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(ticket.getTripId().getId());
        ticketRequest.setRoute(ticket.getTripId().getRouteId().getName());
        ticketRequest.setDate(ticket.getDate().toString());
        ticketRequest.setEmployee(ticket.getEmployeeId().getName());
        ticketRequest.setIsGet(ticket.getIsGet());
       
        
        model.addAttribute("ticket", ticketRequest);
        return "confirmTicketEmployeeView";
    }
    @PostMapping("employee/onlTicket/confirm/{id}")
    public String confirmTicketForEmployee(HttpServletResponse response,
            @ModelAttribute(value = "ticket") TicketRequest ticketRequest) throws DocumentException, IOException{
        
        Ticket ticket = this.ticketService.getTicketById(ticketRequest.getId());

        ticket.setIsGet(Short.valueOf("1"));
        
        if(this.ticketService.editOnlTicket(ticket)){
        // Create a new Document for the PDF
        Document document = new Document();

        // Set the response content type and headers for PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=TicketFor" + ticket.getName() + ".pdf");

        // Get the OutputStream to write the PDF content to the response
        OutputStream out = response.getOutputStream();
        PdfWriter.getInstance(document, out);

        
        // Open the document for writing
        document.open();

        // Add data to the PDF document
        document.add(new Paragraph("Name: " + ticket.getUserId().getName()));
        document.add(new Paragraph("License Plate: " + ticket.getTripId().getVehicleId().getLicensePlate()));
        document.add(new Paragraph("Seat: " + ticket.getSeat()));
        document.add(new Paragraph("Event: " + ticket.getIncreasedPriceId().getEventName()));
        document.add(new Paragraph("Route: " + ticket.getTripId().getRouteId().getName()));
        document.add(new Paragraph("Departure time: " + ticket.getTripId().getDepartureTime()));
        document.add(new Paragraph("Arrival time: " + ticket.getTripId().getArrivalTime()));
        document.add(new Paragraph("Date: " + ticket.getDate()));
        document.add(new Paragraph("Employee Email: " + ticket.getEmployeeId().getEmail()));
        document.add(new Paragraph("Employee Name: " + ticket.getEmployeeId().getName()));

        // Close the document
        document.close();

        // Flush and close the OutputStream
        out.flush();
        out.close();
        }
        return "onlTicket";
    }

}
