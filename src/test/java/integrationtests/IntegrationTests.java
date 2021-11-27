package integrationtests;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import exception.WrongFileFormatException;
import com.fasterxml.jackson.core.JsonProcessingException;
import exception.CityNotFoundException;
import helpers.MyFileReader;
import main.Main;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class IntegrationTests {

    @Test
    public void mainDetailsObjectContainsSpecifiedFields() throws JsonProcessingException, CityNotFoundException {

        String currentWeather = Main.getCurrentWeatherReport("tallinn");

        assertThat(currentWeather).contains("coordinates");
        assertThat(currentWeather).contains("city");
        assertThat(currentWeather).contains("temperatureUnit");

    }


    @Test
    public void outputContainsSpecifiedFields() throws JsonProcessingException, CityNotFoundException {

        String cityName = "Tallinn";

        String output = Main.getCurrentWeatherAndForecastJson(cityName);

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

    @Test
    public void correctWeatherForecastGetsPrinted_WhenCityNameComesFromATextFile() throws JsonProcessingException, CityNotFoundException {

        String fileLocation ="src/main/java/files/city.txt";

        String json = Main.getCurrentWeatherAndForecastJson(fileLocation);

        int dateCount = StringUtils.countMatches(json,"date");
        int tempCount = StringUtils.countMatches(json,"temperature");
        int humidityCount = StringUtils.countMatches(json,"humidity");
        int pressureCount = StringUtils.countMatches(json,"pressure");

        assertThat(dateCount).isEqualTo(4);
        assertThat(tempCount).isEqualTo(4);
        assertThat(humidityCount).isEqualTo(4);
        assertThat(pressureCount).isEqualTo(4);

        assertThat(json).contains("weatherReportDetails");
        assertThat(json).contains("currentWeatherReport");
        assertThat(json).contains("forecastReport");

        assertThat(json).doesNotContain("null");

    }

    @Test
    public void wrongFileFormatExceptionIsThrown_WhenWrongFileFormatIsGivenToGetCurrentWeatherAndForecastJson(){

        String fileLocation = "src/main/java/files/city.json";

        String errorMessage = "Wrong File Format, please use a txt file!";

        Exception exception = assertThrows(WrongFileFormatException.class, () ->{
            Main.getCurrentWeatherAndForecastJson(fileLocation);
        });

        assertThat(exception.getMessage()).isEqualTo(errorMessage);

    }

    @Test
    public void noFileFoundExceptionIsThrown_WhenFileIsNotFound(){

        String fileLocation = "no file";

        String errorMessage = "File not found, please check file location!";

        Exception exception = assertThrows(FileNotFoundException.class, () ->{
            Main.getCurrentWeatherAndForecastJson(fileLocation);
        });

        assertThat(exception.getMessage()).isEqualTo(errorMessage);

    }

    @Test
    public void cityNameFromFileProducesAJsonFileWithWeatherReport(){

        MyFileReader reader = new MyFileReader();

        String fileLocation = "src/main/java/files/city.txt";

        Main.getCurrentWeatherAndForecastJson(fileLocation);

        File file = new File("src/main/java/files/forecast.json");

        assertThat(file.exists()).isTrue();

        String forecastFileContents = reader.readForecastFile();

        int dateCount = StringUtils.countMatches(forecastFileContents,"date");
        int tempCount = StringUtils.countMatches(forecastFileContents,"temperature");
        int humidityCount = StringUtils.countMatches(forecastFileContents,"humidity");
        int pressureCount = StringUtils.countMatches(forecastFileContents,"pressure");

        assertThat(dateCount).isEqualTo(4);
        assertThat(tempCount).isEqualTo(4);
        assertThat(humidityCount).isEqualTo(4);
        assertThat(pressureCount).isEqualTo(4);

        assertThat(forecastFileContents).contains("weatherReportDetails");
        assertThat(forecastFileContents).contains("currentWeatherReport");
        assertThat(forecastFileContents).contains("forecastReport");

        assertThat(forecastFileContents).doesNotContain("null");


    }


}
