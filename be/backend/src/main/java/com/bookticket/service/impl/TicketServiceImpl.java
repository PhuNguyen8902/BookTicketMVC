/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.repository.TicketRepository;
import com.bookticket.service.TicketService;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Map<String, Object>> getTickets() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        // chuyen du lieu notaion
        NumberFormat formatter = new DecimalFormat("###.#####");
        // xu li date time
        Calendar start = Calendar.getInstance();
        for (Object[] o : this.ticketRepository.getTickets()) {
            map.put("id", o[0]);
            map.put("price", formatter.format(o[1]));
            map.put("isActive", o[2]);
            map.put("type", o[3]);
            map.put("payment", o[4]);
            start.setTime(Timestamp.valueOf(String.valueOf(o[5])));
            map.put("date", start.getTime()); // front-end 
        }
        list.add(map);

        return list;
    }

}
