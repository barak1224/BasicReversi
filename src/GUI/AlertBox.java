package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Class used to create Alert Boxes, windows that pop-up after certain events, and wait to be closed.
 */
public class AlertBox {

    /**
     * Method to display the alert box to the user.
     * @param title - the title of the window to pop-up.
     * @param message - the message to show the user in said window.
     */
    public void display(String title, String message) {
        Stage window = new Stage();

        // it will wait to be closed and block the main window until then.
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("OKAY");
        closeButton.setOnAction(e-> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();

    }
}
