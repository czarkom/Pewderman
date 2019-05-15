package pewderman;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public int height;
    public int width;
    public Field[][] fields;

    private Game currentGame;

    public Board(Game currentGame) {
        this.height = 21;
        this.width = 21;

        this.currentGame = currentGame;

        Random generator = new Random();

        this.fields = new Field[width][height];
        System.out.println("pewderman.Board: pewderman.Board has been created, size " + this.width + "," + this.height);
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (i == 0 || j == 0 || i == 20 || j == 20) {
                    this.fields[i][j] = new Field(Field.Type.UNBREAKABLE_WALL, Field.TypeFamily.WALL, i, j);
                } else if (generator.nextDouble() < 0.7) {
                    this.fields[i][j] = new Field(Field.Type.BREAKABLE_WALL, Field.TypeFamily.WALL, i, j);
                } else {
                    this.fields[i][j] = new Field(Field.Type.NO_WALL, Field.TypeFamily.WALL, i, j);
                }
            }
        }
    }


    public void fillBoard(ArrayList<Field> _fileds) {
        for (Field field : _fileds) {
            this.fields[field.cord.x][field.cord.y] = field;
        }
    }
}
