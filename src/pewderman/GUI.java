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

public class GUI extends JFrame {

    public BufferedImage image;
    JFrame frame = new JFrame();
    JPanel panelContainer = new JPanel();
    JPanel panelFirst = new JPanel();
    JPanel panelSecond = new JPanel();
    JButton playButton = new JButton("Play");
    JButton exitButton = new JButton("Exit");
    CardLayout cl = new CardLayout();

    public GUI() {

        super("Bomberman");
        panelContainer.setLayout(cl);
        panelFirst.add(playButton);
        panelFirst.add(exitButton);
        panelSecond.setBackground(Color.GREEN);

        //ImageIcon startGame = new ImageIcon("assets/GUI/play_button.png");

        //Image img = startGame.getImage();
        //Image newImg = img.getScaledInstance(70,86,Image.SCALE_SMOOTH);
        //startGame = new ImageIcon(newImg);

        File imageFile = new File("assets/GUI/java.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image read error");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth()-10, image.getHeight()-10);
        panelContainer.setPreferredSize(dimension);

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



        //setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(600,600);
        //frame.setLocation(400,250);
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

}
