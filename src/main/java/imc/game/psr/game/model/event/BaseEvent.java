package imc.game.psr.game.model.event;

/**
 * The base game event
 * 
 * @author bakenov
 *
 */
public class BaseEvent implements IGameEvent {

	private final EventType type;

	/**
	 * Constructor for Base event
	 * 
	 * @param type the event's type
	 */
	public BaseEvent(EventType type) {
		this.type = type;
	}

	@Override
	public EventType getType() {
		return type;
	}

}
