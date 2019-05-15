package pewderman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import static pewderman.Player.MoveDirection.*;

public class GUI extends JFrame{

    private BufferedImage image;

    JFrame frame = new JFrame();
    JPanel panelContainer = new JPanel();
    JPanel panelFirst = new PicturePanel();
    JPanel panelSecond = new GamePanel();
    JButton playButton = new JButton("Play");
    JButton exitButton = new JButton("Exit");
    CardLayout cl = new CardLayout();

    public GUI(){

        super("Bomberman");
        playButton.setIcon(new ImageIcon("assets/GUI/play_button.png"));
        panelContainer.setLayout(cl);
        panelFirst.add(playButton);
        panelFirst.add(exitButton);
        frame.add(panelFirst);
        validate();


        /**ImageIcon startGame = new ImageIcon("assets/GUI/play_button.png");

        Image img = startGame.getImage();
        Image newImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        startGame = new ImageIcon(newImg);*/

        panelContainer.add(panelFirst, "1");
        panelContainer.add(panelSecond, "2");


        cl.show(panelContainer, "1");


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "2");
            }
        });



        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(panelContainer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400,250);
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
    }

}
