package imc.game.psr.game.player;

/**
 * The game player builder contract
 * 
 * @author bakenov
 *
 */
public interface IPlayerBuilder {

	/**
	 * Builds the game player of the specified type
	 * 
	 * @param name the player's name
	 * @param type the player's type
	 * @return the game player
	 */
	IPlayer buildPlayer(String name, GamePlayerType type);
}
