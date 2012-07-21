/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import codeelearning.domain.QuestionFigure;
import codeelearning.domain.SingleChoiceQuestion;
import codeelearning.domainControllers.QuestionFigureJpaController;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ramzi
 */
public class AddQuestionController implements Initializable {

    @FXML
    private Label labelFile;
    @FXML
    private ImageView questionFigure = new ImageView();
    @FXML
    private Pane imagePane;
    File figureFile;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("choosing");
        
        FileChooser fileChooser = new FileChooser();
         //Open directory from existing directory
                if(figureFile != null){
                    File existDirectory = figureFile.getParentFile();
                    fileChooser.setInitialDirectory(existDirectory);
                }
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("AVI files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show open file dialog
        figureFile = fileChooser.showOpenDialog(CodeELearning.getInstance().getCurrentStage());
        labelFile.setText(figureFile.getPath());
        questionFigure.setImage(new Image(figureFile.getAbsolutePath()));
        //imagePane.getChildren().add(questionFigure);
        
        
        
    }

    @FXML
    private void handleSaveQuestionAction(ActionEvent event) {
        QuestionFigure qf = new QuestionFigure();
        try {
            qf.setFigure(getArrayByteFromFile(figureFile));
        } catch (IOException ex) {
            Logger.getLogger(AddQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeELearningPU");
        QuestionFigureJpaController qfc = new QuestionFigureJpaController(entityManagerFactory);
        qfc.create(qf);
    }
    
@Override
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
public static byte[] getArrayByte(InputStream input, int estimatedSize)
			throws IOException {
		final ByteArrayOutputStream output = new ByteArrayOutputStream(estimatedSize);
		try {
			final byte[] buf = new byte[8192];
			int len;
 
			while ((len = input.read(buf)) >= 0) {
				output.write(buf, 0, len);
			}
		} finally {
			output.close();
		}
		return output.toByteArray();
	}
 
	public static byte[] getArrayByte(InputStream input) throws IOException {
		return getArrayByte(input, 16);
	}
 
	public static byte[] getArrayByteFromFile(File f) throws IOException {
		final long length = f.length();
		if (length > Integer.MAX_VALUE) { // + de 2 Go
			throw new IOException("File too big");
		}
 
		FileInputStream input = new FileInputStream(f);
		try {
			return getArrayByte(input, (int) length);
		} finally {
			input.close();
		}
	}
 
	public static byte[] getArrayByteFromURL(URL url) throws IOException {
		InputStream input = url.openStream();
		try {
			return getArrayByte(input);
		} finally {
			input.close();
		}
	}
}