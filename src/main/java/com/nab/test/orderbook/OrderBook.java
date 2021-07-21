package com.nab.test.orderbook;

import com.nab.test.bo.Side;
import com.nab.test.message.IMarketData;

public class OrderBook implements IOrderBook {

    private final String instrument;
    private final IOrderBookSide buySide, sellSide;


    public OrderBook(final String instrument, final int maxNumberPriceLevelsForSide) {
        this.instrument = instrument;
        this.buySide = new OrderBookSide(Side.BUY, maxNumberPriceLevelsForSide);
        this.sellSide = new OrderBookSide(Side.SELL, maxNumberPriceLevelsForSide);
    }

    protected IOrderBookSide getOrderBookSide(Side side) {
        return side.isBuy() ? buySide : sellSide;
    }

    @Override
    public void reset() {
        buySide.reset();
        sellSide.reset();
    }

    @Override
    public boolean update(IMarketData data) {
        if (!data.getInstrument().equals(instrument)) {
            return false;
        }
        buySide.update(data.getSource(), data.getBuyOrders());
        sellSide.update(data.getSource(), data.getSellOrders());
        return true;
    }

    @Override
    public long getTotalQuantityForPriceAndSide(long price, Side side) {
        IOrderBookSide bookSide = getSide(side);
        return bookSide.getTotalQuantityForPrice(price);
    }

    @Override
    public double getVwapForQuantityAndSide(long quantity, Side side) {
        IOrderBookSide bookSide = getSide(side);
        return bookSide.getVwapForQuantity(quantity);
    }

    private IOrderBookSide getSide(Side side) {
        return side.isBuy() ? buySide : sellSide;
    }

    public String toString() {
        return "OrderBook(" + instrument + ")\n" + buySide.toString()
                + "********************************\n" + sellSide.toString();
    }
}
