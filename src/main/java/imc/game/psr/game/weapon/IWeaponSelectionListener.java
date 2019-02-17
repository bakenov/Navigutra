package imc.game.psr.game.weapon;

import imc.game.psr.game.player.IPlayer;

public interface IWeaponSelectionListener<W extends IWeapon> {

	void onSelection(W selection, IPlayer player);
}
