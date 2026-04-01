package ac.csg.in2033.ipos.pu.sales;

import java.util.List;

/**
 * Handles the financial calculations and checkout orchestration for IPOS-PU.
 */
public class CheckoutManager {

    // Standard UK VAT rate (20%)
    private static final double STANDARD_TAX_RATE = 0.20;

    // References to our other helper managers
    private final DiscountManager discountManager;
    private final OrderManager orderManager;

    public CheckoutManager(DiscountManager discountManager, OrderManager orderManager) {
        this.discountManager = discountManager;
        this.orderManager = orderManager;
    }

    /**
     * Calculates the final breakdown of the cart, delegating to DiscountManager.
     */
    public double calculateFinalTotal(Cart cart, String customerId, boolean isTenthPurchase) {
        double baseSubtotal = cart.getSubtotal();

        // 1. Ask DiscountManager for Promos
        double promoDiscountAmount = discountManager.checkPromotionDiscount(cart);
        double totalAfterPromos = baseSubtotal - promoDiscountAmount;

        // 2. Ask DiscountManager for Loyalty
        double loyaltyDiscountAmount = discountManager.check10thPurchaseDiscount(customerId, totalAfterPromos, isTenthPurchase);

        // 3. Calculate Tax locally
        double totalTax = calculateTax(cart);

        return totalAfterPromos - loyaltyDiscountAmount + totalTax;
    }

    public double calculateTax(Cart cart) {
        double taxTotal = 0.0;
        List<Cart.CartItem> items = cart.getItems();
        for (Cart.CartItem item : items) {
            if (!item.isMedicalGood()) {
                taxTotal += (item.getLineTotal() * STANDARD_TAX_RATE);
            }
        }
        return taxTotal;
    }

    public boolean processCheckout(Cart cart, String customerId, boolean isTenthPurchase, String paymentDetails) {
        // Step 1: Calculate the money
        double finalTotal = calculateFinalTotal(cart, customerId, isTenthPurchase);

        // Step 2: Confirm Order Summary
        System.out.println(orderManager.confirmOrder(cart, finalTotal));

        // Step 3: Make the Purchase
        boolean paymentSuccess = orderManager.makePurchase(finalTotal, paymentDetails);

        // Step 4: Handle Success or Failure
        if (paymentSuccess) {
            orderManager.recordItemsSold(cart, customerId, finalTotal);

            // Generate a dummy order ID for skeleton purposes
            String orderId = "ORD-" + System.currentTimeMillis();
            orderManager.updateOrderStatus(orderId, "PAID_AND_CONFIRMED");

            // Empty the cart after successful purchase
            cart.clearCart();
            return true;
        } else {
            System.out.println("Payment failed. Checkout aborted.");
            return false;
        }
    }
}