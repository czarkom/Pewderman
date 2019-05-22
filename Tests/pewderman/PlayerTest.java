package pewderman;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void PlayerPosition(){
        Game current_game = new Game(2);
        Board board = new Board();
        Player player = new Player(20, 20, "Player", 1, current_game);
        assertTrue("Wrong position", player.cord.x<board.width && player.cord.y<board.height);
    }
    @Test
    public void PlayerName(){
        Game current_game = new Game(2);
        Player player = new Player(10, 10, "Player", 1, current_game);
        String name = player.getName();
        assertEquals("Player", name);
    }
}
