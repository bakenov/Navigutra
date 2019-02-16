package imc.visitor.shape;

import static imc.visitor.utils.DistanceUnits.CENTIMETER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import imc.visitor.utils.DistanceUnits;

/**
 * This is a test for the {@link Circle} class.
 *
 * @author bakenov
 *
 */
public class CircleTest {

	private static final double EPSILON = 1.e-10;

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new Circle(-10.0, DistanceUnits.CENTIMETER);
	}

	@Test
	public void testCircle() {
		double radius = 0.0;
		Circle circle = new Circle(radius, CENTIMETER);
		assertEquals(radius, circle.getRadius(), EPSILON);
		radius = 5.0;
		circle = new Circle(radius, CENTIMETER);
		assertEquals(radius, circle.getRadius(), EPSILON);
		radius = Double.MAX_VALUE;
		circle = new Circle(radius, CENTIMETER);
		assertEquals(radius, circle.getRadius(), EPSILON);
		radius = Double.NaN;
		circle = new Circle(radius, CENTIMETER);
		assertEquals(radius, circle.getRadius(), EPSILON);
		assertEquals(CENTIMETER, circle.getUnits());
		assertEquals(ShapeType.CIRCLE, circle.getType());
	}

}
