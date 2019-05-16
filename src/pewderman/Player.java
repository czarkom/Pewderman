package pewderman;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    enum MoveDirection {UP, DOWN, RIGHT, LEFT, NONE}

    enum PlayerState {DEAD, ALIVE}

    public int moveCounter;

    MoveDirection faceDirection = MoveDirection.UP;
    MoveDirection moveDirection = MoveDirection.NONE;
    public Point cord;
    public String name;
    public PlayerState playerState;
    public int playerId; // dodaje pole tu i w pewderman.Bomb w celu przyznawania punktow graczom
    public int bombsToPlantCount;
    public int bombsRange;
    public int lives;
    public int movementSpeed;

    private Game currentGame;

    private long lastMoveTimestamp = 0;
    private long moveFrame = 160;

    public Player(int x, int y, String _name, int playerId, Game currentGame) {
        cord = new Point(x, y);
        name = _name;
        playerState = PlayerState.ALIVE;
        this.playerId = playerId;
        this.currentGame = currentGame;
        moveCounter = 0;
        bombsToPlantCount = 1;
        bombsRange = 1;
        lives = 1;
        movementSpeed = 3;
        System.out.println("pewderman.Player [" + this.playerId + "]: constructor");
    }


    //metody

    public void move() {
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

//        if (moveDirection == MoveDirection.UP) {
//            this.faceDirection = moveDirection;
//            ;
//            System.out.printf("Next Field Type: %s%n", nextField.getFieldType());
//            if (nextField.isEmpty() || nextField.isAPowerUp()) {
//                cord.y--;
//                moveCounter = 0;
//                if (nextField.isAPowerUp()) {
//                    collectPowerUp();
//                }
//            }
//        } else if (moveDirection == MoveDirection.DOWN) {
//            this.faceDirection = moveDirection;
//            nextField = currentGame.board.fields[this.cord.x][this.cord.y++];
//            System.out.printf("Next Field Type: %s%n", nextField.getFieldType());
//            if (nextField.isEmpty() || nextField.isAPowerUp()) {
//                cord.y++;
//                moveCounter = 0;
//                if (nextField.isAPowerUp()) {
//                    collectPowerUp();
//                }
//            }
//        } else if (moveDirection == MoveDirection.LEFT) {
//            this.faceDirection = moveDirection;
//            nextField = currentGame.board.fields[this.cord.x--][this.cord.y];
//            if (nextField.isEmpty() || nextField.isAPowerUp()) {
//                cord.x--;
//                moveCounter = 0;
//                if (nextField.isAPowerUp()) {
//                    collectPowerUp();
//                }
//            }
//        } else if (moveDirection == MoveDirection.RIGHT) {
//            this.faceDirection = moveDirection;
//            nextField = currentGame.board.fields[this.cord.x++][this.cord.y];
//            if (nextField.isEmpty() || nextField.isAPowerUp()) {
//                cord.x++;
//                moveCounter = 0;
//                if (nextField.isAPowerUp()) {
//                    collectPowerUp();
//                }
//            }
//        } else if (moveDirection == MoveDirection.NONE) {
//            moveCounter++;
//            if (moveCounter == 30) {
//                System.out.println("pewderman.Player.move: Gotta keep moving");
//            }
//        }
        lastMoveTimestamp = System.currentTimeMillis();
    }

    public void dropBomb() {
        if (this.bombsToPlantCount != 0) {
            System.out.println("pewderman.Player: planted a bomb on field:" + cord.x + ", " + cord.y + ".");
            Bomb bomb = new Bomb(cord.x, cord.y, this, currentGame);
            currentGame.bombs.add(bomb);
            this.bombsToPlantCount--;
        }
    }

    public boolean isAlive() {
        return playerState == PlayerState.ALIVE;
    }

    public void die() {
        playerState = PlayerState.DEAD;
        System.out.println("pewderman.Player [" + playerId + "] :  has died");
    }

    public void looseALife() {
        lives--;
        if (lives <= 0) die();
    }

    public void collectPowerUp() {
        switch (currentGame.board.fields[cord.x][cord.y].getFieldType()) {
            case RANGE:
                this.bombsRange++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP, false);
                break;
            case CUBA_LIBRE:
                this.bombsRange++;
                this.bombsToPlantCount++;
                this.movementSpeed++;
                this.lives++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP, false);
                break;
            case LIVES:
                this.lives++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP, false);
                break;
            case BOMBS:
                this.bombsToPlantCount++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP, false);
                break;
            case BOOTS:
                this.movementSpeed++;
                currentGame.board.fields[cord.x][cord.y].destroy(Field.TypeFamily.POWER_UP, false);
                break;
        }
    }
}
