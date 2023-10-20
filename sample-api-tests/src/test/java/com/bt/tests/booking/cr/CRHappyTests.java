package com.bt.tests.booking.cr;

import com.avis.core.Configuration;
import com.avis.dataprovider.ExcelDataProvider;
import com.avis.dataprovider.TestmethodData;
import com.avis.requests.RestRequest;
import com.avis.utilities.JSONDeepCompare;
import com.bt.helper.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.StringEncoderComparator;
import org.mkfl3x.jsondelta.Feature;
import org.mkfl3x.jsondelta.JsonDelta;
import org.mkfl3x.jsondelta.JsonDeltaReport;
import org.testng.annotations.Test;

import static com.avis.dataprovider.ExcelDataProvider.EXCEL_DATA_AS_POJO;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@Log4j2
public class CRHappyTests extends TestBaseExtn {

    @Test(priority = 1, dataProvider = EXCEL_DATA_AS_POJO, dataProviderClass = ExcelDataProvider.class)
    public void CR_Service_Validate_Response_Mandatory_Fields(TestmethodData data){
        String inputJson1 = JSONDeepCompare.readJSON(data.getInputJson());
        response = RestRequest.post(Configuration.SERVICE_URI, inputJson1);
        response.then()
                .contentType(ContentType.JSON)
                .statusCode(BTConstants.AVL_SUCCESS);

        Response expected = response;

        String inputJson2 = JSONDeepCompare.readJSON(data.getInputJson());
        response = RestRequest.post(Configuration.SERVICE_URI, inputJson2);
        response.then()
                .contentType(ContentType.JSON)
                .statusCode(BTConstants.AVL_SUCCESS);

        Response actual = response;

        JSONDeepCompare.jsonCompare(expected , actual, "response_context.response_time", "confirmation_number");

    }@Test(priority = 1)
    public void CR_Service_Validate_Response_Mandatory_Fields2(){
//        String inputJson1 = JSONDeepCompare.readJSON(data.getInputJson());
//        response = RestRequest.post(Configuration.SERVICE_URI, inputJson1);
//        response.then()
//                .contentType(ContentType.JSON)
//                .statusCode(BTConstants.AVL_SUCCESS);
//
//        Response expected = response;
//
//        String inputJson2 = JSONDeepCompare.readJSON(data.getInputJson());
//        response = RestRequest.post(Configuration.SERVICE_URI, inputJson2);
//        response.then()
//                .contentType(ContentType.JSON)
//                .statusCode(BTConstants.AVL_SUCCESS);

        Response actual = response;

        String one = JSONDeepCompare.readJSON("/src/main/resources/requestJsons/Beta.json");
        String two = JSONDeepCompare.readJSON("/src/main/resources/requestJsons/Prod.json");

        JSONDeepCompare.jsonCompare(false, one , two);

//        JsonDelta jsonDelta = new JsonDelta();
//        JsonDeltaReport report = jsonDelta.compare(one, two); // comparison with excluded field "root.b.y"
//        System.out.println(report);

    }


}
