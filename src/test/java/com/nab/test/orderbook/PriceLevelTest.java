package com.nab.test.orderbook;

import static com.nab.test.test.TestUtils.buildOrderData;
import static com.nab.test.test.TestUtils.doPriceLevelTest;
import static com.nab.test.test.TestUtils.buildPriceLevel;
import static com.nab.test.test.TestUtils.PRICE;
import static com.nab.test.test.TestUtils.BUY_SIDE;
import static com.nab.test.test.TestUtils.PARTICIPANT_1;
import static com.nab.test.test.TestUtils.PARTICIPANT_2;
import static com.nab.test.test.TestUtils.PARTICIPANT_3;
import static com.nab.test.test.TestUtils.PRICE_DBL;
import static com.nab.test.test.TestUtils.SELL_SIDE;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nab.test.message.OrderData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceLevelTest {

    private PriceLevel priceLevel;

    @BeforeEach
    public void before() {
        priceLevel = new PriceLevel(PRICE, BUY_SIDE);
    }

    @Test
    public void testBuySideAddOrder() {
        doPriceLevelTest(priceLevel, 0, BUY_SIDE, PRICE_DBL, null, null);
        long total = priceLevel.getTotalQuantity();
        assertEquals(0, total);

        OrderData od1 = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                PRICE_DBL, 1_000_000);
        priceLevel.addOrder(od1);
        doPriceLevelTest(priceLevel, 1, BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_1}, new long[]{1_000_000});

        OrderData od2 = buildOrderData(PARTICIPANT_2, BUY_SIDE,
                PRICE_DBL, 2_000_000);
        priceLevel.addOrder(od2);
        doPriceLevelTest(priceLevel, 2, BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_2, PARTICIPANT_1},
                new long[]{2_000_000, 1_000_000});

        OrderData od3 = buildOrderData(PARTICIPANT_3, BUY_SIDE,
                PRICE_DBL, 2_000_000);
        priceLevel.addOrder(od3);
        doPriceLevelTest(priceLevel, 3, BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_3, PARTICIPANT_2, PARTICIPANT_1},
                new long[]{2_000_000, 2_000_000, 1_000_000});
    }

    @Test
    public void testBuySideUpdateOrder() {
        OrderData od = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                PRICE_DBL, 3_000_000);
        priceLevel.updateOrder(od);
        doPriceLevelTest(priceLevel, 0, BUY_SIDE, PRICE_DBL,
                null, null);
    }

    @Test
    public void testBuySideUpdateOrder2() {
        priceLevel = buildPriceLevel(BUY_SIDE, PRICE_DBL,
        new String[]{PARTICIPANT_2, PARTICIPANT_3, PARTICIPANT_1},
                new long[]{1_000_000, 2_000_000, 2_000_001});

        doPriceLevelTest(priceLevel, 3, BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_1, PARTICIPANT_3, PARTICIPANT_2},
                new long[]{2_000_001, 2_000_000, 1_000_000});

        OrderData od = buildOrderData(PARTICIPANT_2, BUY_SIDE,
                PRICE_DBL, 3_000_000);
        priceLevel.updateOrder(od);

        doPriceLevelTest(priceLevel, 3, BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_2, PARTICIPANT_1, PARTICIPANT_3},
                new long[]{3_000_000, 2_000_001, 2_000_000});
    }

    @Test
    public void testBuySideRemoveOrder() {
        priceLevel = buildPriceLevel(BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_2, PARTICIPANT_3, PARTICIPANT_1},
                new long[]{1_000_000, 2_000_000, 2_000_001});

        OrderData od = buildOrderData(PARTICIPANT_1, BUY_SIDE,
                PRICE_DBL, 2_000_001);
        priceLevel.removeOrder(od);

        doPriceLevelTest(priceLevel, 2, BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_3, PARTICIPANT_2},
                new long[]{2_000_000, 1_000_000});
    }

    @Test
    public void testSellSide() {
        doPriceLevelTest(priceLevel, 0, SELL_SIDE, PRICE_DBL, null, null);

        OrderData od1 = buildOrderData(PARTICIPANT_2, SELL_SIDE,
                PRICE_DBL, 2_000_000);
        priceLevel.addOrder(od1);
        doPriceLevelTest(priceLevel, 1, SELL_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_2}, new long[]{2_000_000});

        OrderData od2 = buildOrderData(PARTICIPANT_1, SELL_SIDE,
                PRICE_DBL, 1_000_000);
        priceLevel.addOrder(od2);
        doPriceLevelTest(priceLevel, 2, SELL_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_2, PARTICIPANT_1},
                new long[]{2_000_000, 1_000_000});

        OrderData od3 = buildOrderData(PARTICIPANT_3, SELL_SIDE,
                PRICE_DBL, 2_000_000);
        priceLevel.addOrder(od3);
        doPriceLevelTest(priceLevel, 3, SELL_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_3, PARTICIPANT_2, PARTICIPANT_1},
                new long[]{2_000_000, 2_000_000, 1_000_000});
    }

    @Test
    public void testResetEmptyLevel() {
        doPriceLevelTest(priceLevel, 0, BUY_SIDE, PRICE_DBL, null, null);
        priceLevel.reset();
        doPriceLevelTest(priceLevel, 0, BUY_SIDE, PRICE_DBL, null, null);
    }

    @Test
    public void testReset() {
        priceLevel = buildPriceLevel(BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_2, PARTICIPANT_3, PARTICIPANT_1},
                new long[]{1_000_000, 2_000_000, 2_000_001});

        doPriceLevelTest(priceLevel, 3, BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_1, PARTICIPANT_3, PARTICIPANT_2},
                new long[]{2_000_001, 2_000_000, 1_000_000});

        priceLevel.reset();
        doPriceLevelTest(priceLevel, 0, BUY_SIDE, PRICE_DBL, null, null);
    }

    @Test
    public void testGetTotalQuantity() {
        priceLevel = buildPriceLevel(BUY_SIDE, PRICE_DBL,
                new String[]{PARTICIPANT_2, PARTICIPANT_3, PARTICIPANT_1},
                new long[]{1_000_000, 2_000_000, 2_000_001});

        long total = priceLevel.getTotalQuantity();
        assertEquals(5_000_001, total);
    }
}
