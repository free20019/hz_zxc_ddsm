package com.twkf.helper;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

@Component
@Slf4j
public class UrlUtils {

    /**
     * POST请求调用方式
     *
     * @param url 请求URL
     * @param responseType 返回对象类型
     * @return
     */
    public static <T> T  post(String url, Object requestBody , Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        log.info("msg="+requestBody);
        return restTemplate.postForObject(url, requestBody, responseType);
    }

    /**
     * POST请求
     * */
    public static String request_post(String url, String json){
        Gson gson = new Gson();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        org.springframework.http.HttpEntity<String> requestEntity = null;
        String data = gson.toJson(json);
        log.info("msg="+data);
        requestEntity = new org.springframework.http.HttpEntity<String>(data, requestHeaders);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String back = response.getBody().toString();
        log.info("back="+back);
        return back;
    }

    /**
     * POST_boby请求
     * */
    public static String request_post_boby(String url, String json){
        HttpHeaders requestHeaders = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        org.springframework.http.HttpEntity<MultiValueMap> requestEntity = null;
        //body
        MultiValueMap<String, String> requestBody = castMultiValueMap(json);
        log.info("msg="+requestBody);
        requestEntity = new org.springframework.http.HttpEntity<MultiValueMap>(requestBody, requestHeaders);
        ResponseEntity<String> response = template.postForEntity(url,  requestEntity, String.class);
        String back = response.getBody().toString();
        log.info("back="+back);
        return back;

    }

    private static MultiValueMap<String, String> castMultiValueMap(String json) {
        Gson gson = new Gson();
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map = gson.fromJson(json, map.getClass());
        for (String key : map.keySet()) {
            multiValueMap.add(key, map.get(key));
        }
        return multiValueMap;
    }

    /**
     * GET请求
     * */
    public static String request_get(String url){
        HttpHeaders requestHeaders = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        org.springframework.http.HttpEntity<String> requestEntity = null;
        requestEntity = new org.springframework.http.HttpEntity<String>("", requestHeaders);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String back = response.getBody().toString();
        log.info("back="+back);
        return back;
    }

}

