package pewderman;

import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Game game;

    @BeforeEach
    void setUp() {
        GameConfiguration config = new GameConfiguration("data/boardData.txt");
        game = new Game(2);
        game.board.fillBoard(config.getWalls());
    }

    @Test
    void shouldNotMoveIntoTheWall() {
        Point firstPos = new Point(game.players[0].cord);

        game.players[0].moveDirection = Player.MoveDirection.UP;
        game.players[0].faceDirection = Player.MoveDirection.UP;

        game.players[0].move();

        assertEquals(firstPos, game.players[0].cord);
    }

    @Test
    void shouldMoveToFreeSpace() {
        Point firstPos = new Point(game.players[0].cord);
        Point expected = new Point(game.players[0].cord);
        expected.x++;

        game.players[0].moveDirection = Player.MoveDirection.RIGHT;
        game.players[0].faceDirection = Player.MoveDirection.RIGHT;

        game.players[0].move();

        assertNotEquals(firstPos, game.players[0].cord);
        assertEquals(expected, game.players[0].cord);
    }

    @Test
    void shouldDropABombOnPlayersCoordinates() {
    }

    @Test
    void isAlive() {
    }

    @Test
    void numberOfHalos() {
    }

    @Test
    void getName() {
    }

    @Test
    void looseALife() {
    }

    @Test
    void useTrumpsBlessing() {
    }
}