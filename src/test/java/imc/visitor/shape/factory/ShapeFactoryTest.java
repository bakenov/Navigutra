package imc.visitor.shape.factory;

import static imc.visitor.utils.DistanceUnits.CENTIMETER;
import static imc.visitor.utils.DistanceUnits.METER;
import static imc.visitor.utils.DistanceUnits.MILLIMETER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import imc.visitor.shape.Circle;
import imc.visitor.shape.CommonTest;
import imc.visitor.shape.IShape;
import imc.visitor.shape.Rectangle;
import imc.visitor.shape.ShapeType;
import imc.visitor.shape.Triangle;
import imc.visitor.utils.DistanceUnits;

/**
 * Tests for the {@link ShapeFactory} class.
 *
 * @author bakenov
 *
 */
public class ShapeFactoryTest extends CommonTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new ShapeFactory(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCircleInvalid() {
		ShapeFactory factory = new ShapeFactory(CENTIMETER);
		factory.createShape(-20);
	}

	@Test
	public void testCreateCircle() {
		double radius = 20;
		DistanceUnits units = METER;
		ShapeFactory factory = new ShapeFactory(units);
		IShape shape = factory.createShape(radius);
		assertCircle(radius, units, shape);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateRectangleInvalid() {
		ShapeFactory factory = new ShapeFactory(CENTIMETER);
		factory.createShape(-20, 30);
	}

	@Test
	public void testCreateRectangle() {
		double width = 20;
		double height = 30;
		DistanceUnits units = METER;
		ShapeFactory factory = new ShapeFactory(units);
		IShape shape = factory.createShape(width, height);
		assertRectangle(width, height, units, shape);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateTriangleInvalid() {
		ShapeFactory factory = new ShapeFactory(CENTIMETER);
		factory.createShape(-20, 30, 30);
	}

	@Test
	public void testCreateTriangle() {
		double a = 20;
		double b = 20;
		double angle = 30;
		DistanceUnits units = MILLIMETER;
		ShapeFactory factory = new ShapeFactory(units);
		IShape shape = factory.createShape(a, b, angle);
		assertTriangle(a, b, angle, units, shape);
	}

	private void assertCircle(double radius, DistanceUnits units, IShape shape) {
		assertEquals(ShapeType.CIRCLE, shape.getType());
		Circle circle = (Circle) shape;
		assertEquals(radius, circle.getRadius(), EPSILON);
		assertEquals(units, circle.getUnits());
	}

	private void assertRectangle(double width, double height, DistanceUnits units,
			IShape shape) {
		assertEquals(ShapeType.RECTANGLE, shape.getType());
		Rectangle rect = (Rectangle) shape;
		assertEquals(width, rect.getWidth(), EPSILON);
		assertEquals(height, rect.getHeight(), EPSILON);
		assertEquals(units, rect.getUnits());
	}

	private void assertTriangle(double a, double b, double alpha,
			DistanceUnits units, IShape shape) {
		assertEquals(ShapeType.TRIANGLE, shape.getType());
		Triangle triangle = (Triangle) shape;
		assertEquals(a, triangle.getSideA(), EPSILON);
		assertEquals(b, triangle.getSideB(), EPSILON);
		assertEquals(alpha, triangle.getAngle(), EPSILON);
		assertEquals(units, triangle.getUnits());
	}

}
