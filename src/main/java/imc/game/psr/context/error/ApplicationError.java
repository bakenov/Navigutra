package imc.game.psr.context.error;

/**
 * The errors that force the game to exit
 * 
 * @author bakenov
 *
 */
public enum ApplicationError {

	INVALID_CONFIG_FILE("Invalid Application configuration file"),
	CONFIG_FILE_NOT_SPECIFIED("Application configuration file is not specified"),
	ERROR_PROCESSING_CONFIG_FILE(
			"Error processing Application configuration file"),
	INVALID_INPUT("Input command is invalid"),
	UNKNOWN_COMMAND("Unknown input command type");

	private final String desc;

	/**
	 * The application error
	 * 
	 * @param desc an error's description
	 */
	ApplicationError(String desc) {
		this.desc = desc;
	}

	/**
	 * Returns error's description
	 * 
	 * @return an error's description
	 */
	public String getDescription() {
		return desc;
	}

}
