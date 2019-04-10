import java.awt.*;

public class Player {
    public Point cord;
    public String name;
    public Boolean isAlive;
    public int playerId; // dodaje pole tu i w Bomb w celu przyznawania punktow graczom

    public Player(int x,int y, String _name, int _playerId){
        cord = new Point(x,y);
        name = _name;
        isAlive = true;
        playerId = _playerId;
    }

}
