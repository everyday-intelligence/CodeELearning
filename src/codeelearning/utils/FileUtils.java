/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.utils;

import codeelearning.CodeELearning;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ramzi
 */
public class FileUtils {
    public static File getFileFromDisk(Stage stage){
        System.out.println("choosing");

        File file = null;
        FileChooser fileChooser = new FileChooser();
        //Open directory from existing directory
        if (file != null) {
            File existDirectory = file.getParentFile();
            fileChooser.setInitialDirectory(existDirectory);
        }
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("AVI files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show open file dialog
        file = fileChooser.showOpenDialog(CodeELearning.getInstance().getCurrentStage());
        return file;
    }
}
