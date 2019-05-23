package pewderman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {

    @Test
    void shouldReturn_RANGE_20prct() {
        double numberOfPowerUps = 0;
        double iterations = 3000000;

        for (int i = 0; i < iterations; i++) {
            if(PowerUp.getPowerUp() == Field.Type.RANGE) numberOfPowerUps++;
        }

        assertEquals(20, Math.round((numberOfPowerUps/iterations) * 100));
    }

    @Test
    void shouldReturn_TRUMPS_BLESSING_20_prct() {
        double numberOfPowerUps = 0;
        double iterations = 3000000;

        for (int i = 0; i < iterations; i++) {
            if(PowerUp.getPowerUp() == Field.Type.TRUMP_BLESSING) numberOfPowerUps++;
        }

        assertEquals(20, Math.round((numberOfPowerUps/iterations) * 100));
    }

    @Test
    void shouldReturn_BOOTS_20_prct() {
        double numberOfPowerUps = 0;
        double iterations = 3000000;

        for (int i = 0; i < iterations; i++) {
            if(PowerUp.getPowerUp() == Field.Type.BOOTS) numberOfPowerUps++;
        }

        assertEquals(20, Math.round((numberOfPowerUps/iterations) * 100));
    }

    @Test
    void shouldReturn_BOMBS_20_prct() {
        double numberOfPowerUps = 0;
        double iterations = 3000000;

        for (int i = 0; i < iterations; i++) {
            if(PowerUp.getPowerUp() == Field.Type.BOMBS) numberOfPowerUps++;
        }

        assertEquals(20, Math.round((numberOfPowerUps/iterations) * 100));
    }

    @Test
    void shouldReturn_LIVES_10_prct() {
        double numberOfPowerUps = 0;
        double iterations = 3000000;

        for (int i = 0; i < iterations; i++) {
            if(PowerUp.getPowerUp() == Field.Type.LIVES) numberOfPowerUps++;
        }

        assertEquals(10, Math.round((numberOfPowerUps/iterations) * 100));
    }

    @Test
    void shouldReturn_CUBA_LIBRE_10_prct() {
        double numberOfPowerUps = 0;
        double iterations = 3000000;

        for (int i = 0; i < iterations; i++) {
            if(PowerUp.getPowerUp() == Field.Type.CUBA_LIBRE) numberOfPowerUps++;
        }

        assertEquals(10, Math.round((numberOfPowerUps/iterations) * 100));
    }
}