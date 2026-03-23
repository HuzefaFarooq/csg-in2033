package ac.csg.in2033.ipos.pu.sales;

import java.util.List;

/**
 * Handles the financial calculations and checkout process for IPOS-PU.
 */
public class CheckoutManager {

    // Standard UK VAT rate (20%)
    private static final double STANDARD_TAX_RATE = 0.20;

    // Loyalty discount rate (10%)
    private static final double LOYALTY_DISCOUNT_RATE = 0.10;

    /**
     * Calculates the final breakdown of the cart, applying all business rules.
     * * @param cart The user's shopping cart.
     * @param isTenthPurchase True if the user is a Non-Commercial member on their 10th order.
     * @return The final amount to charge the customer's card.
     */
    public double calculateFinalTotal(Cart cart, boolean isTenthPurchase) {

        double baseSubtotal = cart.getSubtotal();

        double promoDiscountAmount = 0.0; // requires input from IPOS-SA

        double totalAfterPromos = baseSubtotal - promoDiscountAmount;

        double loyaltyDiscountAmount = 0.0;
        if (isTenthPurchase) {
            loyaltyDiscountAmount = totalAfterPromos * LOYALTY_DISCOUNT_RATE;
        }

        double totalTax = calculateTax(cart);

        double finalTotalToCharge = totalAfterPromos - loyaltyDiscountAmount + totalTax;

        return finalTotalToCharge;
    }

    /**
     * Helper method to calculate tax based on item type.
     */
    private double calculateTax(Cart cart) {
        double taxTotal = 0.0;

        List<Cart.CartItem> items = cart.getItems();
        for (Cart.CartItem item : items) {
            // Only apply tax if it is NOT a medical good
            if (!item.isMedicalGood()) {
                taxTotal += (item.getLineTotal() * STANDARD_TAX_RATE);
            }
        }

        return taxTotal;
    }
}
