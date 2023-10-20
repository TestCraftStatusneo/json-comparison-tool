package com.avis.utilities;

import com.github.javafaker.Faker;

/**
 * Contains faker utility methods
 *
 * @author ikumar
 */

public class FakerUtils {

    public static String generateName() {
        Faker faker = new Faker();
        return "Name " + faker.regexify("[A-Za-z0-9 ,_-]{10}");
    }

    public static String generateDescription() {
        Faker faker = new Faker();
        return "Description " + faker.regexify("[ A-Za-z0-9_@./#&+-]{50}");
    }
}
