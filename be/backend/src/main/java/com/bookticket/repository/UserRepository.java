/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.pojo.User;
import java.util.List;

/**
 *
 * @author ADMIN
 */
//@Repository
public interface UserRepository {
    boolean addUser(User user);
    List<User> getUsers(String email);
}
