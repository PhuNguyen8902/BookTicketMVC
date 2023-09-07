/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.ZaloPay;
import com.bookticket.pojo.User;
import com.bookticket.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import static org.springframework.web.servlet.function.RequestPredicates.param;

/**
 *
 * @author ADMIN
 */
@RestController
public class ZaloDemoController {

//    @Autowired
    private Mac HmacSHA256;
    @Autowired
    private UserService userSer;

    private Logger logger = Logger.getLogger(this.getClass().getName());
//    @Autowired
//    private ZaloPay zaloPay;
    final Map embeddata = new HashMap() {
        {
            put("redirecturl", "https://docs.zalopay.vn/result");
//            put("columninfo", "{\\\"branch_id\\\": \\\"HCM\\\",\\\"store_id\\\": \\\"CH123\\\",\\\"store_name\\\": \\\"Saigon Centre\\\",\\\"mc_campaign_id\\\": \\\"FREESHIP\\\"}");
//            put("promotioninfo", "{\"campaigncode\":\"blackfriday\"}");
            put("zlppaymentid", "P4201372");
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
//    private String apptransid = "";

    Map<String, Object> order = new HashMap<String, Object>() {
        {
            put("appid", "554");
//            put("apptransid", getCurrentTimeString("yyMMdd") + "_" + UUID.randomUUID());
            put("apptransid", System.currentTimeMillis());
            put("apptime", System.currentTimeMillis()); // miliseconds
            put("appuser", "demo");
            put("amount", 100000);
            put("description", "ZaloPay Intergration Demo");
            put("bankcode", "zalopayapp");
            put("item", new JSONObject(item).toString());
            put("embeddata", new JSONObject(embeddata).toString());
        }
    };

    @RequestMapping(value = "/zalo/demo", method = RequestMethod.GET)
    public ResponseEntity<?> callZalo() throws UnsupportedEncodingException, IOException {
        Map<String, Object> dataInput = new HashMap<>();
//        JSONObject dataInput = new JSONObject();
        dataInput.put("appid", 554);
        dataInput.put("appuser", order.get("appuser"));
        dataInput.put("apptime", order.get("apptime"));
        dataInput.put("amount", order.get("amount"));
        dataInput.put("apptransid", order.get("apptransid"));
        dataInput.put("embeddata", embeddata);
        dataInput.put("item", item);
        String data = 554 + "|" + order.get("apptransid") + "|" + order.get("appuser")
                + "|" + order.get("amount") + "|" + order.get("apptime") + "|" + embeddata
                + "|" + item;
        dataInput.put("mac", new HmacUtils("HmacSHA256", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn").hmacHex(data));
//        dataInput.put("bankcode", "zalopayapp");
        dataInput.put("bankcode", "CC");
//        dataInput.put("bankcode", "VTB");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://sandbox.zalopay.com.vn/v001/tpe/createorder");

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : dataInput.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        };
//        Content - Type: application / x - www - form - urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
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

    @RequestMapping(value = "api/zalo/create-qr", method = RequestMethod.POST)
    public ResponseEntity<?> createQRZalo(@RequestBody ApiTicketRequest item) throws UnsupportedEncodingException, IOException {

        User u = this.userSer.getUserById(item.getUserId());

//        String apptransid = getCurrentTimeString("yyMMdd") + "_" + UUID.randomUUID();
                String apptransid = Long.toString(System.currentTimeMillis());


//        apptransid = getCurrentTimeString("yyMMdd HHmmss") + "_" + UUID.randomUUID();
        long appTime = System.currentTimeMillis();
        long amout = Math.round(item.getPrice());
        String appuser = u.getName();

        String emb = "{\"redirecturl\": \"http://localhost:3000/thanks-zalo\","
                + "\"columninfo\": \"{\\\"branch_id\\\": \\\"HCM\\\",\\\"store_id\\\": \\\"CH123\\\",\\\"store_name\\\": \\\"Saigon Centre\\\",\\\"mc_campaign_id\\\": \\\"FREESHIP\\\"}\","
                + "\"promotioninfo\": \"{\\\"campaigncode\\\":\\\"blackfriday\\\"}\","
                + "\"zlppaymentid\": \"P4201372\"}";

        Map<String, Object> dataInput = new HashMap<>();
//        JSONObject dataInput = new JSONObject();
        dataInput.put("appid", 554);
        dataInput.put("appuser", appuser);
        dataInput.put("apptime", appTime);
        dataInput.put("amount", amout);
        dataInput.put("apptransid", apptransid);
        dataInput.put("embeddata", emb);
        dataInput.put("item", item);
        String data = 554 + "|" + apptransid + "|" + appuser
                + "|" + amout + "|" + appTime + "|" + emb
                + "|" + item;
        dataInput.put("mac", new HmacUtils("HmacSHA256", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn").hmacHex(data));
        dataInput.put("bankcode", "zalopayapp");
//        dataInput.put("bankcode", "CC");
//        dataInput.put("bankcode", "VTB");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://sandbox.zalopay.com.vn/v001/tpe/createorder");

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : dataInput.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        };
//        Content - Type: application / x - www - form - urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;
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

    @RequestMapping(value = "/api/zalo/query", method = RequestMethod.POST)
    public ResponseEntity<?> query() throws URISyntaxException, UnsupportedEncodingException, IOException {
        Map<String, Object> dataInput = new HashMap<>();
//        String apptransid = getCurrentTimeString("yyMMdd") + "_" + UUID.randomUUID();
String apptransid = Long.toString(System.currentTimeMillis());

//        JSONObject dataInput = new JSONObject();
        dataInput.put("app_id", 554);
        dataInput.put("app_trans_id", apptransid);
        String data = "554" + "|" + apptransid + "|" + "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn";
        dataInput.put("mac", new HmacUtils("HmacSHA256", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn").hmacHex(data));

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("appid", "554"));
        params.add(new BasicNameValuePair("apptransid", order.get("apptransid").toString()));
        params.add(new BasicNameValuePair("mac",
                new HmacUtils("HmacSHA256", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn").hmacHex(data)));
//        System.out.println(order.get("apptransid").toString());

        URIBuilder uri = new URIBuilder("https://sb-openapi.zalopay.vn/v2/query");
        uri.addParameters(params);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(uri.build());
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        for (String key : result.keySet()) {
            System.out.format("%s = %s\n", key, result.get(key));
        }
        return ResponseEntity.ok(result.toString());
    }

//    @RequestMapping(value = "/zalo/query", method = RequestMethod.GET)
//    public ResponseEntity<?> queryZalo() throws UnsupportedEncodingException, IOException, URISyntaxException {
//        Map<String, Object> dataInput = new HashMap<>();
////        JSONObject dataInput = new JSONObject();
//        dataInput.put("app_id", 554);
//        dataInput.put("app_trans_id", order.get("apptransid"));
//        String data = "554" + "|" + order.get("apptransid") + "|" + "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn";
//        dataInput.put("mac", new HmacUtils("HmacSHA256", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn").hmacHex(data));
//
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("appid", "554"));
//        params.add(new BasicNameValuePair("apptransid", order.get("apptransid").toString()));
//        params.add(new BasicNameValuePair("mac",
//                new HmacUtils("HmacSHA256", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn").hmacHex(data)));
//        System.out.println(order.get("apptransid").toString());
//
//        URIBuilder uri = new URIBuilder("https://sb-openapi.zalopay.vn/v2/query");
//        uri.addParameters(params);
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost post = new HttpPost(uri.build());
//        post.setEntity(new UrlEncodedFormEntity(params));
//
//        CloseableHttpResponse res = client.execute(post);
//        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
//        StringBuilder resultJsonStr = new StringBuilder();
//        String line;
//
//        while ((line = rd.readLine()) != null) {
//            resultJsonStr.append(line);
//        }
//
//        JSONObject result = new JSONObject(resultJsonStr.toString());
//        for (String key : result.keySet()) {
//            System.out.format("%s = %s\n", key, result.get(key));
//        }
//        return ResponseEntity.ok(result.toString());
//    }
    @RequestMapping(value = "/zalo/call-back", method = RequestMethod.POST)
    public ResponseEntity<?> callBack(@RequestBody String jsonStr) {
        JSONObject result = new JSONObject();

        try {
            JSONObject cbdata = new JSONObject(jsonStr);
            Map<String, Object> dataMap = new HashMap();
            dataMap.put("appid", order.get("appid"));
            dataMap.put("apptransid", order.get("apptransid"));
            dataMap.put("apptime", order.get("apptime"));
            dataMap.put("appuser", order.get("appuser"));
            dataMap.put("amount", order.get("amount"));
            dataMap.put("embeddata", embeddata);
            dataMap.put("item", item);

//            String dataStr = cbdata.getString("data");
            String reqMac = cbdata.getString("mac");
//            byte[] hashBytes = HmacSHA256.doFinal(dataMap.getBytes());
            String dataStr = generateDataString(dataMap);
            byte[] hashBytes = computeHmacSHA256(dataStr, "uUfsWgfLkRLzq6W2uNXTCxrfxs51auny".getBytes(StandardCharsets.UTF_8));

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

    private static String generateDataString(Map<String, Object> dataMap) {
        StringBuilder dataStr = new StringBuilder();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            dataStr.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return dataStr.toString();
    }

    private static byte[] computeHmacSHA256(String data, byte[] key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }
}
