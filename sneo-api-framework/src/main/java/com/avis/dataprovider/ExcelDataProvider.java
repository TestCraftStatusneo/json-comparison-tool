package com.avis.dataprovider;


import com.avis.datautils.DataConverter;
import com.avis.datautils.JacksonUtils;
import com.avis.datautils.ExcelUtils;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.avis.core.Configuration.*;


/**
 * Class contains all generic excel data providers
 *
 * @author ikumar
 */
public class ExcelDataProvider {

    public static final String EXCEL_DATA_AS_POJO = "dataAsPojo";
    public static final String EXCEL_DATA_AS_MAP = "dataAsMap";
    private static String[][] excelData;
    public static final String EXCEL_PATH = ENVIRONMENT + "_" + BRAND + "_" + SERVICE;

    static {
        excelData = ExcelUtils.getInstance().getAllSheetsData(EXCEL_PATH);
    }

    @SneakyThrows
    @DataProvider(name = EXCEL_DATA_AS_POJO)
    public Object[][] dataAsPojo(Method method) {
        List<String[]> testMethodList = DataConverter.getTestMethodDataAsList(excelData,method);
        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testMethodList);
        TestmethodData[][] data = JacksonUtils.convertTwoDMapToPOJO(testMethodMap, TestmethodData[][].class);
        return data;
    }

    @SneakyThrows
    @DataProvider(name = EXCEL_DATA_AS_MAP)
    public Object[][] dataAsMap(Method method) {
        List<String[]> testMethodList = DataConverter.getTestMethodDataAsList(excelData,method);
        Map<String, String>[][] testMethodMap = DataConverter.convertListToTwoDHashMap(testMethodList);
        return testMethodMap;
    }


}
