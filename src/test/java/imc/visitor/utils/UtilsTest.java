package imc.visitor.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import imc.visitor.shape.CommonTest;

/**
 * Tests for the {@link Utils} class.
 *
 * @author bakenov
 *
 */
public class UtilsTest extends CommonTest {

	@Test
	public void convertDegreesToRadians() {
		double degree = 60;
		assertEquals(Math.PI / 3., Utils.convertDegreesToRadians(degree), EPSILON);
	}

	@Test
	public void convertDegreesToRadians2() {
		double degree = 0.;
		assertEquals(0., Utils.convertDegreesToRadians(degree), EPSILON);
	}

	@Test
	public void convertDegreesToRadians3() {
		double degree = Double.NaN;
		assertEquals(Double.NaN, Utils.convertDegreesToRadians(degree), EPSILON);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotNull() {
		Utils.notNull(null, "test");
	}

	@Test
	public void testNotNull2() {
		Object o = new Object();
		Object o2 = Utils.notNull(o, "test");
		assertEquals(o, o2);
	}

	@Test
	public void testEquals() {
		double v1 = 1.0;
		double v2 = 1.00000000001;
		assertTrue(Utils.equals(v1, v2));
	}

	@Test
	public void testEquals2() {
		double v1 = 1.0;
		double v2 = 1.0000001;
		assertFalse(Utils.equals(v1, v2));
	}
}
