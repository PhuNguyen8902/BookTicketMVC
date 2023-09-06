/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.service.IncreasedPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("api/increase_price/")
public class IncreasePriceController {

    @Autowired
    private IncreasedPriceService increaSer;

    @GetMapping("/check/{date}")
    public ResponseEntity<IncreasedPrice> checkIncreasePrice(@PathVariable long date) {
        IncreasedPrice result = this.increaSer.checkIncreasePrice(date);
        return ResponseEntity.ok(result);
    }
}
