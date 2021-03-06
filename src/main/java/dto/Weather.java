package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class Weather {

    private String date;
    private String temp;
    private String pressure;
    private String humidity;

    public Weather() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = dtf.format(LocalDateTime.now());
    }

}

