public class Board {
    public int height;
    public int width;
    public Field[][] fields = new Field[height][width];

    public Board(int _height,int _width, Field.Type _fieldType ){
        height = _height;
        width = _width;

        for(int x=0;x<height;x++){

            for(int y=0;y<width;y++){

                fields[x][y] = new Field(_fieldType, x, y);

            }
        }
    }

}
