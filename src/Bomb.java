import java.awt.*;

public class Bomb {
    public Bomb(int x, int y, int playerId){
        System.out.println("Bomb has been planted on field: "+ x +", " + y);
        plantedBy = playerId;
        cord.x = x;
        cord.y = y;
        time_to_explode = 3;
    }
    public int time_to_explode;
    public Point cord;
    public int plantedBy; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom

}
