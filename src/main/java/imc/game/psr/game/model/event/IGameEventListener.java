package imc.game.psr.game.model.event;

/**
 * The listener for the game events
 * 
 * @author bakenov
 *
 */
public interface IGameEventListener {

	/**
	 * Called when the new game event occured
	 * 
	 * @param event the game event
	 */
	void onGameEvent(IGameEvent event);
}
