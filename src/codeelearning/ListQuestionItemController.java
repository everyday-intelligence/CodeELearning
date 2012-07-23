/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import codeelearning.models.ChoiceItemModel;
import codeelearning.models.ListQuestionItemModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Ramzi
 */
   
public class ListQuestionItemController{
    
    @FXML
    private Label questionText;
    @FXML
    private Label themes;
    
    private ListQuestionItemModel model;
    
    
    
    

     private final ChangeListener<String> CHOICETEXT_CHANGE_LISTENER = new ChangeListener<String>()
    {
        public void changed(ObservableValue<? extends String> property, String oldValue, String newValue)
        {
            updateQuestionTextView(newValue);
        }
    };


    
    
    public ListQuestionItemModel getModel()
    {
        return model;
    }

    
    public void setModel(ListQuestionItemModel model)
    {
        if(this.model != null)
            removeModelListeners();
        this.model = model;
        setupModelListeners();
        updateView();
    }

 private void removeModelListeners()
    {
        model.QuestionTextProperty().removeListener(CHOICETEXT_CHANGE_LISTENER);
        model.ThemesProperty().removeListener(CHOICETEXT_CHANGE_LISTENER);
        themes.textProperty().unbindBidirectional(model.ThemesProperty());
        questionText.textProperty().unbindBidirectional(model.QuestionTextProperty());
    }

    private void setupModelListeners()
    {
        model.QuestionTextProperty().addListener(CHOICETEXT_CHANGE_LISTENER);
        model.ThemesProperty().addListener(CHOICETEXT_CHANGE_LISTENER);
        questionText.textProperty().bindBidirectional(model.QuestionTextProperty());
        themes.textProperty().bindBidirectional(model.ThemesProperty());
    }

    private void updateView()
    {
        updateQuestionTextView();
        updateThemesView();
    }

    private void updateQuestionTextView(){ updateQuestionTextView(model.getQuestionText()); }
    private void updateQuestionTextView(String newValue)
    {
        this.questionText.setText(newValue);
    }

    private void updateThemesView(){ updateThemes(model.getThemes()); }
    private void updateThemes(String themes)
    {
        this.themes.setText(themes);
    }
    
}

