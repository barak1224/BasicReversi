package GUI;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.List;

/**
 * Class in charge of reading the settings from a file and saving them.
 */
public class IOSettings {
    private static final String PLAYER_ONE_COLOR = "Core.Player one color";
    private static final String PLAYER_TWO_COLOR = "Core.Player two color";
    private static final String SIZE_BOARD = "Size board";
    private static final String WHO_STARTS = "Who starts";
    private static final String SEPERATOR = ":";
    private static final String SETTINGS_FILE_NAME = "UserSettings.txt";


    /**
     * Method in charge of reading from the file and returning an instance of GameSettings containing the relevant settings.
     *
     * @return GameSettings - the settings for the follorwing game.
     */
    public static GameSettings read() {
        BufferedReader bReader = null;
        GameSettings gameSettings = null;
        try {
            File fileLocation = new File(SETTINGS_FILE_NAME);
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileLocation)));
            gameSettings = buildGameSettings(bReader);
        } catch (Exception e) {
            System.err.print("File Couldn't open");
        } finally {
            if (bReader != null) {
                try {
                    bReader.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return gameSettings;
    }

    /**
     * Method in charge to write the settings to the file, in order to save them for the next time the game is opened.
     *
     * @param bWriter - the writer.
     * @param lines   - the list of lines to be written to the file.
     */
    public static void writeToFile(PrintWriter bWriter, List<String> lines) {
        for (String line : lines) {
            bWriter.println(line);
        }
    }

    /**
     * Method in charge of actually building the GameSettings, basing them on the data being obtained from the file.
     *
     * @param reader - the reader, to get the data from the file.
     * @return GameSettings - an instance containing the settings for the following game.
     * @throws IOException - in case it fails reading the file.
     */
    private static GameSettings buildGameSettings(BufferedReader reader) throws IOException {
        Color player1 = null, player2 = null;
        Integer sizeBoard = null;
        PlayerStart whoStarts = null;
        String line;
        while ((line = reader.readLine()) != null) {
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

    /**
     * Parses the read info to understand which player starts.
     *
     * @param stringInteger - the data read from the file.
     * @return PlayerStart - an instance setting which player will start playing.
     */
    private static PlayerStart whoStartsParse(String stringInteger) {
        if (stringInteger.equals(PlayerStart.BLACK.name()) || stringInteger.equals(PlayerStart.WHITE.name())) {
            return PlayerStart.valueOf(stringInteger);
        } else {
            return null;
        }
    }

    /**
     * Parses the data to get the size of the Board from the read text.
     *
     * @param stringInteger - the data read from the file.
     * @return Integer - the parsed size of the Board.
     */
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

    /**
     * Parses the read data to get the Color for the player.
     * @param stringColor - the data read from the file.
     * @return Color - the desired color for the player.
     */
    private static Color colorParse(String stringColor) {
        Color color;
        try {
            color = Color.web(stringColor);
        } catch (Exception e) {
            return null;
        }
        return color;
    }

    /**
     * Checks if the GameSettings created containg data for each slot, and returns null if it fails.
     * @param whoStarts - instance of PlayerStart, defining who starts playing.
     * @param player1 - The color of P1.
     * @param player2 - The color of P2.
     * @param sizeBoard - the size of the board.
     * @return an instance of GameSettingss if every slot is filled, and null elsewise.
     */
    private static GameSettings checkGameSettings(PlayerStart whoStarts, Color player1, Color player2, Integer sizeBoard) {
        if (whoStarts != null && player1 != null && player2 != null && sizeBoard != null) {
            return new GameSettings(whoStarts, player1, player2, sizeBoard.intValue());
        } else {
            return null;
        }
    }
}

