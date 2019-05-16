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

    private BufferedImage BombImage;

    private BufferedImage PUBombsImage;
    private BufferedImage PULivesImage;
    private BufferedImage PUCubaLibreImage;
    private BufferedImage PUGhostImage;
    private BufferedImage PUBootsImage;
    private BufferedImage PUOneUpImage;
    private BufferedImage PURangeImage;
    private BufferedImage PUThanosImage;
    private BufferedImage PUTrumpImage;

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

            BombImage = ImageIO.read(new File("assets/sprites/bomba.png"));

            PUBombsImage = ImageIO.read(new File("assets/power_ups/add_bomba.png"));
            PULivesImage = ImageIO.read(new File("assets/power_ups/add_lives.png"));
            PUCubaLibreImage = ImageIO.read(new File("assets/power_ups/cuba_libre.png"));
            PUGhostImage = ImageIO.read(new File("assets/power_ups/ghost.png"));
            PUBootsImage = ImageIO.read(new File("assets/power_ups/movement_speed_powerup.png"));
            PUOneUpImage = ImageIO.read(new File("assets/power_ups/one_up.png"));
            PURangeImage = ImageIO.read(new File("assets/power_ups/range_up.png"));
            PUThanosImage = ImageIO.read(new File("assets/power_ups/thanos.png"));
            PUTrumpImage = ImageIO.read(new File("assets/power_ups/trump.png"));

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

                    case BOMBS:
                        g.drawImage(NWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        g.drawImage(PUBombsImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                    case RANGE:
                        g.drawImage(NWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        g.drawImage(PURangeImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                    case LIVES:
                        g.drawImage(NWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        g.drawImage(PULivesImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                    case CUBA_LIBRE:
                        g.drawImage(NWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        g.drawImage(PUCubaLibreImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                    case BOOTS:
                        g.drawImage(NWImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        g.drawImage(PUBootsImage, x * scaleCoeficient, y * scaleCoeficient, null);
                        break;
                }
            }
        }

        for (Bomb bomb : currentGame.bombs) {
            g.drawImage(BombImage, bomb.cord.x * scaleCoeficient, bomb.cord.y * scaleCoeficient, null);
        }

        Player currentPlayer;
        for (int i = 0; i < currentGame.players.length; i++) {
            currentPlayer = currentGame.players[i];

            if(currentPlayer.isAlive()) g.drawImage(PlayerImage[i], currentPlayer.cord.x * scaleCoeficient, currentPlayer.cord.y * scaleCoeficient, null);
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
