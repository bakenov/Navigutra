package imc.game.psr.game.model.event;

/**
 * The take input from player event
 * 
 * @author bakenov
 *
 */
public interface ITakeInputEvent {

	/**
	 * Returns the player name
	 * 
	 * @return the player name
	 */
	String getPlayerName();

	/**
	 * Returns the weapon selection event listener for callback.
	 * 
	 * @return the weapon selection event listener
	 */
	IGameEventListener getSelectionListener();

}
