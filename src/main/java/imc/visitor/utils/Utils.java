package imc.visitor.utils;

/**
 * Collection of the all utility methods
 * 
 * @author bakenov
 *
 */
public class Utils {

	public static final double EPSILON = 1.e-8;
	public static final double RIGHT_ANGLE = 90.0;
	private static final String PARAMETER_NULL_MESSAGE = " cannot be null.";
	private static final String NEGATIVE_PARAMETER_ERROR_MESSAGE = " cannot be negative";
	private static final String ZERO_NEGATIVE_PARAMETER_ERROR_MESSAGE = " cannot be zero or negative";

	/**
	 * Converts the degrees to the radians
	 * 
	 * @param degrees the degrees to be converted to radians
	 * @return the angle in radians
	 */
	public static double convertDegreesToRadians(double degrees) {
		return Math.PI * degrees / (2 * RIGHT_ANGLE);
	}

	/**
	 * Asserts that the specified object of type {@code T} is not {@code null}.
	 *
	 * @param            <T> the type of the object to check
	 * @param object     the object to be tested
	 * @param objectName the name of the object
	 * @return the original validated object
	 * @throws IllegalArgumentException if the object is null
	 */
	public static <T> T notNull(T object, String objectName) {
		if (object == null)
			throw new IllegalArgumentException(objectName + PARAMETER_NULL_MESSAGE);
		return object;
	}

	/**
	 * Asserts that the specified double value is not negative
	 *
	 * @param p         the double value to be tested
	 * @param parameter the name of the parameter
	 * @return the original validated value
	 * @throws IllegalArgumentException if the value is negative
	 */
	public static double notNegative(double p, String parameter) {
		if (p < 0)
			throw new IllegalArgumentException(
					parameter + NEGATIVE_PARAMETER_ERROR_MESSAGE);
		return p;
	}

	/**
	 * Asserts that the specified double value is not zero and not negative
	 *
	 * @param p         the double value to be tested
	 * @param parameter the name of the parameter
	 * @return the original validated value
	 * @throws IllegalArgumentException if the value is zero or negative
	 */
	public static double notZeroAndNegative(double p, String parameter) {
		if (p <= 0)
			throw new IllegalArgumentException(
					parameter + ZERO_NEGATIVE_PARAMETER_ERROR_MESSAGE);
		return p;
	}

	/**
	 * Returns {@code true} if the difference between doubles is within the range
	 * of allowed error (inclusive).
	 *
	 * @param x   First value.
	 * @param y   Second value.
	 * @param eps Amount of allowed absolute error.
	 * @return {@code true} if the values are within range of each other.
	 *
	 */
	public static boolean equals(double x, double y) {
		return Math.abs(y - x) <= EPSILON;
	}

	/**
	 * Returns {@code true} if the specified double value is zero or NaN.
	 *
	 * @param v The double value.
	 * @return {@code true} if the value is zero or NaN.
	 *
	 */
	public static boolean isZeroOrNaN(double v) {
		return equals(v, 0.) || v != v;
	}

}
