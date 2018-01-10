import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOSettings {
    private static final String PLAYER_ONE_COLOR = "Player one color";
    private static final String PLAYER_TWO_COLOR = "Player two color";
    private static final String SIZE_BOARD = "Size board";
    private static final String WHO_STARTS = "Who starts";
    private static final String SEPERATOR = ":";


    public static GameSettings read() {
        BufferedReader bReader = null;
        GameSettings gameSettings = null;
        try {
            File fileLocation = new File("UserSettings.txt");
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileLocation)));
            gameSettings = buildGameSettings(bReader);
        } catch (Exception e) {
            System.err.print("File Couldn't open");
        } finally {
            if (bReader != null) {
                try {
                    bReader.close();
                }catch (IOException e) {
                    return null;
                }
            }
        }
        return gameSettings;
    }

    public static void writeToFile(PrintWriter bWriter, List<String> lines) {
        for (String line: lines) {
            bWriter.println(line);
        }
    }

    private static GameSettings buildGameSettings(BufferedReader reader) throws IOException {
        Color player1 = null, player2 = null;
        Integer sizeBoard = null;
        PlayerStart whoStarts = null;
        String line;
        while((line = reader.readLine()) != null) {
            String[] split = line.split(SEPERATOR);
            if (split[0].equals(PLAYER_ONE_COLOR)) {
                player1 = colorParse(split[1]);
            }
            if (split[0].equals(PLAYER_TWO_COLOR)) {
                player2 = colorParse(split[1]);
            }
            if (split[0].equals(SIZE_BOARD)) {
                sizeBoard = sizeBoardParse(split[1]);
            }
            if (split[0].equals(WHO_STARTS)) {
                whoStarts = whoStartsParse(split[1]);
            }
        }
        return checkGameSettings(whoStarts, player1, player2, sizeBoard);
    }

    private static PlayerStart whoStartsParse(String stringInteger) {
        if (stringInteger.equals(PlayerStart.BLACK.name()) || stringInteger.equals(PlayerStart.WHITE.name())) {
            return PlayerStart.valueOf(stringInteger);
        } else {
            return null;
        }
    }

    private static Integer sizeBoardParse(String stringInteger) {
        Integer number;
        try {
            number = Integer.parseInt(stringInteger);
        } catch (Exception e) {
            return null;
        }
        if (number >= 4 && number <= 20 && number % 2 == 0) {
            return number;
        } else {
            return null;
        }
    }

    private static Color colorParse(String stringColor) {
        Color color;
        try {
            color = Color.web(stringColor);
        } catch (Exception e) {
            return null;
        }
        return color;
    }

    private static GameSettings checkGameSettings(PlayerStart whoStarts, Color player1, Color player2, Integer sizeBoard) {
        if (whoStarts != null && player1 != null && player2 != null && sizeBoard != null) {
            return new GameSettings(whoStarts, player1, player2, sizeBoard.intValue());
        } else {
            return null;
        }
    }
}

