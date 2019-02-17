package imc.game.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * A property loader class
 * 
 * @author bakenov
 *
 */
public class PropertyLoader {

	/**
	 * Loads the property data from specified configuration file
	 * 
	 * @param configFile a configuration file
	 * @return all properties loaded from the file
	 */
	public static Properties loadProperties(String configFile) {
		Properties prop = new Properties();
		try {
			prop.load(PropertyLoader.class.getClassLoader()
					.getResourceAsStream(configFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}
}
