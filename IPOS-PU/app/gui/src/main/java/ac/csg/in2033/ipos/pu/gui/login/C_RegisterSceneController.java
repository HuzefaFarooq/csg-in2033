package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class C_RegisterSceneController extends SceneController {

    public Label fileSelectionLabel;
    RegisterSceneController parentController;
    Stage popupStage;

    public void OnRegisterButtonClick() {
    }

    public void OnCancelButtonClick() throws NullPointerException {
        if (popupStage == null) {
            throw new NullPointerException("Stage is null.");
        }

        popupStage.close();
    }

    public void OnChooseFileButtonClick() {
        FileChooser fileChooser = new FileChooser();

        // Set the file types allowed for selection (pdfs and images)
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg"));

        // Show the file chooser dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(popupStage);

        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            // Truncate file name if it's too long
            if (fileName.length() > 30) {
                fileName = fileName.substring(0, 30) + "...";
            }

            // Update the label to show the file name
            fileSelectionLabel.setText("Selected file: " + fileName);

            // Pass this file somewhere to be sent to IPOS-SA

        } else {
            // Handle case where no file was selected
            fileSelectionLabel.setText("No file selected.");
        }
    }

    public void setParent(RegisterSceneController registerSceneController) {
        this.parentController = registerSceneController;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }
}
