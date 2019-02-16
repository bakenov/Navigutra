package imc.visitor.shape.factory;

import imc.visitor.shape.IShape;

/**
 * Builds the geometric shapes
 * 
 * @author bakenov
 *
 * @param <S> the type of the objects to build
 */
public interface IShapeFactory<S extends IShape> {

	/**
	 * Builds the circle
	 * 
	 * @param radius the radius of the circle
	 * @return the circle shape
	 */
	S createShape(double radius);

	/**
	 * Builds the rectangle
	 * 
	 * @param width  the width of the rectangle
	 * @param height the height of the rectangle
	 * @return the rectangle shape
	 */
	S createShape(double width, double height);

	/**
	 * Builds the triangle
	 * 
	 * @param sideA the side of the triangle
	 * @param sideB the side of the triangle
	 * @param alpha the angle between the sides in degrees
	 * @return the triangle shape
	 */
	S createShape(double sideA, double sideB, double alpha);
}
