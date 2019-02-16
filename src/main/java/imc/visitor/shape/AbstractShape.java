package imc.visitor.shape;

import static imc.visitor.utils.Utils.notNull;

import imc.visitor.utils.DistanceUnits;

/**
 * Represents the common functionalities for all geometric shapes
 * 
 * @author bakenov
 *
 */
public abstract class AbstractShape implements IShape {

	private final DistanceUnits units;
	private final ShapeType shapeType;

	/**
	 * The constructor of the circle
	 * 
	 * @param radius the radius of the circle
	 * @param units  the distance units
	 */
	public AbstractShape(ShapeType shapeType, DistanceUnits units) {
		this.shapeType = notNull(shapeType, "shapeType");
		this.units = notNull(units, "units");
	}

	/**
	 * Returns the distance units
	 * 
	 * @return the distance units
	 */
	public DistanceUnits getUnits() {
		return units;
	}

	@Override
	public ShapeType getType() {
		return shapeType;
	}

	String format(double value) {
		return String.format("%.2f", value);
	}
}
