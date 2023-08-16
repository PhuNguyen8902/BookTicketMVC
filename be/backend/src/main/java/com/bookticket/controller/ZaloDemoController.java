/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.ZaloPay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.Mac;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@RestController
public class ZaloDemoController {

    @Autowired
    private Mac HmacSHA256;

    private Logger logger = Logger.getLogger(this.getClass().getName());
//    @Autowired
//    private ZaloPay zaloPay;
    final Map embeddata = new HashMap() {
        {
            put("merchantinfo", "embeddata123");
        }
    };

    public String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }
    public Map item
            = new HashMap() {
        {
            put("itemid", "knb");
            put("itemname", "kim nguyen bao");
            put("itemprice", 198400);
            put("itemquantity", 1);
        }
    };

    Map<String, Object> order = new HashMap<String, Object>() {
        {
            put("appid", "554");
            put("apptransid", getCurrentTimeString("yyMMdd") + "_" + UUID.randomUUID()); // mã giao dich có định dạng yyMMdd_xxxx
            put("apptime", System.currentTimeMillis()); // miliseconds
            put("appuser", "demo");
            put("amount", 50000);
            put("description", "ZaloPay Intergration Demo");
            put("bankcode", "zalopayapp");
            put("item", new JSONObject(item).toString());
            put("embeddata", new JSONObject(embeddata).toString());
        }
    };

    @RequestMapping(value = "/zalo/demo", method = RequestMethod.GET)
    public ResponseEntity<?> callZalo() throws UnsupportedEncodingException, IOException {
        String data = order.get("appid") + "|" + order.get("apptransid") + "|" + order.get("appuser") + "|" + order.get("amount")
                + "|" + order.get("apptime") + "|" + order.get("embeddata") + "|" + order.get("item");
        order.put("mac", new HmacUtils("HmacSHA256", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn").hmacHex(data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://sandbox.zalopay.com.vn/v001/tpe/createorder");

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        };
//        Content - Type: application / x - www - form - urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
//
        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        for (String key
                : result.keySet()) {
            System.out.format("%s = %s\n", key, result.get(key));
        }
        return ResponseEntity.ok(result.toString());
    }

    @RequestMapping(value = "/zalo/call-back", method = RequestMethod.POST)
    public ResponseEntity<?> callBack(@RequestBody String jsonStr) {
        JSONObject result = new JSONObject();

        try {
            JSONObject cbdata = new JSONObject(jsonStr);
            String dataStr = cbdata.getString("data");
            String reqMac = cbdata.getString("mac");

            byte[] hashBytes = HmacSHA256.doFinal(dataStr.getBytes());

            String mac = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();

            // kiểm tra callback hợp lệ (đến từ ZaloPay server)
            if (!reqMac.equals(mac)) {
                // callback không hợp lệ
                result.put("returncode", -1);
                result.put("returnmessage", "mac not equal");
            } else {
                // thanh toán thành công
                // merchant cập nhật trạng thái cho đơn hàng
                JSONObject data = new JSONObject(dataStr);
                logger.info("update order's status = success where apptransid = " + data.getString("apptransid"));

                result.put("returncode", 1);
                result.put("returnmessage", "success");
            }
        } catch (Exception ex) {
            result.put("returncode", 0); // ZaloPay server sẽ callback lại (tối đa 3 lần)
            result.put("returnmessage", ex.getMessage());
        }

        // thông báo kết quả cho ZaloPay server
        return ResponseEntity.ok(result.toString());

    }
}
