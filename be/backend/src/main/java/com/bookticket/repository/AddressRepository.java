/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Request.AddressRequest;
import com.bookticket.pojo.Address;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vegar
 */
@Repository
public interface AddressRepository {
    List<AddressRequest> getAdminAddress(Map<String, String> params);
    List<Address> getAdminAdressInfo();
    boolean addAddress(Address a);
    boolean editAddress(Address a);
    boolean deleteAddress(Address a);
    Address getAddressById(Integer id);
    
}
