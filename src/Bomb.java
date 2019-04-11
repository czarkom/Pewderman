import java.awt.*;

public class Bomb {
    public int time_to_explode;
    public Point cord;
    public int plantedBy; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom

    public Bomb(int x, int y, int playerId){
        System.out.println("Bomb: Bomb has been planted on field: "+ x +", " + y);
        plantedBy = playerId;
        cord = new Point(x,y);
        time_to_explode = 500;
    }

    //metody

    public void explode(Player[] players, Board board) {

        System.out.println("Bomb: Bomb has exploded. Checked if any players near if so tougth luck... Also checked if nearby walls are destroyable.");
        for (Player player : players) {
            if(player.cord==this.cord){
                player.die();
            }
        }
    }
}
