package com.nab.test.orderbook;

import com.nab.test.bo.Side;
import com.nab.test.message.OrderData;
import com.nab.test.utils.PriceUtils;
import it.unimi.dsi.fastutil.longs.*;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.nab.test.utils.LongUtils.long98ToDouble;
import static com.nab.test.utils.PriceUtils.VwapCalculator;

public class OrderBookSide implements IOrderBookSide {

    private final Side side;
    private final int maxNumberPriceLevels;
    private final Long2ObjectSortedMap<PriceLevel> levels;
    private final Map<String, Long2ObjectMap<OrderData>> sourcePricesMap;
    private final AllLevelIterator levelIterator = new AllLevelIterator();
    private final LongSet reusablePriceSet = new LongOpenHashSet();
    private final VwapCalculator vwapCalc = new VwapCalculator();

    public OrderBookSide(final Side side, final int maxNumberPriceLevels) {
        this.side = side;
        this.maxNumberPriceLevels = maxNumberPriceLevels;
        LongComparator sorter = side.isBuy() ? PriceUtils.DESC : PriceUtils.ASC;
        levels = new Long2ObjectAVLTreeMap<>(sorter);
        sourcePricesMap = new HashMap<>();
    }

    @Override
    public Iterator<PriceLevel> getPriceIterator() {
        return levelIterator;
    }

    @Override
    public long getTotalQuantityForPrice(long price) {
        PriceLevel pl = levels.get(price);
        if (pl == null) {
            return 0;
        }
        return pl.getTotalQuantity();
    }

    public double getVwapForQuantity(long quantity) {
        vwapCalc.reset(quantity);
        levelIterator.reset();
        while(levelIterator.hasNext()) {
            PriceLevel pl = levelIterator.next();
            if (!pl.processVwapCalc(vwapCalc)) {
                return vwapCalc.getVwap();
            }
        }
        return vwapCalc.getVwap();
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public int getPriceLevels() {
        return levels.size();
    }

    @Override
    public void reset() {
        levels.clear();
        sourcePricesMap.clear();
    }

    @Override
    public void update(final String source, final List<OrderData> orders) {
        process(source, orders, getSourcePricesMap(source));
    }

    public void process(String source, List<OrderData> orders, Long2ObjectMap<OrderData> sourceMap) {
        if (orders.isEmpty()) {
            ObjectCollection<OrderData> ordersDelete = sourceMap.values();
            for (ObjectIterator<OrderData> iter = ordersDelete.iterator(); iter.hasNext();) {
                OrderData od = iter.next();
                deleteOrder(od, false);
            }
            sourcePricesMap.remove(source);
            return;
        }

        LongSet existingPrices = sourceMap.keySet();
        if (existingPrices.isEmpty()) {
            orders.forEach(this::addOrder);
        } else {
            reusablePriceSet.clear();
            for(OrderData o : orders) {
                reusablePriceSet.add(o.getPrice());
                if (existingPrices.contains(o.getPrice())) {
                    if (!updateOrder(o)) {
                        // some problems here
                        System.err.println("The Order cannot be updated");
                    }
                } else {
                    if(!addOrder(o)) {
                       // some problems here
                       System.err.println("Max number price levels is reached");
                    }
                }
            }
            // do we need to remove some existing orders
            for(long p : existingPrices) {
                if (!reusablePriceSet.contains(p)) {
                    if (!deleteOrder(sourceMap.get(p), true)) {
                        // some problems here
                        System.err.println("Order cannot be deleted");
                    }
                }
            }
        }
    }

    private boolean addOrder(OrderData data) {
        PriceLevel pl = levels.get(data.getPrice());
        if (pl == null) {
            if (levels.size() >= maxNumberPriceLevels) {
                // log issue here
                return false;
            }
            pl = new PriceLevel(data.getPrice(), side);
            levels.put(data.getPrice(), pl);
        }
        pl.addOrder(data);
        addOrderToSourceCache(data);
        return true;
    }

    private boolean updateOrder(OrderData data) {
        PriceLevel pl = levels.get(data.getPrice());
        if (pl == null) {
            // TODO proper log error message
            System.err.println("Price level for update Order operation must exist");
            return false;
        }
        pl.updateOrder(data);
        addOrderToSourceCache(data);
        return true;
    }

    private boolean deleteOrder(OrderData data, boolean removeFromSourceCache) {
        PriceLevel pl = levels.get(data.getPrice());
        if (pl == null) {
            // TODO proper log error message
            System.err.println("Price level for delete Order operation must exist");
            return false;
        }
        pl.removeOrder(data);
        if (pl.getNumberOrders() == 0) {
            // all orders from level are removed
            levels.remove(data.getPrice());
        }
        if (removeFromSourceCache) {
            removeOrderFromSourceCache(data);
        }
        return true;
    }

    private void addOrderToSourceCache(OrderData data) {
        Long2ObjectMap<OrderData> sourceMap = getSourcePricesMap(data.getSource());
        sourceMap.put(data.getPrice(), data);
    }

    private void removeOrderFromSourceCache(OrderData data) {
        Long2ObjectMap<OrderData> sourceMap = getSourcePricesMap(data.getSource());
        sourceMap.remove(data.getPrice());
        if (sourceMap.size() == 0) {
            sourcePricesMap.remove(data.getSource());
        }
    }

    private Long2ObjectMap<OrderData> getSourcePricesMap(final String source) {
        Long2ObjectMap<OrderData> priceMapForSource = sourcePricesMap.get(source);
        if (priceMapForSource == null) {
            priceMapForSource = new Long2ObjectArrayMap<>();
            sourcePricesMap.put(source, priceMapForSource);
        }
        return priceMapForSource;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ObjectSortedSet<Long2ObjectMap.Entry<PriceLevel>> set = levels.long2ObjectEntrySet();
        Iterator<Long2ObjectMap.Entry<PriceLevel>> iter =  set.stream().iterator();
        while(iter.hasNext()) {
            Long2ObjectMap.Entry<PriceLevel> entry = iter.next();
            long price = entry.getLongKey();
            PriceLevel pl = entry.getValue();
            sb.append(long98ToDouble(price));
            sb.append(":");
            sb.append(pl);
        }
        return sb.toString();
    }

    private class AllLevelIterator implements Iterator<PriceLevel> {
        Iterator<PriceLevel> limitLevelIterator;

        public void reset() {
            limitLevelIterator = null;
        }

        @Override
        public boolean hasNext() {
            if (limitLevelIterator != null)
                return limitLevelIterator.hasNext();

            limitLevelIterator = levels.values().iterator();
            return limitLevelIterator.hasNext();
        }

        @Override
        public PriceLevel next() {
            if (limitLevelIterator == null) {
                limitLevelIterator = levels.values().iterator();
            }
            return limitLevelIterator.next();
        }

    }

}
