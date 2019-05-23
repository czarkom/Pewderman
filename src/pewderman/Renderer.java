package pewderman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer extends JPanel {
    private static final int minFrameDuration = 40;
    private Game currentGame;

    private BufferedImage NWImage;
    private BufferedImage BWImage;
    private BufferedImage UWImage;

    public static JFrame frame = new JFrame();

    public static CardLayout cl = new CardLayout();
    public static JPanel panelCont = new JPanel();

    private BufferedImage BombImage;

    private BufferedImage PUBombsImage;
    private BufferedImage PULivesImage;
    private BufferedImage PUCubaLibraImage;
    private BufferedImage PUGhostImage;
    private BufferedImage PUBootsImage;
    //    private BufferedImage PUOneUpImage;
    private BufferedImage PURangeImage;
    //    private BufferedImage PUThanosImage;
    private BufferedImage PUTrumpImage;

    private BufferedImage FireImage;

    private static int spriteSize = 40;
    private static int boardSize = 840;

    private static double scale = 1;
    private static int scaledSprite = (int) (scale * spriteSize);
    private static int scaledBoard = (int) (scale * boardSize);

    private BufferedImage[] PlayerImage = new BufferedImage[2];
    private BufferedImage DeadPlayerImage;
    private BufferedImage HaloImage;

    /**
     * Initializes the Renderer object
     *
     * @param game reference to the current Game
     */
    private Renderer(Game game) {
        setSize(scaledBoard, scaledBoard);
        setPreferredSize(new Dimension(scaledBoard, scaledBoard));

        currentGame = game;

        setFocusable(true);
        addKeyListener(currentGame);
    }


    /**
     * Loads the images to be used as sprites
     */
    private void loadImages() {
        try {
            NWImage = ImageIO.read(new File("assets/walls/walking_space.png"));
            BWImage = ImageIO.read(new File("assets/walls/wall_to_decide.png"));
            UWImage = ImageIO.read(new File("assets/walls/wall_to_decide_2_2.png"));

            BombImage = ImageIO.read(new File("assets/sprites/bomba.png"));

            PUBombsImage = ImageIO.read(new File("assets/power_ups/add_bomba.png"));
            PULivesImage = ImageIO.read(new File("assets/power_ups/add_lives.png"));
            PUCubaLibraImage = ImageIO.read(new File("assets/power_ups/cuba_libre.png"));
            PUGhostImage = ImageIO.read(new File("assets/power_ups/ghost.png"));
            PUBootsImage = ImageIO.read(new File("assets/power_ups/movement_speed_powerup.png"));
//            PUOneUpImage = ImageIO.read(new File("assets/power_ups/one_up.png"));
            PURangeImage = ImageIO.read(new File("assets/power_ups/range_up.png"));
//            PUThanosImage = ImageIO.read(new File("assets/power_ups/thanos.png"));
            PUTrumpImage = ImageIO.read(new File("assets/power_ups/trump.png"));

            FireImage = ImageIO.read(new File("assets/walls/explosion_2.png"));

            PlayerImage[0] = ImageIO.read(new File("assets/sprites/player_1_sprote__LGBTQSans.png"));
            PlayerImage[1] = ImageIO.read(new File("assets/sprites/player_3_sprite__gotta_go_fast.png"));
            DeadPlayerImage = ImageIO.read(new File("assets/sprites/Dead.png"));
            HaloImage = ImageIO.read(new File("assets/sprites/halo.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the frame
     *
     * @param g Graphics context
     */
    public void paint(Graphics g) {
        super.paint(g);
        Field currentField;
        for (int y = 0; y < currentGame.board.height; y++) {
            for (int x = 0; x < currentGame.board.width; x++) {
                currentField = currentGame.board.fields[x][y];

                switch (currentField.getFieldType()) {
                    case NO_WALL:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case BREAKABLE_WALL:
                        g.drawImage(BWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case UNBREAKABLE_WALL:
                        g.drawImage(UWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;

                    case BOMBS:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(PUBombsImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case RANGE:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(PURangeImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case LIVES:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(PULivesImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case CUBA_LIBRE:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(PUCubaLibraImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case BOOTS:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(PUBootsImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case GHOST:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(PUGhostImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case FIRE:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(FireImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                    case TRUMP_BLESSING:
                        g.drawImage(NWImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        g.drawImage(PUTrumpImage, x * scaledSprite, y * scaledSprite, scaledSprite, scaledSprite, null);
                        break;
                }
            }
        }

        for (Bomb bomb : currentGame.bombs) {
            g.drawImage(BombImage, bomb.cord.x * scaledSprite, bomb.cord.y * scaledSprite, scaledSprite, scaledSprite, null);
        }

        Player currentPlayer;
        for (int i = 0; i < currentGame.players.length; i++) {
            currentPlayer = currentGame.players[i];

            if (!currentPlayer.isAlive())
                g.drawImage(DeadPlayerImage, currentPlayer.cord.x * scaledSprite, currentPlayer.cord.y * scaledSprite, scaledSprite, scaledSprite, null);
        }

        for (int i = 0; i < currentGame.players.length; i++) {
            currentPlayer = currentGame.players[i];

            if (currentPlayer.isAlive())
                g.drawImage(PlayerImage[i], currentPlayer.cord.x * scaledSprite, currentPlayer.cord.y * scaledSprite, scaledSprite, scaledSprite, null);

            for (int j = 0; j < currentPlayer.numberOfHalos(); j++)
                g.drawImage(HaloImage, currentPlayer.cord.x * scaledSprite, currentPlayer.cord.y * scaledSprite, scaledSprite, scaledSprite, null);
        }
    }

    /**
     * Starts a game
     */
    static void runGameWithRenderer() {

//        System.out.println("Main: start");

        GameConfiguration config = new GameConfiguration("data/boardData.txt");

        Game game = new Game(config.getPlayerCount());

        game.board.fillBoard(config.getWalls());

        Renderer renderer = new Renderer(game);

        renderer.loadImages();


        frame.setTitle("PewDerMan");

        frame.add(renderer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.repaint();

        long frameStart;
        long timeDiff;
        while (!game.isEndGame()) {
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
        System.exit(0);
    }

    public static void main(String[] args) {
        runGameWithRenderer();
    }
}
