package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ThreeDayWeatherForecast;
import dto.WeatherForecast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ForecastReportFormatter{

    private void formatDate(ThreeDayWeatherForecast forecast){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (WeatherForecast w : forecast.getList()){

            w.setDt(df.format(new Date(Long.parseLong(w.getDt()) * 1000)));

        }
    }

    private String formatStringsToNumbers(ThreeDayWeatherForecast forecast) throws JsonProcessingException {

        String json = new ObjectMapper().writeValueAsString(forecast);

        Pattern p1 = Pattern.compile("\\{\"max\":(.*?}),");
        Matcher m1 = p1.matcher(json);

        Pattern p = Pattern.compile("\"([\\d]*)\"");
        Matcher m = p.matcher(json);


        for (WeatherForecast w : forecast.getList()){
            int temp = (int) ((w.getTemp().getMax() + w.getTemp().getMin()) / 2);

            if (m1.find()){

                json = json.replace(m1.group(1),String.valueOf(temp))
                        .replace("\"temp\":{\"max\"", "\"temperature\"");

            }

        }


        while (m.find()){
            json = json.replace(String.format("\"%s\"", m.group(1)), m.group(1));
        }

        return json;
    }

    public String getFormattedForecast(ThreeDayWeatherForecast forecast) throws JsonProcessingException {



        formatDate(forecast);

        String forecastString = formatStringsToNumbers(forecast);


        forecastString = forecastString
                .replace("list", "forecastReport")
                .replace("dt", "date")
                .replaceAll("\\{", "");



        Pattern pattern = Pattern.compile("(\\d\",)");
        Matcher matcher = pattern.matcher(forecastString);
        while (matcher.find()){
            String foundSequence = matcher.group(1);
            forecastString = forecastString.replace(foundSequence, String.format("%s\"weather\":{", foundSequence));
        }

        forecastString = forecastString.replaceAll("\"date\"", "{\"date\"")
                .replaceAll("}","}}")
                .replace("]}", "]");

        return forecastString;
    }

}
