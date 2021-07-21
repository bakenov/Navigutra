package com.nab.test.orderbook;

import com.nab.test.bo.Side;
import com.nab.test.message.MarketData;

import com.nab.test.test.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.nab.test.test.TestUtils.*;
import static com.nab.test.utils.LongUtils.LONG_98_EPSILON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static com.nab.test.utils.LongUtils.doubleToLong98;

public class OrderBookTest {

    private OrderBook orderBook;

    @BeforeEach
    public void before() {
        orderBook = createOrderBook();
    }

    @Test
    public void testWrongInstrument() {
        MarketData data = buildMarketData("EURUSD", PARTICIPANT_1);
        assertFalse(orderBook.update(data));
    }

    @Test
    public void testStructure() {
        MarketData data1 = buildMarketData(INSTRUMENT, PARTICIPANT_1,
                new double[]{0.7880, 0.7878}, new long[]{1_000_000, 2_000_000},
                new double[]{0.7882, 0.7883}, new long[]{1_000_000, 2_000_000});
        orderBook.update(data1);

        List<TestUtils.PriceLevelTestData> testData = new ArrayList<>();
        testData.add(new TestUtils.PriceLevelTestData(1, Side.BUY, 0.7880,
                new String[]{PARTICIPANT_1}, new long[]{1_000_000}));
        testData.add(new TestUtils.PriceLevelTestData(1, Side.BUY, 0.7878,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));

        doBookSideTest(orderBook.getOrderBookSide(Side.BUY), 2, testData);

        testData = new ArrayList<>();
        testData.add(new TestUtils.PriceLevelTestData(1, Side.SELL, 0.7882,
                new String[]{PARTICIPANT_1}, new long[]{1_000_000}));
        testData.add(new TestUtils.PriceLevelTestData(1, Side.SELL, 0.7883,
                new String[]{PARTICIPANT_1}, new long[]{2_000_000}));

        doBookSideTest(orderBook.getOrderBookSide(Side.SELL), 2, testData);

        System.out.println("test()   orderBook=" + orderBook);
        MarketData data2 = buildMarketData(INSTRUMENT, PARTICIPANT_2,
                new double[]{0.7880, 0.7878}, new long[]{1_100_000, 2_000_000},
                new double[]{0.7882, 0.7883}, new long[]{1_000_000, 2_000_000});
        orderBook.update(data2);

        testData = new ArrayList<>();
        testData.add(new TestUtils.PriceLevelTestData(1, Side.BUY, 0.7880,
                new String[]{PARTICIPANT_2, PARTICIPANT_1}, new long[]{1_100_000, 1_000_000}));
        testData.add(new TestUtils.PriceLevelTestData(1, Side.BUY, 0.7878,
                new String[]{PARTICIPANT_1, PARTICIPANT_2}, new long[]{2_000_000, 2_000_000}));

        doBookSideTest(orderBook.getOrderBookSide(Side.BUY), 2, testData);

        testData = new ArrayList<>();
        testData.add(new TestUtils.PriceLevelTestData(1, Side.SELL, 0.7882,
                new String[]{PARTICIPANT_1, PARTICIPANT_2}, new long[]{1_000_000, 1_000_000}));
        testData.add(new TestUtils.PriceLevelTestData(1, Side.SELL, 0.7883,
                new String[]{PARTICIPANT_1, PARTICIPANT_2}, new long[]{2_000_000, 2_000_000}));

        doBookSideTest(orderBook.getOrderBookSide(Side.SELL), 2, testData);


        System.out.println("test()   orderBook=" + orderBook);
    }

    @Test
    public void testReset() {
        MarketData data1 = buildMarketData(INSTRUMENT, PARTICIPANT_1,
                new double[]{0.7880, 0.7878}, new long[]{1_000_000, 2_000_000},
                new double[]{0.7882, 0.7883}, new long[]{1_000_000, 2_000_000});
        orderBook.update(data1);
        assertEquals(2, orderBook.getOrderBookSide(Side.BUY).getPriceLevels());
        assertEquals(2, orderBook.getOrderBookSide(Side.SELL).getPriceLevels());

        orderBook.reset();
        assertEquals(0, orderBook.getOrderBookSide(Side.BUY).getPriceLevels());
        assertEquals(0, orderBook.getOrderBookSide(Side.SELL).getPriceLevels());

        System.out.println("test()   orderBook=" + orderBook);
    }

    @Test
    public void testGetTotalQuantityForPriceAndSide() {
        MarketData data1 = buildMarketData(INSTRUMENT, PARTICIPANT_1,
                new double[]{0.7880, 0.7878}, new long[]{1_000_000, 2_000_000},
                new double[]{0.7882, 0.7883}, new long[]{1_100_000, 2_000_000});
        orderBook.update(data1);
        assertEquals(2, orderBook.getOrderBookSide(Side.BUY).getPriceLevels());
        assertEquals(2, orderBook.getOrderBookSide(Side.SELL).getPriceLevels());

        long q = orderBook.getTotalQuantityForPriceAndSide(doubleToLong98(0.7880), Side.BUY);
        assertEquals(1_000_000, q);
        q = orderBook.getTotalQuantityForPriceAndSide(doubleToLong98(0.7882), Side.SELL);
        assertEquals(1_100_000, q);
    }

    @Test
    public void testGetVwapForQuantityAndSide() {
        MarketData data1 = buildMarketData(INSTRUMENT, PARTICIPANT_1,
                new double[]{0.7880, 0.7878}, new long[]{1_000_000, 2_000_000},
                new double[]{0.7882, 0.7883}, new long[]{1_100_000, 2_000_000});
        orderBook.update(data1);
        assertEquals(2, orderBook.getOrderBookSide(Side.BUY).getPriceLevels());
        assertEquals(2, orderBook.getOrderBookSide(Side.SELL).getPriceLevels());

        double vwap = orderBook.getVwapForQuantityAndSide(10_000_000, Side.BUY);
        double vwapTest = (0.7880 * 1_000_000 + 0.7878 * 2_000_000) /
                (2_000_000 + 1_000_000);
        assertEquals(vwapTest, vwap, LONG_98_EPSILON);

        vwap = orderBook.getVwapForQuantityAndSide(10_000_000, Side.SELL);
        vwapTest = (0.7882 * 1_100_000 + 0.7883 * 2_000_000) /
                (2_000_000 + 1_100_000);
        assertEquals(vwapTest, vwap, LONG_98_EPSILON);
    }

}
