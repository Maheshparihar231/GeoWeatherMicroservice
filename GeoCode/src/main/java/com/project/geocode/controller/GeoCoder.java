package com.project.geocode.controller;

import com.project.geocode.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/geo")
public class GeoCoder {

    @Value("${googleapi.key}")
    private String googleApiKey;

    @Value("${googleapi.host}")
    private String googleApiHost;

    @GetMapping("/test")
    public String  geoTest(){
        return "Geo Testing";
    }

    @GetMapping("/getLocation")
    public ResponseEntity<Response> getResponse(@RequestParam String address){
        String apiUrl = ""+address;
        //System.out.println(apiUrl);
        HttpHeaders headers = new HttpHeaders();

        headers.set("Google-API-Key", googleApiKey);
        headers.set("Google-API-Host", googleApiHost);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiUrl));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Response> responseEntity = restTemplate.exchange(requestEntity, Response.class);

        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }
}
