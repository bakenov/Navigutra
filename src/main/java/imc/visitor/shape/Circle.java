package imc.visitor.shape;

import imc.visitor.shape.visitor.IVisitor;

/**
 * Represents the circle geometric shape
 * 
 * @author bakenov
 *
 */
public class Circle implements IShape {

	private final double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public ShapeType getType() {
		return ShapeType.CIRCLE;
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
