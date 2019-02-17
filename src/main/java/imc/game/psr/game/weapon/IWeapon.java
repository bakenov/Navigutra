package imc.game.psr.game.weapon;

/**
 * The weapon's contract that allows compare the strength of the game players'
 * weapons
 * 
 * @author bakenov
 *
 */
public interface IWeapon {

	/**
	 * Compares the weapon's strength against other weapons
	 * 
	 * @param other the other weapon to be compared with
	 * @return the result of the strength comparison between the weapons
	 */
	StrengthType getStrengthAgainst(IWeapon other);
}
