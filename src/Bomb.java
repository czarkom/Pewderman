import java.awt.*;

public class Bomb {
    public int time_to_explode;
    public Point cord;
    public int plantedBy; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom

    public Bomb(int x, int y, int playerId){
        System.out.println("Bomb has been planted on field: "+ x +", " + y);
        plantedBy = playerId;
        cord = new Point(x,y);
        time_to_explode = 3;
    }
}
