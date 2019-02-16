package imc.visitor.shape.visitor;

import static imc.visitor.utils.Utils.convertDegreesToRadians;

import imc.visitor.shape.Circle;
import imc.visitor.shape.Rectangle;
import imc.visitor.shape.Triangle;
import imc.visitor.utils.DistanceUnits;

/**
 * The visitor of the geometric shapes, specializing in calculation of the
 * shapes's area
 * 
 * @author bakenov
 *
 */
public class AreaVisitor implements IVisitor {

	private final DistanceUnits units;
	private double totalArea;

	public AreaVisitor(DistanceUnits units) {
		this.units = units;
	}

	@Override
	public void visit(Circle circle) {
		double convertedR = units.convert(circle.getRadius(), circle.getUnits());
		totalArea += Math.PI * convertedR * convertedR;
	}

	@Override
	public void visit(Rectangle rectangle) {
		double convertedWidth = units.convert(rectangle.getWidth(),
				rectangle.getUnits());
		double convertedHeight = units.convert(rectangle.getHeight(),
				rectangle.getUnits());
		totalArea += convertedWidth * convertedHeight;
	}

	@Override
	public void visit(Triangle triangle) {
		totalArea += 0.5 * triangle.getSideA() * triangle.getSideB()
				* Math.sin(convertDegreesToRadians(triangle.getAngle()));
	}

	/**
	 * Returns the total area of the all visited shapes
	 * 
	 * @return the total area
	 */
	public double getTotalArea() {
		return totalArea;
	}
}
