package imc.game.psr.game.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Test;

import imc.game.utils.PropertyLoader;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.chars.CharSet;

/**
 * Tests for the {@link GameConfig} class.
 *
 * @author bakenov
 *
 */
public class GameConfigTest {

	private Properties loadProperties() {
		return PropertyLoader.loadProperties("testGame.properties");
	}

	@Test
	public void testLoad() {
		Properties properties = loadProperties();
		GameConfig config = new GameConfig();
		config.updateFromProperties(properties);

		assertEquals("Test message", config.getConsoleMessage());

		Char2ObjectMap<String> symbolToNameMap = config.getSymbolToNameMap();

		assertEquals(3, symbolToNameMap.size());
		CharSet chars = symbolToNameMap.keySet();
		CharIterator iter = chars.iterator();
		while (iter.hasNext()) {
			char c = iter.nextChar();
			String s = symbolToNameMap.get(c);
			switch (c) {
			case 'P':
				assertEquals("paper", s);
				break;
			case 'R':
				assertEquals("rock", s);
				break;
			case 'S':
				assertEquals("scissors", s);
				break;
			default:
				fail("should not be here");
			}
		}
	}
}
