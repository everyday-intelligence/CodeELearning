/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import codeelearning.domain.Choice;
import codeelearning.domain.Question;
import codeelearning.domain.QuestionFigure;
import codeelearning.domainControllers.QuestionJpaController;
import codeelearning.models.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ramzi
 */
public class ListQuestionsController implements Initializable {

    @FXML
    private ListView<ListQuestionItemModel> questionsItems;
    @FXML
    private ListView<ChoiceItemModel> choicesItems;
    @FXML
    private Label questionText;
    @FXML
    private ImageView questionFigure;
    private List<Question> questions;
    private Question selectedQuestion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionsItems.setCellFactory(new Callback<ListView<ListQuestionItemModel>, ListCell<ListQuestionItemModel>>() {
            @Override
            public ListCell<ListQuestionItemModel> call(ListView<ListQuestionItemModel> p) {
                return new ListQuestionItemCell();
            }
        });

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeELearningPU");
        QuestionJpaController qc = new QuestionJpaController(entityManagerFactory);
        questions = qc.findQuestionEntities();
        ArrayList<ListQuestionItemModel> questionsModels = new ArrayList<ListQuestionItemModel>();
        for (Question q : questions) {
            questionsModels.add(new ListQuestionItemModel(q));
        }
        //String style = "-fx-font-weight:normal; -fx-color: #f0f0f0; -fx-font-size:11; -fx-font-family: Verdana;";
        //questionsItems.setStyle(style);
        questionsItems.setItems(FXCollections.observableArrayList(questionsModels));


    }

    @FXML
    public void handleSelectAction(MouseEvent arg0) {
        int selectedIndex = questionsItems.getSelectionModel().getSelectedIndex();
        selectedQuestion = questions.get(selectedIndex);
        questionText.setText(selectedQuestion.getQuestionText());
        if (selectedQuestion != null) {
            choicesItems.setCellFactory(new Callback<ListView<ChoiceItemModel>, ListCell<ChoiceItemModel>>() {
                @Override
                public ListCell<ChoiceItemModel> call(ListView<ChoiceItemModel> p) {
                    return new ListChoiceItemCell();
                }
            });
            ArrayList<ChoiceItemModel> choicesModels = new ArrayList<ChoiceItemModel>();
            for (Choice c : selectedQuestion.getChoices()) {
                choicesModels.add(new ChoiceItemModel(c));
            }
            choicesItems.setItems(FXCollections.observableArrayList(choicesModels));

            QuestionFigure qf = selectedQuestion.getQuestionFigure();
            questionFigure.setImage(qf.convertToFXImage());
        }
    }

    @FXML
    public void showCorrectAnswerAction(ActionEvent arg0) {
        choicesItems.setCellFactory(new Callback<ListView<ChoiceItemModel>, ListCell<ChoiceItemModel>>() {
            @Override
            public ListCell<ChoiceItemModel> call(ListView<ChoiceItemModel> p) {
                return new ListChoiceCorrectionItemCell();
            }
        });
    }

    @FXML
    public void verifyAnswerAction(ActionEvent arg0) {
        List<Choice> selectedAnswers = new ArrayList<Choice>();
        Boolean isResponseCorrect = true;
        for(ChoiceItemModel chitm:choicesItems.getItems()){
            if(chitm.isSelected()){
                selectedAnswers.add(chitm.getChoice());
            }
            isResponseCorrect = selectedQuestion.isAnswerCorrect(selectedAnswers);
        }
        if(isResponseCorrect){
            //TODO afficher bonne reponse et son
            choicesItems.setCellFactory(new Callback<ListView<ChoiceItemModel>, ListCell<ChoiceItemModel>>() {
            @Override
            public ListCell<ChoiceItemModel> call(ListView<ChoiceItemModel> p) {
                return new ListChoiceCorrectionItemCell();
            }
        });
        }else{
            choicesItems.setCellFactory(new Callback<ListView<ChoiceItemModel>, ListCell<ChoiceItemModel>>() {
            @Override
            public ListCell<ChoiceItemModel> call(ListView<ChoiceItemModel> p) {
                return new ListChoiceIncorrectItemCell();
            }
        });
        }
    }

    
}
