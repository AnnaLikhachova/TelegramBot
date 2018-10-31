package com.telegram.weatherbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.telegram.weatherbot.model.WeatherBot;


@SpringBootApplication
@EnableScheduling
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private WeatherBot weatherBot;

    @Scheduled(fixedDelayString = "${bot.poll_interval}")
    public void update() {
        weatherBot.handleRequests();
    }

}

