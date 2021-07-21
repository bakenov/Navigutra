package com.nab.test.message;

import com.nab.test.bo.Side;

import java.util.List;

public class MarketData implements IMarketData {

    private final String source;
    private final String instrument;
    private List<OrderData> buyOrders;
    private List<OrderData> sellOrders;

    public MarketData(final String source,
                      final String instrument) {
        this(source, instrument, null, null);
    }

    public MarketData(final String source,
                      final String instrument,
                      final List<OrderData> buyOrders,
                      final List<OrderData> sellOrders) {
        this.source = source;
        this.instrument = instrument;
        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;
    }

    public void setOrders(final Side side, final List<OrderData> orders) {
        if (side.isBuy()) this.buyOrders = orders;
        else this.sellOrders = orders;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getInstrument() {
        return instrument;
    }

    @Override
    public List<OrderData> getBuyOrders() {
        return buyOrders;
    }

    @Override
    public List<OrderData> getSellOrders() {
        return sellOrders;
    }

}
