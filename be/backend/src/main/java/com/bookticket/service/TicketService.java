/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Api.ApiChangeTicket;
import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.Api.ApiTicketResponse;
import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.pojo.OrderOnline;
import com.bookticket.pojo.Ticket2;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TicketService {

    List<TicketRequest> getOnlTickets(Map<String, String> params);


    Integer addOffTicket2(TicketRequest ticket);


    Ticket2 getTicket2ById(Integer id);


    List<Short> getAllSeatTicketByTripId(Integer id);

    List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params);


    boolean addOnlTicket2(ApiTicketRequest ticket);

    List<ApiTicketResponse> getListTickets(Map<String, String> params);

    boolean changeTicket(ApiChangeTicket ticket);

    OrderOnline getOrderByTicket2Id(Integer id);

    boolean editOnlTicket2(ApiChangeTicket ticket);
    
        boolean updateTicket(Ticket2 ticket);


}
