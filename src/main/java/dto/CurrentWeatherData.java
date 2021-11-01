package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {

    private String name;
    private Coordinates coord;
    private String temperatureUnit;
    private Weather main;

    public CurrentWeatherData() {
        this.temperatureUnit = "Celsius";

    }
}
