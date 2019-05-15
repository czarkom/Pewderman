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

    public Player(int x, int y, String _name, int _playerId, Game currentGame) {
        this.cord = new Point(x, y);
        this.name = _name;
        this.playerState = PlayerState.ALIVE;
        this.playerId = _playerId;
        this.currentGame = currentGame;
        this.moveCounter = 0;
        this.bombsToPlantCount = 1;
        this.bombsRange = 1;
        this.lives = 1;
        this.movementSpeed = 3;
        System.out.println("pewderman.Player [" + this.playerId + "]: constructor");
    }


    //metody


    public void move(MoveDirection _moveDirection) {

        if (_moveDirection == MoveDirection.UP) {
            cord.y++;
            this.moveCounter = 0;
        } else if (_moveDirection == MoveDirection.DOWN) {
            cord.y--;
            this.moveCounter = 0;
        } else if (_moveDirection == MoveDirection.LEFT) {
            cord.x--;
            this.moveCounter = 0;
        } else if (_moveDirection == MoveDirection.RIGHT) {
            cord.x++;
            this.moveCounter = 0;
        } else if (_moveDirection == MoveDirection.NONE) {


            this.moveCounter++;

            if (this.moveCounter == 30) {

                System.out.println("pewderman.Player.move: Gotta keep moving");
            }
        }

    }

    public void dropBomb() {
        System.out.println("pewderman.Player: planted a bomb on field:" + cord.x + ", " + cord.y + ".");
        Bomb bomb = new Bomb(cord.x, cord.y, playerId, currentGame, bombsRange);
        currentGame.bombs.add(bomb);
        // Tutaj powstaje pytanie czy chcemy robic polaczenie miedzy graczem a detonacja bomby czy poprostu uruchomic drugi timer
        // w graczu i przywracac mu po czasie
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
                // immortality need an implementation discussion
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
