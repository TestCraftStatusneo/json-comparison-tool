package com.bt.helper;

import com.avis.requests.RestRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.avis.specbuilder.AbstractBuilder.getResponseSpec;
import static com.avis.specbuilder.RestSpecBuilder.getRequestSpec;
import static io.restassured.RestAssured.given;

public class RestRequestExtn extends RestRequest {
    public static Response post(String path, Object requestBody, ContentType contentType) {
        RestAssured.useRelaxedHTTPSValidation();
        return given(getRequestSpec(path, contentType)).
                body(requestBody).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String path, Object requestBody) {
        RestAssured.useRelaxedHTTPSValidation();
        return given(getRequestSpec(path)).
                auth().oauth2(OktaTokenHelper.OKTA_TOKEN).
                body(requestBody).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
}
