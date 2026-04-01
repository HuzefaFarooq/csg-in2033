package ac.csg.in2033.ipos.pu.sales;

/**
 * Handles all discount and promotion logic for the Sales module.
 */
public class DiscountManager {

    // Loyalty discount rate (10%) moved here from CheckoutManager
    private static final double LOYALTY_DISCOUNT_RATE = 0.10;


    public boolean checkItemForPromotion(String productId) {
        // TODO: Future integration with IPOS-SA
        System.out.println("Checking promotion status for item: " + productId);
        return false;
    }


    public double checkPromotionDiscount(Cart cart) {
        // TODO: Future integration.
        double totalPromoDiscount = 0.0;
        return totalPromoDiscount;
    }

    public double check10thPurchaseDiscount(String customerId, double amountAfterPromos, boolean isTenthPurchase) {
        // TODO: Future integration to check customer database. Using boolean for now.
        if (isTenthPurchase) {
            return amountAfterPromos * LOYALTY_DISCOUNT_RATE;
        }
        return 0.0;
    }
}