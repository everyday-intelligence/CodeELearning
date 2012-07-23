/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

import codeelearning.models.ChoiceItemModel;
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
   
public class ListChoiceItemController{
    private final ChangeListener<String> CHOICETEXT_CHANGE_LISTENER = new ChangeListener<String>()
    {
        public void changed(ObservableValue<? extends String> property, String oldValue, String newValue)
        {
            updateChoiceTextView(newValue);
        }
    };

    private final ChangeListener<Boolean> IS_SELECTED_CHANGE_LISTENER = new ChangeListener<Boolean>()
    {
        public void changed(ObservableValue<? extends Boolean> property, Boolean oldValue, Boolean newValue)
        {
            updateIsSelectedView(newValue);
        }
    };

    @FXML
    private Label choiceText;
    @FXML
    private CheckBox isSelectedView;
    
    private ChoiceItemModel model;

    public ChoiceItemModel getModel()
    {
        return model;
    }

    public void setModel(ChoiceItemModel model)
    {
        if(this.model != null)
            removeModelListeners();
        this.model = model;
        setupModelListeners();
        updateView();
    }

    private void removeModelListeners()
    {
        model.choiceTextProperty().removeListener(CHOICETEXT_CHANGE_LISTENER);
        model.isSelectedProperty().removeListener(IS_SELECTED_CHANGE_LISTENER);
        isSelectedView.selectedProperty().unbindBidirectional(model.isSelectedProperty());
        //choiceText.textProperty().unbindBidirectional(model.choiceTextProperty());
    }

    private void setupModelListeners()
    {
        model.choiceTextProperty().addListener(CHOICETEXT_CHANGE_LISTENER);
        model.isSelectedProperty().addListener(IS_SELECTED_CHANGE_LISTENER);
        isSelectedView.selectedProperty().bindBidirectional(model.isSelectedProperty());
        choiceText.textProperty().bindBidirectional(model.choiceTextProperty());
    }

    private void updateView()
    {
        updateChoiceTextView();
        updateIsSelectedView();
    }

    private void updateChoiceTextView(){ updateChoiceTextView(model.getChoiceText()); }
    private void updateChoiceTextView(String newValue)
    {
        choiceText.setText(newValue);
    }

    private void updateIsSelectedView(){ updateIsSelectedView(model.isSelected()); }
    private void updateIsSelectedView(boolean newValue)
    {
        isSelectedView.setSelected(newValue);
    }
    
}