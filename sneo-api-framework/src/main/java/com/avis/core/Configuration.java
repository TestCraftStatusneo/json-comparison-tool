package com.avis.core;

import lombok.extern.log4j.Log4j2;
import org.testng.xml.XmlTest;

import java.util.Map;

import static com.avis.datautils.PropertyUtils.getValue;

/**
 * Class contains all the properties required to configure the environment variables
 *
 * @author ikumar
 */
@Log4j2
public class Configuration {
    public static String CSV_DATA_FOLDER;
    public static String EXCEL_DATA_FOLDER;
    public static String EXTENT_REPORT_FOLDER;
    /* Environment Configuration */
    public static String ENVIRONMENT;
    public static String REST_BASE_URI;
    public static String SOAP_BASE_URI;
    public static String SOAP_PATH = getValue("SOAP_PATH");
    public static String VERSION;
    public static String BRAND;
    public static String SERVICE;
    public static String SERVICE_URI;
    public static String SERVICE_IMF_URI;
    public static String IMF_REQUEST_FILE;



    public static void setTestNGParameters(XmlTest xmlTest) {
        Map<String, String> XML_PARAMS_MAP = xmlTest.getAllParameters();
        // Assigning all the XML parameters to the Base Class Global variables
        ENVIRONMENT = XML_PARAMS_MAP.get("environment") != null ? XML_PARAMS_MAP.get("environment") : getValue("environment");
        VERSION = XML_PARAMS_MAP.get("version") != null ? XML_PARAMS_MAP.get("version") : getValue("version");
        EXCEL_DATA_FOLDER = XML_PARAMS_MAP.get("excel_data_folder") != null ? XML_PARAMS_MAP.get("excel_data_folder") : getValue("excel_data_folder");
        CSV_DATA_FOLDER = XML_PARAMS_MAP.get("csv_data_folder") != null ? XML_PARAMS_MAP.get("csv_data_folder") : getValue("csv_data_folder");
        EXTENT_REPORT_FOLDER = XML_PARAMS_MAP.get("extent_report_folder") != null ? XML_PARAMS_MAP.get("extent_report_folder") : getValue("extent_report_folder");
        ENVIRONMENT = ENVIRONMENT.toUpperCase();
        VERSION = VERSION.toUpperCase();
        BRAND = XML_PARAMS_MAP.get("brand") != null ? XML_PARAMS_MAP.get("brand") : getValue("brand");
        SERVICE = XML_PARAMS_MAP.get("service") != null ? XML_PARAMS_MAP.get("service") : getValue("service");
        SERVICE_URI = getValue(ENVIRONMENT + "_" + BRAND + "_" + SERVICE + "_URI");
        SERVICE_IMF_URI = getValue(ENVIRONMENT + "_" + BRAND + "_" + SERVICE + "_IMF_URI");
        IMF_REQUEST_FILE = getValue(ENVIRONMENT + "_" + BRAND + "_" + SERVICE + "_IMF_REQ_FILE");
    }

    public static void setURL() {

        REST_BASE_URI = getValue(ENVIRONMENT + "_REST_BASE_URI");
        SOAP_BASE_URI = getValue(ENVIRONMENT + "_SOAP_BASE_URI");
    }
}
