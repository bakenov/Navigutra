package imc.game.psr.game.model.event;

/**
 * The generic game event interface
 * 
 * @author bakenov
 *
 */
public interface IGameEvent {

	/**
	 * Returns the type of the event
	 * 
	 * @return the type of the event
	 */
	EventType getType();

}
