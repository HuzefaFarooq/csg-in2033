package ac.csg.in2033.ipos.pu.gui.login;

import ac.csg.in2033.ipos.pu.gui.SceneController;
import ac.csg.in2033.ipos.pu.members.UserDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class C_RegisterSceneController extends SceneController {

    public Label fileSelectionLabel;
    RegisterSceneController parentController;
    Stage popupStage;
    public TextField companyRegNumberField;
    public TextField companyNameField;
    public TextField directorsField;
    public TextField businessTypeField;
    public TextField addressField;
    public TextField emailField;
    public Label statusLabel;

    public void OnRegisterButtonClick() {
        String companyRegNumber = companyRegNumberField.getText().trim();
        String companyName = companyNameField.getText().trim();
        String directors = directorsField.getText().trim();
        String businessType = businessTypeField.getText().trim();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();

        // Make sure all fields are filled in before submitting
        if (companyRegNumber.isEmpty() || companyName.isEmpty() || directors.isEmpty()
                || businessType.isEmpty() || address.isEmpty() || email.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }
        // Send the application to be reviewed by IPOS-SA
        UserDatabase.insertCommercialApplication(companyRegNumber, companyName, directors, businessType, address, email);
        statusLabel.setText("Application submitted.");
        if (popupStage != null) {
            popupStage.close();
        }
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
