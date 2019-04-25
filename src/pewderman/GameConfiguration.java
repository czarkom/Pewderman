package pewderman;

import java.nio.file.Files;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static pewderman.Field.Type.*;

public class GameConfiguration {
    private ArrayList<Field> configWalls = new ArrayList<>();
    private String currentLine = null;

    GameConfiguration(String fileName) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                System.out.println("Read config: " + line);
                this.currentLine = line;
                parseLine();
                line = reader.readLine();
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

    private void parseLine() {
        String [] keyValue = currentLine.split(",");

        int x = Integer.parseInt(keyValue[0].trim());
        int y = Integer.parseInt(keyValue[1].trim());

        configWalls.add(new Field(UNDESTROYABLE_WALL, x, y));
    }
}
