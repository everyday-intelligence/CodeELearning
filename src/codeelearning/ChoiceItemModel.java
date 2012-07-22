/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning;

/**
 *
 * @author Ramzi
 */
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChoiceItemModel
{
    private StringProperty choiceText;
    private BooleanProperty isSelected;

    public ChoiceItemModel()
    {
        this(null, false);
    }

    public ChoiceItemModel(String label)
    {
        this(label, false);
    }

    public ChoiceItemModel(String label, boolean isSelected)
    {
        this.choiceText = new SimpleStringProperty(label);
        this.isSelected = new SimpleBooleanProperty(isSelected);
    }

    public String getChoiceText(){ return choiceText.get(); }
    public void setChoiceText(String choiceText){ this.choiceText.set(choiceText); }
    public StringProperty choiceTextProperty(){ return choiceText; }

    public boolean isSelected(){ return isSelected.get(); }
    public void setSelected(boolean isSelected){ this.isSelected.set(isSelected); }
    public BooleanProperty isSelectedProperty(){ return isSelected; }

    @Override
    public String toString() {
        return choiceText.getValue() + " = " + isSelected.getValue();
    }
    
}