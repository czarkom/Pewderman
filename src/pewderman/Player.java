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
    private long moveFrame = 400;

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


    public void move(MoveDirection _moveDirection) {
        if (lastMoveTimestamp != 0 && (System.currentTimeMillis() - lastMoveTimestamp) < moveFrame) return;

        Field nextField;

        if (_moveDirection == MoveDirection.UP) {
            this.faceDirection = _moveDirection;
            nextField = currentGame.board.fields[this.cord.x][this.cord.y++];
            if (nextField.isEmpty() || nextField.isAPowerUp()) {
                cord.y++;
                moveCounter = 0;
                if (nextField.isAPowerUp()) {
                    collectPowerUp();
                }
            }
        } else if (_moveDirection == MoveDirection.DOWN) {
            this.faceDirection = _moveDirection;
            nextField = currentGame.board.fields[this.cord.x][this.cord.y--];
            if (nextField.isEmpty() || nextField.isAPowerUp()) {
                cord.y--;
                moveCounter = 0;
                if (nextField.isAPowerUp()) {
                    collectPowerUp();
                }
            }
        } else if (_moveDirection == MoveDirection.LEFT) {
            this.faceDirection = _moveDirection;
            nextField = currentGame.board.fields[this.cord.x--][this.cord.y];
            if (nextField.isEmpty() || nextField.isAPowerUp()) {
                cord.x--;
                moveCounter = 0;
                if (nextField.isAPowerUp()) {
                    collectPowerUp();
                }
            }
        } else if (_moveDirection == MoveDirection.RIGHT) {
            this.faceDirection = _moveDirection;
            nextField = currentGame.board.fields[this.cord.x++][this.cord.y];
            if (nextField.isEmpty() || nextField.isAPowerUp()) {
                cord.x++;
                moveCounter = 0;
                if (nextField.isAPowerUp()) {
                    collectPowerUp();
                }
            }
        } else if (_moveDirection == MoveDirection.NONE) {


            moveCounter++;

            if (moveCounter == 30) {

                System.out.println("pewderman.Player.move: Gotta keep moving");
            }
        }

        lastMoveTimestamp = System.currentTimeMillis();
    }

    public void dropBomb() {
        System.out.println("pewderman.Player: planted a bomb on field:" + cord.x + ", " + cord.y + ".");
        Bomb bomb = new Bomb(cord.x, cord.y, playerId, currentGame, bombsRange);
        currentGame.bombs.add(bomb);
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
                break;
            case CUBA_LIBRE:
                this.bombsRange++;
                this.bombsToPlantCount++;
                this.movementSpeed++;
                this.lives++;
                break;
            case LIVES:
                this.lives++;
                break;
            case BOMBS:
                this.bombsToPlantCount++;
                break;
            case BOOTS:
                this.movementSpeed++;
                break;
        }
    }
}
