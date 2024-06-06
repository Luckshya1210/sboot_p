package com.example.demo.services;

import com.example.demo.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiKey;
    private static final String API="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/CITY?unitGroup=us&key=API_KEY&contentType=json";

    @Autowired
    private RestTemplate restTemplate;



    public WeatherResponse getWeater(String city){

        String replace=API.replace("CITY",city).replace("API_KEY",apiKey);
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.set("key","val");
//        User vo=User.builder().username().password().build();
//        HttpEntity<String> httpEntity=new HttpEntity<>(vo,headers);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(replace, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

}
