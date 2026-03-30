package ac.csg.in2033.ipos.pu.gui.dashboard;

import ac.csg.in2033.ipos.pu.prm.Promotion;
import ac.csg.in2033.ipos.pu.sales.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PromotionBannerSceneController {

    @FXML private Label promotionName;
    @FXML private Label promotionDates;
    @FXML private Label promotionStatus;
    @FXML private ListView<Product> promotionProductList;
    @FXML private VBox expandedSection; // wrap promotionProductList in this VBox

    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public void setPromotion(Promotion promotion, List<Product> allProducts, boolean expanded) {
        promotionName.setText(promotion.getName());
        promotionDates.setText(
                promotion.getStartDate().format(DATE_FMT) +
                        " → " +
                        promotion.getEndDate().format(DATE_FMT)
        );
        promotionStatus.setText(promotion.isActive() ? "● ACTIVE" : "○ INACTIVE");
        promotionStatus.setStyle(promotion.isActive()
                ? "-fx-text-fill: green;"
                : "-fx-text-fill: grey;");

        // Show/hide expanded product list
        expandedSection.setVisible(expanded);
        expandedSection.setManaged(expanded); // prevents it taking up space when hidden

        if (expanded && allProducts != null) {
            Map<String, Double> discounts = promotion.getProductDiscounts();

            // Only include products that this promotion covers
            ObservableList<Product> promoProducts = FXCollections.observableArrayList();
            for (Product p : allProducts) {
                if (discounts.containsKey(String.valueOf(p.getId()))) {
                    double discount = discounts.get(String.valueOf(p.getId()));
                    p.setDiscountedPrice(p.getPrice() * (1 - discount / 100));
                    promoProducts.add(p);
                }
            }

            promotionProductList.setItems(promoProducts);
            promotionProductList.setCellFactory(param -> new ListCell<Product>() {
                private VBox productCell;
                private ProductItemSceneController controller;

                {
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("fxml/product-item.fxml")
                        );
                        productCell = loader.load();
                        controller = loader.getController();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                protected void updateItem(Product product, boolean empty) {
                    super.updateItem(product, empty);
                    if (empty || product == null) {
                        setGraphic(null);
                    } else {
                        controller.setProduct(product);
                        setGraphic(productCell);
                    }
                }
            });
        }
    }
}