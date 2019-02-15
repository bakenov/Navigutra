package imc.visitor.shape;

import imc.visitor.shape.visitor.IVisitor;

public class Rectangle implements IShape {

	private final double width;
	private final double height;

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public ShapeType getType() {
		return ShapeType.RECTANGLE;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Returns the width of the rectangle
	 * 
	 * @return the width of the rectangle
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the height of the rectangle
	 * 
	 * @return the height of the rectangle
	 */
	public double getHeight() {
		return height;
	}
}