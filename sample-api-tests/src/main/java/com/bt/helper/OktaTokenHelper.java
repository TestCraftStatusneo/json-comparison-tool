package com.bt.helper;

import static com.avis.datautils.PropertyUtils.getValue;
import static com.avis.specbuilder.AbstractBuilder.getResponseSpec;
import static com.avis.specbuilder.RestSpecBuilder.getRequestSpec;
import static io.restassured.RestAssured.given;
import java.util.Base64;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OktaTokenHelper {
        public static String OKTA_TOKEN = null;
        public static String OKTA_URL = getValue("OKTA_URL_" + getValue("environment"));
        public static String OKTA_CLIENT_ID = getValue("OKTA_CLIENT_ID_" + getValue("environment"));
        public static String OKTA_CLIENT_SECRET = getValue("OKTA_CLIENT_SECRET_" + getValue("environment"));


        public static void storeOktaToken() {
            if (OKTA_TOKEN == null) {
                OKTA_TOKEN =  getOktaToken(OKTA_URL, OKTA_CLIENT_ID, OKTA_CLIENT_SECRET).path("access_token");
            }
        }

        private static Response getOktaToken(String uri, String username, String password) {
            String encoding = Base64.getEncoder()
                    .encodeToString((username + ":" + password).getBytes());
            return given(getRequestSpec(uri)).header("Authorization", "Basic " + encoding).
                        header("Content-Type", ContentType.URLENC).header("Connection", "keep-alive")
                    .header("Accept", "application/json").header("Content-Type", "application/x-www-form-urlencoded")
                    .formParam("grant_type", "client_credentials").when().post().then().spec(getResponseSpec()).extract()
                    .response();
        }
}
