/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.Api.ApiTicketResponse;
import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.repository.IncreasedPriceRepository;
import com.bookticket.repository.TicketRepository;
import com.bookticket.repository.TripRepository;
import com.bookticket.repository.UserRepository;
import com.bookticket.service.TicketService;
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

    @Override
    public List<TicketRequest> getOnlTickets(Map<String, String> params) {
        return this.ticketRepository.getOnlTickets(params);
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
    public boolean addOnlTicket(ApiTicketRequest ticket) {
        Short active = 1;
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
        return this.ticketRepository.addOffTicket(tic);
    }

    @Override
    public List<ApiTicketResponse> getListTickets(Map<String, String> map) {
        return this.ticketRepository.getListTickets(map);
    }
}
