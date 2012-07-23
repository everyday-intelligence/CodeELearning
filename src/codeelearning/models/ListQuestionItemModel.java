/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.models;

/**
 *
 * @author Ramzi
 */
import codeelearning.domain.Question;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListQuestionItemModel
{
    private StringProperty questionText;
    private StringProperty themes;

    public ListQuestionItemModel()
    {
        this(null, null);
    }

   

    public ListQuestionItemModel(String questionText, String themes)
    {
        this.questionText = new SimpleStringProperty(questionText);
        this.themes = new SimpleStringProperty(themes);
    }

   public ListQuestionItemModel(Question q)
    {
        this(q.getQuestionText(), q.getThemes().toString());
    }
   
    public String getQuestionText(){ return questionText.get(); }
    public void setQuestionText(String questionText){ this.questionText.set(questionText); }
    public StringProperty QuestionTextProperty(){ return questionText; }

    public String getThemes(){ return themes.get(); }
    public void setThemes(String themes){ this.themes.set(themes); }
    public StringProperty ThemesProperty(){ return themes; }


    @Override
    public String toString() {
        return questionText.getValue() + " , " + themes.getValue();
    }
    
}