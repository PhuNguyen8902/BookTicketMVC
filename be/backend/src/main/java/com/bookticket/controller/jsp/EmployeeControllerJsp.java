/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.pojo.User;
import com.bookticket.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("/admin")
public class EmployeeControllerJsp {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String list(Model model) {
//        model.addAttribute("employee", new User());
        List<User> userList = this.employeeService.getAllEmployee(null);
        model.addAttribute("employee",userList);
        return "employee";
    }
}
