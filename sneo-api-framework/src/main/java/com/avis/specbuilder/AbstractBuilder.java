package com.avis.specbuilder;

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

/**
 * Class contains REST RequestSpecification and ResponseSpecification
 *
 * @author ikumar
 */

@Log4j2
public abstract class AbstractBuilder {


    @Getter @Setter
    protected static StringWriter requestWriter;
    protected static PrintStream requestCapture;


    public static ResponseSpecification getResponseSpec() {
        log.info("=====================RESPONSE=====================");
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
