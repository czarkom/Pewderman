import java.awt.*;

public class Player {
    public Player(int x,int y, String nm, int id){
        cord.x = x;
        cord.y = y;
        name = nm;
        isAlive = Boolean.TRUE;
        playerId = id;
    }
    public Point cord;
    public String name;
    public Boolean isAlive;
    public int playerId; // dodaje pole tu i w Bomb w celu przyznawania punktow graczom
}
