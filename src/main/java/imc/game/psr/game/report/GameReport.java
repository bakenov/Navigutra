package imc.game.psr.game.report;

import java.util.ArrayList;
import java.util.List;

import imc.game.psr.game.weapon.StrengthType;

/**
 * The game's report
 * 
 * @author bakenov
 *
 */
public class GameReport implements IReport {

	private final String firstPlayer;
	private final String secondPlayer;
	private final List<IGameResult> results;
	private int numberFirstPlayerWon;
	private int numberSecondPlayerWon;
	private int numberDraw;

	public GameReport(String firstPlayer, String secondPlayer) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		results = new ArrayList<>();
	}

	@Override
	public void registerResult(IGameResult gameResult) {
		results.add(gameResult);
	}

	private void getStatistics() {
		numberFirstPlayerWon = 0;
		numberSecondPlayerWon = 0;
		results.forEach((r) -> {
			StrengthType type = r.getStrengthType();
			switch (type) {
			case STRONGER:
				numberFirstPlayerWon++;
				break;
			case WEAKER:
				numberSecondPlayerWon++;
				break;
			case EQUAL:
				numberDraw++;
				break;
			default:
			}
		});
	}

	@Override
	public String buildReport() {
		StringBuilder sb = new StringBuilder();
		getStatistics();
		sb.append("\n");
		sb.append("The player ");
		sb.append(firstPlayer);
		sb.append(" won:");
		sb.append(numberFirstPlayerWon);
		sb.append(" times, lost:");
		sb.append(numberSecondPlayerWon);
		sb.append(" times, draw:");
		sb.append(numberDraw);
		sb.append("\n");
		sb.append("The player ");
		sb.append(secondPlayer);
		sb.append(" won:");
		sb.append(numberSecondPlayerWon);
		sb.append(" times, lost:");
		sb.append(numberFirstPlayerWon);
		sb.append(" times, draw:");
		sb.append(numberDraw);
		return sb.toString();
	}

	public String toString() {
		return buildReport();
	}

}
