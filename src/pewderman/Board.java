package pewderman;

import java.util.ArrayList;
import java.util.Random;

class Board {
    int height;
    int width;
    Field[][] fields;

    /**
     * Creates a Board object with UNBREAKABLE_WALL boundary, then fills it in with BREAKABLE_WALLs and NO_WALLs at random
     */
    Board() {
        this.height = 21;
        this.width = 21;

        Random generator = new Random();

        this.fields = new Field[width][height];
//        System.out.println("pewderman.Board: has been created, size " + this.width + "," + this.height);
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

    /**
     * Changes Fields on a Board to reflect the provided list of Fields
     *
     * @param _fields a list of desired Fields
     */
    void fillBoard(ArrayList<Field> _fields) {
        for (Field field : _fields) {
            this.fields[field.cord.x][field.cord.y] = field;
        }
    }
}
