package integrationtests;

import static com.google.common.truth.Truth.assertThat;
import com.fasterxml.jackson.core.JsonProcessingException;
import exception.CityNotFoundException;
import main.Main;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class IntegrationTests {

    @Test
    public void mainDetailsObjectContainsSpecifiedFields() throws JsonProcessingException, CityNotFoundException {

        String currentWeather = Main.getWeatherData("tallinn");

        assertThat(currentWeather).contains("coordinates");
        assertThat(currentWeather).contains("city");
        assertThat(currentWeather).contains("temperatureUnit");

    }


    @Test
    public void outputContainsSpecifiedFields(){

        String cityName = "Tallinn";

        String output = Main.getWeatherReport(cityName);

        int dateCount = StringUtils.countMatches(output,"date");
        int tempCount = StringUtils.countMatches(output,"temperature");
        int humidityCount = StringUtils.countMatches(output,"humidity");
        int pressureCount = StringUtils.countMatches(output,"pressure");

        assertThat(dateCount).isEqualTo(4);
        assertThat(tempCount).isEqualTo(4);
        assertThat(humidityCount).isEqualTo(4);
        assertThat(pressureCount).isEqualTo(4);

        assertThat(output).contains("weatherReportDetails");
        assertThat(output).contains("currentWeatherReport");
        assertThat(output).contains("forecastReport");

        assertThat(output).doesNotContain("null");


    }
}
