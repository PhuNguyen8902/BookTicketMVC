/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Api.ApiChangeTicket;
import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.Api.ApiTicketResponse;
import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.enums.Payment;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.OrderOnline;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Ticket2;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.repository.IncreasedPriceRepository;
import com.bookticket.repository.RouteRepository;
import com.bookticket.repository.TicketRepository;
import com.bookticket.repository.TripRepository;
import com.bookticket.repository.UserRepository;
import com.bookticket.service.TicketService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vegar
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TripRepository tripRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private IncreasedPriceRepository increaseRepo;

    @Autowired
    private RouteRepository routeRepo;

    @Override
    public List<TicketRequest> getOnlTickets(Map<String, String> params) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        List<ApiTicketResponse> list = this.ticketRepository.getListTickets(params);

        List<TicketRequest> tickets = new ArrayList<>();
        for (ApiTicketResponse ticket : list) {
            Route route = this.routeRepo.getRouteById(ticket.getRouteId());
            TicketRequest t = new TicketRequest();
            t.setId(ticket.getTicketId());
            t.setSeat(Integer.valueOf(ticket.getSeat().toString()));
            t.setRoute(route.getName());
            Date departTime = new Date(ticket.getDepartureTime());
            String formatDepartureTime = dateFormat.format(departTime);
            t.setDepartureTime(formatDepartureTime);
            Date arrivalTime = new Date(ticket.getArrivalTime());
            String formatArrivalTime = dateFormat.format(arrivalTime);
            t.setArrivalTime(formatArrivalTime);
            t.setPrice(ticket.getPrice().toString());
            t.setType(ticket.getType().toString());
            t.setPayment(ticket.getPayment());
            Date bookTime = new Date(ticket.getBookTime());
            String formatBookTime = dateFormat.format(bookTime);
            t.setDate(formatBookTime);
            t.setEmployee(ticket.getEmpName());
            t.setIncreasePrice(ticket.getEventName());
            t.setUserName(ticket.getUserName());
            t.setIsGet(ticket.getIsGet());
            t.setStartTime(ticket.getDepartureTime());
            t.setTotalPage(ticket.getTotalPage());

            tickets.add(t);
        }

        return tickets;
    }

    @Override
    public List<TicketRequest> getOffTickets(Map<String, String> params) {
        return this.ticketRepository.getOffTickets(params);
    }

    @Override
    public List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params) {
        return this.ticketRepository.getListRevenueInTicket(params);
    }

    @Override
    public boolean editOnlTicket(Ticket ticket) {
        return this.ticketRepository.editOnlTicket(ticket);
    }

    @Override
    public boolean editOffTicket(Ticket ticket) {
        return this.ticketRepository.editOffTicket(ticket);
    }

    @Override
    public boolean deleteTicket(Ticket ticket) {
        return this.ticketRepository.deleteTicket(ticket);
    }

    @Override
    public Ticket getTicketById(Integer id) {
        return this.ticketRepository.getTicketById(id);
    }

    @Override
    public List<Short> getAllSeatTicketByTripId(Integer id) {
        return this.ticketRepository.getAllSeatTicketByTripId(id);
    }

    @Override
    public List<Ticket> getTicketsByIncreasedPriceId(Integer intgr) {
        return this.ticketRepository.getTicketsByIncreasedPriceId(intgr);
    }

    @Override
    public boolean addOffTicket(Ticket ticket) {
        return this.ticketRepository.addOffTicket(ticket);
    }

    @Override
    public Integer addOffTicket2(TicketRequest ticket) {
        Ticket2 tic = new Ticket2();
        OrderOnline order = new OrderOnline();
        Trip trip = this.tripRepo.getTripById(ticket.getTripId());

        tic.setSeat(Short.valueOf(ticket.getSeat().toString()));
        tic.setCusName((String) ticket.getUserName());
        tic.setIsGet(Short.valueOf("1"));
        tic.setTicketType(Short.valueOf(ticket.getTicType().toString()));
        tic.setTripId(trip);

        Double price = Double.valueOf(ticket.getPrice());
        if (ticket.getTicType() == 0) {
            price = price / 2;
            tic.setPrice(price);

        } else {
            tic.setPrice(price);
        }

        int ticId = this.ticketRepository.addTicket(tic);

        if (ticId != -1) {
            Ticket2 t = this.ticketRepository.getTicket2ById(ticId);
            User u = this.userRepo.getUserById("None2");
            order.setUserId(u);
            Date date = new Date();
            order.setOrderDate(date);
            order.setTicketId(t);
            User emp = this.userRepo.getUserById(ticket.getEmpId());
            order.setEmpId(emp);
            IncreasedPrice increase = this.increaseRepo.getIncreasedPriceById(ticket.getIncreasePriceId());
            order.setIncreasedPriceId(increase);

            Payment pay = Payment.valueOf("COUNTER");
            order.setPayment(pay);
            order.setPrice(Double.valueOf(ticket.getPrice()));
            boolean rsOrder = this.ticketRepository.addOrder(order);
            return ticId;

        }
        return -1;
    }

    @Override
    public boolean addOnlTicket(ApiTicketRequest ticket) {
        Short active = 1;
        Short notGet = 0;

        Ticket tic = new Ticket();
        Trip trip = this.tripRepo.getTripById(ticket.getTripId());
        User u = this.userRepo.getUserById(ticket.getUserId());
        IncreasedPrice increase = this.increaseRepo.getIncreasedPriceById(ticket.getIncreasePrice());
        Date date = new Date(ticket.getDate());
        tic.setSeat(ticket.getSeat());
        tic.setTripId(trip);
        tic.setPrice(ticket.getPrice());
        tic.setIsActive(active);
        tic.setUserId(u);
        tic.setIncreasedPriceId(increase);
        tic.setType(ticket.getType());
        tic.setDate(date);
        tic.setName(null);
        tic.setEmployeeId(null);
        tic.setPayment(ticket.getPayment());
        tic.setIsGet(notGet); // phan sua cho nay
        return this.ticketRepository.addOffTicket(tic);
    }

    @Override
    public boolean addOnlTicket2(ApiTicketRequest apiTicket) {
        User u = this.userRepo.getUserById(apiTicket.getUserId());
        OrderOnline order = new OrderOnline();
        Ticket2 ticket = new Ticket2();
        Trip trip = this.tripRepo.getTripById(apiTicket.getTripId());
        ticket.setSeat(apiTicket.getSeat());
        ticket.setTripId(trip);
        Short ticType = 0;
        if (apiTicket.getTicType() == 1) {
            ticType = 1;
        }
        ticket.setTicketType(ticType);
        Double price = apiTicket.getPrice();
        if (apiTicket.getTicType() == 0) {
            price = price / 2;
        }
        ticket.setPrice(price);
        ticket.setCusName(u.getName());
        Short get = 0;
        ticket.setIsGet(get);

        int ticId = this.ticketRepository.addTicket(ticket);

        if (ticId != -1) {
            Ticket2 t = this.ticketRepository.getTicket2ById(ticId);
            order.setUserId(u);
            Date date = new Date(apiTicket.getDate());
            order.setOrderDate(date);
            order.setTicketId(t);
            User emp = this.userRepo.getUserById("None");
            order.setEmpId(emp);
            IncreasedPrice increase = this.increaseRepo.getIncreasedPriceById(apiTicket.getIncreasePrice());
            order.setIncreasedPriceId(increase);

            Payment pay = Payment.valueOf(apiTicket.getPayment());
            order.setPayment(pay);
            order.setPrice(apiTicket.getPrice());
            boolean rsOrder = this.ticketRepository.addOrder(order);
            return rsOrder;

        }
        return false;
    }

    @Override
    public List<ApiTicketResponse> getListTickets(Map<String, String> map) {
        return this.ticketRepository.getListTickets(map);
    }

    @Override
    public boolean changeTicket(ApiChangeTicket act) {
        OrderOnline order = this.ticketRepository.getOrderById(act.getOrderId());
        IncreasedPrice increase = this.increaseRepo.checkIncreasePrice(act.getDepartureTime());
        Trip trip = this.tripRepo.getTripById(act.getTripId());
        Ticket2 ticket = this.ticketRepository.getTicket2ById(act.getTicketId());

        order.setIncreasedPriceId(increase);
        order.setPrice(act.getPrice());
        boolean rsOrder = this.ticketRepository.updateOrder(order);

        ticket.setPrice(act.getNewPrice());
        ticket.setTripId(trip);
        ticket.setSeat(act.getSeat());
        boolean rsTicket = this.ticketRepository.updateTicket(ticket);
        return rsTicket;
    }

    @Override
    public Ticket2 getTicket2ById(Integer intgr) {
        return this.ticketRepository.getTicket2ById(intgr);
    }

    @Override
    public OrderOnline getOrderByTicket2Id(Integer intgr) {
        return this.ticketRepository.getOrderByTicket2Id(intgr);
    }

    @Override
    public boolean editOnlTicket2(ApiChangeTicket act) {
        OrderOnline order = this.ticketRepository.getOrderById(act.getOrderId());
        IncreasedPrice increase = this.increaseRepo.getIncreasedPriceById(act.getIncreaseId());
        Trip trip = this.tripRepo.getTripById(act.getTripId());
        Ticket2 ticket = this.ticketRepository.getTicket2ById(act.getTicketId());

        order.setIncreasedPriceId(increase);
        order.setPrice(act.getPrice());
        order.setOrderDate(act.getBookDate());
        User e = this.userRepo.getUserById(act.getEmpId());
        order.setEmpId(e);
        User c = this.userRepo.getUserById(act.getCusId());
        order.setUserId(c);
        boolean rsOrder = this.ticketRepository.updateOrder(order);

        ticket.setPrice(act.getNewPrice());
        ticket.setTripId(trip);
        ticket.setSeat(act.getSeat());
        boolean rsTicket = this.ticketRepository.updateTicket(ticket);
        return rsTicket;
    }
}
