import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

//import static Player.MoveDirection.*;
public class GUI extends JFrame implements KeyListener {

    public KeyTest() {
        super("KeyListener Test");
        setPreferredSize(new Dimension(300, 300));
        addKeyListener(this);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void keyPressed(KeyEvent evt){
    }

    public void keyReleased(KeyEvent evt) {
        switch (evt.getKeyChar()) {
            case 'w':
                Player.MoveDirection;
                break;
            case 'd':
                scrollThread.setKeyRight(false);
                break;
            case 's':
                scrollThread.setKeyDown(false);
                break;
            case 'a':
                scrollThread.setKeyLeft(false);
                break;
        }
    }
}
