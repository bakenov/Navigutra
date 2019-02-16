package imc.visitor.shape;

import static imc.visitor.shape.ShapeType.RECTANGLE;
import static imc.visitor.utils.Utils.notNegative;

import imc.visitor.shape.visitor.IVisitor;
import imc.visitor.utils.DistanceUnits;

/**
 * Represents the rectangle geometric shape
 * 
 * @author bakenov
 *
 */
public class Rectangle extends AbstractShape {

	private final double width;
	private final double height;

	/**
	 * Constructor for the rectangle
	 * 
	 * @param width  the width of the rectangle
	 * @param height the height of the rectangle
	 * @param units  the distance units
	 */
	public Rectangle(double width, double height, DistanceUnits units) {
		super(RECTANGLE, units);
		this.width = notNegative(width, "width");
		this.height = notNegative(height, "height");
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

	@Override
	public String toString() {
		return "Rectangle(" + format(width) + "|" + format(height) + "|"
				+ getUnits().getSymbol() + ")";
	}
}