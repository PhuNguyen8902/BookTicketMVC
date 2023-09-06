/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.IncreasedPriceRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.Ticket;
import com.bookticket.service.IncreasedPriceService;
import com.bookticket.service.TicketService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class IncreasedPriceJsp {
    
    @Autowired
    private IncreasedPriceService increasedPriceService;
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/admin/increasedPrice")
    public String getIncreasedPrice(Model model, @RequestParam Map<String, String> params){
        
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
        
        List<IncreasedPriceRequest> list = this.increasedPriceService.getIncreasedPrice(params);
        
        model.addAttribute("increasedPrice", list);
        
        if (list.size() != 0) {
            model.addAttribute("totalPage", list.get(0).getTotalPage());
        }
        
        return "increasedPrice";
    }
    
    @GetMapping("/admin/increasedPrice/add")
    public String newIncreasedPrice(Model model){
        model.addAttribute("addIncreasedPriceModel", new IncreasedPriceRequest());
        return "addIncreasedPrice";
    }
    
    @PostMapping("/admin/increasedPrice/add")
    public String addIncreasedPrice(Model model, @ModelAttribute(value = "addIncreasedPriceModel") IncreasedPriceRequest p){
        
        IncreasedPrice increasedPrice = new IncreasedPrice();
        increasedPrice.setId(p.getId());
        increasedPrice.setEventName(p.getEventName());
        increasedPrice.setIncreasedPercentage(Short.valueOf(p.getIncreasedPercentage()));
        increasedPrice.setIsActive(Short.valueOf("1"));
        
        if(this.increasedPriceService.addIncreasedPrice(increasedPrice))
            return "redirect:/admin/increasedPrice";
        
        return "addIncreasedPrice";
    }
    
    @GetMapping("/admin/increasedPrice/{id}")
    public String increasedPriceDetail(@PathVariable(value = "id") Integer id, Model model){
        
        IncreasedPrice increasedPrice = this.increasedPriceService.getIncreasedPriceById(id);
        IncreasedPriceRequest increasedPriceRequest = new IncreasedPriceRequest();
        increasedPriceRequest.setId(increasedPrice.getId());
        increasedPrice.setEventName(increasedPrice.getEventName());
        increasedPrice.setIncreasedPercentage(increasedPrice.getIncreasedPercentage());
        
        model.addAttribute("IncreasedPrice", increasedPrice);
        
        return "editIncreasedPrice";
    }
    
    @PostMapping("/admin/increasedPrice")
    public String editIncreasedPrice(@ModelAttribute(value = "IncreasedPrice") IncreasedPriceRequest p,
            Model model){
        
        IncreasedPrice increasedPrice = new IncreasedPrice();
        increasedPrice.setId(p.getId());
        increasedPrice.setEventName(p.getEventName());
        increasedPrice.setIncreasedPercentage(Short.valueOf(p.getIncreasedPercentage()));
        increasedPrice.setIsActive(Short.valueOf("1"));
        
        List<Ticket> tickets = this.ticketService.getTicketsByIncreasedPriceId(p.getId());
        
        for(Ticket ticket : tickets){
            Double basePrice = ticket.getTripId().getPrice();
            ticket.setPrice(basePrice + (basePrice * Double.valueOf(p.getIncreasedPercentage())));
            this.ticketService.editOnlTicket(ticket);
        }
                
        if(this.increasedPriceService.editIncreasedPrice(increasedPrice))
            return "redirect:/admin/increasedPrice";
         
        return "editIncreasedPrice";
    }
    
    @PutMapping("admin/increasedPrice/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable(value = "id") Integer id){
        IncreasedPrice t = this.increasedPriceService.getIncreasedPriceById(id);
        
        t.setIsActive(Short.valueOf("0"));
        
        this.increasedPriceService.deleteIncreasedPrice(t);
    }
    
    
}
