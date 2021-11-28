package helpers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;

public class MyWebResource {

    private Client getConfiguredClient(){
        ClientConfig config = new DefaultClientConfig();

        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(FEATURE_POJO_MAPPING, true);
        return create(config);
    }

    public WebResource currentWeatherDataFor(String city){

        WebResource response = getConfiguredClient().resource("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("appid","a5c83d3b6e9960e717fd2a602e64b99b")
                .queryParam("q", city)
                .queryParam("units", "metric");

        return response;
    }

    public WebResource forecastDataFor(String city){

        WebResource response = getConfiguredClient().resource("https://api.openweathermap.org/data/2.5/forecast/daily")
                .queryParam("q", city)
                .queryParam("cnt", "4")
                .queryParam("appid","c0c4a4b4047b97ebc5948ac9c48c0559")
                .queryParam("units", "metric");

        return response;
    }
}
