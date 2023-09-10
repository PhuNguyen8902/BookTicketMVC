/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.IncreasedPriceRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.service.IncreasedPriceService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    @GetMapping("/admin/increasedPrice")
    public String getIncreasedPrice(Model model, @RequestParam Map<String, String> params){
        
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
        
        List<IncreasedPriceRequest> list = this.increasedPriceService.getIncreasedPrice(params);
        
        model.addAttribute("increasedPrice", list);
        
        if (!list.isEmpty()) {
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
    public String addIncreasedPrice(Model model, @ModelAttribute(value = "addIncreasedPriceModel") IncreasedPriceRequest p) throws ParseException{
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDate = p.getStartDate();
        startDate += " 00:00:00";
        Date startDateFormating = dateFormat.parse(startDate);
        String endDate = p.getEndDate();
        endDate += " 00:00:00";
        Date endDateFormating = dateFormat.parse(endDate);
        
        IncreasedPrice increasedPrice = new IncreasedPrice();
        increasedPrice.setId(p.getId());
        increasedPrice.setEventName(p.getEventName());
        increasedPrice.setIncreasedPercentage(Short.valueOf(p.getIncreasedPercentage()));
        increasedPrice.setStartDate(startDateFormating);
        increasedPrice.setEndDate(endDateFormating);
        increasedPrice.setIsActive(Short.valueOf("1"));
        
        if(this.increasedPriceService.addIncreasedPrice(increasedPrice))
            return "redirect:/admin/increasedPrice";
        
        return "addIncreasedPrice";
    }
    
    @GetMapping("/admin/increasedPrice/{id}")
    public String increasedPriceDetail(@PathVariable(value = "id") Integer id, Model model){
        
        
        IncreasedPrice increasedPrice = this.increasedPriceService.getIncreasedPriceById(id);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Use the appropriate date format
        
        String startDateFormating = "";
        if(increasedPrice.getStartDate() != null){
            startDateFormating = (dateFormat.format(increasedPrice.getStartDate())).split(" ")[0];
        }
        String endDateFormating = "";
        if(increasedPrice.getEndDate() != null){
            endDateFormating = (dateFormat.format(increasedPrice.getEndDate())).split(" ")[0];
        }
                
        IncreasedPriceRequest increasedPriceRequest = new IncreasedPriceRequest();
        increasedPriceRequest.setId(increasedPrice.getId());
        increasedPriceRequest.setEventName(increasedPrice.getEventName());
        increasedPriceRequest.setIncreasedPercentage(increasedPrice.getIncreasedPercentage().toString());
        increasedPriceRequest.setStartDate(startDateFormating);
        increasedPriceRequest.setEndDate(endDateFormating);
        
        model.addAttribute("IncreasedPrice", increasedPriceRequest);
        
        return "editIncreasedPrice";
    }
    
    @PostMapping("/admin/increasedPrice")
    public String editIncreasedPrice(@ModelAttribute(value = "IncreasedPrice") IncreasedPriceRequest p,
            Model model) throws ParseException{
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDate = p.getStartDate();
        startDate += " 00:00:00";
        Date startDateFormating = dateFormat.parse(startDate);
        String endDate = p.getEndDate();
        endDate += " 00:00:00";
        Date endDateFormating = dateFormat.parse(endDate);
        
        IncreasedPrice increasedPrice = new IncreasedPrice();
        increasedPrice.setId(p.getId());
        increasedPrice.setEventName(p.getEventName());
        increasedPrice.setIncreasedPercentage(Short.valueOf(p.getIncreasedPercentage()));
        increasedPrice.setStartDate(startDateFormating);
        increasedPrice.setEndDate(endDateFormating);
        increasedPrice.setIsActive(Short.valueOf("1"));
        
//        List<OrderOnline> tickets = this.ticketService.getOrderByIncreaseId(p.getId());
//        
//        for(OrderOnline ticket : tickets){
//            Double basePrice = ticket.getTicketId().getTripId().getPrice();
//            ticket.setPrice(basePrice + (basePrice * Double.valueOf(p.getIncreasedPercentage())));
//            this.ticketService.editOnlTicket(ticket);
//        }
                
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
