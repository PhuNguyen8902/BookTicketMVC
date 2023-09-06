/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Request.EmployeeRequest;
import com.bookticket.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public interface EmployeeService {
        List<EmployeeRequest> getAllEmployee(Map<String, String> params);
        boolean addEmployee(User e);
        boolean editEmployee(User e);
        boolean deleteEmployee(User e);
        List<User> getEmployeeInfo();

}
