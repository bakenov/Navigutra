package com.nab.test.orderbook;

import com.nab.test.bo.Side;
import com.nab.test.message.OrderData;
import com.nab.test.utils.PriceUtils;

import static com.nab.test.utils.LongUtils.long98ToDouble;

import java.util.*;

public class PriceLevel implements Comparable<PriceLevel> {

    private final long price;
    private final Side side;
    private final Set<OrderData> orders;
    private static final Comparator<OrderData> COMP =
        (OrderData o1, OrderData o2)-> {
            if (o2.getQuantity() == o1.getQuantity()) {
                return o1.getSource().compareTo(o2.getSource());
            }
            return Long.compare(o2.getQuantity(), o1.getQuantity());
        };

    public PriceLevel(final long price, final Side side) {
        this.price = price;
        this.side = side;
        orders = new TreeSet<>(COMP);
    }

    public void reset() {
        orders.clear();
    }

    public long getPrice() {
        return price;
    }

    public long getNumberOrders() {
        return orders.size();
    }

    public long getTotalQuantity() {
        return orders.stream().mapToLong(OrderData::getQuantity).sum();
    }

    public boolean processVwapCalc(final PriceUtils.VwapCalculator vwapCalc) {
        for (OrderData o : orders) {
            if (!vwapCalc.addOrderData(o.getPrice(), o.getQuantity())) {
                return false;
            }
        }
        return true;
    }

    public void addOrder(OrderData data) {
        orders.add(data);
    }

    public void updateOrder(OrderData data) {
        Optional<OrderData> opt = orders.stream().filter((o) -> o.getSource().equals(data.getSource())).findFirst();
        if (opt.isPresent()) {
            OrderData od = opt.get();
            if (od.getQuantity() != data.getQuantity()) {
                orders.remove(od);
                orders.add(data);
            }
        }
    }

    public void removeOrder(OrderData data) {
        orders.remove(data);
    }

    public Iterator<OrderData> getOrderIterator() {
        return orders.iterator();
    }

    @Override
    public int compareTo(PriceLevel o) {
        if (o == null) {
            return 1;
        }
        return side == Side.BUY ? Long.compare(price, o.getPrice()) :
                Long.compare(o.getPrice(), price);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PriceLevel(");
        sb.append(side);
        sb.append("|");
        sb.append(long98ToDouble(price));
        sb.append(")\n");
        for (OrderData od : orders) {
            sb.append(od);
        }
        return sb.toString();
    }
}
