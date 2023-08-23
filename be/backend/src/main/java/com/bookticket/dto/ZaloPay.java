/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author ADMIN
 */

public class ZaloPay {

    public Map<String, String> config = new HashMap<String, String>() {
        {
            put("appid", "554");
            put("key1", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn");
            put("key2", "uUfsWgfLkRLzq6W2uNXTCxrfxs51auny");
            put("endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/createorder");
        }
    };
    
     public String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }
}
