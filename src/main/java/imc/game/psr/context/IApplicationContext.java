package imc.game.psr.context;

import imc.game.psr.game.config.IGameConfig;
import imc.game.psr.game.model.event.IGameEventListener;

/**
 * The contract for the application context. The context holds the resources
 * used by different application's components.
 * 
 * @author bakenov
 *
 */
public interface IApplicationContext extends IGameEventListener {

	/**
	 * Gets the application Configuration that holds the configuration data for
	 * all components
	 * 
	 * @return the application configuration component
	 */
	IGameConfig getGameConfig();

	/**
	 * Processes the application's parameters. The main parameter is the name of
	 * the configuration file, the application configuration component is built
	 * upon it
	 * 
	 * @param args an application parameters
	 */
	void processCommandLine(String[] args);

	/**
	 * The call asking to close all components/services and free the application
	 * resources
	 */
	void close();
}