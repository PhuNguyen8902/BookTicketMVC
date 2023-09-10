/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.CustomerRequest;
import com.bookticket.dto.Request.DriverRequest;
import com.bookticket.dto.Request.EmployeeRequest;
import com.bookticket.dto.Request.LoginRequest;
import com.bookticket.dto.Request.RegisterRequest;
import com.bookticket.dto.Request.RegisterRequestJsp;
import com.bookticket.dto.Response.TokenResponse;
import com.bookticket.enums.Role;
import com.bookticket.pojo.User;
import com.bookticket.pojo.Vehicle;
import com.bookticket.service.AuthService;
import com.bookticket.service.EmployeeService;
import com.bookticket.service.UserService;
import com.bookticket.service.impl.PictureServiceImpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
public class UserControllerJsp {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login-user")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login-user")
    public String login(@ModelAttribute(value = "loginRequest") @Valid LoginRequest p,
            BindingResult rs) {
        User userDetails = (User) userDetailService.loadUserByUsername(p.getEmail());

        if (userDetails != null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        return "redirect:/";
    }

    @PostMapping("/logout-user")
    public String logout() {
        SecurityContextHolder.clearContext();

        return "redirect:/";
    }

    @GetMapping("/admin/create-account")
    public String createAccount(Model model) {
        model.addAttribute("create", new RegisterRequestJsp());
        return "createAccount";
    }

    @PostMapping("/admin/create-account")
    public String login(@ModelAttribute(value = "create") @Valid RegisterRequestJsp p,
            BindingResult rs, Model model) {
        if (p.getImage().getSize() == 0) {
            rs.rejectValue("image", "image.sameAsStart", "Please choose the avatar");
            return "createAccount";
        }
        if (rs.hasErrors()) {
            return "createAccount";
        }
        try {
            Map res = this.cloudinary.uploader().upload(p.getImage().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String url = res.get("secure_url").toString();
            RegisterRequest register = new RegisterRequest();
            register.setAvatar(url);
            register.setEmail(p.getEmail());
            register.setPassword(p.getPassword());
            register.setName(p.getName());
            register.setPhone(p.getPhone());
            register.setRole(p.getRole());
            TokenResponse response = this.authService.register(register);
        } catch (IOException ex) {
            Logger.getLogger(PictureServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/";
    }

    @ModelAttribute
    public void getDriverName(Model model) {
        List<Map<String, Object>> list = this.userService.getDriverName();
        model.addAttribute("driverName", list);
    }

    @ModelAttribute
    public void getCustomerInfo(Model model) {
        List<User> list = this.userService.getCustomerInfo();
        model.addAttribute("customerInfo", list);
    }

    @GetMapping("admin/customer")
    public String customerList(Model model, @RequestParam Map<String, String> params) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<CustomerRequest> userList = this.userService.getCustomers(params);
        model.addAttribute("customer", userList);

        if (!userList.isEmpty()) {
            model.addAttribute("totalPage", userList.get(0).getTotalPage());
        }

        return "customer";
    }

    @GetMapping("employee/customer")
    public String customerListForEmployee(Model model, @RequestParam Map<String, String> params) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<CustomerRequest> userList = this.userService.getCustomers(params);
        model.addAttribute("customer", userList);

        if (!userList.isEmpty()) {
            model.addAttribute("totalPage", userList.get(0).getTotalPage());
        }

        return "customerEmployeeView";
    }

    @GetMapping("admin/customer/{id}")
    public String employeeDetail(Model model, @PathVariable(value = "id") String id) {

        User customer = this.userService.getUserById(id);
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId(customer.getId());
        customerRequest.setAvatar(customer.getAvatar());
        customerRequest.setPassword(customer.getPassword());
        customerRequest.setEmail(customer.getEmail());
        customerRequest.setPreEmail(customer.getEmail());
        customerRequest.setName(customer.getName());
        customerRequest.setPhone(customer.getPhone());

        model.addAttribute("customer", customerRequest);
        return "editCustomer";
    }

    @PostMapping("admin/customer")
    public String editCustomer(Model model, @ModelAttribute(value = "employee") CustomerRequest customerRequest) {

        List<User> checkEmails = this.userService.getUsers("");

        for (User check : checkEmails) {
            String preEmail = customerRequest.getPreEmail();
            String email = customerRequest.getEmail();
            if (email.equals(check.getEmail()) && !email.equals(preEmail)) {
                model.addAttribute("emailError", "Email is already existed");
                return "redirect:/admin/driver/" + customerRequest.getId();
            }
        }

        User customer = new User();
        customer.setId(customerRequest.getId());
        customer.setAvatar(customerRequest.getAvatar());
        customer.setPassword(customerRequest.getPassword());
        customer.setEmail(customerRequest.getEmail());
        customer.setName(customerRequest.getName());
        customer.setPhone(customerRequest.getPhone());
        customer.setRole(Role.ROLE_EMPLOYEE);
        customer.setIsActive(Short.valueOf("1"));

        if (this.userService.editUser(customer)) {
            return "redirect:/admin/driver";
        }

        return "editDriver";
    }

    @GetMapping("admin/driver")
    public String driverList(Model model, @RequestParam Map<String, String> params) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<DriverRequest> userList = this.userService.getDrivers(params);
        model.addAttribute("driver", userList);

        if (!userList.isEmpty()) {
            model.addAttribute("totalPage", userList.get(0).getTotalPage());
        }

        return "driver";
    }

    @GetMapping("employee/driver")
    public String driverListForEmployee(Model model, @RequestParam Map<String, String> params) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<DriverRequest> userList = this.userService.getDrivers(params);
        model.addAttribute("driver", userList);

        if (!userList.isEmpty()) {
            model.addAttribute("totalPage", userList.get(0).getTotalPage());
        }

        return "driverEmployeeView";
    }

    @GetMapping("admin/driver/{id}")
    public String driverDetail(Model model, @PathVariable(value = "id") String id) {

        User driver = this.userService.getUserById(id);
        DriverRequest driverRequest = new DriverRequest();
        driverRequest.setId(driver.getId());
        driverRequest.setAvatar(driver.getAvatar());
        driverRequest.setPassword(driver.getPassword());
        driverRequest.setEmail(driver.getEmail());
        driverRequest.setPreEmail(driver.getEmail());
        driverRequest.setName(driver.getName());
        driverRequest.setPhone(driver.getPhone());

        model.addAttribute("driver", driverRequest);
        return "editDriver";
    }

    @PostMapping("admin/driver")
    public String editDriver(Model model, @ModelAttribute(value = "employee") DriverRequest driverRequest) {

        List<User> checkEmails = this.userService.getUsers("");

        for (User check : checkEmails) {
            String preEmail = driverRequest.getPreEmail();
            String email = driverRequest.getEmail();
            if (email.equals(check.getEmail()) && !email.equals(preEmail)) {
                model.addAttribute("emailError", "Email is already existed");
                return "redirect:/admin/driver/" + driverRequest.getId();
            }
        }

        User driver = new User();
        driver.setId(driverRequest.getId());
        driver.setAvatar(driverRequest.getAvatar());
        driver.setPassword(driverRequest.getPassword());
        driver.setEmail(driverRequest.getEmail());
        driver.setName(driverRequest.getName());
        driver.setPhone(driverRequest.getPhone());
        driver.setRole(Role.ROLE_EMPLOYEE);
        driver.setIsActive(Short.valueOf("1"));

        if (this.userService.editUser(driver)) {
            return "redirect:/admin/driver";
        }

        return "editDriver";
    }

    @GetMapping("employee/employee")
    public String listForEmployee(Model model, @RequestParam Map<String, String> params) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<EmployeeRequest> userList = this.employeeService.getAllEmployee(params);
        model.addAttribute("employee", userList);

        if (!userList.isEmpty()) {
            model.addAttribute("totalPage", userList.get(0).getTotalPage());
        }

        return "employeeEmployeeView";
    }

    @PutMapping("admin/customer/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCus(@PathVariable(value = "id") String id) {
        User u = this.userService.getUserById(id);

        u.setIsActive(Short.valueOf("0"));

        this.userService.deleteUser(u);
    }

    @PutMapping("admin/employee/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmp(@PathVariable(value = "id") String id) {
        User u = this.userService.getUserById(id);

        u.setIsActive(Short.valueOf("0"));

        this.userService.deleteUser(u);
    }

    @PutMapping("admin/driver/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable(value = "id") String id) {
        User u = this.userService.getUserById(id);

        u.setIsActive(Short.valueOf("0"));

        this.userService.deleteUser(u);
    }
}
