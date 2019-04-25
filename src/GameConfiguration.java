import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameConfiguration {
    private ArrayList<Field> configWalls = new ArrayList<>();
    private String currentLine = null;

    GameConfiguration(String fileName) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Field> getWalls() {
        return this.configWalls;
    }

}
