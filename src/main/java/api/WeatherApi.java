package api;

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import dto.CurrentWeatherData;
import dto.ThreeDayWeatherForecast;
import dto.WeatherForecast;
import exception.CityNotFoundException;
import helpers.MyWebResource;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class WeatherApi{

    MyWebResource webResource = new MyWebResource();

    public CurrentWeatherData getCurrentWeatherData(String cityName) throws CityNotFoundException {

        WebResource response = webResource.currentWeatherDataFor(cityName);

        return returnCurrentWeatherDataIfSuccessfulResponse(response);
    }

    public ThreeDayWeatherForecast getThreeDayForecast(String cityName) throws CityNotFoundException {

        WebResource apiResponse = webResource.forecastDataFor(cityName);

        return returnThreeDayWeatherForecastIfSuccessfulResponse(apiResponse);

    }

    private boolean responseCodeIs404(WebResource response){
        return response.get(ClientResponse.class).getStatus() == HTTP_NOT_FOUND;
    }

    private ThreeDayWeatherForecast returnThreeDayWeatherForecastIfSuccessfulResponse(WebResource response) throws CityNotFoundException {

        if (responseCodeIs404(response)){
            throw new CityNotFoundException();
        }

        ThreeDayWeatherForecast weatherForecast = new Gson().fromJson(response.get(String.class), ThreeDayWeatherForecast.class);
        weatherForecast.setList(weatherForecast.getList().subList(1,4));

        return weatherForecast;
    }

    private CurrentWeatherData returnCurrentWeatherDataIfSuccessfulResponse(WebResource response) throws CityNotFoundException {
        if (responseCodeIs404(response)){
            throw new CityNotFoundException();
        }
        return new Gson().fromJson(response.get(String.class), CurrentWeatherData.class);
    }

}
