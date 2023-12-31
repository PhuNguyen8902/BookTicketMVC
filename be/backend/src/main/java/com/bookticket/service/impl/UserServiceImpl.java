/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.CustomerRequest;
import com.bookticket.dto.Request.DriverRequest;
import com.bookticket.pojo.User;
import com.bookticket.repository.UserRepository;
import com.bookticket.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        return this.userRepository.addUser(user);
    }

    @Override
    public List<User> getUsers(String email) {
        return this.userRepository.getUsers(email);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        List<User> users = this.getUsers(userName);
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(user.getRole().name()));

        return user;
    }

    @Override
    public List<Map<String, Object>> getDriverName() {
        List<Map<String, Object>> list = new ArrayList<>();
        
        for(Object[] o : this.userRepository.getDriverName()){
            Map<String, Object> map = new HashMap<>();
            
            map.put("id", o[0]);
            map.put("email", o[1]);
            map.put("name", o[2]);
            
            list.add(map);
        }
        
        return list;
    }

    @Override
    public User getUserById(String id) {
        return this.userRepository.getUserById(id);
    }

    @Override
    public List<User> getCustomerInfo() {
        return this.userRepository.getCustomerInfo();
    }

    @Override
    public boolean editUser(User user) {
        return this.userRepository.editUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return this.userRepository.deleteUser(user);
    }

    @Override
    public List<CustomerRequest> getCustomers(Map<String, String> params) {
        return this.userRepository.getCustomers(params);
    }

    @Override
    public List<DriverRequest> getDrivers(Map<String, String> params) {
        return this.userRepository.getDrivers(params);
    }
}
