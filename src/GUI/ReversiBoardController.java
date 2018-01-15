package GUI;

import Core.Board;
import Core.Cell;
import Core.CellColor;
import Core.Coordinate;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReversiBoardController extends GridPane {
    private Board board;
    private List<Coordinate> possibleMoves;
    private Color colorP1, colorP2;

    public ReversiBoardController (Board newBoard, Color color1, Color color2) {
        board = newBoard;
        possibleMoves = new ArrayList<>();
        Color colorP1 = color1;
        Color colorP2 = color2;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void draw() {
        this.getChildren().clear();
        int row, col, size = board.getSize();

        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();

        int rowHeight = height / size;
        int colWidth = width / size;
        int radius = Math.min(rowHeight, colWidth);

        Color currentColor;

        for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
                Cell cell = board.getCell(row, col);
                CellColor cellContent = cell.getContent();
                if (cellContent != CellColor.EMPTY) {
                    if (cellContent == CellColor.BLACK) {
                        currentColor = colorP1;
                    } else {
                        currentColor = colorP2;
                    }
                    Circle token = new Circle();
                    token.setRadius(radius);
                    token.setFill(currentColor);
                    this.add(token, row, col);
                }
            }
        }
    }
}
