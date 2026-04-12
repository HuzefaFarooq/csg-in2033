package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import ac.csg.in2033.ipos.pu.gui.StageController;
import ac.csg.in2033.ipos.pu.members.UserDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ChangePasswordSceneController extends SceneController {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label notifLabel;

    private String userEmail;

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    @FXML
    protected void onSaveButtonClick() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (newPassword == null || newPassword.isEmpty()
                || confirmPassword == null || confirmPassword.isEmpty()) {
            notifLabel.setText("Please fill in both fields");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            notifLabel.setText("Passwords do not match");
            return;
        }

        UserDatabase.changePassword(userEmail, newPassword);
        UserDatabase.setFirstLogin(userEmail, false);

        notifLabel.setText("Password changed successfully. Please log in again.");

        StageController.setScene(getClass().getResource(
                "/ac/csg/in2033/ipos/pu/gui/login/fxml/login-root-tab.fxml"
        ));
        StageController.setTitle("Login");
    }
}