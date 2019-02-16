package imc.visitor.shape.visitor;

import static imc.visitor.utils.Utils.notNull;

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
		this.units = notNull(units, "units");
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

	/**
	 * Converts the triangle's side a to distance in visitor's units
	 * 
	 * @param triangle the triangle
	 * @return the distance of the triangle's side a in visitor's units
	 */
	double getSideA(Triangle triangle) {
		return convert(triangle.getSideA(), triangle.getUnits());
	}

	/**
	 * Converts the triangle's side b to distance in visitor's units
	 * 
	 * @param triangle the triangle
	 * @return the distance of the triangle's side b in visitor's units
	 */
	double getSideB(Triangle triangle) {
		return convert(triangle.getSideB(), triangle.getUnits());
	}
}
