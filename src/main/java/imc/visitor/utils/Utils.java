package imc.visitor.utils;

/**
 * Collection of the all utility methods
 * 
 * @author bakenov
 *
 */
public class Utils {

	public static final double RIGHT_ANGLE = 90.0;

	/**
	 * Converts the degrees to the radians
	 * 
	 * @param degrees the degrees to be converted to radians
	 * @return the angle in radians
	 */
	public static double convertDegreesToRadians(double degrees) {
		return Math.PI * degrees / (2 * RIGHT_ANGLE);
	}

}
