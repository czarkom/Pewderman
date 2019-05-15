package pewderman;

import java.awt.*;
import java.util.ArrayList;


public class Game {
    public Board board;
    public Player[] players;
    public ArrayList<Bomb> bombs;

    public Game(int playerCount) {
        this.board = new Board(this);
        this.players = new Player[playerCount];
        this.bombs = new ArrayList<>();

        this.players[0] = new Player(15, 1, "player_1", 1, this);
        if (playerCount == 2) this.players[1] = new Player(16, 1, "player_2", 2, this);
    }


    //metody


    public void start() {
        System.out.println("pewderman.Game: pewderman.Game has started, there are " + players.length + " players alive.");
        this.players[0].dropBomb();
        this.bombs.get(0).explode();
        this.end(this.players.length    , this.players);
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

