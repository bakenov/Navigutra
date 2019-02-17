package imc.game.psr.input;

import imc.game.psr.game.model.event.IGameEventListener;

/**
 * The contract for the IO component.
 * 
 * @author bakenov
 *
 */
public interface IInputOutput extends IGameEventListener {

	/**
	 * starts the IO component
	 */
	void start();

	/**
	 * stops the IO component
	 */
	void stop();
}
