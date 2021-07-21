package com.nab.test.test;

import com.nab.test.bo.Side;
import com.nab.test.message.MarketData;
import com.nab.test.message.OrderData;
import com.nab.test.orderbook.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.nab.test.utils.LongUtils.doubleToLong98;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    public static final String INSTRUMENT = "AUDUSD";
    public static final int NUMBER_LEVELS = 64;
    public static final String PARTICIPANT_1 = "Broker1";
    public static final String PARTICIPANT_2 = "Broker2";
    public static final String PARTICIPANT_3 = "ABroker3";
    public static final Side BUY_SIDE = Side.BUY;
    public static final Side SELL_SIDE = Side.SELL;
    public static final double PRICE_DBL = .7880;
    public static final long PRICE = doubleToLong98(.7880);


    public static OrderBook createOrderBook() {
        return new OrderBook(INSTRUMENT, NUMBER_LEVELS);
    }

    public static MarketData buildMarketData(final String instrument, final String participant) {
        return new MarketData(participant, instrument);
    }

    public static List<OrderData> buildOrderData(final String source, final Side side,
                                                 double[] prices, long[] quantities) {
        List<OrderData> retVal = new ArrayList<>();
        if (prices == null)
            return retVal;
        for (int i = 0; i < prices.length; i++) {
            retVal.add(buildOrderData(source, side, prices[i], quantities[i]));
        }
        return retVal;
    }

    public static OrderData buildOrderData(final String source, final Side side,
                                           double price, long quantity) {
        return new OrderData(source, side, doubleToLong98(price), quantity);
    }

    public static void doPriceLevelTest(final PriceLevel priceLevel,
                                        PriceLevelTestData testData) {
        doPriceLevelTest(priceLevel, testData.getNumberOrders(),
                testData.getSide(), testData.getPriceDouble(),
                testData.getSources(), testData.getQuantities());
    }

    public static void doPriceLevelTest(final PriceLevel priceLevel, int numberOrders,
                                        Side side, double priceDouble, String[] sources,
                                        long[] quantities) {
        long priceLong = doubleToLong98(priceDouble);
        assertEquals(priceLevel.getPrice(), priceLong);
        assertEquals(priceLevel.getNumberOrders(), numberOrders);
        if (priceLevel.getNumberOrders() > 0) {
            assertEquals(priceLevel.getNumberOrders(), sources.length);
            assertEquals(priceLevel.getNumberOrders(), quantities.length);
            Iterator<OrderData> iter = priceLevel.getOrderIterator();
            int i = 0;
            while(iter.hasNext()) {
                OrderData o = iter.next();
                assertEquals(o.getSide(), side);
                assertEquals(o.getPrice(), priceLong);
                assertEquals(o.getQuantity(), quantities[i]);
                assertEquals(o.getSource(), sources[i]);
                i++;
            }
            System.out.println("doPriceLevelTest()   priceLevel=" + priceLevel);
        }
    }

    public static PriceLevel buildPriceLevel(Side side, double priceDouble,
                                             String[] sources, long[] quantities) {
        PriceLevel priceLevel = new PriceLevel(doubleToLong98(priceDouble), side);
        for (int i = 0; i < sources.length; i++) {
            OrderData od = buildOrderData(sources[i], side,
                    priceDouble, quantities[i]);
            priceLevel.addOrder(od);
        }
        return priceLevel;
    }

    public static MarketData buildMarketData(final String instrument,
                                       final String participant,
                                       double[] buyPrices, long[] buyQuantities,
                                       double[] sellPrices, long[] sellQuantities) {
        MarketData data = buildMarketData(instrument, participant);
        data.setOrders(BUY_SIDE, buildOrderData(PARTICIPANT_1, BUY_SIDE,
                buyPrices, buyQuantities));
        data.setOrders(SELL_SIDE, buildOrderData(PARTICIPANT_1, SELL_SIDE,
                sellPrices, sellQuantities));
        return data;
    }

    public static void doBookSideTest(IOrderBookSide orderBookSide,
                                      int numberPriceLevels,
                                      List<PriceLevelTestData> testData) {
        assertEquals(orderBookSide.getPriceLevels(), numberPriceLevels);
        assertEquals(orderBookSide.getPriceLevels(), testData.size());
        int i = 0;
        Iterator<PriceLevel> iter = orderBookSide.getPriceIterator();
        while(iter.hasNext()) {
            doPriceLevelTest(iter.next(), testData.get(i));
            i++;
//            System.out.println("doBookSideTest()  *****  priceLevel=" + pl);
        }
    }

    public static class PriceLevelTestData {
        int numberOrders;
        Side side;
        double priceDouble;
        String[] sources;
        long[] quantities;

        public PriceLevelTestData(int numberOrders,
                                  Side side, double priceDouble,
                                  String[] sources,
                                  long[] quantities) {
            this.numberOrders = numberOrders;
            this.side = side;
            this.priceDouble = priceDouble;
            this.sources = sources;
            this.quantities = quantities;
        }

        public int getNumberOrders() {
            return numberOrders;
        }

        public Side getSide() {
            return side;
        }

        public double getPriceDouble() {
            return priceDouble;
        }

        public String[] getSources() {
            return sources;
        }

        public long[] getQuantities() {
            return quantities;
        }
    }

}
