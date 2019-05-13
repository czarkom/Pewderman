package pewderman;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    enum MoveDirection {UP, DOWN, RIGHT, LEFT, NONE}

    enum IsAlive {DEAD, ALIVE}

    public int moveCounter;

    MoveDirection faceDirection = MoveDirection.UP;
    MoveDirection moveDirection = MoveDirection.NONE;
    public Point cord;
    public String name;
    public IsAlive isAlive;
    public int playerId; // dodaje pole tu i w pewderman.Bomb w celu przyznawania punktow graczom
    public int bombsToPlantCount;
    public int bombsRange;
    public int lifes;

    private Game currentGame;

    public Player(int x, int y, String _name, int _playerId, Game currentGame) {
        this.cord = new Point(x, y);
        this.name = _name;
        this.isAlive = IsAlive.ALIVE;
        this.playerId = _playerId;
        this.currentGame = currentGame;
        this.moveCounter = 0;
        this.bombsToPlantCount = 1;
        this.bombsRange = 1;
        this.lifes = 1;
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

    public void dropBomb(Board board, ArrayList<Bomb> bombs) {
        System.out.println("pewderman.Player: pewderman.Player planted a bomb on field:" + cord.x + ", " + cord.y + ".");
        Bomb bomb = new Bomb(cord.x, cord.y, playerId, currentGame, this.bombsRange);
        bombs.add(bomb);
        // Tutaj powstaje pytanie czy chcemy robic polaczenie miedzy graczem a detonacja bomby czy poprostu uruchomic drugi timer
        // w graczu i przywracac mu po czasie
    }

    public void die() {
        System.out.println("pewderman.Player [" + this.playerId + "] : pewderman.Player has died");
        this.isAlive = IsAlive.DEAD;

    }

    public void collectPowerUp() {
        switch (this.currentGame.board.fields[this.cord.x][this.cord.y].field_type) {
            case RANGE:
                this.bombsRange++;
            case CUBA_LIBRE:
                //immortality need an implemantation discution
            case LIFES:
                this.lifes++;
            case BOMBS:
                this.bombsToPlantCount++;
        }
    }
}
