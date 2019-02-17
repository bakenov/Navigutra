package imc.game.utils;

import static imc.game.psr.game.config.Parameters.MESSAGE;
import static imc.game.psr.game.config.Parameters.WEAPONS;
import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

/**
 * Tests for class {@link PropertyLoader}
 * 
 * @author Anvar Bakenov
 *
 */
public class PropertyLoaderTest {

	@Test
	public void testLoad() {
		Properties properties = PropertyLoader
				.loadProperties("testGame.properties");
		assertEquals("Test message",
				properties.getProperty(MESSAGE.getQualifiedName()));
		assertEquals("'P:paper,R:rock,S:scissors'",
				properties.getProperty(WEAPONS.getQualifiedName()));
	}
}
