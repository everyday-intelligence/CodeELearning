/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ramzi
 */
@Entity
public class Choice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String choiceAnswer;
    @Enumerated(EnumType.STRING)
    protected ChoiceState choiceState = ChoiceState.FALSE;

    public Long getId() {
        return id;
    }

    public ChoiceState getChoiceState() {
        return choiceState;
    }

    public void setChoiceState(ChoiceState choiceState) {
        this.choiceState = choiceState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChoiceAnswer() {
        return choiceAnswer;
    }

    public void setChoiceAnswer(String choiceAnswer) {
        this.choiceAnswer = choiceAnswer;
    }

    public Boolean isCorrect() {
        return this.choiceState == ChoiceState.CORRECT;
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
        if (!(object instanceof Choice)) {
            return false;
        }
        Choice other = (Choice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "codeelearning.domain.Choice[ id=" + id + " ]";
    }

    public void setCorrect(boolean selected) {
        if (selected) {
            this.choiceState = ChoiceState.CORRECT;
        } else {
            this.choiceState = ChoiceState.FALSE;
        }

    }
}
