package pewderman;


import javax.sound.sampled.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.transform.SourceLocator;
import java.io.IOException;
import java.net.URL;

public class Music {



    private static Clip clip;


    public Music(){//String musicSource) {
        Mixer mixer;
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        for(Mixer.Info info: mixInfos){
          System.out.println(info.getName() + " --- " + info.getDescription());
        }

        mixer = AudioSystem.getMixer(mixInfos[2]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        try {
            clip = (Clip) mixer.getLine(dataInfo);
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        }

        try {
            URL soundURL = Music.class.getResource("/music/music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip.open(audioStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        }
    }

    public void playMusic() {
        clip.start();
        /*do {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        while
        (clip.isActive());*/
    }
}
