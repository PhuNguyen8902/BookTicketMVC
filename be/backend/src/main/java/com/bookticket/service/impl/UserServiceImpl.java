/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.pojo.User;
import com.bookticket.repository.UserRepository;
import com.bookticket.service.UserService;
import java.util.HashSet;
import java.util.List;
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
//            throw new UsernameNotFoundException("User khong ton tai");
            return null;
        }
        User user = users.get(0);

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), auth);

    }
}
