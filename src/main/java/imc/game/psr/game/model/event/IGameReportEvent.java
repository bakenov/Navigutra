package imc.game.psr.game.model.event;

/**
 * The event that holds the game report
 * 
 * @author bakenov
 *
 */
public interface IGameReportEvent extends IGameEvent {

	/**
	 * Returns the game report
	 * 
	 * @return the game report
	 */
	String getReport();
}
