package pewderman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class Game implements KeyListener {
    public Board board;
    public Player[] players;
    public ArrayList<Bomb> bombs;
    public Music music;

    @Override
    public void keyTyped(KeyEvent e) {
    }

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
                if(players[0].isAlive()) players[0].buildAWall();
                break;

            case KeyEvent.VK_BACK_SLASH:
                if(players[1].isAlive()) players[1].buildAWall();
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
        }
    }

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
                if(music.getMusicState()){
                    music.stopMusic();
                }
                else{
                    music.playMusic();
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

    public Game(int playerCount) {
        this.board = new Board(this);
        this.players = new Player[playerCount];
        this.bombs = new ArrayList<>();
        this.music = new Music();

        this.players[0] = new Player(1, 1, "player_1", 1, this);
        if (playerCount == 2) this.players[1] = new Player(19, 19, "player_2", 2, this);
    }


    //metody


    public void start() {
        System.out.println("pewderman.Game: pewderman.Game has started, there are " + players.length + " players alive.");
        this.players[0].dropBomb();
        this.bombs.get(0).explode();
        this.end(this.players.length, this.players);
    }

    public void end(int playerCount, Player[] players) {

        int playersAlive = playerCount;

        for (Player player : players) {
            if (player.playerState == Player.PlayerState.DEAD) {
                playersAlive--;
            }
        }

        System.out.println("pewderman.Game: Currently " + playersAlive + " players alive.");

        if (playersAlive == 0) {
            System.out.println("pewderman.Game: The game has ended");
        }


    }

    public void step() {
        for (Player player : players) {
            player.move();
        }

        List<Bomb> bombRemoval = new ArrayList<>();

        for (int i = 0; i < bombs.size(); i++) {
            Bomb currentBomb = bombs.get(i);
            if (currentBomb.isTimerUp()) {
                currentBomb.explode();
                bombRemoval.add(currentBomb);
            }
        }

        for (Bomb bomb : bombRemoval) {
            bombs.remove(bomb);
        }
    }

    public static void main(String[] args) {
        System.out.println("Main: start");

        System.out.println("Main: Insert number of players:");

        GameConfiguration config = new GameConfiguration("data/boardData.txt");

        Game game = new Game(config.getPlayerCount());

        game.board.fillBoard(config.getWalls());

        game.start();


//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new GUI();
//            }
//        });
    }
}

