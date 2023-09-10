/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.EmployeeRequest;
import com.bookticket.enums.Role;
import com.bookticket.pojo.User;
import com.bookticket.service.EmployeeService;
import com.bookticket.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
@ControllerAdvice
@RequestMapping("/admin")
public class EmployeeControllerJsp {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;

    @GetMapping("/employee")
    public String list(Model model, @RequestParam Map<String, String> params) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<EmployeeRequest> userList = this.employeeService.getAllEmployee(params);
        model.addAttribute("employee", userList);

        if (!userList.isEmpty()) {
            model.addAttribute("totalPage", userList.get(0).getTotalPage());
        }

        return "employee";
    }
    

    @GetMapping("/employee/{id}")
    public String employeeDetail(Model model, @PathVariable(value = "id") String id) {

        User employee = this.userService.getUserById(id);
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setId(employee.getId());
        employeeRequest.setAvatar(employee.getAvatar());
        employeeRequest.setPassword(employee.getPassword());
        employeeRequest.setEmail(employee.getEmail());
        employeeRequest.setPreEmail(employee.getEmail());
        employeeRequest.setName(employee.getName());
        employeeRequest.setPhone(employee.getPhone());

        model.addAttribute("employee", employeeRequest);
        return "editEmployee";
    }

    @PostMapping("/employee")
    public String editEmployee(Model model, @ModelAttribute(value = "employee") EmployeeRequest employeeRequest) {

        List<User> checkEmails = this.userService.getUsers("");

        for (User check : checkEmails) {
            String preEmail = employeeRequest.getPreEmail();
            String email = employeeRequest.getEmail();
            if (email.equals(check.getEmail()) && !email.equals(preEmail)) {
                model.addAttribute("emailError", "Email is already existed");
                return "redirect:/admin/employee/" + employeeRequest.getId();
            }
        }

        User employee = new User();
        employee.setId(employeeRequest.getId());
        employee.setAvatar(employeeRequest.getAvatar());
        employee.setPassword(employeeRequest.getPassword());
        employee.setEmail(employeeRequest.getEmail());
        employee.setName(employeeRequest.getName());
        employee.setPhone(employeeRequest.getPhone());
        employee.setRole(Role.ROLE_EMPLOYEE);
        employee.setIsActive(Short.valueOf("1"));

        if (this.userService.editUser(employee)) {
            return "redirect:/admin/employee";
        }

        return "editEmployee";
    }

    @ModelAttribute
    public void getEmployeeInfo(Model model) {
        model.addAttribute("employeeInfo", this.employeeService.getEmployeeInfo());
    }
}
