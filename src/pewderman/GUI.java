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

class GUI extends JFrame implements ActionListener{

    private BufferedImage image;
    public JPanel mainMenu;
    private JButton playButton;
    private JButton exitButton;

    public GUI(JFrame frame){

        //JPanel panelContainer = new JPanel();
        //JPanel panelFirst = new PicturePanel();
        //GamePanel panelSecond = new GamePanel();
        //frame.add(panelFirst);
        //frame.add(panelSecond);
        //frame.setTitle("Bomberman");

        //JButton playButton = new JButton();
        //JButton exitButton = new JButton();
        //CardLayout cl = new CardLayout();


        ImageIcon startGame = new ImageIcon("assets/GUI/play_button.png");

        Image img = startGame.getImage();
        Image newImg = img.getScaledInstance(70,100,Image.SCALE_SMOOTH);
        startGame = new ImageIcon(newImg);

        ImageIcon exit = new ImageIcon("assets/GUI/exit_button.png");

        Image img2 = exit.getImage();
        Image newImg2 = img2.getScaledInstance(70,50,Image.SCALE_SMOOTH);
        exit = new ImageIcon(newImg2);

        playButton = new JButton(startGame);

        exitButton = new JButton(exit);

        playButton.addActionListener(this);
        exitButton.addActionListener(this);

        mainMenu.add(playButton);
        mainMenu.add(exitButton);

        frame.add(mainMenu);

        //panelFirst.setSize(800,800);

        //panelContainer.setLayout(cl);
        //panelFirst.add(playButton);
        //panelFirst.add(exitButton);
        //validate();



        //panelContainer.add(panelFirst, "1");
        //panelContainer.add(panelSecond, "2");

        //cl.show(panelContainer, "1");

        /**playButton.addActionListener(new ActionListener() {
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
        });*/

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == playButton)
            mainMenu.setVisible(false);

        else if(source == exitButton)
            setBackground(Color.BLUE);
    }

}
