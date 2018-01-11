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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUIPrinter implements Printer {

    private static Stage window;
    private GridPane grid;
    private Color player1Color, player2Color;
    private boolean inTurn;
    private int[] input;
//    private GameFlow gameFlow;


    public GUIPrinter(Stage stage, Color player1, Color player2) {
        window = new Stage();
        player1Color = player1;
        player2Color = player2;
        inTurn = false;
        input = new int[2];
        input[0] = 0;
        input[1] = 0;
    }

    @Override
    public void printBoard(Cell[][] matrix, int size, int points1, int points2) {
        window.setTitle("Continue testing");

        grid = new GridPane();
        grid.setGridLinesVisible(true);

        int row, col;

        for (row = 0; row < size; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (col = 0; col < size; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(columnConstraints);
        }

        for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
                addPane(row, col);
            }
        }

        HBox menu = new HBox();
        menu.setPrefWidth(100);

        HBox leftBorder = new HBox();
        leftBorder.setPrefWidth(10);

        VBox topBorder = new VBox();
        topBorder.setPrefHeight(10);

        VBox bottomBorder = new VBox();
        bottomBorder.setPrefHeight(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(grid);
        borderPane.setRight(menu);
        borderPane.setTop(topBorder);
        borderPane.setLeft(leftBorder);
        borderPane.setBottom(bottomBorder);



        Scene scene = new Scene(borderPane, 400, 400);
        window.setScene(scene);
        window.setTitle("Testing things");
        window.show();
    }

    private void addPane(int row, int col) {
        StackPane pane = new StackPane();
        
        pane.setOnMouseClicked(e -> {
            if (inTurn) {
                input[0] = row + 1;
                input[1] = col+ 1;
                System.out.printf("(%d, %d)\n", row+1, col+1);
            }
        });
        grid.add(pane, col, row);
    }


    @Override
    public void printStream(String output) {
        System.out.println(output);
    }

    public void setInTurn(boolean inTurn) {
        this.inTurn = inTurn;
    }

    public int[] getInput() {
        input[0] = 0;
        input[1] = 0;
        while (input[0] == 0 || input[1] == 0 ) {
            //do nothing
        }
        return input;
    }
}
