package imc.game.psr.game.player;

import imc.game.psr.game.weapon.selectionstrategy.ISelectionStrategy;

/**
 * The game player, delegates the weapon selection to the selection strategy
 * 
 * @author bakenov
 */
public class GamePlayer implements IPlayer {

	private final ISelectionStrategy selectionStrategy;
	private final String name;

	GamePlayer(String name, ISelectionStrategy selectionStrategy) {
		this.selectionStrategy = selectionStrategy;
		this.name = name;
	}

	@Override
	public void selectWeapon() {
		selectionStrategy.selectNewWeapon();
	}

	@Override
	public String getName() {
		return name;
	}

}
