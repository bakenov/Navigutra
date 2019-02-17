package imc.game.psr.game.player;

/**
 * The game player interface
 * 
 * @author bakenov
 *
 */
public interface IPlayer {

	/**
	 * Returns the name if the player
	 * 
	 * @return
	 */
	String getName();

	/**
	 * The player is asked to select the weapon
	 */
	void selectWeapon();
}
