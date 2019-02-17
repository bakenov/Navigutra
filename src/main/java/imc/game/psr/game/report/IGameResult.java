package imc.game.psr.game.report;

import imc.game.psr.game.weapon.StrengthType;

/**
 * The result of the game
 * 
 * @author bakenov
 *
 */
public interface IGameResult {

	/**
	 * @return
	 */
	int getCycleId();

	/**
	 * @return
	 */
	char getWeapon1();

	/**
	 * @return
	 */
	char getWeapon2();

	/**
	 * @return
	 */
	StrengthType getStrengthType();

}
