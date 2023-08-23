/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
public class TripRequest {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date departureTime;
    private Date arrivalTime;
    private String price;
    private String driverName;
    private String seatCapacity;
    private String routeName;
    private String startStation;
    private String endStation;
    private Integer totalPage;
}
