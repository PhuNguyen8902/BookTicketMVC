/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto.Api;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class ApiChangeTicket {

    private Short seat;
    private Integer orderId;
    private Integer ticketId;
    private Integer tripId;
    private Double price;
    private Double newPrice;
    private Integer increaseId;
    private long departureTime;
    private Date bookDate;
    private String cusId;
    private String empId;

}
