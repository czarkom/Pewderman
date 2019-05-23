package pewderman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class PicturePanel extends JPanel {

    private BufferedImage image;

    PicturePanel() {

        super();

        File imageFile = new File("assets/GUI/java.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth() - 30, image.getHeight() - 30);
        setPreferredSize(dimension);

        JButton playButton = new JButton("Play");
        add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Renderer.cl.show(Renderer.panelCont,"2");
                Renderer.runGameWithRenderer();

            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}