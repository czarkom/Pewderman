public class Game {
    public Board board;
    public Player[] players;
    public Bomb bombs;

    public Game(int height, int width, int _playerNumber, Player[] _players){
        board = new Board(height,width);
        int counter = _playerNumber;
        while (counter > 0){
            players[counter] = _players[counter];
            counter--;
        }
    }
}
