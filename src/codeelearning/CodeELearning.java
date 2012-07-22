/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ramzi
 */
public class CodeELearning extends Application {
    private Stage currentStage;
    private static CodeELearning instance;

    public CodeELearning() {
        instance = this;
    }
    
    public static CodeELearning getInstance() {
        return instance;
    }
    
    public static void main(String[] args) {
        Application.launch(CodeELearning.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/StartupView.fxml"));
        currentStage = stage;

        stage.setScene(new Scene(root));
        stage.show();
    }
     public Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = currentStage.getScene();
        if (scene == null) {
            scene = new Scene(page, 800, 600);
            currentStage.setScene(scene);
        } else {
            currentStage.getScene().setRoot(page);
        }
        currentStage.sizeToScene();
        return page;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }
    
}
