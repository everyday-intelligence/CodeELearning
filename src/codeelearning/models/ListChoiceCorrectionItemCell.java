/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.models;

/**
 *
 * @author Ramzi
 */

import codeelearning.ListChoiceItemController;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class ListChoiceCorrectionItemCell extends ListCell<ChoiceItemModel> {

        @Override
        public void updateItem(ChoiceItemModel model, boolean bln) {
            super.updateItem(model, bln);
            if (model != null) {

                URL location = ListChoiceItemController.class.getResource("views/ListChoiceItemView.fxml");

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(location);
                fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

                try {
                    Node root = (Node) fxmlLoader.load(location.openStream());
                    ListChoiceItemController controller = (ListChoiceItemController) fxmlLoader.getController();
                    controller.setModel(model);
                    if (model.getChoice().isCorrect()) {
                        root.setStyle("-fx-background-color: #33ff00;");
                    }
                    setGraphic(root);
                } catch (IOException ioe) {
                    throw new IllegalStateException(ioe);
                }


            }
        }
    }
