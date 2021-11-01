package dto;

import lombok.Data;
import java.util.Date;

@Data
public class WeatherForecast {


    String dt;
    Temperature temp;
    String pressure;
    String humidity;

}
