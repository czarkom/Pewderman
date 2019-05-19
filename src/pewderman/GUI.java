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

class GUI extends JFrame{

    private BufferedImage image;

    public GUI(){

        //super("Bomberman");
        JFrame frame = new JFrame();
        JPanel panelContainer = new JPanel();
        JPanel panelFirst = new PicturePanel();
        GamePanel panelSecond = new GamePanel();
        //frame.add(panelFirst);
        frame.add(panelSecond);
        frame.setTitle("Bomberman");

        //JButton playButton = new JButton();
        //JButton exitButton = new JButton();
        CardLayout cl = new CardLayout();


        ImageIcon startGame = new ImageIcon("assets/GUI/play_button.png");

        Image img = startGame.getImage();
        Image newImg = img.getScaledInstance(70,100,Image.SCALE_SMOOTH);
        startGame = new ImageIcon(newImg);

        ImageIcon exit = new ImageIcon("assets/GUI/exit_button.png");

        Image img2 = exit.getImage();
        Image newImg2 = img2.getScaledInstance(70,50,Image.SCALE_SMOOTH);
        exit = new ImageIcon(newImg2);

        /*JFrame frame = new JFrame();
         JPanel panelContainer = new JPanel();
         JPanel panelFirst = new PicturePanel();
         JPanel panelSecond = new GamePanel();

         //JButton playButton = new JButton();
         //JButton exitButton = new JButton();
         CardLayout cl = new CardLayout();*/
        JButton playButton = new JButton(startGame);
        //1 opcja, 2 to deklaracja tego w konstruktorze
        JButton exitButton = new JButton(exit);
        panelContainer.setLayout(cl);
        panelFirst.add(playButton);
        panelFirst.add(exitButton);
        //frame.add(panelFirst); (opcjonalnie)
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
