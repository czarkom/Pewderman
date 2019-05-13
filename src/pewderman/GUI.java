package pewderman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
//import javax.swing.JPanel;

import static pewderman.Player.MoveDirection.*;

public class GUI extends JFrame {
    private Game currentGame;

    public GUI(Game currentGame) {
        super("Bomberman");
        //setSize(800,800);
        setLocation(400,250);

        JPanel pictureJPanel = new PictureJPanel();
        add(pictureJPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
        this.currentGame = currentGame;
    }

    /**public void paint(Graphics graphics){
    }

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
    }*/
}
