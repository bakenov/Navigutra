package imc.visitor.shape;

import imc.visitor.utils.DistanceUnits;

/**
 * Represents the common functionalities for all geometric shapes
 * 
 * @author bakenov
 *
 */
public abstract class AbstractShape implements IShape {

	private static final String NEGATIVE_PARAMETER_ERROR_MESSAGE = " cannot be negative";

	private final DistanceUnits units;
	private final ShapeType shapeType;

	/**
	 * The constructor of the circle
	 * 
	 * @param radius the radius of the circle
	 * @param units  the distance units
	 */
	public AbstractShape(ShapeType shapeType, DistanceUnits units) {
		this.shapeType = shapeType;
		this.units = units;
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

	void validateParameter(double p, String parameter) {
		if (p < 0)
			throw new IllegalArgumentException(
					parameter + NEGATIVE_PARAMETER_ERROR_MESSAGE);
	}
}
