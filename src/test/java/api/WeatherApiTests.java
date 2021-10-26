package api;

import api.CurrentWeatherData;
import api.WeatherApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import exception.CityNotFoundException;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

public class WeatherApiTests {

    WeatherApi api = new WeatherApi();

    @Test
    public void shouldReturnCityNameInCurrentWeatherData_whenCorrectCityNameIsGiven() throws CityNotFoundException, JsonProcessingException {
        String cityName = "Tartu";

        CurrentWeatherData weatherData = api.getCurrentWeatherData(cityName);

        assertThat(weatherData.getName()).isEqualTo(cityName);
    }

    @Test
    public void shouldReturnErrorMessage_whenCityNotFound(){
        String cityName = "0127401274012";

        String errorMessage = "City not found!";

        Exception exception = assertThrows(CityNotFoundException.class, () ->{
            api.getCurrentWeatherData(cityName);
        });

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }
}
