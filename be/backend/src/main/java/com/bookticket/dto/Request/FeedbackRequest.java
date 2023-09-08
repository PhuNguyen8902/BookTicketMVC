/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest {

    private Integer id;

    private String routeName;
    private String DepartureTime;
    private String ArrivalTime;
    private String userName;
    private Integer totalPage;
   
  
  ///duoi cua phu

    private String content;
    private Integer tripId;
    private String userId;

}
