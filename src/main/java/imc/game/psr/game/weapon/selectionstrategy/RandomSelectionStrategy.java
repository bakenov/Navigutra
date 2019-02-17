package imc.game.psr.game.weapon.selectionstrategy;

import java.util.Random;
import java.util.function.Consumer;

import imc.game.psr.game.weapon.IWeapon;

/**
 * The Random selection strategy selects randomly the weapon from list of the
 * available weapons.
 * 
 * @author bakenov
 *
 * @param <W> a type of the selection
 */
public class RandomSelectionStrategy<W extends IWeapon>
		implements ISelectionStrategy<W> {

	private final W[] options;
	private final Random rand;

	/**
	 * Constructor for the selection strategy
	 * 
	 * @param options the list of all available weapons
	 */
	public RandomSelectionStrategy(W[] options) {
		this.options = options;
		rand = new Random();
	}

	@Override
	public void select(Consumer<W> consumer) {
		int selection = rand.nextInt(options.length);
		consumer.accept(options[selection]);
	}

}
