/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.models;

/**
 *
 * @author Ramzi
 */

import codeelearning.AddChoiceItemController;
import codeelearning.ListQuestionItemController;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class ListQuestionItemCell extends ListCell<ListQuestionItemModel>
{
    @Override
    protected void updateItem(ListQuestionItemModel model, boolean bln)
    {
        super.updateItem(model, bln);

        if(model != null)
        {
            URL location = AddChoiceItemController.class.getResource("views/ListQuestionItemView.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

            try
            {
                Node root = (Node)fxmlLoader.load(location.openStream());
                ListQuestionItemController controller = (ListQuestionItemController)fxmlLoader.getController();
                controller.setModel(model);
                setGraphic(root);
            }
            catch(IOException ioe)
            {
                throw new IllegalStateException(ioe);
            }
        }
    }
}
