/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import codeelearning.models.AddChoiceItemCell;
import codeelearning.models.AddChoiceItemModel;
import codeelearning.domain.*;
import codeelearning.domainControllers.QuestionFigureJpaController;
import codeelearning.domainControllers.QuestionJpaController;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
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
    @FXML
    private Button removeChoiceItemButton;
    @FXML
    private VBox choicesVBox;
    @FXML
    private ListView<AddChoiceItemModel> choicesItems;
     @FXML
    private TextField questionText;
    
    File figureFile;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("choosing");

        FileChooser fileChooser = new FileChooser();
        //Open directory from existing directory
        if (figureFile != null) {
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
    private void handleAddChoiceAction(ActionEvent event) {
        System.out.println("adding choice");
        choicesItems.getItems().add(new AddChoiceItemModel());
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
        //QuestionFigureJpaController qfc = new QuestionFigureJpaController(entityManagerFactory);
        //qfc.create(qf);
        //TODO voir si MC ou SC question
        Question q = null;
        int nbCorrects = 0;
        List<Choice> choices = new ArrayList<Choice>();
        for(AddChoiceItemModel ci : choicesItems.getItems()){
            Choice c = new Choice();
            c.setChoiceAnswer(ci.getChoiceText());
            c.setCorrect(ci.isSelected());
            if (c.isCorrect()) {
                nbCorrects += 1;
            }
            choices.add(c);
        }
        if(nbCorrects==1){
            q = new SingleChoiceQuestion();
        }else if(nbCorrects>1){
            q = new MultipleChoicesQuestion();
        }else{
            //TODO pas de réponses exactes signaler
            return;
        }
        q.addAllChoices(choices);
        q.setQuestionFigure(qf);
        q.setQuestionText(questionText.getText());
        //TODO perrsist
        QuestionJpaController qc = new QuestionJpaController(entityManagerFactory);
        qc.create(q);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choicesItems.setCellFactory(new Callback<ListView<AddChoiceItemModel>, ListCell<AddChoiceItemModel>>() {

            public ListCell<AddChoiceItemModel> call(ListView<AddChoiceItemModel> p) {
                return new AddChoiceItemCell();
            }
        });

        choicesItems.setItems(FXCollections.observableArrayList(new ArrayList<AddChoiceItemModel>()));


    }

    @FXML
    public void handleSelectAction(MouseEvent arg0) {
        removeChoiceItemButton.setDisable(false);
    }

    @FXML
    public void handleRemoveChoiceAction(ActionEvent arg0) {
        labelFile.setText("Selected: "
                + choicesItems.getSelectionModel().getSelectedItem());
        choicesItems.getItems().remove(choicesItems.getSelectionModel().getSelectedItem());
                removeChoiceItemButton.setDisable(true);
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
