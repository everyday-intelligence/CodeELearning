/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.models;

/**
 *
 * @author Ramzi
 */
import codeelearning.domain.Choice;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChoiceItemModel
{
    private Choice choice;
    private StringProperty choiceText;
    private BooleanProperty isSelected;

    public ChoiceItemModel()
    {
        this(null);
    }

    public ChoiceItemModel(Choice choice)
    {
        this(choice, false);
    }

    public ChoiceItemModel(Choice choice, boolean isSelected)
    {
        this.choice = choice;
        this.choiceText = new SimpleStringProperty(choice.getChoiceAnswer());
        this.isSelected = new SimpleBooleanProperty(isSelected);
    }

    public String getChoiceText(){ return choiceText.get(); }
    public void setChoiceText(String choiceText){ this.choiceText.set(choiceText); }
    public StringProperty choiceTextProperty(){ return choiceText; }

    public boolean isSelected(){ return isSelected.get(); }
    public void setSelected(boolean isSelected){ this.isSelected.set(isSelected); }
    public BooleanProperty isSelectedProperty(){ return isSelected; }

    public Choice getChoice() {
        return choice;
    }

    @Override
    public String toString() {
        return choiceText.getValue() + " = " + isSelected.getValue();
    }
    
}
