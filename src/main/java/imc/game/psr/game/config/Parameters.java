package imc.game.psr.game.config;

/**
 * The game's configuration Parameters that are specified in configuration file
 * 
 * @author bakenov
 *
 */
public enum Parameters {
	MESSAGE("Message to the user in console"), WEAPONS(
			"Map of available weapons. Key - the weapon's symbol, value - the weapon's name");

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
