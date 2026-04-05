package ac.csg.in2033.ipos.pu.sales;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {

    private final List<MerchantProduct> listings = new ArrayList<>();

    public void addListing(MerchantProduct mp) {
        if (mp == null) {
            throw new IllegalArgumentException("Listing cannot be null.");
        }
        listings.add(mp);
    }

    public void removeListing(int productId) {
        boolean removed = listings.removeIf(mp -> mp.getProductId() == productId);
        if (!removed) {
            throw new IllegalArgumentException("No listing found for product ID: " + productId);
        }
    }

    public List<MerchantProduct> getAllListings() {
        return new ArrayList<>(listings);
    }

    public MerchantProduct viewItem(int productId) {
        for (MerchantProduct mp : listings) {
            if (mp.getProductId() == productId) {
                return mp;
            }
        }
        return null;
    }

    public List<MerchantProduct> searchByKeyword(String keyword) {
        List<MerchantProduct> results = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (MerchantProduct mp : listings) {
            String name = mp.getProductName().toLowerCase();
            String desc = mp.getProduct().getDescription().toLowerCase();
            if (name.contains(lower) || desc.contains(lower)) {
                results.add(mp);
            }
        }
        return results;
    }

    public List<MerchantProduct> filterByMaxPrice(double maxPrice) {
        List<MerchantProduct> results = new ArrayList<>();
        for (MerchantProduct mp : listings) {
            if (mp.getProduct().getPrice() <= maxPrice) {
                results.add(mp);
            }
        }
        return results;
    }

    public List<MerchantProduct> filterInStock() {
        List<MerchantProduct> results = new ArrayList<>();
        for (MerchantProduct mp : listings) {
            if (mp.getStockAvailable() > 0) {
                results.add(mp);
            }
        }
        return results;
    }

    public int size() {
        return listings.size();
    }
}
