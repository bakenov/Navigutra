package imc.game.psr.game.config;

import java.util.Arrays;
import java.util.Properties;

import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;

/**
 * The game's configuration
 * 
 * @author bakenov
 *
 */
public class GameConfig implements IGameConfig {

	public static final char PROPERTY_SEPARATOR = '.';
	public static final String ITEM_SEPARATOR = "\\,";
	public static final String KEY_VALUE_SEPARATOR = "\\:";

	private String welcomeMessage;
	private final Char2ObjectMap<String> symbolToNameMap = new Char2ObjectOpenHashMap<>();

	/**
	 * @param param
	 * @param properties
	 */
	protected void processParam(Parameters param, Properties properties) {
		String value = properties.getProperty(param.getQualifiedName());
		if (value != null) {
			switch (param) {
			case MESSAGE:
				welcomeMessage = value;
				break;
			case WEAPONS:
				String[] parts = value.toString().split(ITEM_SEPARATOR);
				Arrays.stream(parts).forEach(this::processPair);
				break;
			default:
			}
		}
	}

	private void processPair(String pair) {
		String[] parts = pair.toString().split(KEY_VALUE_SEPARATOR);
		if (parts[0].length() == 1) {
			char symbol = parts[0].charAt(0);
			symbolToNameMap.put(symbol, parts[1]);
		}
	}

	@Override
	public String getConsoleMessage() {
		return welcomeMessage;
	}

	@Override
	public void updateFromProperties(Properties properties) {
		for (Parameters param : Parameters.values()) {
			processParam(param, properties);
		}
	}

	@Override
	public Char2ObjectMap<String> getSymbolToNameMap() {
		return symbolToNameMap;
	}
}
