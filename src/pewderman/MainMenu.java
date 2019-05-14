package pewderman;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener {

    private BufferedImage image;
    private JButton startGameButton;
    private JButton exitButton;

    public MainMenu() {

        super();

        ImageIcon startGame = new ImageIcon("assets/GUI/PlayButton.jpg");

        Image img = startGame.getImage();
        Image newImg = img.getScaledInstance(100,30,Image.SCALE_SMOOTH);
        startGame = new ImageIcon(newImg);




        File imageFile = new File("assets/GUI/java.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image read error");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth()-10, image.getHeight()-10);
        setPreferredSize(dimension);
        setLayout(null);

        startGameButton = new JButton( startGame );
        startGameButton.setBounds(image.getWidth()/2-45,40,100,30);
        add(startGameButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(image.getWidth()/2-45,230,100,30);
        add(exitButton);

        startGameButton.addActionListener(this);
        exitButton.addActionListener(this);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exitButton)
            System.exit(0);
        if (source == startGameButton)
            setVisible(false);
    }
}


