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
    private final StringProperty choiceText;
    private final BooleanProperty isSelected;

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

    public String getChoiceTextl(){ return choiceText.get(); }
    public void setChoiceTextl(String choiceTextl){ this.choiceText.set(choiceTextl); }
    public StringProperty labelProperty(){ return choiceText; }

    public boolean isSelected(){ return isSelected.get(); }
    public void setSelected(boolean isSelected){ this.isSelected.set(isSelected); }
    public BooleanProperty isSelectedProperty(){ return isSelected; }
}