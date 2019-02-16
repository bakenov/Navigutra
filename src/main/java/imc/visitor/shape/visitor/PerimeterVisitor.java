package imc.visitor.shape.visitor;

import static imc.visitor.utils.Utils.convertDegreesToRadians;

import imc.visitor.shape.Circle;
import imc.visitor.shape.Rectangle;
import imc.visitor.shape.Triangle;
import imc.visitor.utils.DistanceUnits;

/**
 * The visitor of the geometric shapes, specializing in calculation of the
 * shapes's perimeter
 * 
 * @author bakenov
 *
 */
public class PerimeterVisitor extends AbstractVisitor {

	private double totalLength;

	public PerimeterVisitor(DistanceUnits units) {
		super(units);
	}

	@Override
	public void visit(Circle circle) {
		double convertedR = convert(circle.getRadius(), circle.getUnits());
		totalLength += 2.0 * Math.PI * convertedR;
	}

	@Override
	public void visit(Rectangle rectangle) {
		double convertedWidth = convert(rectangle.getWidth(), rectangle.getUnits());
		double convertedHeight = convert(rectangle.getHeight(),
				rectangle.getUnits());
		totalLength += 4.0 * convertedWidth * convertedHeight;
	}

	@Override
	public void visit(Triangle triangle) {
		totalLength += getPerimeter(triangle);
	}

	/**
	 * Calculates the triangle's perimeter
	 * 
	 * @param triangle the triangle
	 * @return the perimeter of the triangle
	 */
	private double getPerimeter(Triangle triangle) {
		double convertedA = getSideA(triangle);
		double convertedB = getSideB(triangle);
		double c = getC(convertedA, convertedB, triangle.getAngle());
		return convertedA + convertedB + c;
	}

	/**
	 * Calculates the third side of the triangle
	 * 
	 * @param a     the first side of the triangle
	 * @param b     the second side of the triangle
	 * @param alpha the angle between side a and b in degrees
	 * @return the third side of the triangle
	 */
	private double getC(double a, double b, double alpha) {
		return Math.sqrt(
				a * a + b * b - 2.0 * a * b * Math.cos(convertDegreesToRadians(alpha)));
	}

	/**
	 * Returns the total length of the all visited shapes
	 * 
	 * @return the total length
	 */
	public double getTotalLength() {
		return totalLength;
	}

}
