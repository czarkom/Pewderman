import java.awt.*;

public class Field {
    public Point cord;
    enum Type {DESTROYABLE_WALL,UNDESTROYABLE_WALL,NO_WALL} //stwierdzilem ze enumem bedzie sie lepiej operowac niz stringiem
    public Type field_type;

    public Field(Type _field_type, int x,int y){
        field_type = _field_type;
        cord = new Point(x,y);
    }


}
