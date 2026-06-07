package dataandplot.util;

import dataandplot.PlotExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static final int X = 0;
    public static final int Y = 1;

    public static String formatNum(double num) {
        return String.format("%.1f", num);
    }

    public static Properties loadProperties(String fileName) {
        return loadProperties(fileName, Utils.class.getClassLoader());
    }

    public static Properties loadProperties(String fileName, ClassLoader classLoader) {
        Properties properties = new Properties();
        try (InputStream input = classLoader.getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Unable to find configuration file:" + fileName);
            }
            // Load the properties file
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return properties;
    }

    public static Properties loadProperties(final File file) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(file)) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return properties;
    }

}
