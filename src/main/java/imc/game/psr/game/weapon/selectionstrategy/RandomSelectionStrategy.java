package imc.game.psr.game.weapon.selectionstrategy;

import java.util.Random;

import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.model.event.WeaponSelectedEvent;
import it.unimi.dsi.fastutil.chars.CharSet;

/**
 * The Random selection strategy selects randomly the weapon from list of the
 * available weapons.
 * 
 * @author bakenov
 *
 * @param <W> a type of the selection
 */
public class RandomSelectionStrategy implements ISelectionStrategy {

	private final char[] options;
	private final Random rand;
	private final String playerName;
	private final IGameEventListener selectionWeaponListener;

	/**
	 * Constructor for the selection strategy
	 * 
	 * @param options the list of all available weapons
	 */
	public RandomSelectionStrategy(String playerName, CharSet weaponSymbolSet,
			IGameEventListener selectionWeaponListener) {
		this.playerName = playerName;
		this.options = weaponSymbolSet.toCharArray();
		this.selectionWeaponListener = selectionWeaponListener;
		rand = new Random();
	}

	@Override
	public void selectNewWeapon() {
		int selection = rand.nextInt(options.length);
		WeaponSelectedEvent event = new WeaponSelectedEvent(playerName,
				options[selection]);
		selectionWeaponListener.onGameEvent(event);
	}

}
