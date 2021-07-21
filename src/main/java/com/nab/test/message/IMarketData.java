package com.nab.test.message;

import java.util.List;

public interface IMarketData {
    String getSource();
    String getInstrument();
    List<OrderData> getBuyOrders();
    List<OrderData> getSellOrders();
}
