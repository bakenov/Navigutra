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
public class AreaVisitor extends AbstractVisitor {

	private double totalArea;

	public AreaVisitor(DistanceUnits units) {
		super(units);
	}

	@Override
	public void visit(Circle circle) {
		double convertedR = convert(circle.getRadius(), circle.getUnits());
		totalArea += Math.PI * convertedR * convertedR;
	}

	@Override
	public void visit(Rectangle rectangle) {
		double convertedWidth = convert(rectangle.getWidth(), rectangle.getUnits());
		double convertedHeight = convert(rectangle.getHeight(),
				rectangle.getUnits());
		totalArea += convertedWidth * convertedHeight;
	}

	@Override
	public void visit(Triangle triangle) {
		totalArea += getTriangleArea(triangle);
	}

	/**
	 * Returns the total area of the all visited shapes
	 * 
	 * @return the total area
	 */
	public double getTotalArea() {
		return totalArea;
	}

	/**
	 * Calculates the area of the triangle
	 * 
	 * @param triangle the triangle
	 * @return the area of the triangle
	 */
	private double getTriangleArea(Triangle triangle) {
		double convertedA = getSideA(triangle);
		double convertedB = getSideB(triangle);
		return getTriangleArea(convertedA, convertedB, triangle.getAngle());
	}

	/**
	 * Calculates the area of the triangle
	 * 
	 * @param a     the first side of the triangle
	 * @param b     the second side of the triangle
	 * @param alpha the angle between side a and b in degrees
	 * @return the area of the triangle
	 */
	private double getTriangleArea(double a, double b, double alpha) {
		return 0.5 * a * b * Math.sin(convertDegreesToRadians(alpha));
	}
}
