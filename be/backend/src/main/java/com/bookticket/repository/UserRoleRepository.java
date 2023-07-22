/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.pojo.UserRoles;

/**
 *
 * @author ADMIN
 */
public interface UserRoleRepository {
    boolean setCustomer(UserRoles userRole);
    UserRoles findRoleOfUser(String userId);
}
