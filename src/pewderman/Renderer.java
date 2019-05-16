package pewderman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer extends JPanel {
    private static int scaleCoeficient = 40;
    private static int minFrameDuration = 40;
    private Game currentGame;

    private BufferedImage NWImage;
    private BufferedImage BWImage;
    private BufferedImage UWImage;

    long frameCounter = 0;

    private BufferedImage[] PlayerImage = new BufferedImage[2];

    Renderer(Game game) {
        setSize(840, 840);
        setPreferredSize(new Dimension(840, 840));

        currentGame = game;

        setFocusable(true);
        addKeyListener(currentGame);
    }

    private void loadImages() {
        try {
            NWImage = ImageIO.read(new File("assets/walls/walking_space.png"));
            BWImage = ImageIO.read(new File("assets/walls/wall_to_decide_1.png"));
            UWImage = ImageIO.read(new File("assets/walls/wall_to_decide_2.png"));

            PlayerImage[0] = ImageIO.read(new File("assets/sprites/player_1_sprote__LGBTQSans.png"));
            PlayerImage[1] = ImageIO.read(new File("assets/sprites/player_3_sprite__gotta_go_fast.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        frameCounter++;
        Field currentField;
        for (int y = 0; y < currentGame.board.height; y++) {
            for (int x = 0; x < currentGame.board.width; x++) {
                currentField = currentGame.board.fields[x][y];

                switch (currentField.getFieldType()) {
                    case NO_WALL:
                        g.drawImage(NWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                    case BREAKABLE_WALL:
                        g.drawImage(BWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                    case UNBREAKABLE_WALL:
                        g.drawImage(UWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                }
            }
        }

        Player currentPlayer;
        for (int i = 0; i < currentGame.players.length; i++) {
            currentPlayer = currentGame.players[i];

            g.drawImage(PlayerImage[i], currentPlayer.cord.x * scaleCoeficient, currentPlayer.cord.y * scaleCoeficient, null);
        }
    }

    public static void main(String[] args) {
        System.out.println("Main: start");

        System.out.println("Main: Insert number of players:");

        GameConfiguration config = new GameConfiguration("data/boardData.txt");

        Game game = new Game(config.getPlayerCount());

        game.board.fillBoard(config.getWalls());

        Renderer renderer = new Renderer(game);

        renderer.loadImages();

        JFrame frame = new JFrame();
        frame.setTitle("PewDerMan");
        frame.add(renderer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.repaint();

        long frameStart;
        long timeDiff;
        while (true) {
            frameStart = System.currentTimeMillis();

            game.step();
            frame.repaint();

            timeDiff = System.currentTimeMillis() - frameStart;
            if (timeDiff < minFrameDuration) {
                try {
                    Thread.sleep(minFrameDuration - timeDiff);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}