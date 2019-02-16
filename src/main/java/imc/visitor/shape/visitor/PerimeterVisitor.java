package imc.visitor.shape.visitor;

import static imc.visitor.utils.Utils.convertDegreesToRadians;

import imc.visitor.shape.Circle;
import imc.visitor.shape.Rectangle;
import imc.visitor.shape.Triangle;

/**
 * The visitor of the geometric shapes, specializing in calculation of the
 * shapes's perimeter
 * 
 * @author bakenov
 *
 */
public class PerimeterVisitor implements IVisitor {

	private double totalLength;

	@Override
	public void visit(Circle circle) {
		totalLength += 2.0 * Math.PI * circle.getRadius();
	}

	@Override
	public void visit(Rectangle rectangle) {
		totalLength += 4.0 * rectangle.getWidth() * rectangle.getHeight();
	}

	@Override
	public void visit(Triangle triangle) {
		totalLength += Math.sqrt(triangle.getSideA() * triangle.getSideA()
				+ triangle.getSideB() * triangle.getSideB()
				- 2.0 * triangle.getSideA() * triangle.getSideB()
						* Math.cos(convertDegreesToRadians(triangle.getAngle())));
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
