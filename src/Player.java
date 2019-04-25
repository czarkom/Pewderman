import java.awt.*;
import java.util.ArrayList;

public class Player {
    enum MoveDirection {UP, DOWN, RIGHT, LEFT, NONE}

    enum IsAlive {DEAD, ALIVE}

    public int moveCounter;


    MoveDirection faceDirection;
    public Point cord;
    public String name;
    public IsAlive isAlive;
    public int playerId; // dodaje pole tu i w Bomb w celu przyznawania punktow graczom

    private Game currentGame;

    public Player(int x, int y, String _name, int _playerId, Game currentGame) {
        this.cord = new Point(x, y);
        this.name = _name;
        this.isAlive = IsAlive.ALIVE;
        this.playerId = _playerId;
        this.currentGame = currentGame;
        this.moveCounter = 0;
        System.out.println("Player [" + this.playerId + "]: constructor");
    }


    //metody


    public void move(MoveDirection _moveDirection) {

        if (_moveDirection == MoveDirection.UP) {cord.y++; this.moveCounter = 0;}
        else if (_moveDirection == MoveDirection.DOWN) {cord.y--; this.moveCounter = 0;}
        else if (_moveDirection == MoveDirection.LEFT) {cord.x--; this.moveCounter = 0;}
        else if (_moveDirection == MoveDirection.RIGHT) {cord.x++; this.moveCounter = 0;}
        else if (_moveDirection == MoveDirection.NONE) {


            this.moveCounter++;

            if(this.moveCounter == 30){

            System.out.println("Player.move: Gotta keep moving");
            }
        }

    }

    public void dropBomb(Board board, ArrayList<Bomb> bombs) {
        System.out.println("Player: Player planted a bomb on field:" + cord.x + ", " + cord.y + ".");
        Bomb bomb = new Bomb(cord.x, cord.y, playerId, currentGame);
        bombs.add(bomb);
    }

    public void die() {
        System.out.println("Player [" + this.playerId + "] : Player has died");
        this.isAlive = IsAlive.DEAD;

    }
}
