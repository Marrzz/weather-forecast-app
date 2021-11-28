package api;

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import dto.CurrentWeatherData;
import dto.ThreeDayWeatherForecast;
import exception.CityNotFoundException;
import helpers.MyWebResource;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class WeatherApi{

    MyWebResource webResource = new MyWebResource();

    public CurrentWeatherData getCurrentWeatherData(String cityName) throws CityNotFoundException {

        WebResource response = webResource.currentWeatherDataFor(cityName);

        if (response.get(ClientResponse.class).getStatus() == HTTP_NOT_FOUND){
            throw new CityNotFoundException();
        }
        return new Gson().fromJson(response.get(String.class), CurrentWeatherData.class);
    }

    public ThreeDayWeatherForecast getThreeDayForecast(String cityName) {

        WebResource response = webResource.forecastDataFor(cityName);

        ThreeDayWeatherForecast weatherForecast = new Gson().fromJson(response.get(String.class), ThreeDayWeatherForecast.class);
        weatherForecast.setList(weatherForecast.getList().subList(1,4));

        return weatherForecast;

    }

}
