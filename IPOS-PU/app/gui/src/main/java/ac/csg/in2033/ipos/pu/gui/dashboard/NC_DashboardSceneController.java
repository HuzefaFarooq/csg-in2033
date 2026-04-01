package ac.csg.in2033.ipos.pu.gui.dashboard;

import ac.csg.in2033.ipos.pu.prm.Promotion;
import ac.csg.in2033.ipos.pu.sales.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.List;

public class NC_DashboardSceneController {

    @FXML private TextField searchField;

    // fx:include injects the sub-controllers using the id of the include + "Controller"
    @FXML private ProductFeedSceneController productFeedController;
    @FXML private PromotionFeedSceneController promotionFeedController;

    @FXML
    public void initialize() {
        // Dummy promotions — replace with real data source later
        List<Product> products = List.of(
                new Product(0, "Product 0", "This is product 0.", 1.0, "img/products/img-0.png"),
                new Product(1, "Product 1", "This is product 1.", 1.50, "img/products/img-1.png"),
                new Product(2, "Product 2", "This is product 2.", 1.99, "img/products/img-0.png"),
                new Product(3, "Product 3", "This is product 3.", 25.00, "img/products/img-1.png")
        );

        Promotion promo1 = new Promotion("Summer Sale", LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(7));
        promo1.addProduct("0", 10.0); // 10% off product 0
        promo1.addProduct("1", 20.0); // 20% off product 1

        Promotion promo2 = new Promotion("Clearance", LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(3));
        promo2.addProduct("2", 15.0); // 15% off product 2

        List<Promotion> promotions = List.of(promo1, promo2);

        productFeedController.setPromotions(promotions, products);
        promotionFeedController.setData(promotions, products);
    }

    @FXML
    private void onProfileClick() {
    }
}