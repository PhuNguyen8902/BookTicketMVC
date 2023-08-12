/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto.Api;

import javax.validation.constraints.Digits;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRoute {

    private Integer id;
    private String name;
    private String startStation;
    private String endStation;
    @Digits(integer = 10, fraction = 2, message = "{Digits.yourDTO.distance}")
    private Double distance;
    @Digits(integer = 10, fraction = 2, message = "{Digits.yourDTO.distance}")
    private Double duration;
    private Integer totalPage;
}
