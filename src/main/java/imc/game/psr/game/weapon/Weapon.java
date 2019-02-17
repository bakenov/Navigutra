package imc.game.psr.game.weapon;

import imc.visitor.utils.DistanceUnits;

/**
 * Represents the weapons accessible to the game players
 * 
 * @author bakenov
 *
 */
public enum Weapon implements IWeapon {

	/**
	 * The paper weapon that beats rock, but weak against scissors
	 */
	PAPER('p') {
		public StrengthType getStrengthAgainst(IWeapon other) {
			switch ((Weapon) other) {
			case PAPER:
				return StrengthType.EQUAL;
			case ROCK:
				return StrengthType.STRONGER;
			case SCISSORS:
				return StrengthType.WEAKER;
			default:
				return StrengthType.UNKNOWN;
			}
		}
	},

	/**
	 * The rock weapon that beats scissors, but weak against paper
	 */
	ROCK('r') {
		public StrengthType getStrengthAgainst(IWeapon other) {
			switch ((Weapon) other) {
			case PAPER:
				return StrengthType.WEAKER;
			case ROCK:
				return StrengthType.EQUAL;
			case SCISSORS:
				return StrengthType.STRONGER;
			default:
				return StrengthType.UNKNOWN;
			}
		}
	},

	/**
	 * The scissors weapon that beats paper, but weak against rock
	 */
	SCISSORS('s') {
		public StrengthType getStrengthAgainst(IWeapon other) {
			switch ((Weapon) other) {
			case PAPER:
				return StrengthType.STRONGER;
			case ROCK:
				return StrengthType.WEAKER;
			case SCISSORS:
				return StrengthType.EQUAL;
			default:
				return StrengthType.UNKNOWN;
			}
		}
	};

	private final char code;

	Weapon(char code) {
		this.code = code;
	}

	@Override
	public Weapon getWeapon() {
		return this;
	}

	/**
	 * Equivalent to {@link #convert(double, DistanceUnits) MILLIMETER.convert(d,
	 * this)}.
	 * 
	 * @param d the distance
	 * @return the converted distance,
	 */
	public StrengthType getStrengthAgainst(IWeapon anotherWeapon) {
		throw new AbstractMethodError();
	}

	/**
	 * Returns the code of the shape
	 * 
	 * @return the code of the shape
	 */
	public char getCode() {
		return code;
	}

	/**
	 * Returns the shape type for specified code
	 * 
	 * @param code the shape code
	 * @return the shape type
	 */
	public static Weapon getTypeByChar(char code) {
		for (Weapon t : values()) {
			if (t.code == code)
				return t;
		}
		return null;
	}
}
