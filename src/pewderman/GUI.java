package pewderman;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import static pewderman.Player.MoveDirection.*;

public class GUI extends JFrame implements KeyListener {
    private Game currentGame;

    public GUI(Game currentGame) {
        super("KeyListener Test");
        setPreferredSize(new Dimension(300, 300));
        addKeyListener(this);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.currentGame = currentGame;
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
