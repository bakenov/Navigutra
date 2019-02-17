package imc.game.psr.game.config;

import java.util.Properties;

import imc.game.psr.game.player.GamePlayerType;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.CharSet;

/**
 * The game configuration interface
 * 
 * @author bakenov
 *
 */
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
	 * Returns the set of the all available weapon symbols
	 * 
	 * @return the set of the all available weapon symbols
	 */
	CharSet getWeaponSymbolSet();

	/**
	 * Returns the number of the games
	 * 
	 * @return the number of the games
	 */
	int getNumberGames();

	/**
	 * Returns the command to quit the game
	 * 
	 * @return the command to quit the game
	 */
	char getQuitCommand();

	/**
	 * Sets the game's configuration parameters from loaded properties
	 * 
	 * @param properties a loaded game properties
	 */
	void updateFromProperties(Properties properties);

	/**
	 * Returns the name of the first player
	 * 
	 * @return the name of the first player
	 */
	String getFirstPlayerName();

	/**
	 * Returns the type of the first player
	 * 
	 * @return the type of the first player
	 */
	GamePlayerType getFirstPlayerType();

	/**
	 * Returns the name of the second player
	 * 
	 * @return the name of the second player
	 */
	String getSecondPlayerName();

	/**
	 * Returns the type of the second player
	 * 
	 * @return the type of the second player
	 */
	GamePlayerType getSecondPlayerType();
}
