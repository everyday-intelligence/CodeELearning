/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ramzi
 */
public class FileUploadViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView selectedImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleValidateFileUploadAction(ActionEvent e){
            
    }
    @FXML
    private void handleCancelFileUploadAction(ActionEvent e){
            
    }
    public ImageView getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(ImageView selectedImage) {
        this.selectedImage = selectedImage;
    }
    
}
