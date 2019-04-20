import java.util.ArrayList;

public class Game {
    public Board board;
    public Player[] players;
    public ArrayList<Bomb> bombs;

    public Game(int playerCount){
        this.board = new Board();
        this.players = new Player[playerCount];
        this.bombs = new ArrayList<>();

        for(int i=0; i<playerCount; i++) {
            this.players[i] = new Player((i+10)*2, (i+10)*2, "player_" + i, i);
        }
    }


    //metody


    public void start(){
        System.out.println("Game: Game has started, there are "+players.length+" players alive.");
        this.players[0].dropBomb(this.board, this.bombs);
        this.bombs.get(0).explode(this.players, this.board);
        this.players[0].die();
        this.players[1].die();
        this.players[2].die();
        this.end(3,this.players);
    }

    public void end(int playerCount, Player[] players){

        int playersAlive = playerCount;

        for (Player player:players) {
            if (player.isAlive == Player.IsAlive.DEAD){
                playersAlive--;
            }
        }

        System.out.println("Game: Currently "+playersAlive+" players alive.");

        if (playersAlive == 0){
            System.out.println("Game: The game has ended");
        }



    }
    public static void main(String[] args) {
        System.out.println("Main: start");

        Game game = new Game(3);

        game.start();
    }
}

