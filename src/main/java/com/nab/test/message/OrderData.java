package com.nab.test.message;

import com.nab.test.bo.Side;

import static com.nab.test.utils.LongUtils.long98ToDouble;

public class OrderData implements IOrderData {
    private final String source;
    private final Side side;
    private final long quantity;
    private final long price;

    public OrderData(final String source,
                     final Side side,
                     final long price,
                     final long quantity) {
        this.source = source;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public long getQuantity() {
        return quantity;
    }

    @Override
    public long getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + source.hashCode();
        hash = 31 * hash + (int)(quantity ^ (quantity >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OrderData) {
            OrderData od = (OrderData)obj;
            return od.getSource().equals(source)
                    && od.getQuantity() == quantity;
        }
        return false;
    }

    public String toString() {
        return toString(new StringBuilder());
    }

    public String toString(StringBuilder sb) {
        sb.append("   OrderData(");
        sb.append(source);
        sb.append("|");
        sb.append(side);
        sb.append("|");
        sb.append(quantity);
        sb.append(" @ ");
        sb.append(long98ToDouble(price));
        sb.append(")\n");
        return sb.toString();
    }
}
