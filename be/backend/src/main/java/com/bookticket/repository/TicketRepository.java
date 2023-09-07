/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Api.ApiTicketResponse;
import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.pojo.Ticket;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TicketRepository {

    List<TicketRequest> getOnlTickets(Map<String, String> params);

    List<TicketRequest> getOffTickets(Map<String, String> params);

    boolean addOffTicket(Ticket ticket);

    boolean editOnlTicket(Ticket ticket);

    boolean editOffTicket(Ticket ticket);

    boolean deleteTicket(Ticket ticket);

    Ticket getTicketById(Integer id);

    List<Ticket> getTicketsByIncreasedPriceId(Integer id);

    List<Short> getAllSeatTicketByTripId(Integer id);

    List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params);

    List<ApiTicketResponse> getListTickets(Map<String, String> params);

}
