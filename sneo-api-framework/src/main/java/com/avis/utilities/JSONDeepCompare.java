package com.avis.utilities;

import com.google.gson.*;
import io.json.compare.CompareMode;
import io.json.compare.JSONCompare;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class JSONDeepCompare{

    //Compare json string directly or derived from a filepath
    /**
     * @param isFilePath
     * @param actual
     * @param expected
     * @param ignoreKeys
     */
    public static void jsonCompare(boolean isFilePath, String actual, String expected, String... ignoreKeys){
        if(isFilePath){
            actual = readJSON(actual);
            expected = readJSON(expected);
        }
        JsonObject expectedJsonObj;

        expectedJsonObj = JsonParser.parseString(expected).getAsJsonObject();

        for(String key : ignoreKeys) {
            String value = JsonPath.from(expected).getString(key);
            setJsonValueInPath(expectedJsonObj, key, "!"+ value);
        }

        CompareMode arrayFix = CompareMode.JSON_ARRAY_STRICT_ORDER;
        if(arrayFix != null) {
            JSONCompare.assertMatches(expectedJsonObj.toString(), actual, new HashSet<>(Arrays.asList(CompareMode.REGEX_DISABLED, CompareMode.JSON_ARRAY_NON_EXTENSIBLE, CompareMode.JSON_OBJECT_NON_EXTENSIBLE, arrayFix)));
        }
        else{
            JSONCompare.assertMatches(expectedJsonObj.toString(), actual, new HashSet<>(Arrays.asList(CompareMode.REGEX_DISABLED, CompareMode.JSON_ARRAY_NON_EXTENSIBLE, CompareMode.JSON_OBJECT_NON_EXTENSIBLE)));
        }
    }


    public static void jsonCompare(Response actualResponseObj, Response expectedResponseObj, String... ignoreKeys){
        String actualResponse = actualResponseObj.getBody().asString();
        String expectedResponse = expectedResponseObj.getBody().asString();
        jsonCompare(false, expectedResponse, actualResponse, ignoreKeys);
    }

    public static String readJSON(String filePath){
        JsonObject jsonObject = null;
        try {
            jsonObject = JsonParser.parseReader(new java.io.FileReader(System.getProperty("user.dir") + filePath)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();

    }

    public static JsonElement setJsonValueInPath(JsonElement json, String path, String value){

        String[] parts = path.split("\\.|\\[|\\]");
        JsonElement result = json;
        String key;

        for (int i = 0; i < parts.length; i++) {
            key = parts[i];
            key = key.trim();
//            if (key.isEmpty())
//                continue;

            if (result == null){
                result = JsonNull.INSTANCE;
                break;
            }

            if (result.isJsonObject()){
                if(i == parts.length -1) ((JsonObject)result).addProperty(key, value);
                else result = ((JsonObject)result).get(key);
            }
            else if (result.isJsonArray()){
                if(i == parts.length -1) ((JsonObject)result).addProperty(key, value);
                else {
                    int ix = Integer.parseInt(key) - 1;
                    result = ((JsonArray) result).get(ix);
                }
            }
            else break;
        }

        return json;
    }
}
