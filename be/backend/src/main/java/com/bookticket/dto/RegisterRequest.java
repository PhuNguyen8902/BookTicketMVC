/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String password;
    private String email;
    private String phone;
}
