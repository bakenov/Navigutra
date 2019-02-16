package imc.visitor.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import imc.visitor.shape.CommonTest;

/**
 * Tests for the {@link DistanceUnits} class.
 *
 * @author bakenov
 *
 */
public class DistanceUnitsTest extends CommonTest {

	@Test
	public void toMillimeters() {
		DistanceUnits units = DistanceUnits.MILLIMETER;
		assertEquals(123., units.toMillimeters(123.), EPSILON);

		units = DistanceUnits.CENTIMETER;
		assertEquals(123. * 10, units.toMillimeters(123.), EPSILON);

		units = DistanceUnits.METER;
		assertEquals(123. * 1000, units.toMillimeters(123.), EPSILON);
	}

	@Test
	public void toCentimeters() {
		DistanceUnits units = DistanceUnits.MILLIMETER;
		assertEquals(12.3, units.toCentimeters(123.), EPSILON);

		units = DistanceUnits.CENTIMETER;
		assertEquals(123., units.toCentimeters(123.), EPSILON);

		units = DistanceUnits.METER;
		assertEquals(123. * 100, units.toCentimeters(123.), EPSILON);
	}

	@Test
	public void toMeters() {
		DistanceUnits units = DistanceUnits.MILLIMETER;
		assertEquals(0.123, units.toMeters(123.), EPSILON);

		units = DistanceUnits.CENTIMETER;
		assertEquals(1.23, units.toMeters(123.), EPSILON);

		units = DistanceUnits.METER;
		assertEquals(123., units.toMeters(123.), EPSILON);
	}

	@Test
	public void convert() {
		DistanceUnits units = DistanceUnits.MILLIMETER;
		assertEquals(123 * 10, units.convert(123., DistanceUnits.CENTIMETER),
				EPSILON);

		units = DistanceUnits.CENTIMETER;
		assertEquals(123 * 100, units.convert(123., DistanceUnits.METER), EPSILON);

		units = DistanceUnits.METER;
		assertEquals(0.123, units.convert(123., DistanceUnits.MILLIMETER), EPSILON);
	}

}
