/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Request.CustomerRequest;
import com.bookticket.dto.Request.DriverRequest;
import com.bookticket.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
//@Repository
public interface UserRepository {
    boolean addUser(User user);
    boolean editUser(User e);
    boolean deleteUser(User e);
    List<User> getUsers(String email);
    List<CustomerRequest> getCustomers(Map<String, String> params);
    List<DriverRequest> getDrivers(Map<String, String> params);
    List<Object[]> getDriverName();
    List<User> getCustomerInfo();
    User getUserById(String id);
}
