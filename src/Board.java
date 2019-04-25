import java.util.ArrayList;

public class Board {
    public int height;
    public int width;
    public Field[][] fields;

    private Game currentGame;

    public Board(Game currentGame){
        this.height = 100;
        this.width = 100;

        this.currentGame = currentGame;

        this.fields = new Field[width][height];
        System.out.println("Board: Board has been created, size "+this.width+","+this.height);
        for (int i = 0; i <this.width;i++){
            for(int j = 0; j<this.height;j++){
                this.fields[i][j] = new Field(Field.Type.NO_WALL,i,j);
            }

        }

    }


    public void fillBoard(ArrayList<Field> _fileds){
        for (Field field:_fileds) {
            this.fields[field.cord.x][field.cord.y] = field;
            System.out.println("Field: "+field.cord+" "+field.field_type);
        }
    }
}
