package dataandplot.plot.converter;

import dataandplot.model.data.range.MinMaxDouble;
import dataandplot.model.data.range.RangeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AreaToDataConverterImplTest {

    private static final int X = 0;
    private static final int Y = 1;

    private AreaToDataConverterImpl converter;

    @BeforeEach
    void setUp() {
        converter = new AreaToDataConverterImpl();
    }

    @Test
    void testOnePoint() {
        assertFalse(converter.canConvert());
        MinMaxDouble physicalRange = MinMaxDouble.of(0,0, 1, 10);
        assertThrows(RuntimeException.class, () -> converter.onDataRangeChanged(RangeType.PHYSICAL_RANGE, physicalRange));
    }

    @Test
    void testInvalidPixelArea() {
        assertFalse(converter.canConvert());
        MinMaxDouble areaRange = MinMaxDouble.of(-1,1, 1, 10);
        assertThrows(RuntimeException.class, () -> converter.onDataRangeChanged(RangeType.PIXEL_RANGE, areaRange));
    }

    @Test
    void testSimpleCase() {
        MinMaxDouble physicalRange = MinMaxDouble.of(0, 10,0, 10);
        MinMaxDouble areaRange = MinMaxDouble.of(0, 10,0, 10);
        doStandardTest(physicalRange, areaRange);
    }

    @Test
    void testCase1() {
        MinMaxDouble physicalRange = MinMaxDouble.of(10);
        MinMaxDouble areaRange = MinMaxDouble.of(20, 20);
        doStandardTest(physicalRange, areaRange);
    }

    @Test
    void testCase2() {
        MinMaxDouble physicalRange = MinMaxDouble.of(10);
        MinMaxDouble areaRange = MinMaxDouble.of(10, 10);
        doStandardTest(physicalRange, areaRange);
    }

    @Test
    void testCase3() {
        MinMaxDouble physicalRange = MinMaxDouble.of(0, 1, -100, 100);
        MinMaxDouble areaRange = MinMaxDouble.of(20, 20);
        doStandardTest(physicalRange, areaRange);
    }

    private void doStandardTest(MinMaxDouble physicalRange, MinMaxDouble areaRange) {
        assertFalse(converter.canConvert());
        converter.onDataRangeChanged(RangeType.PHYSICAL_RANGE, physicalRange);
        assertFalse(converter.canConvert());
        converter.onDataRangeChanged(RangeType.PIXEL_RANGE, areaRange);
        assertTrue(converter.canConvert());

        List<double[]> physicalPoints = buildDoublePoints(physicalRange);
        List<float[]> areaPoints = buildFloatPoints(areaRange);

        for (int i = 0; i < physicalPoints.size(); i++) {
            testConversion(physicalPoints.get(i), areaPoints.get(i));
        }
    }

    private List<double[]> buildDoublePoints(MinMaxDouble range) {
        List<double[]> list = new ArrayList<>();
        list.add(new double[] {range.minX(), range.minY()});
        list.add(new double[] {range.maxX(), range.minY()});
        list.add(new double[] {range.maxX(), range.maxY()});
        list.add(new double[] {range.minX(), range.maxY()});
        list.add(new double[] {range.minX() + range.width() / 2., range.minY() + range.height() / 2.});
        return list;
    }

    private List<float[]> buildFloatPoints(MinMaxDouble areaRange) {
        List<float[]> list = new ArrayList<>();
        list.add(new float[] {(float) areaRange.minX(), (float) areaRange.minY()});
        list.add(new float[] {(float) areaRange.maxX(), (float) areaRange.minY()});
        list.add(new float[] {(float) areaRange.maxX(), (float) areaRange.maxY()});
        list.add(new float[] {(float) areaRange.minX(), (float) areaRange.maxY()});
        list.add(new float[] {(float) (areaRange.width() / 2.), (float) (areaRange.height() / 2.)});
        return list;
    }

    private void testConversion(double[] physicalPoint, float[] expectedAreaPoint) {
        float[] areaPoint = converter.scalePhysicalToPixel(physicalPoint);
        assertArrayEquals(expectedAreaPoint, areaPoint);
        double[] reversedPhysicalPoint = converter.pixelToPhysical(areaPoint[X], areaPoint[Y]);
        assertArrayEquals(physicalPoint, reversedPhysicalPoint);
    }
}
