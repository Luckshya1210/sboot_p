package com.example.demo.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class WeatherResponse {

    public CurrentConditions getcurrentConditions() {
        return currentConditions;
    }

    public void setcurrentConditions(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }
     
    private CurrentConditions currentConditions;

    public class CurrentConditions{
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(double feelslike) {
            this.feelslike = feelslike;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double temp;
        public double feelslike;
        public double humidity;

    }



}
