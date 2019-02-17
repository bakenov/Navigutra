package imc.game.psr.game.config;

import java.util.Arrays;
import java.util.Properties;

import imc.game.psr.game.player.GamePlayerType;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.chars.CharSet;

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
	private int numberGames;
	private char quitCommand;
	private String firstPlayerName;
	private String secondPlayerName;
	private GamePlayerType firstPlayerType;
	private GamePlayerType secondPlayerType;

	/**
	 * Extracts the value of the configuration parameter from property file
	 * 
	 * @param param      the configuration parameter
	 * @param properties the properties
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
			case NUMBER_GAMES:
				numberGames = Integer.parseInt(value);
				break;
			case QUIT_COMMAND:
				quitCommand = value.charAt(0);
				break;
			case FIRST_PLAYER_NAME:
				firstPlayerName = value;
				break;
			case FIRST_PLAYER_TYPE:
				firstPlayerType = GamePlayerType.valueOf(value.toUpperCase());
				break;
			case SECOND_PLAYER_NAME:
				secondPlayerName = value;
				break;
			case SECOND_PLAYER_TYPE:
				secondPlayerType = GamePlayerType.valueOf(value.toUpperCase());
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

	@Override
	public int getNumberGames() {
		return numberGames;
	}

	@Override
	public char getQuitCommand() {
		return quitCommand;
	}

	@Override
	public CharSet getWeaponSymbolSet() {
		return symbolToNameMap.keySet();
	}

	@Override
	public String getFirstPlayerName() {
		return firstPlayerName;
	}

	@Override
	public GamePlayerType getFirstPlayerType() {
		return firstPlayerType;
	}

	@Override
	public String getSecondPlayerName() {
		return secondPlayerName;
	}

	@Override
	public GamePlayerType getSecondPlayerType() {
		return secondPlayerType;
	}
}
