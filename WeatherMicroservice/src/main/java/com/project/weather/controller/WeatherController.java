package com.project.weather.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.project.weather.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    @GetMapping("/test")
    public String init(){
        return "Weather Microservice";
    }
    @GetMapping("/city/{cityName}")
    public ResponseEntity<Response> getWeatherByCity(@PathVariable String cityName){
        String apiUrl = "https://open-weather13.p.rapidapi.com/city/"+cityName;

        HttpHeaders headers = new HttpHeaders();

        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApiHost);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiUrl));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Response> responseEntity = restTemplate.exchange(requestEntity, Response.class);

        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());

    }

    @GetMapping("/city/{lat}/{lon}")
    public ResponseEntity<Response> getWeatherByLatLon(@PathVariable double lat,@PathVariable double lon){
        String apiUrl = "https://open-weather13.p.rapidapi.com/city/latlon/" + lat + "/" + lon;
        System.out.println(apiUrl);
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApiHost);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiUrl));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Response> responseEntity = restTemplate.exchange(requestEntity, Response.class);

        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());

    }
}
