package pewderman;

import java.awt.*;

class Bomb {
    private int timeToExplode; //w milisekundach
    Point cord;
    private Player planter; // dodaje pole tu oraz w player (player id) w celu przyznawania punktow graczom
    private int range;
    private long plantTime;

    private Game currentGame;

    /**
     * Initializes a Bomb object
     *
     * @param x position on X axis
     * @param y position on Y axis
     * @param planter reference to the Player who planted the bomb
     * @param currentGame reference to the current Game
     */
    Bomb(int x, int y, Player planter, Game currentGame) {
        this.planter = planter;
        cord = new Point(x, y);
        timeToExplode = 2500;
        range = planter.bombsRange;
        this.currentGame = currentGame;
        plantTime = System.currentTimeMillis();

//        System.out.println("pewderman.Bomb [x: " + cord.x + ", y: " + cord.y + ", range: " + range + "]: planted by player " + planter.playerId);
    }

    /**
     * Checks if the timer's ended
     *
     * @return 'true' if timer's up, 'false' otherwise
     */
    boolean isTimerUp() {
        return (System.currentTimeMillis() - plantTime) >= timeToExplode;
    }

    /**
     * Deals damage to players on the provided Field
     *
     * @param currentField field on which the damage is dealt
     */
    private void dealDamageToPlayers(Field currentField) {
        for (Player player : currentGame.players) {
            if (player.isAlive() && player.cord.equals(currentField.cord)) player.looseALife();
        }
    }

    /**
     * Deals damage to the provided Field
     *
     * @param currentField field to which the damage is dealt
     * @return 'true' if the field was destroyed, 'false' otherwise
     */
    private boolean damageTheField(Field currentField) {
        if (!currentField.isEmpty() && currentField.isAWall()) {
            currentField.destroy();
            return false;
        } else {
            currentField.setOnFire(currentField.getFieldType());
        }
        dealDamageToPlayers(currentField);
        return true;
    }

    /**
     * Deals damage to all fields in range in a given direction
     *
     * @param dx X axis direction [ <0 = LEFT ; >0 = RIGHT]
     * @param dy Y axis direction [ <0 = UP ; >0 = DOWN]
     */
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

    /**
     * Sets off the Bombs Field and Player damage methods
     */
    void explode() {
//        System.out.println("pewderman.Bomb [x: " + cord.x + ", y: " + cord.y + ", range: " + range + "]: exploded");

        int range = planter.bombsRange;

        planter.bombsToPlantCount++;

        damageTheField(currentGame.board.fields[cord.x][cord.y]);
        explodeDirection(0, -range); // wybuch w górę
        explodeDirection(range, 0); // wybuch w prawo
        explodeDirection(0, range); // wybuch w dół
        explodeDirection(-range, 0); // wybuch w lewo
    }
}
