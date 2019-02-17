package imc.game.psr.game.weapon;

/**
 * The contract for the weapon builder
 * 
 * @author bakenov
 *
 * @param <W> the type of the weapon
 */
public interface IBuilder<W extends IWeapon> {

	/**
	 * Builds the weapon for specified symbol
	 * 
	 * @param type a weapon's symbol
	 * @return the weapon
	 */
	W build(char type);

}
