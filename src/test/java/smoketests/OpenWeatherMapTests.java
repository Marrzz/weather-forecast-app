package smoketests;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class OpenWeatherMapTests {

    private static final String APPID = "a5c83d3b6e9960e717fd2a602e64b99b";
    private static final String PREM = "c0c4a4b4047b97ebc5948ac9c48c0559";
    private static final String BASEURL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST = "https://api.openweathermap.org/data/2.5/forecast/daily";

    @Test
    public void whenCalledWithoutApi_ReturnsHttpUnauthorized(){
        given()
                .when()
                .get(BASEURL)
                .then()
                .statusCode(401);
    }
    @Test
    public void shouldReturnHttpOK_whenCityNameIsGiven(){
        given()
                .queryParam("q", "Tallinn")
                .queryParam("appid", APPID)
                .queryParam("units", "metric")
                .when()
                .get(BASEURL)
                .then()
                .statusCode(200);
    }

    @Test
    public void ShouldReturnCiryname_whenCityNameIsGiven(){
        given()
                .queryParam("q", "Tallinn")
                .queryParam("appid", APPID)
                .queryParam("units", "metric")
                .when()
                .get(BASEURL)
                .then()
                .body("$",hasKey("name"))
                .body("name", equalTo("Tallinn"));
    }

    @Test
    public void shouldHaveCoordinatesBlock_whenCityNamesIsGiven(){
        given()
                .queryParam("q", "Tallinn")
                .queryParam("appid", APPID)
                .queryParam("units", "metric")
                .when()
                .get(BASEURL)
                .then()
                .body("$",hasKey("coord"))
                .body("coord",hasKey("lat"))
                .body("coord",hasKey("lon"));
    }

    @Test
    public void shouldReturnHttpOK_WhenQueryingThreeDayForecast(){
        given()
                .queryParam("q", "Tartu")
                .queryParam("appid", PREM)
                .queryParam("units", "metric")
                .queryParam("cnt", "3")
                .when()
                .get(FORECAST)
                .then()
                .statusCode(200);
    }

    @Test
    public void hasFieldList_WhenQueryingThreeDayForecast(){
        given()
                .queryParam("q", "Tartu")
                .queryParam("appid", PREM)
                .queryParam("units", "metric")
                .queryParam("cnt", "3")
                .when()
                .get(FORECAST)
                .then()
                .body("", hasKey("list"));
    }

}
