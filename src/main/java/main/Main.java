package main;

import dto.CurrentWeatherData;
import api.WeatherApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.CityNotFoundException;
import exception.WrongFileFormatException;
import helpers.CurrentReportFormatter;
import helpers.ForecastReportFormatter;
import helpers.MyFileReader;
import helpers.MyFileWriter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CityNotFoundException, IOException, WrongFileFormatException {

        List<String> cities = readCitiesFromFile(getFileNameFromUser());

        String json = getCurrentWeatherAndForecastJson(cities.get(0));

        saveForecastToFile(json, cities.get(0));

    }

    public static List<String> readCitiesFromFile(String filename) throws IOException, WrongFileFormatException {
        MyFileReader reader = new MyFileReader();

        return reader.readFile(filename);

    }

    public static void saveForecastToFile(String json, String city) throws IOException {
        MyFileWriter writer = new MyFileWriter();

        writer.writeToFile(json ,city);

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
    public static String getFileNameFromUser(){

        System.out.println("Enter File Name! ");

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
