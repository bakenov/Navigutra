package imc.game.psr.game.report;

/**
 * Game report interface
 * 
 * @author bakenov
 *
 */
public interface IReport {

	/**
	 * Sets the game result to the report
	 * 
	 * @param gameResult the game result
	 */
	void registerResult(IGameResult gameResult);

	/**
	 * Builds the game report
	 * 
	 * @return the game report
	 */
	String buildReport();

}
