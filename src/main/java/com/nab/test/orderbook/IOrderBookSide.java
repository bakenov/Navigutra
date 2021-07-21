package com.nab.test.orderbook;

import com.nab.test.bo.Side;
import com.nab.test.message.OrderData;

import java.util.Iterator;
import java.util.List;

public interface IOrderBookSide {

    Side getSide();
    int getPriceLevels();
    void update(final String source, List<OrderData> orders);
    Iterator<PriceLevel> getPriceIterator();
    long getTotalQuantityForPrice(long price);
    double getVwapForQuantity(long quantity);
    void reset();
}
