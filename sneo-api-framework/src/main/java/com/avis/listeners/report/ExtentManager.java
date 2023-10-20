package com.avis.listeners.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.avis.core.Configuration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *  ExtentManager for Extent Reporting
 *
 * @author ikumar
 */
public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {

        //String date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        //String fileName = "../avis-api-testsdata/extent-reports/" + "extent_" + date.replace(":", "_").replace("-", "_") + ".html";
        String reportFolder = Configuration.EXTENT_REPORT_FOLDER;
        String fileName = ".." + "extent_report.html";


        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("ENVIRONMENT", Configuration.ENVIRONMENT);
        extent.setSystemInfo("REST_BASE_URI", Configuration.REST_BASE_URI);
        extent.setSystemInfo("SOAP_BASE_URI", Configuration.SOAP_BASE_URI);

        return extent;
    }

    }

