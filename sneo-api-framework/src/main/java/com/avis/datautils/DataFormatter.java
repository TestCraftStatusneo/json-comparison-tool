package com.avis.datautils;

import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

/**
 * Contains data formatter methods
 *
 * @author ikumar
 */
@Log4j2
public class DataFormatter {

    public static String formatString(String dataString, String variable, String value){
        return	dataString.replaceAll(Pattern.quote("${"+variable+"}"), value);
    }


}

