package imc.game.psr.input;

/**
 * The contract for the IO component.
 * 
 * @author bakenov
 *
 */
public interface IInputOutput {

	/**
	 * Sets input data listener
	 * 
	 * @param listener a input data listener
	 */
	void setMessageListener(IMessageListener listener);

	/**
	 * starts IO module and all its components
	 */
	void start();
}
