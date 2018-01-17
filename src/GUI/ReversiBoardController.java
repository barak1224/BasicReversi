package GUI;

import Core.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.DropShadow;
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

/**
 * Class in charge of controlling the GUI side of the board.
 * Extends GridPane to be able to easily show it to the screen, and implements ActionNotifier to notify
 * ReversiGameController of the choices of the users.
 */
public class ReversiBoardController extends GridPane implements ActionNotifier {
    private static String BOARD_FXML_NAME = "ReversiBoard.fxml";

    private Board board;
    private List<Move> possibleMoves;
    private Color colorP1, colorP2;
    List<ActionListener> actionListeners;

    /**
     * Constructor.
     * @param newBoard - the board the be stored in this instance.
     * @param color1 - the color of the player one.
     * @param color2 - the color of the player two.
     */
    public ReversiBoardController (Board newBoard, Color color1, Color color2) {
        board = newBoard;
        possibleMoves = new ArrayList<>();
        actionListeners = new ArrayList<>();
        colorP1 = color1;
        colorP2 = color2;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOARD_FXML_NAME));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    /**
     * Setter for the possible moves. Used to update the shown possibilities every turn.
     * @param possibleMoves - the list of possible moves to be shown on the screen.
     */
    public void setPossibleMoves(List<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    /**
     * Method in charge of actually displaying the Board on the screen, with the relevant colors and sizes.
     */
    public void draw() {
        this.getChildren().clear();
        int row, col, size = board.getSize();

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
                Rectangle cellDrawing = new Rectangle(colWidth, rowHeight, Color.rgb(130,130,130));
                cellDrawing.setStroke(Color.BLACK);
                pane.getChildren().add(cellDrawing);
                if (cellContent == CellColor.EMPTY) {
                    this.add(pane, row, col);
                } else {
                    // get the color of the cell
                    if (cellContent == CellColor.BLACK) {
                        currentColor = this.colorP1;
                    } else {    // is CellColor.WHITE
                        currentColor = this.colorP2;
                    }
                    // insert a stackpane to draw the circle centered
                    Circle token = new Circle();
                    token.setRadius(radius);
                    token.setFill(currentColor);
                    token.setStroke(Color.BLACK);
                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setOffsetY(7);
                    dropShadow.setOffsetX(-5);
                    token.setEffect(dropShadow);
                    pane.getChildren().addAll(token);
                    this.add(pane, row, col);
                }
                drawPossibleMoves(colWidth, rowHeight);
            }
        }
    }

    /**
     * Method in charge of showing the possible moves to the user, and more importantly, defining them as notifiers so
     * the user can click them (and nowhere else) and thus play his turn.
     * @param colWidth - the width of the cells in the board.
     * @param rowHeight - the height of the cells in the board.
     */
    private void drawPossibleMoves(double colWidth, double rowHeight) {
        int posRow,posCol;
        //paint the possible moves
        Iterator<Move> it = this.possibleMoves.iterator();
        while (it.hasNext()) {
            Coordinate pos = it.next().getCoordinate();
            posRow = pos.getRow() - 1;
            posCol = pos.getCol() - 1;

            Pane possPane = new Pane();
            possPane.setOnMouseClicked(e -> {
                notifyHit(pos.getRow(), pos.getCol());
            });
            Rectangle possiblePos = new Rectangle(colWidth, rowHeight, Color.rgb(112, 117, 110));
            possPane.getChildren().add(possiblePos);
            this.add(possPane, posRow, posCol);
        }
    }
    /**
     * Notify hit to the block.
     */
    private void notifyHit(int row, int col) {
        // Make a copy of the hitListeners before iterating over them.
        List<ActionListener> listeners = new ArrayList<ActionListener>(this.actionListeners);
        // Notify all listeners about a hit event:
        for (ActionListener hl : listeners) {
            hl.hitEvent(row,col);
        }
    }

    @Override
    public void addHitListener(ActionListener hl) {
        actionListeners.add(hl);
    }

    @Override
    public void removeHitListener(ActionListener hl) {
        actionListeners.remove(hl);
    }
}
