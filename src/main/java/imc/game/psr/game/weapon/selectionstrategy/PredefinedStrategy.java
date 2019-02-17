package imc.game.psr.game.weapon.selectionstrategy;

import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.model.event.WeaponSelectedEvent;

/**
 * The predefined selection strategy.
 * 
 * @author bakenov
 */
public class PredefinedStrategy implements ISelectionStrategy {

	private final String playerName;
	private final IGameEventListener selectionWeaponListener;
	private char[] selections;
	private int index;

	/**
	 * Constructor for the selection strategy
	 * 
	 * @param options the list of all available weapons
	 */
	public PredefinedStrategy(String playerName,
			IGameEventListener selectionWeaponListener) {
		this.playerName = playerName;
		this.selections = new char[] { 'P', 'R', 'S' };
		this.selectionWeaponListener = selectionWeaponListener;
	}

	@Override
	public void selectNewWeapon() {
		WeaponSelectedEvent event = new WeaponSelectedEvent(playerName,
				getWeapon());
		selectionWeaponListener.onGameEvent(event);
	}

	private char getWeapon() {
		int idx = index % 3;
		index++;
		return selections[idx];
	}

}
