package com.telegram.weatherbot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.telegram.weatherbot.model.WeatherBot;
import com.telegram.weatherbot.enums.WeatherUnits;

@Configuration
public class BotConfiguration {

    @Value("${bot.telegram_token}")
    private String token;

    @Value("${bot.open_weather_map_api_key}")
    private String openWeatherMapApiKey;

    @Value("${bot.language}")
    private String language;

    @Value("${bot.units}")
    private String units;

    @Bean
    public WeatherBot weatherBot() {
        WeatherBot weatherBot = new WeatherBot(token, openWeatherMapApiKey);
        weatherBot.setLanguage(language);
        weatherBot.setUnits(WeatherUnits.valueOf(units));
        return weatherBot;
    }

}
