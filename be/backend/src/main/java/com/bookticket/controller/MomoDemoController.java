package com.bookticket.controller;

import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.Api.IPNData;
import com.bookticket.dto.Request.OrderDataQrRequest;
import com.bookticket.pojo.User;

import com.bookticket.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.HmacUtils;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MomoDemoController {
    
    @Autowired
    private UserService userSer;


    private final String endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
    private final String partnerCode = "MOMOBKUN20180529";
    private final String accessKey = "klm05TvNBzhg7h7j";
    private final String secretKey = "at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa";
//    private final String partnerCode = "MOMO";
//    private final String accessKey = "F8BBA842ECF85";
//    private final String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

    private final String defaultOrderInfo = "Thanh toán qua MoMo";
    private final String defaultRedirectUrl = "http://localhost:3000/thanks";
    private final String defaultIpnUrl = "http://localhost:8080/backend/momo/ipn-handler";
    private final String defaultExtraData = "";

    public Map<String, String> createOrder() {
        String orderInfo = "Book Ticket";
        String amount = "100000";
        String extraData = "eyJ1c2VybmFtZSI6ICJtb21vIn0=";
        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
//        String requestType = "payWithATM";
        String requestType = "captureWallet";

        String rawHash = "accessKey=" + accessKey + "&amount=" + amount + "&extraData=" + extraData
                + "&ipnUrl=" + defaultIpnUrl + "&orderId=" + orderId + "&orderInfo=" + orderInfo
                + "&partnerCode=" + partnerCode + "&redirectUrl=" + defaultRedirectUrl
                + "&requestId=" + requestId + "&requestType=" + requestType;
        String signature = new HmacUtils("HmacSHA256", secretKey).hmacHex(rawHash);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("partnerCode", partnerCode);
        requestBody.put("partnerName", "Test");
        requestBody.put("storeId", "MomoTestStore");
        requestBody.put("requestId", requestId);
        requestBody.put("amount", amount);
        requestBody.put("orderId", orderId);
        requestBody.put("orderInfo", orderInfo);
        requestBody.put("redirectUrl", defaultRedirectUrl);
        requestBody.put("ipnUrl", defaultIpnUrl);
        requestBody.put("lang", "vi");
        requestBody.put("extraData", extraData);
        requestBody.put("requestType", requestType);
        requestBody.put("signature", signature);

        return requestBody;
    }

    @RequestMapping(value = "/momo/demo", method = RequestMethod.GET)
    public ResponseEntity<?> callMomo() throws UnsupportedEncodingException, IOException {
        Map<String, String> order = createOrder();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String momoApiUrl = "https://test-payment.momo.vn/v2/gateway/api/create";

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(order, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(momoApiUrl, requestEntity, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    @RequestMapping(value = "/api/momo/create-qr", method = RequestMethod.POST)
    public ResponseEntity<?> createOrderQr(@RequestBody ApiTicketRequest item) {

        User u = this.userSer.getUserById(item.getUserId());
        
        String orderInfo = "Book Ticket";
        double price = item.getPrice();
        Long amount = Math.round(price); // Làm tròn số thập phân

        String extraData = "eyJ1c2VybmFtZSI6ICJtb21vIn0=";
        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
//        String requestType = "payWithATM";
        String requestType = "captureWallet";

        String rawHash = "accessKey=" + accessKey + "&amount=" + amount + "&extraData=" + extraData
                + "&ipnUrl=" + defaultIpnUrl + "&orderId=" + orderId + "&orderInfo=" + orderInfo
                + "&partnerCode=" + partnerCode + "&redirectUrl=" + defaultRedirectUrl
                + "&requestId=" + requestId + "&requestType=" + requestType;
        String signature = new HmacUtils("HmacSHA256", secretKey).hmacHex(rawHash);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("partnerCode", partnerCode);
        requestBody.put("partnerName", u.getName());
        requestBody.put("storeId", "MomoTestStore");
        requestBody.put("requestId", requestId);
        requestBody.put("amount", amount);
        requestBody.put("orderId", orderId);
        requestBody.put("orderInfo", orderInfo);
        requestBody.put("redirectUrl", defaultRedirectUrl);
        requestBody.put("ipnUrl", defaultIpnUrl);
        requestBody.put("lang", "vi");
        requestBody.put("extraData", extraData);
        requestBody.put("requestType", requestType);
        requestBody.put("signature", signature);
//        System.out.println("-----------------------item");
//        System.out.println(requestBody.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String momoApiUrl = "https://test-payment.momo.vn/v2/gateway/api/create";

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(momoApiUrl, requestEntity, String.class);
//        System.out.println("-----------------------item2");
//        System.out.println(response.getBody());
        return ResponseEntity.ok(response.getBody());
    }

    @RequestMapping(value = "/api/momo/query", method = RequestMethod.POST)
    public ResponseEntity<?> query(@RequestBody String jsonStr) {
        JSONObject result = new JSONObject(jsonStr);
        JSONObject data = new JSONObject();

        data.put("partnerCode", result.getString("partnerCode"));
        data.put("requestId", result.getString("requestId"));
        data.put("orderId", result.getString("orderId"));
        data.put("lang", "vi");
        String dt = "accessKey=" + accessKey + "&" + "orderId=" + result.get("orderId") + "&" + "partnerCode=" + result.get("partnerCode") + "&" + "requestId=" + result.get("requestId");
        data.put("signature", new HmacUtils("HmacSHA256", secretKey).hmacHex(dt));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String momoApiUrl = "https://test-payment.momo.vn/v2/gateway/api/query";

        HttpEntity<String> requestEntity = new HttpEntity<>(data.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(momoApiUrl, requestEntity, String.class);

        String responseBody = response.getBody();

//        if (responseBody != null) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                JsonNode jsonNode = objectMapper.readTree(responseBody);
//                String resultCode = jsonNode.get("resultCode").asText();
//                String message = jsonNode.get("message").asText();
//                this.orderService.addOrder(resultCode, message);
//
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }

//        this.orderService.addOrder(userId, response.getBody()., userId)
        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(value = "/momo/confirm", method = RequestMethod.POST)
    public ResponseEntity<?> confirm(@RequestBody String jsonStr) throws NoSuchAlgorithmException {
        JSONObject result = new JSONObject(jsonStr);
        JSONObject data = new JSONObject();

        data.put("partnerCode", result.getString("partnerCode"));
        data.put("requestId", result.getString("requestId"));
        data.put("orderId", result.getString("orderId"));
        data.put("requestType", "capture");
        data.put("lang", "vi");
        data.put("description", "");
        data.put("amount", Long.valueOf(result.getInt("amount")));

        String rawSignature = "accessKey=" + accessKey + "&amount=" + Long.valueOf(result.getInt("amount")) + "&description=" + "" + "&orderId=" + result.getString("orderId") + "&partnerCode=" + partnerCode + "&requestId=" + result.getString("requestId")
                + "&requestType=" + "capture";

        String signature = new HmacUtils("HmacSHA256", secretKey).hmacHex(rawSignature);
        data.put("signature", signature);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String momoApiUrl = "https://test-payment.momo.vn/v2/gateway/api/confirm";

        HttpEntity<String> requestEntity = new HttpEntity<>(data.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(momoApiUrl, requestEntity, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/momo/ipn-handler")
    public ResponseEntity<Void> handleIPN(@RequestBody IPNData ipnData) {
        // Xử lý thông báo IPN
        System.out.println("------------ipn");
        System.out.println(ipnData);

//
//        // Lưu thông tin giao dịch vào cơ sở dữ liệu
//        Transaction transaction = new Transaction();
//        transaction.setTransactionId(ipnData.getTransactionId());
//        transaction.setStatus(ipnData.getStatus());
//        transaction.setAmount(ipnData.getAmount());
//        // Đặt các thông tin khác
//
//        transactionRepository.save(transaction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
