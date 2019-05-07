package pewderman;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

import javax.swing.JFrame;

import static pewderman.Player.MoveDirection.*;

public class GUI extends JFrame implements KeyListener {
    private Game currentGame;
    private int leftCornerX =30;
    private int leftCornerY =100;

    public GUI(Game currentGame) {
        super("Bomberman");
        setPreferredSize(new Dimension(leftCornerX + currentGame.board.height*7 + 100, currentGame.board.width*7 + leftCornerY + 150));
//      Player player1=addKeyListener(new Player);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.currentGame = currentGame;
    }

    public void paint(Graphics graphics){
            graphics.drawRect(0, 25, 100, 100);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyChar()) {
            case 'w':
                handleKeyPress(1, UP);
                break;
            case 'd':
                handleKeyPress(1, RIGHT);
                break;
            case 's':
                handleKeyPress(1, DOWN);
                break;
            case 'a':
                handleKeyPress(1, LEFT);
                break;
        }
    }

    public void keyReleased(KeyEvent evt) {
        switch (evt.getKeyChar()) {
            case 'w':
            case 'd':
            case 's':
            case 'a':
                handleKeyRelease(1);
                break;
        }
    }

    private void handleKeyRelease(int playerNum) {
        if (currentGame.players.length >= playerNum) {
            currentGame.players[playerNum - 1].moveDirection = NONE;
        }
    }

    private void handleKeyPress(int playerNum, Player.MoveDirection direction) {
        if (currentGame.players.length >= playerNum) {
            currentGame.players[playerNum - 1].moveDirection = direction;
        }
    }
}
