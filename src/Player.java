import java.awt.*;

public class Player {
    enum MoveDirection {UP, DOWN, RIGHT, LEFT, NONE}

    enum IsAlive {DEAD, ALIVE};
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
        System.out.println("Player [" + this.playerId + "]: constructor");
    }


    public void move(MoveDirection _moveDirection) {
        if (_moveDirection == MoveDirection.UP) cord.y++;
        else if (_moveDirection == MoveDirection.DOWN) cord.y--;
        else if (_moveDirection == MoveDirection.LEFT) cord.x--;
        else if (_moveDirection == MoveDirection.RIGHT) cord.x++;
    }

    public void dropBomb() {
        System.out.println("Player: Player planted a bomb on field:" + cord.x + ", " + cord.y + ".");
        Bomb bomb = new Bomb(cord.x, cord.y, playerId, currentGame);
        currentGame.bombs.add(bomb);
    }

    public void die() {
        System.out.println("Player [" + this.playerId + "] : Player has died");
        this.isAlive = IsAlive.DEAD;
    }
}
