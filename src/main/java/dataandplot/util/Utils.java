package dataandplot.util;

import dataandplot.PlotExample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String formatNum(double num) {
        return String.format("%.1f", num);
    }

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = PlotExample.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                IO.println("unable to find configSine.properties");
                return null;
            }
            // Load the properties file
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
