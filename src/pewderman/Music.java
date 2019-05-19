package pewderman;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Music {


    private static Clip clip;
    private boolean musicState;

    Music() {
        this.musicState = true;
        Mixer mixer = null;
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();

//        for (Mixer.Info info : mixInfos) {
//            System.out.println(info.getName() + " --- " + info.getDescription());
//        }

        for (Mixer.Info mixInfo : mixInfos) {
            try {
                mixer = AudioSystem.getMixer(mixInfo);
                DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
                clip = (Clip) mixer.getLine(dataInfo);
                break;
            } catch (IllegalArgumentException e) {
                System.out.printf("%s is unsupported!%n", mixInfo);
            } catch (LineUnavailableException e) {
                System.out.printf("%s is unavailable!%n", mixInfo);
            }
        }

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("assets/music/music.wav"));
            clip.open(audioStream);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playMusic() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        this.musicState = true;
    }

    public void stopMusic() {
        this.musicState = false;
        clip.stop();
    }

    public boolean getMusicState() {
        return this.musicState;
    }

    public int getDefaultAudioDeviceNumber() {
        int counter = 0;
        int outputDevice = -1;
        char[] charArray;
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : mixInfos) {
            charArray = info.getName().toCharArray();
            for (char singleChar : charArray) {
                if (singleChar == 'D') {
                    outputDevice = counter;
                }
            }
            counter++;
        }
        return outputDevice;
    }
}
