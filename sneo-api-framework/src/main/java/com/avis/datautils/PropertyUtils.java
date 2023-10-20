package com.avis.datautils;

import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Class contains methods to load and read properties file
 *
 * @author ikumar
 */

@Log4j2
public class PropertyUtils {

    private static Properties prop;

    public static Properties getProp(String filepath) {
        if (prop == null) {
            prop = new Properties();
            InputStream input;
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                input = loader.getResourceAsStream(filepath);
                //input = new FileInputStream(new File("config.properties"));
                prop.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    /**
     * Initially checks for any environment variable. If null
     * Checks for the property value which is set either from the terminal/console. If null
     * Gets the default value from the config file
     *
     * @param propertyName Property Key set from Environment variables/Maven command line/config
     * @return propertyValue
     */
    public static String getValue(String propertyName) {
        String propertyValue = System.getenv(propertyName);
        log.debug("Environment variable for [" + propertyName + "] = [" + propertyValue + "]");

        if (Objects.isNull(propertyValue)) {
            propertyValue = Objects.isNull(System.getProperty(propertyName)) ? getProp("config.properties").getProperty(propertyName) : System.getProperty(propertyName);
        }
        log.info("[" + propertyName + "] value set to [" + propertyValue + "]");
        return propertyValue;
    }


}
