package imc.game.psr.game.weapon;

import static imc.game.psr.game.weapon.StrengthType.EQUAL;
import static imc.game.psr.game.weapon.StrengthType.STRONGER;
import static imc.game.psr.game.weapon.StrengthType.WEAKER;
import static imc.game.psr.game.weapon.Weapon.PAPER;
import static imc.game.psr.game.weapon.Weapon.ROCK;
import static imc.game.psr.game.weapon.Weapon.SCISSORS;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the {@link Weapon} class.
 *
 * @author bakenov
 *
 */
public class WeaponTest {

	@Test
	public void getStrengthAgainst() {
		IWeapon weapon = PAPER;
		assertEquals(EQUAL, weapon.getStrengthAgainst(PAPER));
		assertEquals(STRONGER, weapon.getStrengthAgainst(ROCK));
		assertEquals(WEAKER, weapon.getStrengthAgainst(SCISSORS));

		weapon = ROCK;
		assertEquals(WEAKER, weapon.getStrengthAgainst(PAPER));
		assertEquals(EQUAL, weapon.getStrengthAgainst(ROCK));
		assertEquals(STRONGER, weapon.getStrengthAgainst(SCISSORS));

		weapon = SCISSORS;
		assertEquals(STRONGER, weapon.getStrengthAgainst(PAPER));
		assertEquals(WEAKER, weapon.getStrengthAgainst(ROCK));
		assertEquals(EQUAL, weapon.getStrengthAgainst(SCISSORS));
	}
}
