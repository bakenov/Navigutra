package imc.game.psr.game.player;

import imc.game.psr.game.config.IGameConfig;
import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.weapon.selectionstrategy.ISelectionStrategy;
import imc.game.psr.game.weapon.selectionstrategy.InputSelectionStrategy;
import imc.game.psr.game.weapon.selectionstrategy.RandomSelectionStrategy;

/**
 * The builder for game players
 * 
 * @author bakenov
 *
 */
public class PlayerBuilder implements IPlayerBuilder {

	private final IGameEventListener inputComponent;
	private final IGameEventListener gameModel;
	private final IGameConfig config;

	/**
	 * Constructor of the builder
	 * 
	 * @param inputComponent the input IO event listener
	 * @param gameModel      the game model weapon selection listener
	 * @param config         the game configuration
	 */
	public PlayerBuilder(IGameEventListener inputComponent,
			IGameEventListener gameModel, IGameConfig config) {
		this.inputComponent = inputComponent;
		this.gameModel = gameModel;
		this.config = config;
	}

	@Override
	public IPlayer buildPlayer(String playerName, GamePlayerType type) {
		ISelectionStrategy strategy = null;
		if (type == GamePlayerType.RANDOM) {
			strategy = new RandomSelectionStrategy(playerName,
					config.getWeaponSymbolSet(), gameModel);
		} else if (type == GamePlayerType.INPUT) {
			strategy = new InputSelectionStrategy<>(playerName, inputComponent,
					gameModel);
		}
		if (strategy != null)
			return new GamePlayer(playerName, strategy);
		return null;
	}

}
