/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.domain;

import codeelearning.utils.SwingUtils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.persistence.*;

/**
 *
 * @author Ramzi
 */
@Entity
public class QuestionFigure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic(fetch= FetchType.LAZY)
    @Lob
    private byte[] figure;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFigure() {
        return figure;
    }

    public void setFigure(byte[] figure) {
        this.figure = figure;
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
        if (!(object instanceof QuestionFigure)) {
            return false;
        }
        QuestionFigure other = (QuestionFigure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Image convertToFXImage() {
        try {
            BufferedImage im = ImageIO.read(new ByteArrayInputStream(figure));
            Image fximg = SwingUtils.convertToFxImage(im);
            return fximg;
        } catch (IOException ex) {
            Logger.getLogger(QuestionFigure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "codeelearning.domain.QuestionFigure[ id=" + id + " ]";
    }
    
}
