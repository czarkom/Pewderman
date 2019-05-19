package pewderman;

import java.awt.*;

class Player {
    enum MoveDirection {UP, DOWN, RIGHT, LEFT, NONE}

    enum PlayerState {DEAD, ALIVE}

    MoveDirection faceDirection = MoveDirection.UP;
    MoveDirection moveDirection = MoveDirection.NONE;
    Point cord;
    private String name;
    PlayerState playerState;
    int playerId; // dodaje pole tu i w pewderman.Bomb w celu przyznawania punktow graczom
    int bombsToPlantCount;
    int bombsRange;
    private int lives;
    private int hasTrumpBlessing;

    private Game currentGame;

    private long lastMoveTimestamp = 0;
    private long moveFrame = 160;

    Player(int x, int y, String _name, int playerId, Game currentGame) {
        cord = new Point(x, y);
        name = _name;
        playerState = PlayerState.ALIVE;
        this.playerId = playerId;
        this.currentGame = currentGame;
        int moveCounter = 0;
        bombsToPlantCount = 1;
        bombsRange = 1;
        lives = 1;
        hasTrumpBlessing = 0;
        System.out.println("pewderman.Player [" + this.playerId + "]: constructor");
    }


    //metody

    void move() {
        if (lastMoveTimestamp != 0 && (System.currentTimeMillis() - lastMoveTimestamp) < moveFrame) return;

        switch (moveDirection) {
            case UP:
                faceDirection = moveDirection;
                int cord_yU = cord.y;
                Field nextFieldU = currentGame.board.fields[cord.x][cord_yU - 1];
                System.out.printf("Next Field Type: %s%n", nextFieldU.getFieldType());
                if (nextFieldU.getFieldType() == Field.Type.NO_WALL) {
                    this.cord.y--;
                }
                if (nextFieldU.getFieldTypeFamily() == Field.TypeFamily.POWER_UP) {
                    this.cord.y--;
                    collectPowerUp();
                }
                break;
            case DOWN:
                faceDirection = moveDirection;
                int cord_yD = cord.y;
                Field nextFieldD = currentGame.board.fields[cord.x][cord_yD + 1];
                System.out.printf("Next Field Type: %s%n", nextFieldD.getFieldType());
                if (nextFieldD.getFieldType() == Field.Type.NO_WALL) {
                    this.cord.y++;
                }
                if (nextFieldD.getFieldTypeFamily() == Field.TypeFamily.POWER_UP) {
                    this.cord.y++;
                    collectPowerUp();
                }
                break;
            case LEFT:
                faceDirection = moveDirection;
                int cord_xL = cord.x;
                Field nextFieldL = currentGame.board.fields[cord_xL - 1][cord.y];
                System.out.printf("Next Field Type: %s%n", nextFieldL.getFieldType());
                if (nextFieldL.getFieldType() == Field.Type.NO_WALL) {
                    this.cord.x--;
                }
                if (nextFieldL.getFieldTypeFamily() == Field.TypeFamily.POWER_UP) {
                    this.cord.x--;
                    collectPowerUp();
                }
                break;
            case RIGHT:
                faceDirection = moveDirection;
                int cord_xR = cord.x;
                Field nextFieldR = currentGame.board.fields[cord_xR + 1][cord.y];
                System.out.printf("Next Field Type: %s%n", nextFieldR.getFieldType());
                if (nextFieldR.getFieldType() == Field.Type.NO_WALL) {
                    this.cord.x++;
                }
                if (nextFieldR.getFieldTypeFamily() == Field.TypeFamily.POWER_UP) {
                    this.cord.x++;
                    collectPowerUp();
                }
                break;
        }
        lastMoveTimestamp = System.currentTimeMillis();
    }

    void dropBomb() {
        if (this.bombsToPlantCount != 0) {
            System.out.println("pewderman.Player: planted a bomb on field:" + cord.x + ", " + cord.y + ".");
            Bomb bomb = new Bomb(cord.x, cord.y, this, currentGame);
            currentGame.bombs.add(bomb);
            this.bombsToPlantCount--;
        }
    }

    boolean isAlive() {
        return playerState == PlayerState.ALIVE;
    }

    int numberOfHalos() {
        return lives - 1;
    }

    private void die() {
        playerState = PlayerState.DEAD;
        System.out.println("pewderman.Player [" + playerId + "] :  has died");
    }

    String getName() {
        return name;
    }

    void looseALife() {
        lives--;
        if (lives <= 0) die();
    }

    void useTrumpsBlessing() {
        switch (this.faceDirection) {
            case UP:
                int cordYU = this.cord.y - 1;
                if (currentGame.board.fields[this.cord.x][cordYU].getFieldType() == Field.Type.NO_WALL && this.hasTrumpBlessing > 0) {
                    currentGame.board.fields[this.cord.x][cordYU].buildAWall();
                    this.hasTrumpBlessing--;
                }
                break;
            case DOWN:
                int cordYD = this.cord.y + 1;
                if (currentGame.board.fields[this.cord.x][cordYD].getFieldType() == Field.Type.NO_WALL && this.hasTrumpBlessing > 0) {
                    currentGame.board.fields[this.cord.x][cordYD].buildAWall();
                    this.hasTrumpBlessing--;
                }
                break;
            case RIGHT:
                int cordXR = this.cord.x + 1;
                if (currentGame.board.fields[this.cord.x][cordXR].getFieldType() == Field.Type.NO_WALL && this.hasTrumpBlessing > 0) {
                    currentGame.board.fields[this.cord.x][cordXR].buildAWall();
                    this.hasTrumpBlessing--;
                }
                break;
            case LEFT:
                int cordXL = this.cord.x - 1;
                if (currentGame.board.fields[this.cord.x][cordXL].getFieldType() == Field.Type.NO_WALL && this.hasTrumpBlessing > 0) {
                    currentGame.board.fields[this.cord.x][cordXL].buildAWall();
                    this.hasTrumpBlessing--;
                }
                break;
        }
    }

    private void collectPowerUp() {
        switch (currentGame.board.fields[cord.x][cord.y].getFieldType()) {
            case RANGE:
                this.bombsRange++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP);
                break;
            case CUBA_LIBRE:
                this.bombsRange++;
                this.bombsToPlantCount++;
                if (this.moveFrame > 79) {
                    this.moveFrame = this.moveFrame - 10;
                }
                this.lives++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP);
                break;
            case LIVES:
                this.lives++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP);
                break;
            case BOMBS:
                this.bombsToPlantCount++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP);
                break;
            case BOOTS:
                if (this.moveFrame > 79) {
                    this.moveFrame = this.moveFrame - 10;
                }
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP);
                break;
            case TRUMP_BLESSING:
                this.hasTrumpBlessing++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP);
                break;
        }
    }
}
