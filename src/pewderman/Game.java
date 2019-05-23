package pewderman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class Game implements KeyListener {
    Board board;
    Player[] players;
    ArrayList<Bomb> bombs;
    private Music music;
    private boolean endGame = false;

    /**
     * DOES NOTHING
     * @param e event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key presses in the game
     *
     * @param e event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        switch (c) {
            case KeyEvent.VK_W:
                if (players[0].isAlive()) {
                    players[0].moveDirection = Player.MoveDirection.UP;
                    players[0].faceDirection = Player.MoveDirection.UP;
                }
                break;
            case KeyEvent.VK_S:
                if (players[0].isAlive()) {
                    players[0].moveDirection = Player.MoveDirection.DOWN;
                    players[0].faceDirection = Player.MoveDirection.DOWN;
                }
                break;
            case KeyEvent.VK_A:
                if (players[0].isAlive()) {
                    players[0].moveDirection = Player.MoveDirection.LEFT;
                    players[0].faceDirection = Player.MoveDirection.LEFT;
                }
                break;
            case KeyEvent.VK_D:
                if (players[0].isAlive()) {
                    players[0].moveDirection = Player.MoveDirection.RIGHT;
                    players[0].faceDirection = Player.MoveDirection.RIGHT;
                }
                break;

            case KeyEvent.VK_E:
                if (players[0].isAlive()) players[0].useTrumpsBlessing();
                break;

            case KeyEvent.VK_BACK_SLASH:
                if (players[1].isAlive()) players[1].useTrumpsBlessing();
                break;

            case KeyEvent.VK_UP:
                if (players[1].isAlive()) {
                    players[1].moveDirection = Player.MoveDirection.UP;
                    players[1].faceDirection = Player.MoveDirection.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (players[1].isAlive()) {
                    players[1].moveDirection = Player.MoveDirection.DOWN;
                    players[1].faceDirection = Player.MoveDirection.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (players[1].isAlive()) {
                    players[1].moveDirection = Player.MoveDirection.LEFT;
                    players[1].faceDirection = Player.MoveDirection.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (players[1].isAlive()) {
                    players[1].moveDirection = Player.MoveDirection.RIGHT;
                    players[1].faceDirection = Player.MoveDirection.RIGHT;
                }
                break;

            case KeyEvent.VK_Q:
                if (players[0].isAlive()) players[0].dropBomb();
                break;
            case KeyEvent.VK_SLASH:
                if (players[1].isAlive()) players[1].dropBomb();
                break;

            case KeyEvent.VK_ESCAPE:
                endGame = true;
                break;
        }
    }

    /**
     * Handles key releases in the game
     * @param e event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        switch (c) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                players[0].moveDirection = Player.MoveDirection.NONE;
                break;
            case KeyEvent.VK_M:
                if (music != null) {
                    if (music.getMusicState()) {
                        music.stopMusic();
                    } else {
                        music.playMusic();
                    }
                }
                break;


            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                players[1].moveDirection = Player.MoveDirection.NONE;
                break;
        }
    }

    /**
     * Initializes a Game object
     *
     * @param playerCount how many players is in the game [ max 2 (at the moment)]
     */
    Game(int playerCount) {
        this.board = new Board();
        this.players = new Player[playerCount];
        this.bombs = new ArrayList<>();

        try {
            this.music = new Music();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Starting without music!");
            this.music = null;
        }


        this.players[0] = new Player(1, 1, "player_1", 1, this);
        if (playerCount == 2) this.players[1] = new Player(19, 19, "player_2", 2, this);
    }

    /**
     * Progresses the game by one step
     */
    void step() {
        int alivePlayerCount = 0;
        for (Player player : players) {
            if (player.isAlive()) alivePlayerCount++;
        }

        if (alivePlayerCount == 0 || alivePlayerCount == 1) {
            new Thread(() -> {
                try {
                    Thread.sleep(2500);
                    endGame = true;
                } catch (InterruptedException e) {
                    endGame = true;
                    e.printStackTrace();
                }
            }).start();
        }

        for (Player player : players) {
            player.move();
        }

        List<Bomb> bombRemoval = new ArrayList<>();

        for (Bomb currentBomb : bombs) {
            if (currentBomb.isTimerUp()) {
                currentBomb.explode();
                bombRemoval.add(currentBomb);
            }
        }

        for (Bomb bomb : bombRemoval) {
            bombs.remove(bomb);
        }
    }

    /**
     * Checks if the game ended
     *
     * @return 'true' if ended, 'false' otherwise
     */
    boolean isEndGame() {
        return endGame;
    }
}

