package imc.game.psr.game.model.event;

/**
 * The take input event. This event is issued by the weapon selection strategy
 * and listener for the event is console input component
 * 
 * @author bakenov
 *
 */
public class TakeInputEvent extends BaseEvent implements ITakeInputEvent {

	private final IGameEventListener callback;
	private final String playerName;

	public TakeInputEvent(String playerName, IGameEventListener callback) {
		super(EventType.GET_INPUT);
		this.playerName = playerName;
		this.callback = callback;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public IGameEventListener getSelectionListener() {
		return callback;
	}

}
