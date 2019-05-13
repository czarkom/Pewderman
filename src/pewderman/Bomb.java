package pewderman;

import java.awt.*;

public class Bomb {
    public int time_to_explode;
    public Point cord;
    public int plantedBy; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom
    public int power;

    private Game currentGame;

    public Bomb(int x, int y, int playerId, Game currentGame, int bombPower) {
        System.out.println("pewderman.Bomb: pewderman.Bomb has been planted on field: " + x + ", " + y);
        this.plantedBy = playerId;
        this.cord = new Point(x, y);
        this.time_to_explode = 500;
        this.power = bombPower;
        this.currentGame = currentGame;
    }

    //metody

    public void explode(Player[] players, Board board) {

        System.out.println("pewderman.Bomb: pewderman.Bomb has exploded. Checked if any players near if so tougth luck... Also checked if nearby walls are destroyable.");
        for (Player player : players) {
            if (player.cord == this.cord) {
                player.lives--;
                if (player.lives == 0){
                    player.die();
                }
            }
        }
    }
}
