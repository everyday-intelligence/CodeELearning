/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Ramzi
 */
@Entity
public abstract class Question implements Serializable {
    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @OneToOne(cascade= CascadeType.PERSIST)
    protected QuestionFigure questionFigure;
    protected String questionText;
    @OneToMany(cascade= CascadeType.ALL)
    protected List<Choice> choices = new ArrayList<Choice>();
    @ManyToMany(cascade= CascadeType.MERGE)
    protected List<Theme> themes;
    protected int importance;
    protected int complexity;
    protected Boolean activated = false; 
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionFigure getQuestionFigure() {
        //TODO if null rendre une image par defaut
        return questionFigure;
    }

    public void setQuestionFigure(QuestionFigure questionFigure) {
        this.questionFigure = questionFigure;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public void addTheme(Theme theme) {
        //TODO vérif existance
        this.themes.add(theme);
    }
    
    public void addChoice(Choice choice) {
        //TODO vérif existance
        this.choices.add(choice);
    }
    public void addAllChoices(List<Choice> choices) {
        //TODO vérif existance
        this.choices.addAll(choices);
    }
    public void removeTheme(Theme theme) {
        this.themes.remove(theme);
    }
    
    public void removeChoice(Choice choice) {
        this.choices.remove(choice);
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public abstract Boolean isValid(); 
    public abstract List<Choice> getValidResponse();
    
    public Boolean isAnswerCorrect(List<Choice> answers){
        return answers!=null && this.getValidResponse().containsAll(answers) && this.getValidResponse().size()==answers.size();
    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "codeelearning.domain.Question[ id=" + id + " ]";
    }
    
}
