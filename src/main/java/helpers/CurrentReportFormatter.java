package helpers;

import exception.WrongInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentReportFormatter {


    public static String processInput(String input) throws WrongInputException {

        StringBuilder result = new StringBuilder();

        if (input.matches("^[a-zA-Z\\s]*$")){
            input = input.strip();

            String[] names = input.split(" ");

            for(String name: names){
                result.append(name.substring(0, 1).toUpperCase()).append(name.substring(1).toLowerCase()).append(" ");
            }

        }else{
            throw new WrongInputException();
        }

        return result.toString().strip();
    }

    public static String replaceFieldsInReport(String json){

        json = json.replaceAll("main", "currentWeatherReport")
                .replaceAll("name", "city").replaceFirst("\\{", "\"weatherReportDetails\": {")
                .replace("\"temperatureUnit\":\"Celsius\"","\"temperatureUnit\":\"Celsius\"}");

        json = deconstructCoordinates(json);

        json = roundOutTemperature(json);

        json = replaceNumericStringsWithNumbers(json);

        return json;
    }

    private static String deconstructCoordinates(String json){
        json = json.replaceAll("\"coord\":\\{\"lat\":","\"coordinates\": \"");

        json = json.replaceAll("\"lon\":", "");

        json = json.replaceFirst("}", "\"");

        Pattern p = Pattern.compile("(\\d+\\.+\\d+)");

        Matcher m = p.matcher(json);

        while(m.find()){
            json = json.replace(m.group(1), Double.toString(Math.round(Float.parseFloat(m.group(1))* 100) / 100.0));

        }

        return json;
    }

    private static String roundOutTemperature(String json){

        String temperature = findTemperatureFromJson(json);


        json = json.replace(String.format("\"%s\"",temperature), String.valueOf((int) Float.parseFloat(temperature)));
        return json;
    }

    private static String findTemperatureFromJson(String json){

        Pattern p = Pattern.compile("(\\d+\\.+\\d+)");

        Matcher m = p.matcher(json);

        m.find();

        m.find();

        m.find();

        return m.group(1);

    }

    private static String replaceNumericStringsWithNumbers(String json){

        Pattern p = Pattern.compile("\"([\\d]*)\"");
        Matcher m = p.matcher(json);

        while (m.find()){
            json = json.replace(String.format("\"%s\"", m.group(1)), m.group(1));

        }

        return json;
    }
}
