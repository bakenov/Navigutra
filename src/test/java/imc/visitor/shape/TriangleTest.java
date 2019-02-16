package imc.visitor.shape;

import static imc.visitor.utils.DistanceUnits.CENTIMETER;
import static imc.visitor.utils.DistanceUnits.METER;
import static imc.visitor.utils.DistanceUnits.MILLIMETER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import imc.visitor.utils.DistanceUnits;

/**
 * Tests for the {@link Triangle} class.
 *
 * @author bakenov
 *
 */
public class TriangleTest extends CommonTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new Triangle(-10.0, 5.0, 30, CENTIMETER);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor1() {
		new Triangle(10.0, -5.0, 45, CENTIMETER);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor2() {
		new Triangle(-10.0, -5.0, 60, CENTIMETER);
	}

	@Test
	public void testTriangle() {
		assertTriangle(0.0, 0.0, 0.0, CENTIMETER);
		assertTriangle(0.0, 10.0, 30, CENTIMETER);
		assertTriangle(10.0, 0.0, 0.0, METER);
		assertTriangle(10.0, 10.0, 0.0, METER);
		assertTriangle(10.0, 10.0, 45, METER);
		assertTriangle(Double.MAX_VALUE, 10.0, 30, MILLIMETER);
		assertTriangle(Double.MAX_VALUE, Double.MAX_VALUE, 30, MILLIMETER);
		assertTriangle(10.0, Double.NaN, 30, CENTIMETER);
		assertTriangle(Double.NaN, Double.NaN, 30, CENTIMETER);
		assertTriangle(10.0, 10.0, Double.NaN, METER);
	}

	private void assertTriangle(double a, double b, double alpha,
			DistanceUnits units) {
		Triangle triangle = new Triangle(a, b, alpha, units);
		assertEquals(a, triangle.getSideA(), EPSILON);
		assertEquals(b, triangle.getSideB(), EPSILON);
		assertEquals(alpha, triangle.getAngle(), EPSILON);
		assertEquals(units, triangle.getUnits());
		assertEquals(ShapeType.TRIANGLE, triangle.getType());
	}

}
