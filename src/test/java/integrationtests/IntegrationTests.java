package integrationtests;

import api.CurrentWeatherData;
import static com.google.common.truth.Truth.assertThat;
import com.fasterxml.jackson.core.JsonProcessingException;
import exception.CityNotFoundException;
import main.Main;
import org.junit.Test;

public class IntegrationTests {

    @Test
    public void mainDetailsObjectContainsSpecifiedFields() throws JsonProcessingException, CityNotFoundException {

        String currentWeather = Main.getWeatherData("tallinn");

        assertThat(currentWeather).contains("coordinates");
        assertThat(currentWeather).contains("city");
        assertThat(currentWeather).contains("temperatureUnit");

    }
}
