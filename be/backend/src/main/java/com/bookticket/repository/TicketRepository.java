/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Api.ApiTicketResponse;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.pojo.OrderOnline;
import com.bookticket.pojo.Ticket2;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TicketRepository {

    List<Short> getAllSeatTicketByTripId(Integer id);

    List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params);

    List<ApiTicketResponse> getListTickets(Map<String, String> params);

    int addTicket(Ticket2 ticket);

    boolean addOrder(OrderOnline o);

    Ticket2 getTicket2ById(Integer id);

    OrderOnline getOrderById(Integer id);

    OrderOnline getOrderByTicket2Id(Integer id);

    boolean updateTicket(Ticket2 ticket);

    boolean updateOrder(OrderOnline o);

}
