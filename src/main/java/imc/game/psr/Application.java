package imc.game.psr;

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
import imc.game.psr.input.IInputOutput;
import imc.game.psr.input.InputOutput;

/**
 * The application program that plays Paper Scissors Rock between the computer
 * and a real player.
 * 
 * @author bakenov
 *
 */
public class Application implements IGameEventListener {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

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
	public Application() {
		context = new ApplicationContext();
		context.buildGameConfig("game.properties");

		config = context.getGameConfig();
		inputOutput = new InputOutput(config, this);
		builder = new WeaponBuilder<>(config);
		model = new GameModel<IWeapon>(config, builder, inputOutput, this);
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

	private void close() {
		context.close();
		model.stop();
		inputOutput.stop();
	}

	@Override
	public void onGameEvent(IGameEvent event) {
		if (event.getType() == EventType.QUIT_COMMAND) {
			close();
		}
	}

	public static void main(String[] args) {
		try {
			Application app = new Application();
			app.start();
			log.info("Application finished.");
		} catch (RuntimeException e) {
			log.error("Cannot run Application : {}", e);
		}
	}
}