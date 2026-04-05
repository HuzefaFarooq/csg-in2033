package ac.csg.in2033.ipos.pu.sales;

import java.util.HashMap;
import java.util.Map;

public class ViewTracker {

    private final Map<Integer, Integer> itemViewCounts = new HashMap<>();
    private final Map<Integer, Integer> itemSoldCounts = new HashMap<>();

    public void recordItemViewed(int productId) {
        int current = itemViewCounts.getOrDefault(productId, 0);
        itemViewCounts.put(productId, current + 1);
    }

    public void recordItemsSold(int productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity sold must be at least 1.");
        }
        int current = itemSoldCounts.getOrDefault(productId, 0);
        itemSoldCounts.put(productId, current + quantity);
    }

    public int getViewCount(int productId) {
        return itemViewCounts.getOrDefault(productId, 0);
    }

    public int getSoldCount(int productId) {
        return itemSoldCounts.getOrDefault(productId, 0);
    }

    public double getConversionRate(int productId) {
        int views = getViewCount(productId);
        int sold = getSoldCount(productId);
        if (views == 0) {
            return 0.0;
        }
        return (double) sold / views;
    }

    public Map<Integer, Integer> getAllViewCounts() {
        return new HashMap<>(itemViewCounts);
    }

    public Map<Integer, Integer> getAllSoldCounts() {
        return new HashMap<>(itemSoldCounts);
    }
}
