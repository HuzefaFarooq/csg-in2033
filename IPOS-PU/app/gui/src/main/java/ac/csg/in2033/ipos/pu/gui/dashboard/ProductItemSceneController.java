package ac.csg.in2033.ipos.pu.gui.dashboard;

import ac.csg.in2033.ipos.pu.gui.StageController;
import ac.csg.in2033.ipos.pu.sales.Product;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ProductItemSceneController {
    private final static Logger logger = LoggerFactory.getLogger(ProductItemSceneController.class);

    @FXML
    private ImageView productImage;

    @FXML
    private Text productName;

    @FXML
    private Text productPrice;

    public void setProduct(Product product) {
        InputStream imagePath = getClass().getResourceAsStream(product.getImageURL());

        if (imagePath == null) {
            logger.debug("No image file found.");
            return;
        }

        logger.info("Image file was found:{}", imagePath);

        productImage.setImage(new Image(imagePath));
        productName.setText(product.getName());
        productPrice.setText(String.format("£%.2f", product.getPrice()));
    }
}
