package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.TripRequest;
import com.bookticket.service.TripService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TripControllerJsp {
    
    @Autowired
    private TripService tripService;
    
    @GetMapping("/admin/trip")
    public String getTrips(@RequestParam Map<String, String> params, Model model){
        
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
        
        List<TripRequest> trips = tripService.getAdminTrips(params);
        model.addAttribute("trips", trips);
        if(trips.size() != 0){
             model.addAttribute("totalPage", trips.get(0).getTotalPage());
        }
         
        return "trip";
    }
    
    @GetMapping("/admin/trip/add")
    public String newTrip(Model model){
        model.addAttribute("addTripModel", new TripRequest());

        return "addTrip";
    }
    @PostMapping("/admin/trip/add")
    public String addTrip(@ModelAttribute("addTripModel") @Valid TripRequest p,
        BindingResult rs,
        Model model){
        
        
        
        return "addTrip";
    }
    @GetMapping("/admin/trip/edit")
    public String editTrip(){
        
        return "editTrip";
    }   
}
