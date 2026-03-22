package ac.csg.in2033.ipos.pu.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField user;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    protected void OnLoginButtonClick() {
        // log button press
        if (logger.isDebugEnabled()) {
            logger.debug("Login button pressed.");
        }

        // send user/password text to other system
        // check if user/password are correctly formatted
        // check if user exists
        // check permissions of user
        // if authenticated, show the main view fxml
        // pass along the level of access of the user
    }

    @FXML
    protected void OnRegisterButtonClick() {
        // log button press
        if (logger.isDebugEnabled()) {
            logger.debug("Register button pressed.");
        }

        // change view to register screen
    }

}
