package ac.csg.in2033.ipos.pu.sales;

/**
 * Manages the lifecycle of an order from confirmation to dispatch.
 */
public class OrderManager {


    public String confirmOrder(Cart cart, double finalTotal) {
        return "Order Summary: " + cart.getItemCount() + " items. Total to pay: £" + String.format("%.2f", finalTotal);
    }

    public boolean makePurchase(double amountToCharge, String paymentDetails) {
        // TODO: Future integration with Payment API
        System.out.println("Attempting to charge £" + String.format("%.2f", amountToCharge) + " to card...");
        return true; // Simulate success
    }

    public void recordItemsSold(Cart cart, String customerId, double finalTotal) {
        // TODO: Future integration with IPOS-SA
        System.out.println("Receipt generated and sent to IPOS-SA.");
    }

    public void updateOrderStatus(String orderId, String newStatus) {
        // TODO: Future database update and SMTP email
        System.out.println("Order " + orderId + " status updated to: " + newStatus);
    }
}