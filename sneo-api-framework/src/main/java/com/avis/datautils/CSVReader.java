package com.avis.datautils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads CSV using OpenCSV CSVParser
 *
 * @author ikumar
 */

@Log4j2
public class CSVReader {

    private static Reader reader;
    private static List<String[]> list = new ArrayList<>();

    public static List readAllRows(String filepath) {

        try {

            reader = new BufferedReader(new FileReader("../bt-api-testsdata/resources/csv/"+filepath+".csv"));

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(false)
                    .build();

            com.opencsv.CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build();

            list = csvReader.readAll();
            reader.close();
            csvReader.close();

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while reading the file");
        }
        return list;
    }


}
