package imc.game.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A property loader class
 * 
 * @author bakenov
 *
 */
public class PropertyLoader {

	private static final Logger log = LoggerFactory
			.getLogger(PropertyLoader.class);

	/**
	 * Loads the property data from specified configuration file
	 * 
	 * @param configFile a configuration file
	 * @return all properties loaded from the file
	 */
	public static Properties loadProperties(String configFile) {
		URL url = PropertyLoader.class.getResource("/" + configFile);
		if (url == null) {
			log.error("The specified file does not exist. file : {}", configFile);
			return null;
		}
		Properties prop = new Properties();
		try {
			prop.load(PropertyLoader.class.getClassLoader()
					.getResourceAsStream(configFile));
			return prop;
		} catch (IOException ex) {
			log.error("Error loading property file : {}", ex.getMessage());
			return null;
		}
	}
}
