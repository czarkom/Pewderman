package pewderman;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static pewderman.Field.Type.*;

class GameConfiguration {
    private ArrayList<Field> configWalls = new ArrayList<>();
    private int playerCount = 2;

    private String configFile;
    private String currentLine = null;
    private String currentValue = null;

    private BufferedReader reader;

    GameConfiguration(String fileName) {
        this.configFile = fileName;
//        setPlayerCount();
        getConfig();
    }

    ArrayList<Field> getWalls() {
        return this.configWalls;
    }

    int getPlayerCount() {
        return this.playerCount;
    }



    private void parseLine() {
        String[] keyValue = currentLine.split(":");

        String type = keyValue[0].trim();
        this.currentValue = keyValue[1];

        switch (type) {
            case "U":
                makeWall(UNBREAKABLE_WALL);
                break;
            case "B":
                makeWall(BREAKABLE_WALL);
                break;
            case "N":
                makeWall(NO_WALL);
                break;
        }
    }

    private void makeWall(Field.Type type) {
        String[] keyValue = currentValue.split(",");

        int x = Integer.parseInt(keyValue[0].trim());
        int y = Integer.parseInt(keyValue[1].trim());

        configWalls.add(new Field(type, Field.TypeFamily.WALL, x, y));
    }

    private void advance() {
        try {
            this.currentLine = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initiateConfigReader() {
        try {
            this.reader = new BufferedReader(new FileReader(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConfigReader() {
        try {
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getConfig() {
        initiateConfigReader();
        advance();

        while (this.currentLine != null) {
//            System.out.println("Read config: " + this.currentLine);
            parseLine();
            advance();
        }

        closeConfigReader();
//        System.out.println("End of file!");
    }

    private void setPlayerCount() {
        Scanner input = new Scanner(System.in);

//        System.out.print("Enter a number of players: ");

        this.playerCount = input.nextInt();

        input.close();
    }
}
