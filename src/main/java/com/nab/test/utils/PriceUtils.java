package com.nab.test.utils;

import static com.nab.test.utils.LongUtils.LONG_98_EPSILON;
import static com.nab.test.utils.LongUtils.long98ToDouble;

import it.unimi.dsi.fastutil.longs.LongComparator;

public class PriceUtils {

    public static final LongComparator ASC = (long l, long l1) -> {
        return Long.compare(l, l1);
    };

    public static final LongComparator DESC = (long l, long l1) -> {
        return Long.compare(l1, l);
    };

    public static class VwapCalculator {
        private double priceQuantitySum;
        private long quantitySum;
        private long quantityLimit;

        public void reset(long quantityLimit) {
            this.quantityLimit = quantityLimit;
            priceQuantitySum = 0.0;
            quantitySum = 0L;
        }

        public boolean addOrderData(long price, long quantity) {
            long volumeNeeded = quantityLimit - quantitySum;
            if (quantity <= volumeNeeded) {
                volumeNeeded = quantity;
            }
            priceQuantitySum += long98ToDouble(price) * volumeNeeded;
            quantitySum += volumeNeeded;
            return quantityLimit > quantitySum;
        }

        public double getVwap() {
            if (quantitySum <= LONG_98_EPSILON)
                return 0.0;
            return priceQuantitySum / quantitySum;
        }

    }
}
