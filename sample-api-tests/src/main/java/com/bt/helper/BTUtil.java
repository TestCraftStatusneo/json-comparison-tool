package com.bt.helper;

import com.avis.core.Configuration;
import com.avis.dataprovider.TestmethodData;
import com.google.gson.*;
import io.json.compare.JSONCompare;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log4j2
public class BTUtil {

    private static Map<String, String> DATES;
    public static String getErrorMessage(Response response) {
        if(true) return "";
        else return  response.path("errors[0].message").toString().trim();

    }


    public static Object setValueInJsonRecursively(Object object, String key, String value) {
        return setValueInJsonHelper(object, key, value);
    }

    private static Object setValueInJsonHelper(Object object, String key, String value){
        if(object instanceof JsonArray){
            ((JsonArray) object).forEach(obj ->{
                setValueInJsonHelper(obj, key, value);
            });
        }
        if(object instanceof JsonObject){
            if(((JsonObject) object).has(key)) ((JsonObject) object).addProperty(key, value);
            else {
                Set<String> children = ((JsonObject) object).keySet();
                children.forEach(obj ->{
                    setValueInJsonHelper(((JsonObject) object).get(obj), key, value);
                });
            }
        }
        return object;
    }

    public static JsonElement setJsonValueInPath(JsonElement json, String path, String value){

        String[] parts = path.split("\\.|\\[|\\]");
        JsonElement result = json;
        String key;

        for (int i = 0; i < parts.length; i++) {
            key = parts[i];
            key = key.trim();
            if (key.isEmpty())
                continue;

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
                    int ix = Integer.parseInt(key);
                    result = ((JsonArray) result).get(ix);
                }
            }
            else break;
        }

        return json;
    }

    public static JsonElement setJsonValueInPathMultiple(JsonElement json, String pathsString, String valuesString){
        String[] paths = pathsString.split(",");
        String[] values = valuesString.split(",");
        for (int i = 0; i < paths.length; i++) {
            setJsonValueInPath(json, paths[i], values[i]);
        }
        return json;
    }

    public static String getJsonForTestMethod(TestmethodData data){
//        String jsonStr = FileReader.readFile(System.getProperty("user.dir") + data.getParam1());
        JsonObject jsonObject = null;
        try {
            jsonObject = JsonParser.parseReader(new java.io.FileReader(System.getProperty("user.dir") + data.getParam1())).getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        setJsonValueInPath(jsonObject, data.getParam2(), data.getParam3());
        if(!data.getParam5().equalsIgnoreCase("non-default-date-time"))
            setDates(jsonObject, data);
//        String regex = String.format(, "PUD");

        return jsonObject.toString();

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

    //Json comparison method for json String or filepath as Input



    private static void setDates(JsonObject jsonObject, TestmethodData data){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(11, 0);
        LocalDate defaultDate = date.plusDays(20);

        DATES =new HashMap<String, String>() {{
            put("pud_json", defaultDate.toString());
            put("pud_imf", defaultDate.format(DateTimeFormatter.ofPattern("ddMMMyy")));

            if(Configuration.BRAND.equalsIgnoreCase("PAYLESS")){
                put("dod_json", defaultDate.toString());
                put("dod_imf", defaultDate.format(DateTimeFormatter.ofPattern("ddMMMyy")));
            }
            else {
                put("dod_json", defaultDate.plusDays(1).toString());
                put("dod_imf", defaultDate.plusDays(1).format(DateTimeFormatter.ofPattern("ddMMMyy")));
            }

            put("put_json", time.format(DateTimeFormatter.ofPattern("HHmm")));
            put("put_imf", time.format(DateTimeFormatter.ofPattern("HHmm")));
            put("dot_json", time.plusHours(4).format(DateTimeFormatter.ofPattern("HHmm")));
            put("dot_imf", time.plusHours(4).format(DateTimeFormatter.ofPattern("HHmm")));

        }};

        if(data.getParam5().equalsIgnoreCase("date-time")) {
            switch (data.getParam2()) {
                case "drop_off_date_time.date":
                    LocalDate og_dod_json = LocalDate.parse(DATES.get("dod_json"));
                    DATES.put("dod_json", og_dod_json.plusDays(Long.parseLong(data.getParam3())).toString());
                    DATES.put("dod_imf", og_dod_json.plusDays(Long.parseLong(data.getParam3())).format(DateTimeFormatter.ofPattern("ddMMMyy")));
                    break;

                case "pick_up_date_time.date":
                    DATES.put("pud_json", date.plusDays(Long.parseLong(data.getParam3())).toString());
                    DATES.put("pud_imf", date.plusDays(Long.parseLong(data.getParam3())).format(DateTimeFormatter.ofPattern("ddMMMyy")));
                    break;

                case "drop_off_date_time.time":
                    DATES.put("dod_json", DATES.get("pud_json"));
                    DATES.put("dot_json", time.plusHours(Long.parseLong(data.getParam3())).format(DateTimeFormatter.ofPattern("HHmm")));
                    DATES.put("put_imf", DATES.get("dot_json"));
                    break;

                case "off_hours":
                    DATES.put("dod_json", DATES.get("pud_json"));
                    DATES.put("put_json", time.plusHours(Long.parseLong(data.getParam3())).format(DateTimeFormatter.ofPattern("HHmm")));
                    DATES.put("put_imf", DATES.get("dot_json"));
                    break;
            }
        }

        BTConstants.IMF_REQUEST = BTConstants.IMF_REQUEST.replaceFirst("\\/PUD([^\\\\]*)\\\\", "/PUD" + DATES.get("pud_imf") + "/" + DATES.get("put_imf") + "\\\\");
        BTConstants.IMF_REQUEST = BTConstants.IMF_REQUEST.replaceFirst("\\/DOD([^\\\\]*)\\\\", "/DOD" + DATES.get("dod_imf") + "/" + DATES.get("dot_imf") + "\\\\");
        setJsonValueInPath(jsonObject, "pick_up_date_time.date", DATES.get("pud_json"));
        setJsonValueInPath(jsonObject, "drop_off_date_time.date", DATES.get("dod_json"));
        setJsonValueInPath(jsonObject, "pick_up_date_time.time", DATES.get("put_json"));
        setJsonValueInPath(jsonObject, "drop_off_date_time.time", DATES.get("dot_json"));


    }




    public static File getJsonSchemaFromFile(String filePath){
        return new File(System.getProperty("user.dir") + filePath);
    }
}
