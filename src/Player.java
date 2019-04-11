import java.awt.*;
import java.util.ArrayList;

public class Player {
    enum MoveDirection {UP, DOWN, RIGHT, LEFT}

    ;
    MoveDirection faceDirection;
    public Point cord;
    public String name;
    public Boolean isAlive;
    public int playerId; // dodaje pole tu i w Bomb w celu przyznawania punktow graczom

    public Player(int x, int y, String _name, int _playerId) {
        this.cord = new Point(x, y);
        this.name = _name;
        this.isAlive = true;
        this.playerId = _playerId;
        System.out.println("Player ["+this.playerId+"]: constructor");
    }


    //metody


    public void move(MoveDirection _moveDirection) {
        if(_moveDirection == MoveDirection.UP) cord.y++;
        else if(_moveDirection == MoveDirection.DOWN) cord.y--;
        else if(_moveDirection == MoveDirection.LEFT) cord.x--;
        else if(_moveDirection == MoveDirection.RIGHT) cord.x++;

    }

    public void dropBomb(Board board, ArrayList<Bomb> bombs) {
        System.out.println("Player: Player planted a bomb on field:"+cord.x+", "+cord.y+".");
        Bomb bomb = new Bomb(cord.x,cord.y,playerId);
        bombs.add(bomb);
    }

    public void die() {
        System.out.println("Player [" + this.playerId + "] : Player has died");
        this.isAlive = false;

    }
}
