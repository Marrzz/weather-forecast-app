package dto;

import lombok.Data;

@Data
public class WeatherForecast {


    String dt;
    Temperature temp;
    String pressure;
    String humidity;

}
