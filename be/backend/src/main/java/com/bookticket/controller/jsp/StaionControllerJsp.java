/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.StationRequest;
import com.bookticket.pojo.Address;
import com.bookticket.pojo.Station;
import com.bookticket.service.AddressService;
import com.bookticket.service.StationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ADMIN
 */
@Controller
@ControllerAdvice
public class StaionControllerJsp {

    @Autowired
    private StationService stationService;
    
    @Autowired
    private AddressService addressService;
    
    @ModelAttribute
    public void getNameStation(Model model) {
        List<Map<String,Object>> list = this.stationService.getNameStation();
        model.addAttribute("nameStation", list);
    }
    
    @GetMapping("admin/station")
    public String getAdminStation(@RequestParam  Map<String, String> params, Model model){
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
         
        List<StationRequest> stations = stationService.getAdminStation(params);
        model.addAttribute("stations", stations);
        if(!stations.isEmpty()){
            model.addAttribute("totalPage", stations.get(0).getTotalPage());
        }
        return "station";
    }
    
    @GetMapping("employee/station")
    public String getAdminStationForEmployee(@RequestParam  Map<String, String> params, Model model){
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
         
        List<StationRequest> stations = stationService.getAdminStation(params);
        model.addAttribute("stations", stations);
        if(!stations.isEmpty()){
            model.addAttribute("totalPage", stations.get(0).getTotalPage());
        }
        return "stationEmployeeView";
    }
    
    @GetMapping("admin/station/add")
    public String newStation(Model model){
        model.addAttribute("addStationModel", new StationRequest());
        return "addStation";
    }
    @PostMapping("admin/station/add")
    public String addStation(@ModelAttribute(value = "addStationModel") StationRequest p, 
            Model model){
        
        Address address = this.addressService.getAddressById(Integer.valueOf(p.getAddressInfo()));
        Station station = new Station();
        station.setName(p.getName());
        station.setAddressId(address);
        station.setIsActive(Short.valueOf("1"));
        if(this.stationService.addStation(station)){
            return "redirect:/admin/station";
        }
        
        return "addStation";
    }
    @GetMapping("admin/station/{id}")
    public String stationDetail(Model model, @PathVariable(value = "id") Integer id){
       
        Station station = this.stationService.getStaionById(id);
        
        StationRequest stationRequest = new StationRequest();
        stationRequest.setId(id);
        stationRequest.setName(station.getName());
        stationRequest.setAddressInfo(station.getAddressId().getId());
        stationRequest.setTown(station.getAddressId().getTown());
        stationRequest.setDistrict(station.getAddressId().getDistrict());
        stationRequest.setWard(station.getAddressId().getWard());
        
        model.addAttribute("Station", stationRequest);
        return "editStation";
    }
    @PostMapping("admin/station")
    public String editStation(@ModelAttribute(value = "Station") StationRequest p){
        
        Address address = this.addressService.getAddressById(p.getAddressInfo());
        Station station = new Station();
        station.setId(p.getId());
        station.setName(p.getName());
        station.setAddressId(address);
        station.setIsActive(Short.valueOf("1"));
        
        
        if(this.stationService.editStation(station)){
            return "redirect:/admin/station";
        }
        
        return "editStation";
    }
    
    @PutMapping("admin/station/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStation(@PathVariable(value = "id") Integer id){
        Station station = this.stationService.getStaionById(id);
        
        station.setIsActive(Short.valueOf("0"));
        
        this.stationService.deleteStation(station);
    }
            
}
