/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import codeelearning.domain.QuestionFigure;
import codeelearning.domainControllers.QuestionFigureJpaController;
import codeelearning.utils.FileUtils;
import codeelearning.utils.ImageIOUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ramzi
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handleAddQuestionAction(ActionEvent e) {
        try {
            CodeELearning.getInstance().replaceSceneContent("Views/AddQuestionView.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleCloseAction(ActionEvent e) {
    }

    @FXML
    public void handleAddFigureAction(ActionEvent e) {
        /*
            
         */
        File figureFile = FileUtils.getFileFromDisk(CodeELearning.getInstance().getCurrentStage());
        if (figureFile != null) {
            /*
            try {
                //open window
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();


                URL location = getClass().getResource("views/FileUploadView.fxml");

                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
                Parent root = (Parent) fxmlLoader.load(location.openStream());
                FileUploadViewController fuc = (FileUploadViewController) fxmlLoader.getController();
                ImageView newImage = new ImageView(new Image(figureFile.getAbsolutePath()));
                stage.setScene(new Scene(root));
                fuc.setSelectedImage(newImage);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            * */
        QuestionFigure qf = new QuestionFigure();
        try {
            qf.setFigure(ImageIOUtils.getArrayByteFromFile(figureFile));
        } catch (IOException ex) {
            Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeELearningPU");
        QuestionFigureJpaController qfc = new QuestionFigureJpaController(entityManagerFactory);
        qfc.create(qf);

        }

    }

    @FXML
    public void handleListQuestionsAction(ActionEvent e) {
        try {
            CodeELearning.getInstance().replaceSceneContent("Views/ListQuestionsView.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
