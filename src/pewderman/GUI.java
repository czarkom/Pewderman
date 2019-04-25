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

    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyChar()) {
            case 'w':
                hanleKeyPress(1, UP);
                break;
            case 'd':
                hanleKeyPress(1, RIGHT);
                break;
            case 's':
                hanleKeyPress(1, DOWN);
                break;
            case 'a':
                hanleKeyPress(1, LEFT);
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

    private void hanleKeyPress(int playerNum, Player.MoveDirection direction) {
        if (currentGame.players.length >= playerNum) {
            currentGame.players[playerNum - 1].moveDirection = direction;
        }
    }
}
