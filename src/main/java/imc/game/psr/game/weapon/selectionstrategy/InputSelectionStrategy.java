package imc.game.psr.game.weapon.selectionstrategy;

import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.model.event.TakeInputEvent;
import imc.game.psr.game.weapon.IWeapon;

/**
 * The Input selection strategy sends weapon selection request and wait for the
 * request.
 * 
 * @author bakenov
 *
 * @param <W> a type of the selection
 */
public class InputSelectionStrategy<W extends IWeapon>
		implements ISelectionStrategy {

	private final IGameEventListener requestListener;
	private final IGameEventListener selectionWeaponListener;
	private final String name;

	/**
	 * Constructor for the selection strategy
	 * 
	 * @param name                    the player's name
	 * @param requestListener         the selection request listener (IO
	 *                                component)
	 * @param selectionWeaponListener the weapon selection listener (the model)
	 */
	public InputSelectionStrategy(String name, IGameEventListener requestListener,
			IGameEventListener selectionWeaponListener) {
		this.name = name;
		this.requestListener = requestListener;
		this.selectionWeaponListener = selectionWeaponListener;
	}

	@Override
	public void selectNewWeapon() {
		sendSelectionRequest();
	}

	private void sendSelectionRequest() {
		requestListener
				.onGameEvent(new TakeInputEvent(name, selectionWeaponListener));
	}

}
