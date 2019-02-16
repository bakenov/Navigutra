package imc.visitor.utils;

/**
 * A {@code DistanceUnits} represents the distance at a given unit of
 * granularity and provides utility methods to convert across units
 * 
 * @author bakenov
 *
 */
public enum DistanceUnits {

	/**
	 * Distance unit representing one tenth of a centimeter
	 */
	MILLIMETER("mm") {
		public double toMillimeters(double d) {
			return d;
		}

		public double toCentimeters(double d) {
			return d / C1;
		}

		public double toMeters(double d) {
			return d / C3;
		}

		public double convert(double d, DistanceUnits u) {
			return u.toMillimeters(d);
		}
	},

	CENTIMETER("sm") {
		public double toMillimeters(double d) {
			return d * C1;
		}

		public double toCentimeters(double d) {
			return d;
		}

		public double toMeters(double d) {
			return d / C2;
		}

		public double convert(double d, DistanceUnits u) {
			return u.toCentimeters(d);
		}
	},
	METER("m") {
		public double toMillimeters(double d) {
			return d * C3;
		}

		public double toCentimeters(double d) {
			return d * C2;
		}

		public double toMeters(double d) {
			return d;
		}

		public double convert(double d, DistanceUnits u) {
			return u.toMeters(d);
		}
	};

	// Handy constants for conversion methods
	private static final double C0 = 1L;
	private static final double C1 = C0 * 10.;
	private static final double C2 = C1 * 10.;
	private static final double C3 = C2 * 10.;
	private final String symbol;

	/**
	 * The constructor
	 * 
	 * @param desc
	 */
	DistanceUnits(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	/**
	 * Equivalent to {@link #convert(double, DistanceUnits) MILLIMETER.convert(d,
	 * this)}.
	 * 
	 * @param d the distance
	 * @return the converted distance,
	 */
	public double toMillimeters(double d) {
		throw new AbstractMethodError();
	}

	/**
	 * Equivalent to {@link #convert(double, DistanceUnits) CENTIMETER.convert(d,
	 * this)}.
	 * 
	 * @param d the distance
	 * @return the converted distance,
	 */
	public double toCentimeters(double d) {
		throw new AbstractMethodError();
	}

	/**
	 * Equivalent to {@link #convert(double, DistanceUnits) METER.convert(d,
	 * this)}.
	 * 
	 * @param d the distance
	 * @return the converted distance,
	 */
	public double toMeters(double d) {
		throw new AbstractMethodError();
	}

	/**
	 * Converts the given distance in the given unit to this unit.
	 * <p>
	 * For example, to convert 10 minutes to milliseconds, use:
	 * {@code TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES)}
	 *
	 * @param d          the distance in the given {@code sourceUnit}
	 * @param sourceUnit the unit of the {@code d} argument
	 * @return the converted distance in this unit,
	 */
	public double convert(double d, DistanceUnits sourceUnit) {
		throw new AbstractMethodError();
	}
}
