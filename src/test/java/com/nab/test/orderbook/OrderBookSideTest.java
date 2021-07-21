package com.nab.test.orderbook;

import com.nab.test.bo.Side;
import com.nab.test.message.OrderData;

import static com.nab.test.test.TestUtils.PARTICIPANT_1;
import static com.nab.test.test.TestUtils.PARTICIPANT_2;
import static com.nab.test.test.TestUtils.PARTICIPANT_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.nab.test.test.TestUtils.*;
import static com.nab.test.utils.LongUtils.LONG_98_EPSILON;
import static com.nab.test.utils.LongUtils.doubleToLong98;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderBookSideTest {

    public static final double BID_DBL_1 = .7886;
    public static final double BID_DBL_2 = .7884;
    public static final double BID_DBL_3 = .7882;


    private OrderBookSide orderBookSide;

    @BeforeEach
    public void before() {
        orderBookSide = new OrderBookSide(Side.BUY, 64);
    }

    @Test
    public void testBuyBookSide() {
        assertEquals(orderBookSide.getSide(), Side.BUY);
        List<OrderData> orders1 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{2_000_000, 2_000_000, 1_000_000});
        orderBookSide.update(PARTICIPANT_1, orders1);

        List<PriceLevelTestData> testData = new ArrayList<>();
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_1,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_2,
                new String[]{PARTICIPANT_1}, new long[]{1_000_000}));
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_3,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));

        doBookSideTest(orderBookSide, 3, testData);

        System.out.println("testBuyBookSide() 1  orderBookSide:\n" + orderBookSide);

        List<OrderData> orders2 = buildOrderData(PARTICIPANT_2, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{4_000_000, 4_000_000, 500_000});
        orderBookSide.update(PARTICIPANT_2, orders2);

        testData.clear();
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_1,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 2_000_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_2,
                new String[]{PARTICIPANT_1, PARTICIPANT_2}, new long[]{1_000_000, 500_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_3,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 2_000_000}));

        doBookSideTest(orderBookSide, 3, testData);

        System.out.println("testBuyBookSide() 2  orderBookSide:\n" + orderBookSide);

        List<OrderData> orders3 = buildOrderData(PARTICIPANT_3, BUY_SIDE,
                new double[]{BID_DBL_1},
                new long[]{4_000_000});
        orderBookSide.update(PARTICIPANT_3, orders3);

        testData.clear();
        testData.add(new PriceLevelTestData(3, Side.BUY, BID_DBL_1,
                new String[]{PARTICIPANT_3, PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 4_000_000, 2_000_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_2,
                new String[]{PARTICIPANT_1, PARTICIPANT_2}, new long[]{1_000_000, 500_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_3,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 2_000_000}));
        System.out.println("testBuyBookSide() 3  orderBookSide:\n" + orderBookSide);
    }

    @Test
    public void testRemoveParticipantOrders() {
        assertEquals(orderBookSide.getSide(), Side.BUY);

        List<OrderData> orders1 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{2_000_000, 2_000_000, 1_000_000});
        orderBookSide.update(PARTICIPANT_1, orders1);

        List<OrderData> orders2 = buildOrderData(PARTICIPANT_2, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{4_000_000, 4_000_000, 500_000});
        orderBookSide.update(PARTICIPANT_2, orders2);

        System.out.println("testBuyBookSideRemove() book side is built  orderBookSide:\n" + orderBookSide);

        List<PriceLevelTestData> testData = new ArrayList<>();
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_1,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 2_000_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_2,
                new String[]{PARTICIPANT_1, PARTICIPANT_2}, new long[]{1_000_000, 500_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_3,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 2_000_000}));

        doBookSideTest(orderBookSide, 3, testData);

        List<OrderData> orders3 = buildOrderData(PARTICIPANT_3, BUY_SIDE,
                null, null);
        orderBookSide.update(PARTICIPANT_3, orders3);

        testData.clear();
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_1,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_2,
                new String[]{PARTICIPANT_1}, new long[]{1_000_000}));
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_3,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));

        doBookSideTest(orderBookSide, 3, testData);
    }

    @Test
    public void testRemoveParticipantOneOrder() {
        assertEquals(orderBookSide.getSide(), Side.BUY);

        List<OrderData> orders1 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{2_000_000, 2_000_000, 1_000_000});
        orderBookSide.update(PARTICIPANT_1, orders1);

        List<OrderData> orders2 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3},
                new long[]{2_000_000, 2_000_000});
        orderBookSide.update(PARTICIPANT_1, orders2);

        List<PriceLevelTestData> testData = new ArrayList<>();
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_1,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));
        testData.add(new PriceLevelTestData(1, Side.BUY, BID_DBL_3,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));

        doBookSideTest(orderBookSide, 2, testData);
    }

    @Test
    public void testReset() {
        assertEquals(orderBookSide.getSide(), Side.BUY);

        List<OrderData> orders1 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{2_000_000, 2_000_000, 1_000_000});
        orderBookSide.update(PARTICIPANT_1, orders1);

        List<OrderData> orders2 = buildOrderData(PARTICIPANT_2, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{4_000_000, 4_000_000, 500_000});
        orderBookSide.update(PARTICIPANT_2, orders2);

        // reset now
        orderBookSide.reset();

        orderBookSide.update(PARTICIPANT_1, orders1);
        orderBookSide.update(PARTICIPANT_2, orders2);

        List<PriceLevelTestData> testData = new ArrayList<>();
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_1,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 2_000_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_2,
                new String[]{PARTICIPANT_1, PARTICIPANT_2}, new long[]{1_000_000, 500_000}));
        testData.add(new PriceLevelTestData(2, Side.BUY, BID_DBL_3,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{4_000_000, 2_000_000}));

        doBookSideTest(orderBookSide, 3, testData);
    }

    @Test
    public void testGetTotalQuantityForPrice() {
        assertEquals(orderBookSide.getSide(), Side.BUY);
        long total = orderBookSide.getTotalQuantityForPrice(doubleToLong98(BID_DBL_1));
        assertEquals(0L, total);

        List<OrderData> orders1 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{2_000_000, 2_000_000, 1_000_000});
        orderBookSide.update(PARTICIPANT_1, orders1);

        total = orderBookSide.getTotalQuantityForPrice(doubleToLong98(BID_DBL_1));
        assertEquals(2_000_000, total);
        total = orderBookSide.getTotalQuantityForPrice(doubleToLong98(BID_DBL_2));
        assertEquals(1_000_000, total);
        total = orderBookSide.getTotalQuantityForPrice(doubleToLong98(BID_DBL_3));
        assertEquals(2_000_000, total);

        List<OrderData> orders2 = buildOrderData(PARTICIPANT_2, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{4_000_000, 4_000_000, 500_000});
        orderBookSide.update(PARTICIPANT_2, orders2);

        total = orderBookSide.getTotalQuantityForPrice(doubleToLong98(BID_DBL_1));
        assertEquals(6_000_000, total);
        total = orderBookSide.getTotalQuantityForPrice(doubleToLong98(BID_DBL_2));
        assertEquals(1_500_000, total);
        total = orderBookSide.getTotalQuantityForPrice(doubleToLong98(BID_DBL_3));
        assertEquals(6_000_000, total);
    }

    @Test
    public void testGetVwapForQuantity() {
        assertEquals(orderBookSide.getSide(), Side.BUY);
        double vwap = orderBookSide.getVwapForQuantity(10_000_000);
        assertEquals(0.0, vwap, LONG_98_EPSILON);

        List<OrderData> orders1 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{2_000_000, 2_000_000, 1_000_000});
        orderBookSide.update(PARTICIPANT_1, orders1);
        vwap = orderBookSide.getVwapForQuantity(10_000_000);

        double vwapTest = (BID_DBL_1 * 2_000_000 + BID_DBL_2 * 1_000_000 + BID_DBL_3 * 2_000_000) /
                (2_000_000 + 1_000_000 + 2_000_000);
        assertEquals(vwapTest, vwap, LONG_98_EPSILON);

        List<OrderData> orders2 = buildOrderData(PARTICIPANT_2, BUY_SIDE,
                new double[]{BID_DBL_1, BID_DBL_3, BID_DBL_2},
                new long[]{4_000_000, 4_000_000, 500_000});
        orderBookSide.update(PARTICIPANT_2, orders2);

        vwap = orderBookSide.getVwapForQuantity(5_000_000);
        vwapTest = (BID_DBL_1 * 5_000_000) / 5_000_000;
        assertEquals(vwapTest, vwap, LONG_98_EPSILON);
    }
}