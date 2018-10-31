package com.telegram.weatherbot.weatherTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Weather forecast
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

    @JsonProperty("city")
    private City city;

    @JsonProperty("list")
    List<Current> list;

    public Forecast() {

    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Current> getList() {
        return list;
    }

    public void setList(List<Current> list) {
        this.list = list;
    }
}
