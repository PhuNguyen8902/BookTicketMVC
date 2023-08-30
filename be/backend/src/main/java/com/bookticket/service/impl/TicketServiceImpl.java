/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
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
    public List<TicketRequest> getTickets(Map<String, String> params) {
        return this.ticketRepository.getTickets(params);
    }

    @Override
    public List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params) {
        return this.ticketRepository.getListRevenueInTicket(params);
    }

}
