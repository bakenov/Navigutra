package imc.game.psr.game.model;

import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.player.IPlayerBuilder;

/**
 * The game's model
 * 
 * @author bakenov
 *
 */
public interface IGameModel extends IGameEventListener {

	/**
	 * Sets the player builder
	 * 
	 * @param playerBuilder the player builder
	 */
	void setPlayerBuilder(IPlayerBuilder playerBuilder);

	/**
	 * Starts the model
	 */
	void start();

	/**
	 * Stops the model
	 */
	void stop();

}
