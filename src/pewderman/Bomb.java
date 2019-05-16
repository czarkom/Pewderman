package pewderman;

import java.awt.*;

public class Bomb {
    private int timeToExplode; // w milisekundach
    public Point cord;
    private Player planter; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom
    private int range;
    private long plantTime;

    private Game currentGame;

    public Bomb(int x, int y, Player planter, Game currentGame) {
        this.planter = planter;
        cord = new Point(x, y);
        timeToExplode = 2500;
        range = planter.bombsRange;
        this.currentGame = currentGame;
        plantTime = System.currentTimeMillis();

        System.out.println("pewderman.Bomb [x: " + cord.x + ", y: " + cord.y + ", range: " + range + "]: planted by player " + planter.playerId);
    }

    public boolean isTimerUp() {
        return (System.currentTimeMillis() - plantTime) >= timeToExplode;
    }

    private void dealDamageToPlayers(Field currentField) {
        for (Player player : currentGame.players) {
            if (player.isAlive() && player.cord.equals(currentField.cord)) player.looseALife();
        }
    }

    private boolean damageTheField(Field currentField) {
        if (!currentField.isEmpty() && currentField.isAWall()) {
            currentField.destroy(Field.TypeFamily.WALL, false);
            return false;
        }
        dealDamageToPlayers(currentField);
        return true;
    }

    private void explodeDirection(int dx, int dy) {
        if (dx > 0) dx = 1;
        else if (dx < 0) dx = -1;

        if (dy > 0) dy = 1;
        else if (dy < 0) dy = -1;

        Field currentField;

        for (int i = 1; i <= range; i++) {
            try {
                currentField = currentGame.board.fields[cord.x + i * dx][cord.y + i * dy];
                if (!damageTheField(currentField))  {
                    break;
                }
            } catch (IndexOutOfBoundsException err) {
                break;
            }
        }
    }

    public void explode() {
        System.out.println("pewderman.Bomb [x: " + cord.x + ", y: " + cord.y + ", range: " + range + "]: exploded");

        damageTheField(currentGame.board.fields[cord.x][cord.y]);
        explodeDirection(0, -1); // wybuch w górę
        explodeDirection(1, 0); // wybuch w prawo
        explodeDirection(0, 1); // wybuch w dół
        explodeDirection(-1, 0); // wybuch w lewo
    }
}
