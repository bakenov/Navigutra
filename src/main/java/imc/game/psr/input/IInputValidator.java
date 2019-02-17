package imc.game.psr.input;

/**
 * The console weapon selection validator
 * 
 * @author bakenov
 *
 */
public interface IInputValidator {

	/**
	 * Checks the weapon selection, if the selection is valid returns
	 * {@code true}, otherwise returns {@code false}
	 * 
	 * @param selection the weapon selection
	 * @return returns {@code true} if the selection is valid
	 */
	boolean isSelectionValid(char selection);
}
