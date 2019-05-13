package pewderman;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PictureJPanel extends JPanel{
    private BufferedImage image;

    public PictureJPanel() {
        super();

        File imageFile = new File("data/java.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image read error");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth()-10, image.getHeight()-10);
        setPreferredSize(dimension);
        setLayout(new GridLayout(10, 10));
        add(new JButton("New Game"));
        add(new JButton("Settings"));

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}

