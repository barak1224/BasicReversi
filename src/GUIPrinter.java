package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUIPrinter {

    private static Stage window;
    private Scene scene1, scene2;
    private GridPane grid;

    public GUIPrinter() {
        window = new Stage();
    }

    public void print() {
        window.setTitle("Continue testing");

        grid = new GridPane();
        grid.setGridLinesVisible(true);

        int numRows = 8;
        int numCols = 8;
        int row, col;

        for (row = 0; row < numRows; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (col = 0; col < numCols; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(columnConstraints);
        }

        for (row = 0; row < numRows; row++) {
            for (col = 0; col < 8; col++) {
                addPane(row, col);
            }
        }

        HBox menu = new HBox();
        Label points = new Label("Points");



        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.setTitle("Testing things");
        window.show();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Continue testing");

        grid = new GridPane();
        grid.setGridLinesVisible(true);

        int numRows = 8;
        int numCols = 8;
        int row, col;

        for (row = 0; row < numRows; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (col = 0; col < numCols; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(columnConstraints);
        }

        for (row = 0; row < numRows; row++) {
            for (col = 0; col < 8; col++) {
                addPane(row, col);
            }
        }

        HBox menu = new HBox();
        Label points = new Label("Points");



        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.setTitle("Testing things");
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void addPane(int row, int col) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            System.out.printf("You picked cell (%d, %d)\n", row+1, col+1);
        });
        grid.add(pane, col, row);

    }
}
