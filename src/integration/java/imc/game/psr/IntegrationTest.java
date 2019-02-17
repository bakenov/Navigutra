package imc.game.psr;

import org.junit.Test;

import imc.game.psr.context.ApplicationContext;
import imc.game.psr.game.config.IGameConfig;
import imc.game.psr.game.model.GameModel;
import imc.game.psr.game.model.IGameModel;
import imc.game.psr.game.model.event.EventType;
import imc.game.psr.game.model.event.IGameEvent;
import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.player.IPlayerBuilder;
import imc.game.psr.game.player.PlayerBuilder;
import imc.game.psr.game.weapon.IBuilder;
import imc.game.psr.game.weapon.IWeapon;
import imc.game.psr.game.weapon.WeaponBuilder;
import imc.game.psr.input.IInputOutput;

/**
 * System integration test
 * 
 * @author bakenov
 *
 */
public class IntegrationTest {

	private ApplicationContext context;
	private IInputOutput inputOutput;
	private IGameConfig config;
	private IGameModel model;
	private IBuilder<IWeapon> builder;
	private IPlayerBuilder playerBuilder;

	@Test
	public void test() {

		context = new ApplicationContext();
		context.buildGameConfig("game.properties");

		IGameEventListener quitListener = new IGameEventListener() {
			@Override
			public void onGameEvent(IGameEvent event) {
				if (event.getType() == EventType.QUIT_COMMAND) {
					close();
				}
			}
		};

		config = context.getGameConfig();
		inputOutput = new InputOutputIntegTest(config, quitListener);
		builder = new WeaponBuilder<>(config);
		model = new GameModel<IWeapon>(config, builder, inputOutput, quitListener);
		playerBuilder = new PlayerBuilder(inputOutput, model, config);
		model.setPlayerBuilder(playerBuilder);

		inputOutput.start();
		model.start();

		close();
	}

	private void close() {
		context.close();
		model.stop();
		inputOutput.stop();
	}

}