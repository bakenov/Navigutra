package imc.game.psr.game.weapon.selectionstrategy;

import java.util.function.Consumer;

import imc.game.psr.game.weapon.IWeapon;

public interface ISelectionStrategy<W extends IWeapon> {

	void select(Consumer<W> consumer);

}
