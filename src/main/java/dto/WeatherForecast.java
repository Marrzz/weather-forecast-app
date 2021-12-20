package dto;

import lombok.Data;

@Data
public class WeatherForecast {


    private String dt;
    private Temperature temp;
    private String pressure;
    private String humidity;

}
