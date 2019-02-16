package imc.visitor.shape;

import static imc.visitor.utils.DistanceUnits.CENTIMETER;
import static imc.visitor.utils.DistanceUnits.METER;
import static imc.visitor.utils.DistanceUnits.MILLIMETER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import imc.visitor.utils.DistanceUnits;

/**
 * Tests for the {@link Rectangle} class.
 *
 * @author bakenov
 *
 */
public class RectangleTest extends CommonTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new Rectangle(-10.0, 5.0, CENTIMETER);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor1() {
		new Rectangle(10.0, -5.0, CENTIMETER);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor2() {
		new Rectangle(-10.0, -5.0, CENTIMETER);
	}

	@Test
	public void testRectangle() {
		assertRect(0.0, 0.0, CENTIMETER);
		assertRect(0.0, 10.0, CENTIMETER);
		assertRect(10.0, 0.0, METER);
		assertRect(10.0, 10.0, METER);
		assertRect(Double.MAX_VALUE, 10.0, MILLIMETER);
		assertRect(Double.MAX_VALUE, Double.MAX_VALUE, MILLIMETER);
		assertRect(10.0, Double.NaN, CENTIMETER);
		assertRect(Double.NaN, Double.NaN, CENTIMETER);
	}

	private void assertRect(double width, double height, DistanceUnits units) {
		Rectangle rect = new Rectangle(width, height, units);
		assertEquals(width, rect.getWidth(), EPSILON);
		assertEquals(height, rect.getHeight(), EPSILON);
		assertEquals(units, rect.getUnits());
		assertEquals(ShapeType.RECTANGLE, rect.getType());
	}
}
