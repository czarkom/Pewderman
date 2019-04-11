public class Board {
    public int height;
    public int width;
    public Field[][] fields;

    public Board(){
        this.height = 100;
        this.width = 100;

        this.fields = new Field[width][height];
        System.out.println("Board: Board has been created, size "+this.width+","+this.height);

    }

}
