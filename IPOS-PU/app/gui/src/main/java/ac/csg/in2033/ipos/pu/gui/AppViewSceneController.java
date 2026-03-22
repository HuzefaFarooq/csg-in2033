package ac.csg.in2033.ipos.pu.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppViewSceneController extends SceneController {
    String javafxVersion = System.getProperty("javafx.version");
    String javaVersion = System.getProperty("java.version");
    private final static Logger logger = LoggerFactory.getLogger(AppViewSceneController.class);

    @FXML
    private Label javafxVersionText;

    @FXML
    private Label javaVersionText;

    @FXML
    protected void onJavafxButtonClick() {
        javafxVersionText.setText("Welcome to JavaFX version " + javafxVersion + "!");
        if (logger.isDebugEnabled()) {
            logger.debug("JavaFX button pressed.");
        }
    }

    @FXML
    protected void onJavaButtonClick() {
        javaVersionText.setText("Welcome to Java version " + javaVersion + "!");
        if (logger.isDebugEnabled()) {
            logger.debug("Java button pressed.");
        }
    }
}

