/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.pojo.Ticket;
import com.bookticket.repository.TicketRepository;
import com.bookticket.service.TicketService;
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

}
