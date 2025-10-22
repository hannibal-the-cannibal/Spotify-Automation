package Spotify.oath2.Tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    String access_token= "BQDW5yOSaKJohzyikX8sSl3cGCvnV-UloAVe6mwHJZW2ncXTkwmUuBMwjo9Wc8PgtkNeEhisUqxinYC-QDqTxDRmU8OtZTmLEfVTWflx7GXi4otUn4FSZQ9tgEL-EeRpmtldD8npRjcZ7gof55osU_81E3-3UEa7OqiLCz0geupov2bVkKdncOEbTBGE8mDsebsUjEBVd55zZZVkWL-XANpQuc0wqBmXP0smKUeh6u21JoZ-Q7VJlm44POGhGe_hO9smRXTaI9Me-auEmOPX25zhFHyuYqXdLzy_TTnO9r03Vnv-jNopAz40f2nR0XRO";

    @BeforeClass
    public void beforeclass(){
        RestAssured.baseURI="https://api.spotify.com";
    }

    @Test
    public void createplaylist(){
        String payload="{\n" +
                "    \"name\": \"New Playlist from Rest\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .header("Authorization","Bearer "+access_token)
                .header("Content-Type","application/json")
                .basePath("/v1")
                .body(payload)
                .when()
                .post("users/315y5retr5kviil3j4kw52s7hfju/playlists")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .body("name", equalTo("New Playlist from Rest"));
    }

    @Test
    public void getplaylist(){
        given()
                .header("Authorization","Bearer "+access_token)
                .header("Content-Type","application/json")
                .basePath("/v1")
                .when()
                .get("playlists/5lpGfraFPwkikYrFrQZDLE")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void updateplaylist(){
        String payload="{\n" +
                "    \"name\": \"New Playlist from Rest\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .header("Authorization","Bearer "+access_token)
                .header("Content-Type","application/json")
                .basePath("/v1")
                .body(payload)
                .when()
                .put("playlists/4t2dKafOZn6niprNGqEgit")
                .then()
                .log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void createplaylistwithoutname(){
        String payload="{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .header("Authorization","Bearer "+access_token)
                .header("Content-Type","application/json")
                .basePath("/v1")
                .body(payload)
                .when()
                .post("users/315y5retr5kviil3j4kw52s7hfju/playlists")
                .then()
                .log().all()
                .assertThat().statusCode(400)
                .body("error.message", equalTo("Missing required field: name"));
    }
}
