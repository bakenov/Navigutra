package imc.game.psr.game.config;

import java.util.Properties;

import it.unimi.dsi.fastutil.chars.Char2ObjectMap;

public interface IGameConfig {

	/**
	 * Gets the console message for the player
	 * 
	 * @return the console message for the player
	 */
	String getConsoleMessage();

	/**
	 * Returns the map between the player's input command and weapon name
	 * 
	 * @return the map between the player's input command and weapon name
	 */
	Char2ObjectMap<String> getSymbolToNameMap();

	/**
	 * Sets the game's configuration parameters from loaded properties
	 * 
	 * @param properties a loaded game properties
	 */
	void updateFromProperties(Properties properties);
}
