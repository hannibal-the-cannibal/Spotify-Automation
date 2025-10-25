package com.spotify.oath2.Tests;

import com.spotify.oath2.Utils.DataLoader;
import com.spotify.oath2.pojo.Error;
import com.spotify.oath2.pojo.Playlist;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oath2.Api.ApplicationApi.PlayListApi.postCall;
import static com.spotify.oath2.Api.SpecBuilder.getRequestSpec;
import static com.spotify.oath2.Api.TokenManager.getToken;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Playlists")
public class PlaylistTests {

    //String access_token= "BQAmz820Yr5sX0JR5Tsj6X5du0tg91GTwYdz1oDPYbTA7ZfRJv-x1MeD0Pg15gYkv4clg39Iu951SQescIQ5p4tWRVs_lAeF_xILjveZyKNvOD4a9YxMArLYCR5aT9vennCrt11t7w-NldJ9lKgo8jQDG2y9sH67rgPM41NS0HSZToLsOhVeMJn2P_7ZUmwDlaEElBQbdrDIAwClqGpdu7tQ21bCSMF0ZC5OL4xAem7mggqmjkRJxtx5iCiexKiOTWTWwil821nYyIF1_jdmuBr9Y058EA4-3OAnfKD1h6j6iN-uoeRf7Hp_FCgbTLJm";

    String access_token= "123";


    @BeforeClass
    public void beforeclass(){
        RestAssured.baseURI="https://api.spotify.com";
    }


    @Description("This is desc from Allure")
    @Story("This is ADO 112211 Story")
    @Test(description = "User creates a Playlist")
    public void createplaylist() {
        Playlist reqPlaylist = new Playlist();
        reqPlaylist.setName("New Pojo Playlist");
        reqPlaylist.setDescription("New POJO DESC");
        reqPlaylist.setPublic(false);

//        String payload="{\n" +
//                "    \"name\": \"New Playlist from Rest\",\n" +
//                "    \"description\": \"New playlist description\",\n" +
//                "    \"public\": false\n" +
//                "}";


        Response response=postCall(reqPlaylist);
        assertThat(response.statusCode(), equalTo(201));

        Playlist responseplaylist= response.as(Playlist.class);

//        Playlist responseplaylist = given(getRequestSpec())
//                .body(reqPlaylist)
//                .when()
//                .post("users/315y5retr5kviil3j4kw52s7hfju/playlists")
//                .then()
//                .log().all()
//                .assertThat().statusCode(201)
//                .extract().response()
//                .as(Playlist.class);
//
        // assertion using JAVA object
        assertThat(responseplaylist.getName(), equalTo(reqPlaylist.getName()));

    }

    @Test
    public void getplaylist(){
        given()
                .header("Authorization","Bearer "+access_token)
                .header("Content-Type","application/json")
                .basePath("/v1")
                .when()
                .get("playlists/"+ DataLoader.getInstance().getplaylistid())
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void updateplaylist(){
        Playlist reqPlaylist = new Playlist();
        reqPlaylist.setName("New Pojo Playlist");
        reqPlaylist.setDescription("New POJO DESC");
        reqPlaylist.setPublic(false);

//        String payload="{\n" +
//                "    \"name\": \"New Playlist from Rest\",\n" +
//                "    \"description\": \"New playlist description\",\n" +
//                "    \"public\": false\n" +
//                "}";
        given()
                .header("Authorization","Bearer "+access_token)
                .header("Content-Type","application/json")
                .basePath("/v1")
                .body(reqPlaylist)
                .when()
                .put("playlists/"+ DataLoader.getInstance().updateplaylistid())
                .then()
                .log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void createplaylistwithoutname(){
        Playlist reqPlaylist = new Playlist();
        reqPlaylist.setName("");
        reqPlaylist.setDescription("New POJO DESC");
        reqPlaylist.setPublic(false);

//        String payload="{\n" +
//                "    \"name\": \"\",\n" +
//                "    \"description\": \"New playlist description\",\n" +
//                "    \"public\": false\n" +
//                "}";


        Error error= given()
                .header("Authorization","Bearer "+access_token)
                .header("Content-Type","application/json")
                .basePath("/v1")
                .body(reqPlaylist)
                .when()
                .post("users/315y5retr5kviil3j4kw52s7hfju/playlists")
                .then()
                .log().all()
                .assertThat().statusCode(400)
                .extract()
                .as(Error.class);

        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

    }
}
