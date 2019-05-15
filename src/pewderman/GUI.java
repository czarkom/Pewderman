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
    //JButton playButton = new JButton();
    //JButton exitButton = new JButton();
    CardLayout cl = new CardLayout();
    private JButton playButton;
    private JButton exitButton;

    public GUI(){

        super("Bomberman");

        ImageIcon startGame = new ImageIcon("assets/GUI/play_button.png");

        Image img = startGame.getImage();
        Image newImg = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        startGame = new ImageIcon(newImg);

        ImageIcon exit = new ImageIcon("assets/GUI/exit_button.png");

        Image img2 = exit.getImage();
        Image newImg2 = img2.getScaledInstance(75,20,Image.SCALE_SMOOTH);
        exit = new ImageIcon(newImg2);

        playButton = new JButton(startGame);
        exitButton = new JButton(exit);
        //playButton.setLayout(null);
        //playButton.setBounds(200,200,200,200);
        //exitButton.setBounds(300,300,60,30);
        panelContainer.setLayout(cl);
        panelFirst.add(playButton);
        panelFirst.add(exitButton);
        frame.add(panelFirst);
        validate();



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
