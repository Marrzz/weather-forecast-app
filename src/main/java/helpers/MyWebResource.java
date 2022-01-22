package helpers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;

public class MyWebResource {

    String API_KEY = "YOUR API KEY HERE!!!";

    private Client getConfiguredClient(){
        ClientConfig config = new DefaultClientConfig();

        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(FEATURE_POJO_MAPPING, true);
        return create(config);
    }

    public WebResource getCurrentWeatherDataFor(String city){

        WebResource response = getConfiguredClient().resource("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("appid",API_KEY)
                .queryParam("q", city)
                .queryParam("units", "metric");

        return response;
    }

    public WebResource getForecastDataFor(String city){

        WebResource response = getConfiguredClient().resource("https://api.openweathermap.org/data/2.5/forecast/daily")
                .queryParam("q", city)
                .queryParam("cnt", "4")
                .queryParam("appid",API_KEY)
                .queryParam("units", "metric");

        return response;
    }
}
