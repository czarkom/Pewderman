package pewderman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombTest {

    private Bomb fieldWithBomb;
    private Player player;
    private  Game game;

    @BeforeEach
    void setup(){
        game = new Game(1);
        player = new Player(11,11, "Jon", 1, game);
        fieldWithBomb = new Bomb(11,11, player, game );
    }

    @Test
    void timerShouldNotBeUpAtTheStart() {
        assertFalse(fieldWithBomb.isTimerUp());
    }

    @Test
    void timerShouldBeUpAfter2500ms() {
        try {
            Thread.sleep(2501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(fieldWithBomb.isTimerUp());
    }

}