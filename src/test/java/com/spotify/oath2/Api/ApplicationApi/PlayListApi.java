package com.spotify.oath2.Api.ApplicationApi;

import com.spotify.oath2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oath2.Api.Route.*;
import static com.spotify.oath2.Api.SpecBuilder.getRequestSpec;
import static com.spotify.oath2.Api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class PlayListApi {

    public static Response postCall(Playlist reqPlaylist){
       return given(getRequestSpec())
                .body(reqPlaylist)
                .when()
                .post(Users+ "/315y5retr5kviil3j4kw52s7hfju/"+ Playlist)
                .then().spec(getResponseSpec())
                .extract().response();

    }

    public static Response getCall(String playListId){
        return given(getRequestSpec())
                .when().get("/playlists/"+ playListId)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response postAccountToken(HashMap<String,String> map){
       return given()
                .baseUri("https://accounts.spotify.com")
                .formParams(map)
                .contentType(ContentType.URLENC)
                .when()
                .post("/api/token")
                .then()
                .log().all()
                .spec(getResponseSpec())
                .extract().response();
    }
}
