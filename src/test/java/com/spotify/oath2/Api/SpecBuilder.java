package com.spotify.oath2.Api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    static String access_token= "BQAmz820Yr5sX0JR5Tsj6X5du0tg91GTwYdz1oDPYbTA7ZfRJv-x1MeD0Pg15gYkv4clg39Iu951SQescIQ5p4tWRVs_lAeF_xILjveZyKNvOD4a9YxMArLYCR5aT9vennCrt11t7w-NldJ9lKgo8jQDG2y9sH67rgPM41NS0HSZToLsOhVeMJn2P_7ZUmwDlaEElBQbdrDIAwClqGpdu7tQ21bCSMF0ZC5OL4xAem7mggqmjkRJxtx5iCiexKiOTWTWwil821nYyIF1_jdmuBr9Y058EA4-3OAnfKD1h6j6iN-uoeRf7Hp_FCgbTLJm";
    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer "+ access_token)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

    }

    public static ResponseSpecification getResponseSpec(){

        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

    }
}
