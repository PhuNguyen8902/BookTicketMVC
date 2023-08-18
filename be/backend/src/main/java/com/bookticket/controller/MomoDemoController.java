package com.bookticket.controller;

import com.bookticket.dto.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MomoDemoController {

    private Mac HmacSHA256;

    private final String endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
    private final String partnerCode = "MOMOBKUN20180529";
    private final String accessKey = "klm05TvNBzhg7h7j";
    private final String secretKey = "at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa";
//    private final String partnerCode = "MOMO";
//    private final String accessKey = "F8BBA842ECF85";
//    private final String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

    private final String defaultOrderInfo = "Thanh toán qua MoMo";
    private final String defaultRedirectUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b";
    private final String defaultIpnUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b";
    private final String defaultExtraData = "";

    public Map<String, String> createOrder() {
        String orderInfo = "Test Order";
        String amount = "500000";
        String extraData = "Test Extra Data";
        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
        String requestType = "payWithATM";

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

    @RequestMapping(value = "/momo/query", method = RequestMethod.POST)
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

        return ResponseEntity.ok(response.getBody());
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
}
