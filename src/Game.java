public class Game {
    public Game(Board brd, int plyr_nmbr, Player[] plyrs){
        board = brd;
        int counter = plyr_nmbr;
        while (counter > 0){
            players[counter] = plyrs[counter];
            counter--;
        }
    }
    public Board board;
    public Player[] players;
    public Bomb bombs;

}
