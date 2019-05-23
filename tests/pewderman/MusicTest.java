package pewderman;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicTest {
    private Music muse = new Music();


    @Test
    void shouldPlayMusicByDefault(){
        assertTrue(muse.getMusicState());
    }

    @Test
    void playMusicTest() {
        muse.playMusic();
        assertTrue(muse.getMusicState());
    }

    @Test
    void stopMusicTest() {
        muse.stopMusic();
        assertFalse(muse.getMusicState());
    }

    @AfterEach
    void cleanUp() {
        muse.stopMusic();
    }
}