package pewderman;

import java.nio.file.Files;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static pewderman.Field.Type.*;

public class GameConfiguration {
    private ArrayList<Field> configWalls = new ArrayList<>();
    private int playerCount;
    private String currentLine = null;

    GameConfiguration(String fileName) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            this.currentLine = reader.readLine();
            parseInitLine();
            this.currentLine = reader.readLine();

            while (this.currentLine != null) {
                System.out.println("Read config: " + this.currentLine);
                parseWallLine();
                this.currentLine = reader.readLine();
            }
            reader.close();
            System.out.println("End of file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Field> getWalls() {
        return this.configWalls;
    }

    public int getPlayerCount() {
        return this.playerCount;
    }

    private void parseInitLine() {
        String[] keyValue = currentLine.split(":");

        this.playerCount = Integer.parseInt(keyValue[1].trim());
    }

    private void parseWallLine() {
        String[] keyValue = currentLine.split(",");

        int x = Integer.parseInt(keyValue[0].trim());
        int y = Integer.parseInt(keyValue[1].trim());

        configWalls.add(new Field(UNDESTROYABLE_WALL, x, y));
    }
}
