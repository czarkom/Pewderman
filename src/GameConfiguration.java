import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameConfiguration {
    private ArrayList<Field> configWalls = new ArrayList<>();

    public ArrayList<Field> getWalls() {
        return this.configWalls;
    }
}
