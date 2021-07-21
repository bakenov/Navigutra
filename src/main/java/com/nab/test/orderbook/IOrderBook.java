package com.nab.test.orderbook;

import com.nab.test.bo.Side;
import com.nab.test.message.IMarketData;

public interface IOrderBook {
    void reset();
    boolean update(IMarketData data);
    long getTotalQuantityForPriceAndSide(long price, Side side);
    double getVwapForQuantityAndSide(long quantity, Side side);
}
