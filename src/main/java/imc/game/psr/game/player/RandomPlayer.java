package imc.game.psr.game.player;

import java.util.function.Consumer;

import imc.game.psr.game.weapon.IWeapon;
import imc.game.psr.game.weapon.IWeaponSelectionListener;
import imc.game.psr.game.weapon.selectionstrategy.ISelectionStrategy;

public class RandomPlayer<W extends IWeapon> implements IPlayer, Consumer<W> {

	private final ISelectionStrategy<W> selectionStrategy;
	private final IWeaponSelectionListener<W> selectionListener;

	RandomPlayer(ISelectionStrategy<W> selectionStrategy,
			IWeaponSelectionListener<W> selectionListener) {
		this.selectionStrategy = selectionStrategy;
		this.selectionListener = selectionListener;
	}

	@Override
	public void selectWeapon() {
		selectionStrategy.select(this);
	}

	@Override
	public void accept(W weapon) {
		selectionListener.onSelection(weapon, this);
	}

}
