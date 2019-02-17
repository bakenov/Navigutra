package imc.game.psr.game.model.event;

/**
 * The event that will be issued when the player selected the weapon
 * 
 * @author bakenov
 *
 */
public class WeaponSelectedEvent extends BaseEvent
		implements IWeaponSelectedEvent {

	private final String playerName;
	private final char weapon;

	public WeaponSelectedEvent(String playerName, char weapon) {
		super(EventType.WEAPON_SELECTED_BY_PLAYER);
		this.playerName = playerName;
		this.weapon = weapon;
	}

	@Override
	public char getWeaponSymbol() {
		return weapon;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

}
