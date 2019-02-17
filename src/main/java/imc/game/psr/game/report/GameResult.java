package imc.game.psr.game.report;

import imc.game.psr.game.model.GameModel.PlayerData;
import imc.game.psr.game.weapon.StrengthType;

/**
 * The result of the game
 * 
 * @author bakenov
 *
 */
public class GameResult implements IGameResult {

	private final int cycleId;
	private final StrengthType result;
	private final char firstWeapon;
	private final char secondWeapon;

	/**
	 * Constructor for game's result
	 * 
	 * @param cycleId          the game cycle
	 * @param firstPlayerData  the first player's data
	 * @param secondPlayerData the second player's data
	 */
	public GameResult(int cycleId, PlayerData firstPlayerData,
			PlayerData secondPlayerData) {
		this.cycleId = cycleId;
		this.firstWeapon = firstPlayerData.getWeapon().getCode();
		this.secondWeapon = secondPlayerData.getWeapon().getCode();
		result = firstPlayerData.getWeapon()
				.getStrengthAgainst(secondPlayerData.getWeapon());
	}

	@Override
	public int getCycleId() {
		return cycleId;
	}

	@Override
	public char getWeapon1() {
		return firstWeapon;
	}

	@Override
	public char getWeapon2() {
		return secondWeapon;
	}

	@Override
	public StrengthType getStrengthType() {
		return result;
	}

	@Override
	public String toString() {
		return "GameResult(" + cycleId + "|" + firstWeapon + " --> " + secondWeapon
				+ "  " + result + ")";
	}
}
