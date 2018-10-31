package com.telegram.weatherbot.weatherTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {

    @JsonProperty("coord")
    private Coordinates coordinates;

    @JsonProperty("weather")
    private List<WeatherCondition> weatherCondition;

    @JsonProperty("main")
    private WeatherCore weather;

    @JsonProperty("dt")
    private int date;

    @JsonProperty("dt_txt")
    private String dateText;

    @JsonProperty("name")
    private String cityName;

    public Current() {

    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<WeatherCondition> getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(List<WeatherCondition> weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public WeatherCore getWeather() {
        return weather;
    }

    public void setWeather(WeatherCore weather) {
        this.weather = weather;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }
}
