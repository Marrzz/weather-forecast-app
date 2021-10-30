package main;

import dto.CurrentWeatherData;
import api.WeatherApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.CityNotFoundException;
import exception.WrongInputException;
import helpers.StringFormatter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CityNotFoundException, JsonProcessingException, WrongInputException {

        String city = StringFormatter.processInput(getCityNameFromUser());

        System.out.println(getWeatherData(city));
    }

    public static String getWeatherData(String cityName) throws CityNotFoundException, JsonProcessingException {
        WeatherApi api = new WeatherApi();

        CurrentWeatherData w = api.getCurrentWeatherData(cityName);

        return StringFormatter.replaceFieldsInReport(new ObjectMapper().writeValueAsString(w));
    }

    public static String getCityNameFromUser(){

        System.out.println("Enter a city name!");

        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

}
