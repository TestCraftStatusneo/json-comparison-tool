package com.avis.specbuilder;

import com.avis.listeners.report.ExtentListener;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static com.avis.core.Configuration.REST_BASE_URI;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;

/**
 * Class contains REST RequestSpecification and ResponseSpecification
 *
 * @author ikumar
 */

@Log4j2
public class RestSpecBuilder extends AbstractBuilder {


    public static RequestSpecification getRequestSpec() {
        log.info("=====================REQUEST=====================");
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));

        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri(REST_BASE_URI).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();

        return requestSpecification;
    }

    public static RequestSpecification getRequestSpec(String baseURI) {
        log.info("=====================REQUEST=====================");
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));


        return new RequestSpecBuilder().
                setBaseUri(baseURI).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }

    public static RequestSpecification getRequestSpec(String baseURI, ContentType contentType) {
        log.info("=====================REQUEST=====================");
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));

        return new RequestSpecBuilder().
                setBaseUri(baseURI).
                setContentType(contentType).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }
}
