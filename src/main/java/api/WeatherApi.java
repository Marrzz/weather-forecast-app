package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import dto.CurrentWeatherData;
import dto.WeatherForecast;
import exception.CityNotFoundException;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import  static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class WeatherApi{

    private WebResource response;

    public CurrentWeatherData getCurrentWeatherData(String cityName) throws CityNotFoundException, JsonProcessingException {


        WebResource response = getConfiguredClient().resource("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("appid","a5c83d3b6e9960e717fd2a602e64b99b")
                .queryParam("q", cityName)
                .queryParam("units", "metric");


        if (response.get(ClientResponse.class).getStatus() == HTTP_NOT_FOUND){
            throw new CityNotFoundException();
        }
        return new Gson().fromJson(response.get(String.class), CurrentWeatherData.class);
    }

    public WeatherForecast getFiveDayForecast(String cityName){

        WebResource response = getConfiguredClient().resource("https://api.openweathermap.org/data/2.5/forecast")
                .queryParam("appid","a5c83d3b6e9960e717fd2a602e64b99b")
                .queryParam("q", cityName)
                .queryParam("units", "metric");

        return new Gson().fromJson(response.get(String.class), WeatherForecast.class);

    }

    private static Client getConfiguredClient(){
        ClientConfig config = new DefaultClientConfig();

        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(FEATURE_POJO_MAPPING, true);
        return create(config);
    }

}
