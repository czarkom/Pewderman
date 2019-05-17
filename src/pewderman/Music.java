package pewderman;


import javax.sound.sampled.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.transform.SourceLocator;
import java.io.IOException;
import java.net.URL;

public class Music {



    private static Clip clip;
    private boolean musicState;

    public Music(){//String musicSource) {
        this.musicState = true;
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
            URL soundURL = Music.class.getResource("/music/music2.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip.open(audioStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        }
        clip.start();
        clip.loop(10000);
    }

    public void playMusic() {
        clip.start();
        clip.loop(10000);
        this.musicState = true;
    }
    public void stopMusic() {
        this.musicState = false;
        clip.stop();
    }

    public boolean getMusicState(){
        return this.musicState;
    }
}
