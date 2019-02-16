package imc.visitor.shape.visitor;

import static imc.visitor.utils.DistanceUnits.CENTIMETER;
import static imc.visitor.utils.DistanceUnits.METER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import imc.visitor.shape.CommonTest;
import imc.visitor.shape.IShape;
import imc.visitor.shape.factory.ShapeFactory;
import imc.visitor.utils.DistanceUnits;

/**
 * Tests for the {@link AreaVisitor} class.
 *
 * @author bakenov
 *
 */
public class AreaVisitorTest extends CommonTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new AreaVisitor(null);
	}

	@Test
	public void testCircleArea() {
		double radius = 20;
		DistanceUnits units = METER;
		IShape shape = buildCircle(radius, units);

		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		shape.accept(visitor);
		double testArea = visitor.getTotalArea();
		double areaMeters = Math.PI * radius * radius; // in meters
		double areaCents = areaMeters * 10000; // in centimeters

		assertEquals(areaCents, testArea, EPSILON);
	}

	@Test
	public void testCircleAreaZero() {
		IShape shape = buildCircle(0, METER);
		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		shape.accept(visitor);
		double testArea = visitor.getTotalArea();
		assertEquals(0.0, testArea, EPSILON);
	}

	@Test
	public void testCircleAreaNaN() {
		IShape shape = buildCircle(Double.NaN, METER);
		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		shape.accept(visitor);
		double testArea = visitor.getTotalArea();
		assertEquals(0.0, testArea, EPSILON);
	}

	@Test
	public void testTwoCirclesArea() {
		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		IShape shape1 = buildCircle(Double.NaN, METER);
		double radius = 20;
		DistanceUnits units = METER;
		IShape shape2 = buildCircle(radius, units);

		shape1.accept(visitor);
		shape2.accept(visitor);

		double testArea = visitor.getTotalArea();
		double areaMeters = Math.PI * radius * radius; // in meters
		double areaCents = areaMeters * 10000; // in centimeters
		assertEquals(areaCents, testArea, EPSILON);
	}

	@Test
	public void testTwoCirclesArea2() {
		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		double radius = 20;
		DistanceUnits units = METER;
		IShape shape1 = buildCircle(radius, units);
		IShape shape2 = buildCircle(Double.NaN, METER);

		shape1.accept(visitor);
		shape2.accept(visitor);

		double testArea = visitor.getTotalArea();
		double areaMeters = Math.PI * radius * radius; // in meters
		double areaCents = areaMeters * 10000; // in centimeters
		assertEquals(areaCents, testArea, EPSILON);
	}

	@Test
	public void testRectangleArea() {
		double width = 10;
		double height = 10;
		DistanceUnits units = METER;
		IShape shape = buildRectangle(width, height, units);

		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		shape.accept(visitor);
		double testArea = visitor.getTotalArea();
		double areaMeters = 100; // in meters
		double areaCents = areaMeters * 10000; // in centimeters
		assertEquals(areaCents, testArea, EPSILON);
	}

	@Test
	public void testRectangleZeroArea() {
		double width = 0;
		double height = 10;
		DistanceUnits units = METER;
		IShape shape = buildRectangle(width, height, units);

		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		shape.accept(visitor);
		double testArea = visitor.getTotalArea();
		assertEquals(0.0, testArea, EPSILON);
	}

	@Test
	public void testThreeRectanglesArea() {
		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		IShape shape1 = buildRectangle(10, 10, METER);
		IShape shape2 = buildRectangle(10, 10, METER);
		IShape shape3 = buildRectangle(Double.NaN, 10, METER);

		shape1.accept(visitor);
		shape2.accept(visitor);
		shape3.accept(visitor);

		double testArea = visitor.getTotalArea();
		double areaMeters = 200; // in meters
		double areaCents = areaMeters * 10000; // in centimeters
		assertEquals(areaCents, testArea, EPSILON);
	}

	@Test
	public void testTriangleZeroArea() {
		double a = 10;
		double b = 10;
		double angle = 0;
		DistanceUnits units = METER;
		IShape shape = buildTriangle(a, b, angle, units);

		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		shape.accept(visitor);
		double testArea = visitor.getTotalArea();
		assertEquals(0.0, testArea, EPSILON);
	}

	@Test
	public void testTriangleZeroArea2() {
		double a = 0;
		double b = 10;
		double angle = 60;
		DistanceUnits units = METER;
		IShape shape = buildTriangle(a, b, angle, units);

		AreaVisitor visitor = new AreaVisitor(CENTIMETER);
		shape.accept(visitor);
		double testArea = visitor.getTotalArea();
		assertEquals(0.0, testArea, EPSILON);
	}

	private IShape buildCircle(double radius, DistanceUnits units) {
		ShapeFactory factory = new ShapeFactory(units);
		return factory.createShape(radius);
	}

	private IShape buildRectangle(double w, double h, DistanceUnits units) {
		ShapeFactory factory = new ShapeFactory(units);
		return factory.createShape(w, h);
	}

	private IShape buildTriangle(double a, double b, double angle,
			DistanceUnits units) {
		ShapeFactory factory = new ShapeFactory(units);
		return factory.createShape(a, b, angle);
	}

}
