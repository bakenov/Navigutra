package imc.game.psr.input;

import imc.game.psr.game.config.IGameConfig;
import it.unimi.dsi.fastutil.chars.CharSet;

/**
 * The checker for the weapon symbol
 * 
 * @author bakenov
 *
 */
public class InputValidator implements IInputValidator {

	private final CharSet weaponSymbolSet;

	public InputValidator(IGameConfig config) {
		weaponSymbolSet = config.getWeaponSymbolSet();
	}

	@Override
	public boolean isSelectionValid(char selection) {
		if (weaponSymbolSet.contains(selection))
			return true;
		return false;
	}

}
