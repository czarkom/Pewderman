package pewderman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombTest {

    private Bomb bomb;
    private Player player;
    private  Game game;

    @BeforeEach
    void setup(){
        game = new Game(1);
        player = new Player(11,11, "Jon", 1, game);
        bomb = new Bomb(11,11, player, game );
    }

    @Test
    void timerShouldNotBeUpAtTheStart() {
        assertFalse(bomb.isTimerUp());
    }

    @Test
    void timerShouldBeUpAfter2500ms() {
        try {
            Thread.sleep(2501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(bomb.isTimerUp());
    }

}