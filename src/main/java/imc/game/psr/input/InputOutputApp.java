package imc.game.psr.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class InputOutputApp implements IGameEventListener {

	private static final Logger log = LoggerFactory
			.getLogger(InputOutputApp.class);

	private final ApplicationContext context;
	private IInputOutput inputOutput;
	private final IGameConfig config;
	private final IGameModel model;
	private final IBuilder<IWeapon> builder;
	private final IPlayerBuilder playerBuilder;

	/**
	 * Constructs the application
	 * 
	 * @param args the application arguments
	 */
	public InputOutputApp() {
		context = new ApplicationContext();
		context.buildGameConfig("game.properties");

		config = context.getGameConfig();
		inputOutput = new InputOutput(config, this);
		builder = new WeaponBuilder<>(config);
		model = new GameModel<IWeapon>(config, builder, inputOutput);
		playerBuilder = new PlayerBuilder(inputOutput, model, config);
		model.setPlayerBuilder(playerBuilder);
	}

	/**
	 * Starts Listening for user input. The program will be run until the user
	 * types the quit command
	 */
	public void start() {
		inputOutput.start();
		model.start();
	}

	public static void main(String[] args) {
		try {
			InputOutputApp app = new InputOutputApp();
			app.start();
			log.info("Application finished.");
		} catch (RuntimeException e) {
			log.error("Cannot run Application : {}", e);
		}
	}

	@Override
	public void onGameEvent(IGameEvent event) {
		if (event.getType() == EventType.QUIT_COMMAND) {
			context.close();
		}
	}
}
