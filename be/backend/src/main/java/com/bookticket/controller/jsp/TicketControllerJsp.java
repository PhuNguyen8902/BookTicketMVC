package com.bookticket.controller.jsp;

import com.bookticket.dto.Api.ApiChangeTicket;
import com.bookticket.dto.Api.ApiTrip;
import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.OrderOnline;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (!tickets.isEmpty()) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "onlTicketEmployeeView";
    }

    @GetMapping("/admin/onlTicket/{id}")
    public String onlTicketDetail(Model model, @PathVariable(value = "id") Integer id) {
        OrderOnline order = this.ticketService.getOrderByTicket2Id(id);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setUserName(order.getUserId().getEmail());
        ticketRequest.setSelectSeat(Integer.valueOf(order.getTicketId().getSeat().toString()));
        ticketRequest.setSeat(Integer.valueOf(order.getTicketId().getSeat().toString()));
        ticketRequest.setPayment(order.getPayment().toString());
        ticketRequest.setIncreasePrice(order.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(order.getTicketId().getTripId().getId());
        ticketRequest.setRoute(order.getTicketId().getTripId().getRouteId().getName());
        ticketRequest.setDate(order.getOrderDate().toString());
        ticketRequest.setEmployee(order.getEmpId().getName());

        model.addAttribute("ticket", ticketRequest);

        return "editOnlTicket";
    }

    @PostMapping("/admin/onlTicket")
    public String editOnlTicket(Model model,
            @ModelAttribute(value = "ticket") @Valid TicketRequest ticketRequest, BindingResult rs) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        String date = ticketRequest.getDate();
        date = date.replace("T", " ");
        date = date.concat(":00");
        Date formatDate = dateFormat.parse(date);

        User customer = this.userService.getUserById(ticketRequest.getUserName());
        IncreasedPrice increasePrice = this.increasedPriceService.getIncreasedPriceById(Integer.valueOf(ticketRequest.getIncreasePrice()));
        Trip trip = this.tripService.getTripById(Integer.valueOf(ticketRequest.getRoute()));
        User employee = this.userService.getUserById(ticketRequest.getEmployee());
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

        ApiChangeTicket ticket = new ApiChangeTicket();
        ticket.setTicketId(ticketRequest.getId());
        ticket.setTripId(trip.getId());
        ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString()));
        Ticket2 t = this.ticketService.getTicket2ById(ticketRequest.getId());
        ticket.setPrice(t.getPrice());
        OrderOnline order = this.ticketService.getOrderByTicket2Id(ticketRequest.getId());
        ticket.setOrderId(order.getId());
        Double newPrice = price;
        if (t.getTicketType() == 0) {
            newPrice = newPrice / 2;
        }
        ticket.setNewPrice(newPrice);
        Date departTime = trip.getDepartureTime();
        ticket.setDepartureTime(departTime.getTime());
        ticket.setIncreaseId(increasePrice.getId());
        ticket.setBookDate(formatDate);
        ticket.setCusId(customer.getId());
        ticket.setEmpId(employee.getId());

        if (this.ticketService.editOnlTicket2(ticket)) {
            return "redirect:/admin/onlTicket";
        }

        return "redirect:/admin/onlTicket/" + ticketRequest.getId();
    }

    @GetMapping("/employee/onlTicket/{id}")
    public String onlTicketDetailEmp(Model model, @PathVariable(value = "id") Integer id) {
        OrderOnline order = this.ticketService.getOrderByTicket2Id(id);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setUserName(order.getUserId().getEmail());
        ticketRequest.setSelectSeat(Integer.valueOf(order.getTicketId().getSeat().toString()));
        ticketRequest.setSeat(Integer.valueOf(order.getTicketId().getSeat().toString()));
        ticketRequest.setPayment(order.getPayment().toString());
        ticketRequest.setIncreasePrice(order.getIncreasedPriceId().getEventName());
        ticketRequest.setIncreasePriceId(order.getIncreasedPriceId().getId());
        ticketRequest.setTripId(order.getTicketId().getTripId().getId());
        ticketRequest.setRoute(order.getTicketId().getTripId().getRouteId().getName());
        ticketRequest.setDate(order.getOrderDate().toString());
        ticketRequest.setEmployee(order.getEmpId().getEmail());
        Short type = order.getTicketId().getTicketType();
        if (type == 1) {
            ticketRequest.setTicTypeStr("Adult");
        } else {
            ticketRequest.setTicTypeStr("Children");

        }
        Integer routeId = order.getTicketId().getTripId().getRouteId().getId();
        List<ApiTrip> list = this.tripService.getListTripByRoute(routeId);
        ticketRequest.setListTrip(list);
        
        model.addAttribute("ticket", ticketRequest);

        return "editOnlTicketEmp";
    }

    @PostMapping("/employee/onlTicket")
    public String editOnlTicketEmp(Model model,
            @ModelAttribute(value = "ticket") @Valid TicketRequest ticketRequest, BindingResult rs) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        String date = ticketRequest.getDate();
        date = date.replace("T", " ");
        date = date.concat(":00");
        Date formatDate = dateFormat.parse(date);

        User customer = this.userService.getUsers(ticketRequest.getUserName()).get(0);

        IncreasedPrice increasePrice = this.increasedPriceService.getIncreasedPriceById(ticketRequest.getIncreasePriceId());
        Trip trip = this.tripService.getTripById(Integer.valueOf(ticketRequest.getRoute()));
        User employee = this.userService.getUsers(ticketRequest.getEmployee()).get(0);
        List<Short> existSeatList = this.ticketService.getAllSeatTicketByTripId(trip.getId());

        Short selectSeat = Short.valueOf(ticketRequest.getSelectSeat().toString());
        Short seat = Short.valueOf(ticketRequest.getSeat().toString());
        for (Short existSeat : existSeatList) {

            if (seat.equals(existSeat) && !seat.equals(selectSeat)) {
                rs.rejectValue("seat", "seat.noSeatEqual",
                        "Seat is already existed");
                model.addAttribute("seatError", "Seat is already existed");
                return "redirect:/employee/onlTicket/" + ticketRequest.getId();
            }
        }

        Short maxSeat = trip.getVehicleId().getSeatCapacity();
        if (seat > maxSeat || seat <= 0) {
            model.addAttribute("seatError", "That Seat doesn't exist");
            return "redirect:/employee/offTicket/" + ticketRequest.getId();
        }

        Double price = trip.getPrice() + (trip.getPrice() * (increasePrice.getIncreasedPercentage() / 100.0));

        ApiChangeTicket ticket = new ApiChangeTicket();
        ticket.setTicketId(ticketRequest.getId());
        ticket.setTripId(trip.getId());
        ticket.setSeat(Short.valueOf(ticketRequest.getSeat().toString()));
        Ticket2 t = this.ticketService.getTicket2ById(ticketRequest.getId());
        ticket.setPrice(t.getPrice());
        OrderOnline order = this.ticketService.getOrderByTicket2Id(ticketRequest.getId());
        ticket.setOrderId(order.getId());
        Double newPrice = price;
        if (t.getTicketType() == 0) {
            newPrice = newPrice / 2;
        }
        ticket.setNewPrice(newPrice);
        Date departTime = trip.getDepartureTime();
        ticket.setDepartureTime(departTime.getTime());
        ticket.setIncreaseId(increasePrice.getId());
        ticket.setBookDate(formatDate);
        ticket.setCusId(customer.getId());
        ticket.setEmpId(employee.getId());

        if (this.ticketService.editOnlTicket2(ticket)) {
            return "redirect:/employee/onlTicket";
        }

        return "redirect:/employee/onlTicket/" + ticketRequest.getId();
    }

    @GetMapping("admin/onlTicket/confirm/{id}")
    public String goToConfirmTicket(@PathVariable(value = "id") Integer id, Model model) {
        OrderOnline order = this.ticketService.getOrderByTicket2Id(id);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setUserName(order.getTicketId().getCusName());
        ticketRequest.setSelectSeat(Integer.valueOf(order.getTicketId().getSeat()));
        ticketRequest.setSeat(Integer.valueOf(order.getTicketId().getSeat()));
        ticketRequest.setPrice(order.getTicketId().getPrice().toString());
        ticketRequest.setPayment(order.getPayment().toString());
        ticketRequest.setIncreasePrice(order.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(order.getTicketId().getTripId().getId());
        ticketRequest.setRoute(order.getTicketId().getTripId().getRouteId().getName());
        ticketRequest.setDate(order.getOrderDate().toString());
        ticketRequest.setEmployee(order.getEmpId().getName());
        ticketRequest.setIsGet(order.getTicketId().getIsGet());

        model.addAttribute("ticket", ticketRequest);
        return "confirmTicket";
    }

    @PostMapping("admin/onlTicket/confirm/{id}")
    public String confirmTicket(HttpServletResponse response,
            @ModelAttribute(value = "ticket") TicketRequest ticketRequest) throws DocumentException, IOException {

        Ticket2 ticket = this.ticketService.getTicket2ById(ticketRequest.getId());
        OrderOnline order = this.ticketService.getOrderByTicket2Id(ticket.getId());

        ticket.setIsGet(Short.valueOf("1"));

        if (this.ticketService.updateTicket(ticket)) {
            Document document = new Document();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=TicketFor" + ticket.getCusName()+ ".pdf");

            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("Name: " + ticket.getCusName()));
            document.add(new Paragraph("License Plate: " + ticket.getTripId().getVehicleId().getLicensePlate()));
            document.add(new Paragraph("Seat: " + ticket.getSeat()));
            document.add(new Paragraph("Event: " + order.getIncreasedPriceId().getEventName()));
            document.add(new Paragraph("Route: " + ticket.getTripId().getRouteId().getName()));
            document.add(new Paragraph("Departure time: " + ticket.getTripId().getDepartureTime()));
            document.add(new Paragraph("Arrival time: " + ticket.getTripId().getArrivalTime()));
            document.add(new Paragraph("Date: " + order.getOrderDate()));
            document.add(new Paragraph("Employee Email: " + order.getEmpId().getEmail()));
            document.add(new Paragraph("Employee Name: " + order.getEmpId().getName()));

            document.close();

            out.flush();
            out.close();
        }
        return "onlTicket";
    }

    @GetMapping("employee/onlTicket/confirm/{id}")
    public String goToConfirmTicketForEmployee(@PathVariable(value = "id") Integer id, Model model) {
        OrderOnline order = this.ticketService.getOrderByTicket2Id(id);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setUserName(order.getTicketId().getCusName());
        ticketRequest.setSelectSeat(Integer.valueOf(order.getTicketId().getSeat()));
        ticketRequest.setSeat(Integer.valueOf(order.getTicketId().getSeat()));
        ticketRequest.setPrice(order.getTicketId().getPrice().toString());
        ticketRequest.setPayment(order.getPayment().toString());
        ticketRequest.setIncreasePrice(order.getIncreasedPriceId().getEventName());
        ticketRequest.setTripId(order.getTicketId().getTripId().getId());
        ticketRequest.setRoute(order.getTicketId().getTripId().getRouteId().getName());
        ticketRequest.setDate(order.getOrderDate().toString());
        ticketRequest.setEmployee(order.getEmpId().getName());
        ticketRequest.setIsGet(order.getTicketId().getIsGet());

        model.addAttribute("ticket", ticketRequest);
        return "confirmTicketEmployeeView";
    }

    @PostMapping("employee/onlTicket/confirm/{id}")
    public String confirmTicketForEmployee(HttpServletResponse response,
            @ModelAttribute(value = "ticket") TicketRequest ticketRequest) throws DocumentException, IOException {

        Ticket2 ticket = this.ticketService.getTicket2ById(ticketRequest.getId());
        OrderOnline order = this.ticketService.getOrderByTicket2Id(ticket.getId());

        ticket.setIsGet(Short.valueOf("1"));

        if (this.ticketService.updateTicket(ticket)) {
            Document document = new Document();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=TicketFor" + ticket.getCusName()+ ".pdf");

            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("Name: " + ticket.getCusName()));
            document.add(new Paragraph("License Plate: " + ticket.getTripId().getVehicleId().getLicensePlate()));
            document.add(new Paragraph("Seat: " + ticket.getSeat()));
            document.add(new Paragraph("Event: " + order.getIncreasedPriceId().getEventName()));
            document.add(new Paragraph("Route: " + ticket.getTripId().getRouteId().getName()));
            document.add(new Paragraph("Departure time: " + ticket.getTripId().getDepartureTime()));
            document.add(new Paragraph("Arrival time: " + ticket.getTripId().getArrivalTime()));
            document.add(new Paragraph("Date: " + order.getOrderDate()));
            document.add(new Paragraph("Employee Email: " + order.getEmpId().getEmail()));
            document.add(new Paragraph("Employee Name: " + order.getEmpId().getName()));

            document.close();

            out.flush();
            out.close();
        }
        return "onlTicket";
    }

}
