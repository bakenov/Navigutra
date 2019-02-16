package imc.visitor.shape.factory;

import imc.visitor.shape.Circle;
import imc.visitor.shape.IShape;
import imc.visitor.shape.Rectangle;
import imc.visitor.shape.Triangle;
import imc.visitor.utils.DistanceUnits;

/**
 * The factory for geometric shapes
 * 
 * @author bakenov
 *
 */
public class ShapeFactory implements IShapeFactory<IShape> {

	private final DistanceUnits units;

	/**
	 * The constructor of the shape factory
	 * 
	 * @param units the distance units
	 */
	public ShapeFactory(DistanceUnits units) {
		this.units = units;
	}

	@Override
	public IShape createShape(double radius) {
		return new Circle(radius, units);
	}

	@Override
	public IShape createShape(double width, double height) {
		return new Rectangle(width, height, units);
	}

	@Override
	public IShape createShape(double sideA, double sideB, double alpha) {
		return new Triangle(sideA, sideB, alpha, units);
	}

}
