package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import ac.csg.in2033.ipos.pu.gui.StageController;
import ac.csg.in2033.ipos.pu.gui.dashboard.A_DashboardSceneController;
import ac.csg.in2033.ipos.pu.gui.dashboard.NC_DashboardSceneController;
import ac.csg.in2033.ipos.pu.members.UserDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginSceneController extends SceneController {
    private final static Logger logger = LoggerFactory.getLogger(LoginSceneController.class);
    public Label descriptionLabel;
    public Label emailLabel;
    public Label passwordLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink registerLink;

    @FXML
    public Label notifLabel;

    @FXML
    protected void OnLoginButtonClick() {
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            notifLabel.setText("Please enter your email and password");
            return;
        }

        boolean success = UserDatabase.login(email, password);

        if (!success) {
            notifLabel.setText("Invalid email or password");
            return;
        }

        if (UserDatabase.isFirstLogin(email)) {
            try {
                loadChangePasswordScreen(email);
            } catch (IOException e) {
                logger.error("Failed to load change password screen", e);
                notifLabel.setText("Failed to load change password screen");
            }
            return;
        }

        String userType = UserDatabase.getUserType(email);

        try {
            switch (userType) {
                case "A" -> loadAdminDashboard();
                case "NC" -> loadNonCommercialDashboard();
                // Add commercial dashboard later
                default -> notifLabel.setText("Unknown user type: " + userType);
            }
        } catch (IOException e) {
            logger.error("Failed to load dashboard for user type {}: ", userType, e);
            notifLabel.setText("Failed to load dashboard");
        }
    }

    private void loadAdminDashboard() {
        StageController.setScene(A_DashboardSceneController.class.getResource(
                "/ac/csg/in2033/ipos/pu/gui/dashboard/fxml/admin-dashboard.fxml"
        ));
        StageController.setTitle("Admin Dashboard");
        System.out.println("Loading admin dashboard...");
    }

    private void loadNonCommercialDashboard() throws IOException {
        StageController.setScene(NC_DashboardSceneController.class.getResource(
                "/ac/csg/in2033/ipos/pu/gui/dashboard/fxml/non-commercial-dashboard.fxml"
        ));
        StageController.setTitle("Dashboard");
    }
    private void loadChangePasswordScreen(String email) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/ac/csg/in2033/ipos/pu/gui/login/fxml/change-password.fxml"
        ));
        Parent root = loader.load();

        ChangePasswordSceneController controller = loader.getController();
        controller.setUserEmail(email);

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Change Password");
        stage.show();
    }
}
