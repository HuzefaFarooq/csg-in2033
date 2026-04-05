package ac.csg.in2033.ipos.pu.sales;

import java.util.List;

public class CatalogueFilter {

    private final Catalogue catalogue;

    public CatalogueFilter(Catalogue catalogue) {
        if (catalogue == null) {
            throw new IllegalArgumentException("Catalogue cannot be null.");
        }
        this.catalogue = catalogue;
    }

    public List<MerchantProduct> filterByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return catalogue.getAllListings();
        }
        return catalogue.searchByKeyword(keyword);
    }

    public List<MerchantProduct> filterByMaxPrice(double maxPrice) {
        return catalogue.filterByMaxPrice(maxPrice);
    }

    public List<MerchantProduct> filterInStock() {
        return catalogue.filterInStock();
    }

    public List<MerchantProduct> filterByKeywordAndPrice(String keyword, double maxPrice) {
        List<MerchantProduct> byKeyword = filterByKeyword(keyword);
        List<MerchantProduct> results = new java.util.ArrayList<>();
        for (MerchantProduct mp : byKeyword) {
            if (mp.getProduct().getPrice() <= maxPrice) {
                results.add(mp);
            }
        }
        return results;
    }
}
