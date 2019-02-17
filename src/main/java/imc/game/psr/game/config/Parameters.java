package imc.game.psr.game.config;

/**
 * The game's configuration Parameters that are specified in configuration file
 * 
 * @author bakenov
 *
 */
public enum Parameters {
	MESSAGE("Message to the user in console"),
	WEAPONS(
			"Map of available weapons. Key - the weapon's symbol, value - the weapon's name"),
	NUMBER_GAMES("Number games to play"),
	QUIT_COMMAND("Symbol for game quit command"),
	FIRST_PLAYER_NAME("The name of the first player"),
	FIRST_PLAYER_TYPE("The type of the first player"),
	SECOND_PLAYER_NAME("The name of the second player"),
	SECOND_PLAYER_TYPE("The type of the second player");

	private final String desc;
	private final String qName;

	Parameters(String desc) {
		this.desc = desc;
		this.qName = this.name().replace("_", "").toLowerCase();
	}

	public String getQualifiedName() {
		return qName;
	}

	public String getDescription() {
		return desc;
	}

}
