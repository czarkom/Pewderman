package pewderman;

import java.awt.*;

public class Bomb {
    private int timeToExplode; // w milisekundach
    private Point cord;
    private int plantedBy; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom
    private int range;
    private long plantTime;

    private Game currentGame;

    public Bomb(int x, int y, int playerId, Game currentGame, int bombRange) {
        plantedBy = playerId;
        cord = new Point(x, y);
        timeToExplode = 6000;
        range = bombRange;
        this.currentGame = currentGame;
        plantTime = System.currentTimeMillis();

        System.out.println("pewderman.Bomb [x: " + cord.x + ", y: " + cord.y + ", range: " + range + "]: planted by player " + plantedBy);
    }

    public boolean isTimerUp() {
        return (System.currentTimeMillis() - plantTime) >= timeToExplode;
    }

    public void explode() {
        System.out.println("pewderman.Bomb [x: " + cord.x + ", y: " + cord.y + ", range: " + range + "]: exploded");

        for (Player player : currentGame.players) {
            if (player.isAlive() && player.cord.equals(cord)) player.looseALife();
        }
    }
}
