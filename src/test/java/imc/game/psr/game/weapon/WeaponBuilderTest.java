package imc.game.psr.game.weapon;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;

import imc.game.psr.game.config.GameConfig;
import imc.game.psr.game.config.IGameConfig;
import imc.game.utils.PropertyLoader;

/**
 * Tests for the {@link WeaponBuilder} class.
 *
 * @author bakenov
 *
 */
public class WeaponBuilderTest {

	private IGameConfig buildConfig() {
		Properties properties = PropertyLoader
				.loadProperties("testGame.properties");
		GameConfig config = new GameConfig();
		config.updateFromProperties(properties);
		return config;
	}

	@Test
	public void testLoad() {
		IGameConfig config = buildConfig();
		WeaponBuilder<IWeapon> builder = new WeaponBuilder<>(config);
		assertEquals(Weapon.PAPER, builder.build('P').getWeapon());
		assertEquals(Weapon.ROCK, builder.build('R').getWeapon());
		assertEquals(Weapon.SCISSORS, builder.build('S').getWeapon());
		assertEquals(null, builder.build('s'));
	}
}
