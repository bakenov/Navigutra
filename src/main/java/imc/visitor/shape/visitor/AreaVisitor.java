package imc.visitor.shape.visitor;

import imc.visitor.shape.Circle;
import imc.visitor.shape.Rectangle;
import imc.visitor.shape.Triangle;

/**
 * The visitor of the geometric shapes, specializing in calculation of the
 * shapes's area
 * 
 * @author bakenov
 *
 */
public class AreaVisitor implements IVisitor {

	private double totalArea;

	@Override
	public void visit(Circle circle) {
		double r = circle.getRadius();
		totalArea += Math.PI * r * r;

	}

	@Override
	public void visit(Rectangle rectangle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Triangle triangle) {
		// TODO Auto-generated method stub

	}

	public double getTotalArea() {
		return totalArea;
	}

}
