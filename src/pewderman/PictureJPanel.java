package pewderman;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PictureJPanel extends JPanel{
    private BufferedImage image;
   //private ImageIcon buttonPlay;

    public PictureJPanel() {
        super();

        File imageFile = new File("data/java.jpg");
       // File imageFileButton = new File("data/PlayButton.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image read error");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth()-10, image.getHeight()-10);
        setPreferredSize(dimension);
        setLayout(null);
        JButton button = new JButton("New Game");
        button.setBounds(image.getWidth()/2-45,40,100,30);
        add(button);
        JButton button2 = new JButton("Settings");
        button2.setBounds(image.getWidth()/2-45,230,100,30);
        add(button2);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}

