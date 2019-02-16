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
 * Tests for the {@link PerimeterVisitor} class.
 *
 * @author bakenov
 *
 */
public class PerimeterVisitorTest extends CommonTest {

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new PerimeterVisitor(null);
	}

	@Test
	public void testCirclePerimeter() {
		double radius = 20;
		DistanceUnits units = METER;
		IShape shape = buildCircle(radius, units);

		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		double areaMeters = 2. * Math.PI * radius; // in meters
		double areaCents = areaMeters * 100; // in centimeters

		assertEquals(areaCents, testLength, EPSILON);
	}

	@Test
	public void testCirclePerimeterZero() {
		IShape shape = buildCircle(0, METER);
		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		assertEquals(0.0, testLength, EPSILON);
	}

	@Test
	public void testCirclePerimeterNaN() {
		IShape shape = buildCircle(Double.NaN, METER);
		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		assertEquals(0.0, testLength, EPSILON);
	}

	@Test
	public void testTwoCirclesPerimeter() {
		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		IShape shape1 = buildCircle(Double.NaN, METER);
		double radius = 20;
		DistanceUnits units = METER;
		IShape shape2 = buildCircle(radius, units);

		shape1.accept(visitor);
		shape2.accept(visitor);

		double testLength = visitor.getTotalLength();
		double pMeters = 2 * Math.PI * radius; // in meters
		double pCents = pMeters * 100; // in centimeters
		assertEquals(pCents, testLength, EPSILON);
	}

	@Test
	public void testThreeCirclesPerimeter() {
		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		double radius = 20;
		DistanceUnits units = METER;
		IShape shape1 = buildCircle(radius, units);
		IShape shape2 = buildCircle(Double.NaN, METER);
		IShape shape3 = buildCircle(radius, units);

		shape1.accept(visitor);
		shape2.accept(visitor);
		shape3.accept(visitor);

		double testLength = visitor.getTotalLength();
		double pMeters = 2 * Math.PI * radius; // in meters
		double pCents = pMeters * 100; // in centimeters
		assertEquals(2. * pCents, testLength, EPSILON);
	}

	@Test
	public void testRectanglePerimeter() {
		double width = 10;
		double height = 10;
		DistanceUnits units = METER;
		IShape shape = buildRectangle(width, height, units);

		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		double pMeters = 4 * 10; // in meters
		double pCents = pMeters * 100; // in centimeters
		assertEquals(pCents, testLength, EPSILON);
	}

	@Test
	public void testRectangleZeroPerimeter() {
		double width = 0;
		double height = 10;
		DistanceUnits units = METER;
		IShape shape = buildRectangle(width, height, units);

		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		assertEquals(0.0, testLength, EPSILON);
	}

	@Test
	public void testRectangleZeroPerimeter2() {
		double width = 10;
		double height = Double.NaN;
		DistanceUnits units = METER;
		IShape shape = buildRectangle(width, height, units);

		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		assertEquals(0.0, testLength, EPSILON);
	}

	@Test
	public void testThreeRectanglesPerimeter() {
		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		IShape shape1 = buildRectangle(10, 10, METER);
		IShape shape2 = buildRectangle(10, 10, METER);
		IShape shape3 = buildRectangle(Double.NaN, 10, METER);

		shape1.accept(visitor);
		shape2.accept(visitor);
		shape3.accept(visitor);

		double testLength = visitor.getTotalLength();
		double pMeters = 2 * 40; // in meters
		double pCents = pMeters * 100; // in centimeters
		assertEquals(pCents, testLength, EPSILON);
	}

	@Test
	public void testTriangleZeroPerimeter() {
		double a = 10;
		double b = 10;
		double angle = 0;
		DistanceUnits units = METER;
		IShape shape = buildTriangle(a, b, angle, units);

		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		assertEquals(0.0, testLength, EPSILON);
	}

	@Test
	public void testTriangleZeroPerimeter2() {
		double a = 0;
		double b = 10;
		double angle = 60;
		DistanceUnits units = METER;
		IShape shape = buildTriangle(a, b, angle, units);

		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		assertEquals(0.0, testLength, EPSILON);
	}

	@Test
	public void testTriangleZeroPerimeter3() {
		double a = 10;
		double b = Double.NaN;
		double angle = 60;
		DistanceUnits units = METER;
		IShape shape = buildTriangle(a, b, angle, units);

		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);
		shape.accept(visitor);
		double testLength = visitor.getTotalLength();
		assertEquals(0.0, testLength, EPSILON);
	}

	@Test
	public void testThreeTrianglesPerimeter() {
		PerimeterVisitor visitor = new PerimeterVisitor(CENTIMETER);

		double a = 10;
		double b = 10;
		double angle = 60;
		IShape shape1 = buildTriangle(a, b, angle, METER);
		IShape shape2 = buildTriangle(a, b, angle, METER);
		IShape shape3 = buildTriangle(Double.NaN, b, angle, METER);

		shape1.accept(visitor);
		shape2.accept(visitor);
		shape3.accept(visitor);

		double testLength = visitor.getTotalLength();
		double pMeters = 2 * 30.; // in meters
		double pCents = pMeters * 100; // in centimeters
		assertEquals(pCents, testLength, EPSILON);
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
