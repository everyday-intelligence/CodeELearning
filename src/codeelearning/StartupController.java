/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Ramzi
 */
public class StartupController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleGoToAddQuestionAction(ActionEvent event) {
        System.out.println("go to figure");
        try {
            CodeELearning.getInstance().replaceSceneContent("views/AddQuestionView.fxml");
        } catch (Exception ex) {
            Logger.getLogger(StartupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
    private void handleGoToListQuestionsAction(ActionEvent event) {
        System.out.println("go to figure");
        try {
            CodeELearning.getInstance().replaceSceneContent("views/ListQuestionsView.fxml");
        } catch (Exception ex) {
            Logger.getLogger(StartupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
