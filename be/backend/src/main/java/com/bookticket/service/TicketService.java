/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TicketService {
    List<TicketRequest> getTickets(Map<String, String> params);
    List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params);
}
