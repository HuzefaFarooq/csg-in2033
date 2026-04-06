package ac.csg.in2033.ipos.pu.gui.dashboard;

import ac.csg.in2033.ipos.pu.prm.Promotion;
import ac.csg.in2033.ipos.pu.prm.PromotionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class A_DashboardSceneController {

    @FXML private TableView<Promotion> promotionsTable;
    @FXML private TableColumn<Promotion, String> nameColumn;
    @FXML private TableColumn<Promotion, String> startColumn;
    @FXML private TableColumn<Promotion, String> endColumn;
    @FXML private TableColumn<Promotion, String> statusColumn;
    @FXML private TableColumn<Promotion, String> productsColumn;

    private final PromotionManager promotionManager = new PromotionManager();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));
        startColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStartDate().format(formatter)));
        endColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEndDate().format(formatter)));
        statusColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().isActive() ? "Active" : "Inactive"));
        productsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getProductDiscounts().size())));

        refreshTable();
    }

    private void refreshTable() {
        ObservableList<Promotion> items = FXCollections.observableArrayList(
                promotionManager.getAllPromotions()
        );
        promotionsTable.setItems(items);
    }

    @FXML
    private void onCreateClick() {
        // TODO: open create promotion popup
    }

    @FXML
    private void onEditClick() {
        Promotion selected = promotionsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            // TODO: show "please select a promotion" message
            return;
        }
        // TODO: open edit promotion popup
    }

    @FXML
    private void onTerminateClick() {
        Promotion selected = promotionsTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        promotionManager.terminatePromotion(selected.getName());
        refreshTable();
    }

    @FXML
    private void onDeleteClick() {
        Promotion selected = promotionsTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        promotionManager.deletePromotion(selected.getName());
        refreshTable();
    }
}