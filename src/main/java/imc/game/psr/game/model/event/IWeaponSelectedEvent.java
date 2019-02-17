package imc.game.psr.game.model.event;

/**
 * The weapon selected event by the player
 * 
 * @author bakenov
 *
 */
public interface IWeaponSelectedEvent extends IGameEvent {

	/**
	 * Returns the selected weapon
	 * 
	 * @return the selected weapon
	 */
	char getWeaponSymbol();

	/**
	 * The player's name
	 * 
	 * @return the player's name
	 */
	String getPlayerName();

}
