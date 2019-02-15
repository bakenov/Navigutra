package imc.visitor.shape.visitor;

import imc.visitor.shape.Circle;
import imc.visitor.shape.Rectangle;
import imc.visitor.shape.Triangle;

/**
 * A Shape visitor
 * 
 * @author bakenov
 *
 */
public interface IVisitor {

	/**
	 * Visits the Circle shape
	 * 
	 * @param circle the circle shape
	 */
	void visit(Circle circle);

	/**
	 * Visits the rectangle shape
	 * 
	 * @param rectangle the rectangle shape
	 */
	void visit(Rectangle rectangle);

	/**
	 * Visits the triangle shape
	 * 
	 * @param circle the triangle shape
	 */
	void visit(Triangle triangle);

}
