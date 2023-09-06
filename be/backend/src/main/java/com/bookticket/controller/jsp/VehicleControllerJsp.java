/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.dto.Request.VehicleRequest;
import com.bookticket.pojo.Vehicle;
import com.bookticket.service.VehicleService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class VehicleControllerJsp {
    
    @Autowired
    private VehicleService vehicleService;
    
    @ModelAttribute
    public void getVehicleName(Model model){
        model.addAttribute("vehicleName", this.vehicleService.getVehicleName());
    }
            
    @GetMapping("/admin/vehicle")
     public String list(@RequestParam Map<String, String> params, Model model) {
         
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
         
        List<VehicleRequest> vehicles = vehicleService.getVehicles(params);
        model.addAttribute("vehicles", vehicles);
        if(vehicles.size() != 0){
            model.addAttribute("totalPage", vehicles.get(0).getTotalPage());
        }
        return "vehicle";
    }
     
    @GetMapping("/employee/vehicle")
     public String listForEmployee(@RequestParam Map<String, String> params, Model model) {
         
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
         
        List<VehicleRequest> vehicles = vehicleService.getVehicles(params);
        model.addAttribute("vehicles", vehicles);
        if(vehicles.size() != 0){
            model.addAttribute("totalPage", vehicles.get(0).getTotalPage());
        }
        return "vehicleEmployeeView";
    }
     
    @GetMapping("/admin/vehicle/add")
    public String newVehicle(Model model) {
        model.addAttribute("addVehicleModel", new VehicleRequest());
        return "addVehicle";
    }
    @PostMapping("/admin/vehicle/add")
    public String addVehicle(@ModelAttribute("vehicles") @Valid VehicleRequest vehicle,
        BindingResult rs2,
        @ModelAttribute("addRouteModel") @Valid VehicleRequest p,
        BindingResult rs,
        Model model) {
        
        VehicleRequest vehicleRequest = new VehicleRequest();
        vehicleRequest.setId(p.getId());
        vehicleRequest.setSeatCapacity(p.getSeatCapacity());
        vehicleRequest.setLicensePlate(p.getLicensePlate());
        
        if(this.vehicleService.addVehicle(vehicleRequest)){
            return "redirect:/admin/vehicle";
        }
        return "addVehicle";
    }
    
    @GetMapping("/admin/vehicle/{id}")
    public String vehicleDetail(Model model, @PathVariable(value = "id") Integer id){
        Vehicle vehicle = this.vehicleService.getVehicleById(id);
        VehicleRequest vehicleRequest = new VehicleRequest();
        
        vehicleRequest.setId(id);
        vehicleRequest.setSeatCapacity(vehicle.getSeatCapacity());
        vehicleRequest.setLicensePlate(vehicle.getLicensePlate());
        
        model.addAttribute("vehicle", vehicleRequest); 
        return "editVehicle";
    }
    
    @PostMapping("/admin/vehicle")
    public String editVehicle(@ModelAttribute(value = "vehicle") @Valid VehicleRequest p){
        
        VehicleRequest vehicleRequest = new VehicleRequest();
        vehicleRequest.setId(p.getId());
        vehicleRequest.setSeatCapacity(p.getSeatCapacity());
        vehicleRequest.setLicensePlate(p.getLicensePlate());
        if(this.vehicleService.editVehicle(vehicleRequest)){
            return "redirect:/admin/vehicle";
        }
        
        return "editVehicle";
    }
    @PutMapping("admin/vehicle/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable(value = "id") Integer id){
        Vehicle v = this.vehicleService.getVehicleById(id);
        
        v.setIsActive(Short.valueOf("0"));
        
        this.vehicleService.deleteVehicle(v);
    }
}
