/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.AddressRequest;
import com.bookticket.pojo.Address;
import com.bookticket.service.AddressService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @author vegar
 */
@Controller
@ControllerAdvice
public class AddressControllerJsp {
    
    @Autowired
    private AddressService addressService;
    
    @GetMapping("/admin/address")
    public String getAddress(@RequestParam Map<String, String> params, Model model){
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
         
        List<AddressRequest> address = addressService.getAdminAddress(params);
        model.addAttribute("address", address);
        if(!address.isEmpty()){
            model.addAttribute("totalPage", address.get(0).getTotalPage());
        }
        
        return "address";
    }
     @GetMapping("/employee/address")
    public String getAddressForEmployee(@RequestParam Map<String, String> params, Model model){
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
         
        List<AddressRequest> address = addressService.getAdminAddress(params);
        model.addAttribute("address", address);
        if(!address.isEmpty()){
            model.addAttribute("totalPage", address.get(0).getTotalPage());
        }
        
        return "addressEmployeeView";
    }
    @GetMapping("/admin/address/add")
    public String newAddress(Model model){
        model.addAttribute("addAddressModel", new AddressRequest());
        return "addAddress";
    }
    
    @PostMapping("/admin/address/add")
    public String addAddress(@ModelAttribute(value = "addAddressModel") AddressRequest p,
            Model model){
        
        Address address = new Address();
        
        address.setTown(p.getTown());
        address.setDistrict(p.getDistrict());
        address.setWard(p.getWard());
        address.setIsActive(Short.valueOf("1"));
        
        if(this.addressService.addAddress(address)){
            return "redirect:/admin/address";
        }
        
        return "addAddress";
    }
    @GetMapping("/admin/address/{id}")
    public String addressDetail(@PathVariable(value = "id") Integer id, Model model){
        
        Address address = addressService.getAddressById(id);
        
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setId(address.getId());
        addressRequest.setTown(address.getTown());
        addressRequest.setDistrict(address.getDistrict());
        addressRequest.setWard(address.getWard());
        
        model.addAttribute("address", addressRequest);
        return "editAddress";
    }
    @PostMapping("/admin/address")
    public String editAddress(@ModelAttribute(value = "address") AddressRequest p){
        
        Address address = new Address();
        
        address.setId(p.getId());
        address.setTown(p.getTown());
        address.setDistrict(p.getDistrict());
        address.setWard(p.getWard());
        address.setIsActive(Short.valueOf("1"));
           
        if(this.addressService.editAddress(address)){
            return "redirect:/admin/address";
        }
        
        return "editAddress";
    }
    @PutMapping("admin/address/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable(value = "id") Integer id){
        Address a = this.addressService.getAddressById(id);
        
        a.setIsActive(Short.valueOf("0"));
        
        this.addressService.deleteAddress(a);
    }
    
    @ModelAttribute
    public void getAdminAddress(Model model){
        model.addAttribute("addressInfo", this.addressService.getAdminAdressInfo());
    }
}
