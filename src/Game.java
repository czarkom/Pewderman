import java.util.ArrayList;

public class Game {
    public Board board;
    public Player[] players;
    public ArrayList<Bomb> bombs;

    public Game(int player_count){
        this.board = new Board();
        this.players = new Player[player_count];
        this.bombs = new ArrayList<Bomb>();

        for(int i=0; i<player_count; i++) {
            this.players[i] = new Player((i+10)*2, (i+10)*2, "player_" + i, i);
        }
    }


    //metody


    public void start(){
        System.out.println("Game: Game has started, there are "+players.length+" players alive.");
        this.players[0].dropBomb(this.board, this.bombs);
        this.bombs.get(0).explode(this.players, this.board);
        this.players[0].die();
    }

    public void end(){


    }
    public static void main(String[] args) {
        System.out.println("Main: start");

        Game game = new Game(3);

        game.start();
    }
}

