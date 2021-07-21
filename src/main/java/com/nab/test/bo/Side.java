package com.nab.test.bo;

public enum Side {
    BUY, SELL;

    public boolean isBuy() {
        return this == BUY;
    }
}
