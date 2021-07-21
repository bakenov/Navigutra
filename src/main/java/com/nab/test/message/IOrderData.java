package com.nab.test.message;

import com.nab.test.bo.Side;

public interface IOrderData {
    String getSource();
    Side getSide();
    long getQuantity();
    long getPrice();
}
