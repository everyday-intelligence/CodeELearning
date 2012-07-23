/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import codeelearning.domain.Question;
import codeelearning.domainControllers.QuestionJpaController;
import codeelearning.models.AddChoiceItemCell;
import codeelearning.models.AddChoiceItemModel;
import codeelearning.models.ListQuestionItemCell;
import codeelearning.models.ListQuestionItemModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionsItems.setCellFactory(new Callback<ListView<ListQuestionItemModel>, ListCell<ListQuestionItemModel>>() {

            public ListCell<ListQuestionItemModel> call(ListView<ListQuestionItemModel> p) {
                return new ListQuestionItemCell();
            }
        });

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CodeELearningPU");
        QuestionJpaController qc = new QuestionJpaController(entityManagerFactory);
        List<Question> questions = qc.findQuestionEntities();
        ArrayList<ListQuestionItemModel> questionsModels = new ArrayList<ListQuestionItemModel>();
        for(Question q:questions){
            questionsModels.add(new ListQuestionItemModel(q));
        }
        //String style = "-fx-font-weight:normal; -fx-color: #f0f0f0; -fx-font-size:11; -fx-font-family: Verdana;";
        //questionsItems.setStyle(style);
        questionsItems.setItems(FXCollections.observableArrayList(questionsModels));
 

    }
    @FXML
    public void handleSelectAction(MouseEvent arg0) {
    }
}
