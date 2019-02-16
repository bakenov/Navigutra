package imc.visitor.shape;

import static imc.visitor.shape.ShapeType.CIRCLE;

import imc.visitor.shape.visitor.IVisitor;
import imc.visitor.utils.DistanceUnits;

/**
 * Represents the circle geometric shape
 * 
 * @author bakenov
 *
 */
public class Circle extends AbstractShape {

	private static final String RADIUS_PARAMETER = "The radius";

	private final double radius;

	/**
	 * The constructor of the circle
	 * 
	 * @param radius the radius of the circle
	 * @param units  the distance units
	 */
	public Circle(double radius, DistanceUnits units) {
		super(CIRCLE, units);
		validateParameter(radius, RADIUS_PARAMETER);
		this.radius = radius;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Returns the radius of the circle
	 * 
	 * @return the radius of the circle
	 */
	public double getRadius() {
		return radius;
	}
}
