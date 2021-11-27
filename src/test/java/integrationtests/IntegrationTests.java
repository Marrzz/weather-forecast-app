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
import java.io.IOException;

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
    public void wrongFileFormatExceptionIsThrown_WhenWrongFileFormatIsGivenToGetCurrentWeatherAndForecastJson(){

        String fileName = "city.json";

        String errorMessage = "Wrong File Format, please use a txt file!";

        Exception exception = assertThrows(WrongFileFormatException.class, () ->{
            Main.readCitiesFromFile(fileName);
        });

        assertThat(exception.getMessage()).isEqualTo(errorMessage);

    }

    @Test
    public void noFileFoundExceptionIsThrown_WhenFileIsNotFound(){

        String fileLocation = "no file";

        String errorMessage = "File Not found! " +
                "Check the file name and extension, only txt files are accepted and the file must be in root folder!";

        Exception exception = assertThrows(FileNotFoundException.class, () ->{
            Main.readCitiesFromFile(fileLocation);
        });

        assertThat(exception.getMessage()).isEqualTo(errorMessage);

    }

    @Test
    public void cityNameFromFileProducesAJsonFileWithWeatherReport() throws IOException, CityNotFoundException {

        MyFileReader reader = new MyFileReader();

        String fileName = "Tallinn_" + java.time.LocalDate.now() + ".json";
        //Tallinn

        Main.saveForecastToFile(Main.getCurrentWeatherAndForecastJson("Tallinn"), "Tallinn");

        File file = new File(fileName);

        assertThat(file.exists()).isTrue();

        String forecastFileContents = reader.readForecastFile(fileName);

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
