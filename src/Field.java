import java.awt.*;

public class Field {
    public Field(Type f_type, int x,int y){
        field_type = f_type;
        cord.x = x;
        cord.y = y;
    }
    public Point cord;
    enum Type {DESTROYABLE_WALL,UNDESTROYABLE_WALL,NO_WALL} //stwierdzilem ze enumem bedzie sie lepiej operowac niz stringiem
    public Type field_type;

}
