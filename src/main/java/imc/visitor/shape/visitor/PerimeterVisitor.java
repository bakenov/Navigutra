package imc.visitor.shape.visitor;

import static imc.visitor.utils.Utils.convertDegreesToRadians;
import static imc.visitor.utils.Utils.isZeroOrNaN;

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
		double p = getPerimeter(circle);
		totalLength += p != p ? 0. : p;
	}

	@Override
	public void visit(Rectangle rectangle) {
		double p = getPerimeter(rectangle);
		totalLength += p != p ? 0. : p;
	}

	@Override
	public void visit(Triangle triangle) {
		double p = getPerimeter(triangle);
		totalLength += p != p ? 0. : p;
	}

	/**
	 * Calculates the perimeter of the circle
	 * 
	 * @param circle the circle
	 * @return the perimeter of the circle
	 */
	private double getPerimeter(Circle circle) {
		double convertedR = convert(circle.getRadius(), circle.getUnits());
		return 2.0 * Math.PI * convertedR;
	}

	/**
	 * Calculates the perimeter of the rectangle. If one of the side is zero or
	 * NaN, the perimeter is zero.
	 * 
	 * @param rectangle the rectangle
	 * @return the perimeter of the rectangle
	 */
	private double getPerimeter(Rectangle rectangle) {
		double w = rectangle.getWidth();
		double h = rectangle.getHeight();
		if (isZeroOrNaN(w) || isZeroOrNaN(h))
			return 0.;
		double convertedWidth = convert(w, rectangle.getUnits());
		double convertedHeight = convert(h, rectangle.getUnits());
		return 2. * convertedWidth + 2. * convertedHeight;
	}

	/**
	 * Calculates the triangle's perimeter
	 * 
	 * @param triangle the triangle
	 * @return the perimeter of the triangle
	 */
	private double getPerimeter(Triangle triangle) {
		double a = triangle.getSideA();
		double b = triangle.getSideB();
		double angle = triangle.getAngle();
		if (isZeroOrNaN(a) || isZeroOrNaN(b) || isZeroOrNaN(angle))
			return 0.;
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
