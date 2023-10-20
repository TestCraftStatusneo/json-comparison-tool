package com.avis.specbuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static com.avis.core.Configuration.SOAP_BASE_URI;

/**
 * Class contains SOAP RequestSpecification and ResponseSpecification
 *
 * @author ikumar
 */
@Log4j2
public class SoapSpecBuilder extends AbstractBuilder {

    public static RequestSpecification getRequestSpec() {
        log.info("=====================REQUEST=====================");
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));

        return new RequestSpecBuilder().
                setBaseUri(SOAP_BASE_URI).
                setContentType(ContentType.XML).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }

    public static RequestSpecification getRequestSpec(String baseURI) {
        log.info("=====================REQUEST=====================");
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));

        return new RequestSpecBuilder().
                setBaseUri(baseURI).
                setContentType(ContentType.XML).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }



}
