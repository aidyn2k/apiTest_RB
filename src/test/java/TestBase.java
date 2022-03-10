import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TestBase {

    @BeforeAll
    public static void mainHost() {
        RestAssured.baseURI = "https://gateway.choco.kz";
    }

    public static String getAuthToken() {

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("login", "adilkhan.a@rahmetapp.kz")
                .formParam("password", "razdvatri")
                .formParam("client_id", "34958380")
                .formParam("grant_type", "password")
                .when()
                .post("https://gateway.choco.kz/auth/token")
                .then()
                .extract()
                .response();

        return response.path("data.token");
    }
}
