package pewderman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    public Board board;
    public Player[] players;
    public ArrayList<Bomb> bombs;

    public Game(int playerCount) {
        this.board = new Board(this);
        this.players = new Player[playerCount];
        this.bombs = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            this.players[i] = new Player((i + 10) * 2, (i + 10) * 2, "player_" + i, i, this);
        }
    }


    //metody


    public void start() {
        System.out.println("pewderman.Game: pewderman.Game has started, there are " + players.length + " players alive.");
        this.players[0].dropBomb(this.board, this.bombs);
        this.bombs.get(0).explode(this.players, this.board);
        this.players[0].die();
        this.end(this.players.length    , this.players);
    }

    public void end(int playerCount, Player[] players) {

        int playersAlive = playerCount;

        for (Player player : players) {
            if (player.isAlive == Player.IsAlive.DEAD) {
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

        Scanner scan = new Scanner(System.in);

        int playerCount = scan.nextInt();

        scan.close();

        File fileUW = new File(args[0]);

        ArrayList<Field> fieldsModifiedUW = new ArrayList<>();

        try {
            Scanner sc = new Scanner(fileUW);
            while (sc.hasNext()) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                System.out.println("Scan:" + x + " " + y);
                Field _field = new Field(Field.Type.UNDESTROYABLE_WALL, x, y);
                fieldsModifiedUW.add(_field);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Game game = new Game(playerCount);

        game.board.fillBoard(fieldsModifiedUW);

        game.start();
    }
}
