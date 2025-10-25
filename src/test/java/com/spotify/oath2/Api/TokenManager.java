package com.spotify.oath2.Api;

import com.spotify.oath2.Utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oath2.Api.ApplicationApi.PlayListApi.postAccountToken;
import static com.spotify.oath2.Api.SpecBuilder.getRequestSpec;
import static com.spotify.oath2.Api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){
        try{
            if(access_token==null || Instant.now().isAfter(expiry_time)){
                Response response= renewToken();
                access_token= response.path("access_token");
                int expduration= response.path("expires_in");

                expiry_time= Instant.now().plusSeconds(expduration-300);
            }
            else{
                System.out.println("Token is good to resuse");
            }
            }

        catch (Exception e){
            throw new RuntimeException("TOKEN FAILED!!");
        }

        return access_token;



    }

    private static Response renewToken(){
        HashMap<String,String > map= new HashMap<>();
        map.put("client_id", ConfigLoader.getInstance().getClientId());
        map.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        map.put("grant_type",ConfigLoader.getInstance().getGrantType());
        map.put("redirect_uri",ConfigLoader.getInstance().getRedirectUri());
        map.put("code",ConfigLoader.getInstance().getCode());


        Response response= postAccountToken(map);

        if(response.statusCode()!=200){
            throw new RuntimeException("Token Failed!!");
        }
        return response;


    }
}
