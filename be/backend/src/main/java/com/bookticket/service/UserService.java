/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ADMIN
 */
public interface UserService extends UserDetailsService {

    boolean addUser(User user);

    List<User> getUsers(String email);
    List<Map<String, Object>> getDriverName();
    
    User getUserById(String id);
}
