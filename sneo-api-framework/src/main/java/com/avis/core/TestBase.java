package com.avis.core;

import com.avis.listeners.report.ExtentListener;
import com.avis.listeners.report.ExtentManager;
import com.avis.specbuilder.AbstractBuilder;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlTest;


import java.lang.reflect.Method;


/**
 * Class needs to be extended by all Test classes
 *
 * @author ikumar
 */
@Listeners({ExtentListener.class})
@Log4j2
public class TestBase {

    protected Response response;

//    @BeforeTest(alwaysRun = true)
//    public void startTest(XmlTest xmlTest) {
//        Configuration.setTestNGParameters(xmlTest);
//        Configuration.setURL();
//        ExtentListener.extent = ExtentManager.createInstance();
//    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        log.info("Starting test: " + m.getName());
        log.info("Thread ID: " + Thread.currentThread().getId());
    }

    @AfterMethod
    public void afterMethod() {
        if (AbstractBuilder.getRequestWriter()!=null) {
            ExtentListener.logCodeBlock(AbstractBuilder.getRequestWriter().toString());
            AbstractBuilder.setRequestWriter(null);
        }
        if (response!=null) {
            ExtentListener.logCodeBlock(response.prettyPrint());
            response = null;
        }
    }

}
