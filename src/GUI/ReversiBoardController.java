package GUI;

import Core.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReversiBoardController extends GridPane {
    private Board board;
    private List<Move> possibleMoves;
    private Color colorP1, colorP2;

    public ReversiBoardController (Board newBoard, Color color1, Color color2) {
        board = newBoard;
        possibleMoves = new ArrayList<>();
        colorP1 = color1;
        colorP2 = color2;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void setPossibleMoves(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public void draw() {
        this.getChildren().clear();
        int row, col, size = board.getSize(), posRow, posCol;

        double height = this.getPrefHeight();
        double width = this.getPrefWidth();

        double rowHeight = height / size;
        double colWidth = width / size;
        int radius = (int) Math.min(rowHeight, colWidth) / 2 - 5;

        Color currentColor;

        for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
                Cell cell = board.getCell(row+1, col+1);
                CellColor cellContent = cell.getContent();
                StackPane pane = new StackPane();
                if (cellContent == CellColor.EMPTY) {
                    Rectangle cellDrawing = new Rectangle(colWidth, rowHeight, Color.rgb(0,153,0));
                    cellDrawing.setStroke(Color.BLACK);
                    pane.getChildren().add(cellDrawing);
                    this.add(pane, row, col);
                } else {
                    // get the color of the cell
                    if (cellContent == CellColor.BLACK) {
                        currentColor = this.colorP1;
                    } else {    // is CellColor.WHITE
                        currentColor = this.colorP2;
                    }
                    // insert a stackpane to draw the circle centered
                    Rectangle cellDrawing = new Rectangle(colWidth, rowHeight, Color.rgb(0,153,0));
                    cellDrawing.setStroke(Color.BLACK);
                    Circle token = new Circle();
                    token.setRadius(radius);
                    token.setFill(currentColor);
                    token.setStroke(Color.BLACK);
                    pane.getChildren().addAll(cellDrawing, token);
                    this.add(pane, row, col);
                }
            }
        }
        //paint the possible moves
        Iterator<Move> it = this.possibleMoves.iterator();
        while (it.hasNext()) {
            Coordinate pos = it.next().getCoordinate();
            posRow = pos.getRow() - 1;
            posCol = pos.getCol() - 1;

            Pane possPane = new Pane();
            possPane.setOnMouseClicked(e -> {
                System.out.println(pos.toString());
            });
            Rectangle possiblePos = new Rectangle(colWidth, rowHeight, Color.rgb(0, 255, 0));
            possPane.getChildren().add(possiblePos);
            this.add(possPane, posRow, posCol);
        }
    }

}
