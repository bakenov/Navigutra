package imc.visitor.shape;

import static imc.visitor.utils.DistanceUnits.CENTIMETER;
import static imc.visitor.utils.DistanceUnits.METER;
import static imc.visitor.utils.DistanceUnits.MILLIMETER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import imc.visitor.utils.DistanceUnits;

/**
 * Tests for the {@link Circle} class.
 *
 * @author bakenov
 *
 */
public class CircleTest extends CommonTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new Circle(-10.0, DistanceUnits.CENTIMETER);
	}

	@Test
	public void testCircle() {
		assertCircle(0.0, CENTIMETER);
		assertCircle(5.0, METER);
		assertCircle(Double.MAX_VALUE, MILLIMETER);
		assertCircle(Double.NaN, MILLIMETER);
	}

	private void assertCircle(double radius, DistanceUnits units) {
		Circle circle = new Circle(radius, units);
		assertEquals(radius, circle.getRadius(), EPSILON);
		assertEquals(units, circle.getUnits());
		assertEquals(ShapeType.CIRCLE, circle.getType());
	}

}
