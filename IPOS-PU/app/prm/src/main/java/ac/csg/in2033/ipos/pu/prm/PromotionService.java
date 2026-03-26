package ac.csg.in2033.ipos.pu.prm;

public interface PromotionService {

    void recordCampaignClick(String campaignName);

    void recordItemAdded(String campaignName, String productId, int quantity);

    void recordItemPurchased(String campaignName, String productId, int quantity);

    double getDiscount(String productId);
}
