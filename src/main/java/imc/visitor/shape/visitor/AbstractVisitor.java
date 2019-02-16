package imc.visitor.shape.visitor;

import imc.visitor.shape.Triangle;
import imc.visitor.utils.DistanceUnits;

/**
 * The abstract visitor of the geometric shapes. Provides common functionalities
 * for all shape visitors
 * 
 * @author bakenov
 *
 */
abstract class AbstractVisitor implements IVisitor {

	private final DistanceUnits units;

	public AbstractVisitor(DistanceUnits units) {
		this.units = units;
	}

	/**
	 * Converts the specified distance in source units to the distance in
	 * visitor's units
	 * 
	 * @param d          the distance to be converted
	 * @param sourceUnit the units of the specified distance
	 * @return the distance in visitor's units
	 */
	double convert(double d, DistanceUnits sourceUnit) {
		return units.convert(d, sourceUnit);
	}

	/**
	 * Returns the distance units
	 * 
	 * @return the distance units
	 */
	public DistanceUnits getUnits() {
		return units;
	}

	double getSideA(Triangle triangle) {
		return convert(triangle.getSideA(), triangle.getUnits());
	}

	double getSideB(Triangle triangle) {
		return convert(triangle.getSideB(), triangle.getUnits());
	}
}
