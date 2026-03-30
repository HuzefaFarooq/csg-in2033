package ac.csg.in2033.ipos.pu.gui.dashboard;

import ac.csg.in2033.ipos.pu.prm.Promotion;
import ac.csg.in2033.ipos.pu.sales.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionFeedSceneController {

    @FXML
    private ListView<Promotion> promotionListView;

    private ObservableList<Promotion> promotions = FXCollections.observableArrayList();
    private List<Product> allProducts;

    // Track which promotion is currently expanded (null = all collapsed)
    private Promotion expandedPromotion = null;

    @FXML
    public void initialize() {
        promotionListView.setItems(promotions);

        promotionListView.setCellFactory(param -> new ListCell<Promotion>() {
            private VBox bannerCell;
            private PromotionBannerSceneController controller;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("fxml/promo/promotion-banner.fxml")
                    );
                    bannerCell = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            protected void updateItem(Promotion promotion, boolean empty) {
                super.updateItem(promotion, empty);

                if (empty || promotion == null) {
                    setGraphic(null);
                } else {
                    boolean isExpanded = promotion.equals(expandedPromotion);
                    controller.setPromotion(promotion, allProducts, isExpanded);

                    // Toggle expand/collapse on click
                    setOnMouseClicked(event -> {
                        if (promotion.equals(expandedPromotion)) {
                            expandedPromotion = null; // collapse if already open
                        } else {
                            expandedPromotion = promotion;
                            promotion.recordCampaignClick(); // hook into your counter
                        }
                        promotionListView.refresh(); // re-render all cells
                    });

                    setGraphic(bannerCell);
                }
            }
        });
    }

    public void setData(List<Promotion> promoList, List<Product> products) {
        this.allProducts = products;
        // Only show active promotions
        List<Promotion> active = promoList.stream()
                .filter(Promotion::isActive)
                .collect(Collectors.toList());
        this.promotions.setAll(active);
    }
}