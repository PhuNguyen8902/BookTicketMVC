/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto.Request;

import com.bookticket.dto.Api.ApiTrip;
import com.bookticket.pojo.Trip;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author vegar
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {

    private Integer id;
    private Integer selectSeat;
    private Integer seat;
    private Integer tripId;
    private String route;
    private String departureTime;
    private String arrivalTime;
    private String price;
    private String user;
    private String userName;
    private String increasePrice;
    private String type;
    private String payment;
    private String date;
    private String employee;
    private Short isGet;
    private String eventName;
    private Short increasedPercentage;
    private Short ticType;
    private String empId;
    private Integer increasePriceId;
    private long startTime;
    private String ticTypeStr;
    private List<ApiTrip> listTrip;
    private Integer totalPage;

}
