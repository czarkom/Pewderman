package pewderman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    Timer tm = new Timer(5,this);
    int x=0, y=0, velX=0, velY=0;

    public GamePanel(){
        setBackground(Color.BLUE);
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e){
       if(e.getKeyCode() == KeyEvent.VK_A)
       {
            velX = -1;
            velY = 0;
       }
       else if (e.getKeyCode() == KeyEvent.VK_W)
       {
            velY = -1;
            velX = 0;
       }
       else if (e.getKeyCode() == KeyEvent.VK_S)
       {
            velX = 0;
            velY = 1;
       }
       else if (e.getKeyCode() == KeyEvent.VK_D)
       {
            velX = 1;
            velY = 0;
       }
       /**else if (e.getKeyCode() == KeyEvent.VK_W)
       {

       }
       else if (e.getKeyCode() == KeyEvent.VK_W)
       {

       }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x = x + velX;
        y = y + velY;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(x,y,50,30);
    }
}
