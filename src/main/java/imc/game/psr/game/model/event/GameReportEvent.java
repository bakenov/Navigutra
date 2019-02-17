package imc.game.psr.game.model.event;

/**
 * The game report event
 * 
 * @author bakenov
 *
 */
public class GameReportEvent extends BaseEvent implements IGameReportEvent {

	private final String report;

	/**
	 * Constructor for Report event
	 * 
	 * @param report the game report
	 */
	public GameReportEvent(String report) {
		super(EventType.REPORT);
		this.report = report;
	}

	@Override
	public String getReport() {
		return report;
	}

}
