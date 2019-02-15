package imc.visitor.shape;

import imc.visitor.shape.visitor.IVisitor;

/**
 * Represents geometric shapes
 * 
 * @author bakenov
 *
 */
public interface IShape {

	/**
	 * Returns the shape type
	 * 
	 * @return the shape type
	 */
	ShapeType getType();

	/**
	 * Accepts the shape visitor
	 * 
	 * @param visitor a shape visitor
	 */
	void accept(IVisitor visitor);

}
