package imc.game.psr.context;

import static imc.game.psr.context.error.ApplicationError.CONFIG_FILE_NOT_SPECIFIED;
import static imc.game.psr.context.error.ApplicationError.INVALID_CONFIG_FILE;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import imc.game.psr.game.config.GameConfig;
import imc.game.psr.game.config.IGameConfig;
import imc.game.psr.game.model.event.EventType;
import imc.game.psr.game.model.event.IGameEvent;
import imc.game.utils.PropertyLoader;

/**
 * The application context that holds the resources used by different
 * application's components
 * 
 * @author bakenov
 *
 */
public class ApplicationContext implements IApplicationContext {

	/**
	 * Command-line option to specify the name of configuration file.
	 */
	private static final String CONFIG_FILE = "-c";

	private static final Logger log = LoggerFactory
			.getLogger(ApplicationContext.class);

	private Properties properties;
	private IGameConfig gameConfig;

	/**
	 * The constructor for the application context
	 */
	public ApplicationContext() {
		this.properties = new Properties();
	}

	@Override
	public IGameConfig getGameConfig() {
		return gameConfig;
	}

	@Override
	public void close() {
		log.info("close()    closing...");
	}

	public void buildGameConfig(String configFile) {
		if (configFile == null) {
			throw new RuntimeException(CONFIG_FILE_NOT_SPECIFIED.getDescription());
		} else {
			loadProperties(configFile);
			gameConfig = new GameConfig();
			gameConfig.updateFromProperties(properties);
		}
	}

	private void loadProperties(String configFile) {
		properties = PropertyLoader.loadProperties(configFile);
		if (properties == null)
			throw new RuntimeException(INVALID_CONFIG_FILE.getDescription());
	}

	@Override
	public void processCommandLine(String[] args) {
		if (args != null) {
			int n = args.length;
			if (n > 0) {
				String optArg;
				String fileName = null;
				for (int i = 0; i < n; ++i) {
					String opt = args[i];
					if (CONFIG_FILE.equals(opt)) {
						if (n - i >= 2) {
							optArg = args[i + 1];
							++i;
							if (optArg != null && !optArg.startsWith("-")) {
								fileName = optArg;
								break;
							}
						}
					}
				}
				if (fileName != null) {
					buildGameConfig(fileName);
					return;
				}
			}
		}
		throw new RuntimeException(CONFIG_FILE_NOT_SPECIFIED.getDescription());
	}

	@Override
	public void onGameEvent(IGameEvent event) {
		if (event.getType() == EventType.QUIT_COMMAND) {
			close();
		}
	}
}
