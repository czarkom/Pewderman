import java.awt.*;

public class Bomb {
    public Bomb(int x, int y){
        System.out.println("Bomb has been planted on field: "+ x +", " + y);
    }
    public int time_to_explode;
    public Point cord;
    public int plantedBy; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom

}
