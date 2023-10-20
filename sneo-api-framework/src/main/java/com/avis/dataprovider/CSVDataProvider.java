package com.avis.dataprovider;

import com.avis.datautils.DataConverter;
import com.avis.datautils.CSVReader;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import static com.avis.core.Configuration.ENVIRONMENT;
import static com.avis.core.Configuration.VERSION;


/**
 * Class contains all generic csv data providers
 *
 * @author ikumar
 */
public class CSVDataProvider {

    public static final String CSV_DATA_AS_STRING = "csvDataAsString";
    public static final String CSV_PATH = "partner_awd";
    private static List<String[]> methodData;


    static {
        methodData = CSVReader.readAllRows(CSV_PATH);
    }

    @DataProvider(name = CSV_DATA_AS_STRING)
    public Object[][] csvDataAsString(Method method) {
        List testMethodDataList = DataConverter.getTestMethodDataAsList(methodData, method);
        String[][] testMethodDataArr = DataConverter.convertListToTwoDArray(testMethodDataList);
        return testMethodDataArr;
    }


}
