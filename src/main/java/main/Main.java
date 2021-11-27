package main;

import dto.CurrentWeatherData;
import api.WeatherApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.CityNotFoundException;
import exception.WrongInputException;
import helpers.CurrentReportFormatter;
import helpers.ForecastReportFormatter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CityNotFoundException, JsonProcessingException, WrongInputException {

        String city = CurrentReportFormatter.processInput(getCityNameFromUser());

        System.out.println(getCurrentWeatherAndForecastJson(city));
    }

    public static String getCurrentWeatherReport(String cityName) throws CityNotFoundException, JsonProcessingException {
        WeatherApi api = new WeatherApi();

        CurrentWeatherData w = api.getCurrentWeatherData(cityName);

        return CurrentReportFormatter.replaceFieldsInReport(new ObjectMapper().writeValueAsString(w));
    }

    public static String getCityNameFromUser(){

        System.out.println("Enter a city name!");

        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public static String getWeatherForecast(String cityName) throws JsonProcessingException {

        ForecastReportFormatter formatter = new ForecastReportFormatter();

        WeatherApi api = new WeatherApi();

        return formatter.getFormattedForecast(api.getThreeDayForecast(cityName));
    }

    public static String getCurrentWeatherAndForecastJson(String city) throws JsonProcessingException, CityNotFoundException {

        return "{" + getCurrentWeatherReport(city).replace("}}", "},") + getWeatherForecast(city);

    }
}
