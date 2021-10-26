package smoketests;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class OpenWeatherMapTests {

    private static final String APPID = "a5c83d3b6e9960e717fd2a602e64b99b";
    private static final String BASEURL = "https://api.openweathermap.org/data/2.5/weather";

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
}
