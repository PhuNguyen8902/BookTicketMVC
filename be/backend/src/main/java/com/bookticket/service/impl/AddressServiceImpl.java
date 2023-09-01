/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.AddressRequest;
import com.bookticket.pojo.Address;
import com.bookticket.repository.AddressRepository;
import com.bookticket.service.AddressService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vegar
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService{
    
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressRequest> getAdminAddress(Map<String, String> params) {
        return this.addressRepository.getAdminAddress(params);
    }

    @Override
    public List<Address> getAdminAdressInfo() {
        return this.addressRepository.getAdminAdressInfo();
    }

    @Override
    public boolean addAddress(Address a) {
        return this.addressRepository.addAddress(a);
    }

    @Override
    public boolean editAddress(Address a) {
        return this.addressRepository.editAddress(a);
    }

    @Override
    public boolean deleteAddress(Address a) {
        return this.addressRepository.deleteAddress(a);
    }

    @Override
    public Address getAddressById(Integer id) {
        return this.addressRepository.getAddressById(id);
    }
    
}
