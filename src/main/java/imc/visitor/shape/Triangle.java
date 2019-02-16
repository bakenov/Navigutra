package imc.visitor.shape;

import static imc.visitor.shape.ShapeType.TRIANGLE;

import imc.visitor.shape.visitor.IVisitor;
import imc.visitor.utils.DistanceUnits;

/**
 * Represents the triangle geometric shape
 * 
 * @author bakenov
 *
 */
public class Triangle extends AbstractShape {

	private static final String A_PARAMETER = "The first side";
	private static final String B_PARAMETER = "The second side";
	private static final String ALPHA_PARAMETER = "The angle";

	private final double a;
	private final double b;
	private final double alpha;

	/**
	 * The constructor for the Triangle
	 * 
	 * @param a     the first side of the triangle
	 * @param b     the second side of the triangle
	 * @param alpha the angle between the sides in degrees
	 * @param units the distance units
	 */
	public Triangle(double a, double b, double alpha, DistanceUnits units) {
		super(TRIANGLE, units);
		validateParameter(a, A_PARAMETER);
		validateParameter(b, B_PARAMETER);
		validateParameter(alpha, ALPHA_PARAMETER);
		this.a = a;
		this.b = b;
		this.alpha = alpha;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Returns the side a of the triangle
	 * 
	 * @return the side a of the triangle
	 */
	public double getSideA() {
		return a;
	}

	/**
	 * Returns the side b of the triangle
	 * 
	 * @return the side b of the triangle
	 */
	public double getSideB() {
		return b;
	}

	/**
	 * Returns the angle between two sides in degrees
	 * 
	 * @return the angle between two sides in degrees
	 */
	public double getAngle() {
		return alpha;
	}

}