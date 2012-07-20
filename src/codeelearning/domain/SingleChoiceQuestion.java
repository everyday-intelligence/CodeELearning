/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Ramzi
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class SingleChoiceQuestion extends Question {
    @Override
    public Boolean isValid() {
        int nbCorrects = 0;
        for(Choice choice:choices){
            if(choice.isCorrect()){
                nbCorrects +=1;                
            }
        }
        return nbCorrects==1;
    }
    @Override
    public String toString() {
        return "codeelearning.domain.SingleChoiceQuestion[ id=" + id + " ]";
    }

    @Override
    public List<Choice> getValidResponse() {
        ArrayList correctChoices = new ArrayList<Choice>();
        Iterator<Choice> it = choices.iterator();
        while(it.hasNext()){
            Choice choice = it.next();
            if(choice.isCorrect()){
                correctChoices.add(choice);
                return correctChoices;
            }
        }
        return correctChoices;
    }
    
    
}
