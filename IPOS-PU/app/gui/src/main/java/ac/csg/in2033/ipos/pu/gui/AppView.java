package ac.csg.in2033.ipos.pu.gui;

import ac.csg.in2033.ipos.pu.members.UserDatabase;
import ac.csg.in2033.ipos.pu.prm.Promotion;
import ac.csg.in2033.ipos.pu.prm.PromotionDatabase;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class AppView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Initialise databases
        UserDatabase.createTable();
        PromotionDatabase.createTable();

        StageController.setStage(stage);
        StageController.setTitle("InfoPharma Online Portal");
        StageController.setScene(getClass().getResource("login/fxml/login-root-tab.fxml"));
    }

    public static void main(String[] args) {
        launch();
    }
}

