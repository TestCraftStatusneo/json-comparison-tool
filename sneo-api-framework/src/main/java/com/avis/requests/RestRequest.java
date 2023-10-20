package com.avis.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static com.avis.specbuilder.RestSpecBuilder.getRequestSpec;
import static com.avis.specbuilder.RestSpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;

/**
 * Class contains common REST Request Methods
 *
 * @author ikumar
 */

public class RestRequest {

    public static Response get(String path) {
        return given(getRequestSpec()).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, Map headerMap) {
        return given(getRequestSpec()).
                headers(headerMap).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, Map paramsMap, Map headerMap) {
        return given(getRequestSpec()).
                params(paramsMap).
                headers(headerMap).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String path, Object requestBody) {
        return given(getRequestSpec()).
                body(requestBody).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, Object requestBody) {
        return given(getRequestSpec()).
                body(requestBody).
                when().put(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response delete(String path) {
        return given(getRequestSpec()).
                when().delete(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
}
