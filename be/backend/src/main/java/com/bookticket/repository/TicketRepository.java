/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Response.RevenueChartResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TicketRepository {
    List<Object[]> getTickets();
    List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params);
}
