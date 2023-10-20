package com.avis.requests;

import com.avis.specbuilder.SoapSpecBuilder;
import io.restassured.response.Response;

import java.io.File;

import static com.avis.core.Configuration.SOAP_PATH;
import static io.restassured.RestAssured.given;

/**
 * Class contains common SOAP Request Methods
 *
 * @author ikumar
 */

public class SoapRequest {

    public static Response post(String requestBody) {
        return given(SoapSpecBuilder.getRequestSpec()).
                body(requestBody).
                when().post(SOAP_PATH).
                then().spec(SoapSpecBuilder.getResponseSpec()).
                extract().
                response();
    }

    public static Response post(File requestBody) {
        return given(SoapSpecBuilder.getRequestSpec()).
                body(requestBody).
                when().post(SOAP_PATH).
                then().spec(SoapSpecBuilder.getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String path,Object requestBody) {
        return given(SoapSpecBuilder.getRequestSpec()).
                body(requestBody).
                when().post(path).
                then().spec(SoapSpecBuilder.getResponseSpec()).
                extract().
                response();
    }

}
