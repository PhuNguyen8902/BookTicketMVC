/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiTicketResponse {

    private Short seat;
    private Double price;
//    private Short isActive;
    private String userName;
    private Short increasedPercentage;
    private String eventName;
    private long departureTime;
    private long arrivalTime;
    private long bookTime;
    private Integer tripId;
    private Integer routeId;
    private Short seatCapacity;
    private String licensePlate;
    private String payment;
    private Short type;
    private String startStation;
    private String endStation;
    private Integer ticketId;
    private Integer totalPage;
    private Short isGet;
}
