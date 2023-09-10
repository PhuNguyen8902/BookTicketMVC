/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.EmployeeRequest;
import com.bookticket.pojo.User;
import com.bookticket.repository.EmployeeRepository;
import com.bookticket.service.EmployeeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public List<EmployeeRequest> getAllEmployee(Map<String, String> params) {
        return this.employeeRepo.getAllEmployee(params);
    }

    @Override
    public List<User> getEmployeeInfo() {
        return this.employeeRepo.getEmployeeInfo();
    }

}
