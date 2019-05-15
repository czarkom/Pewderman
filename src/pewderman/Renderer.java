package pewderman;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JFrame {
    private static int scaleCoeficient = 40;

    private Game currentGame;

    Renderer() {
        setTitle("PewDerMan");
        setSize(840, 840);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Main: start");

        System.out.println("Main: Insert number of players:");

        GameConfiguration config = new GameConfiguration("data/boardData.txt");

        Game game = new Game(config.getPlayerCount());

        game.board.fillBoard(config.getWalls());

    }
}
