package pewderman;

import java.util.Random;

class PowerUp {
    /**
     * Return a random Power-Up
     *
     * @return a random Power-Up
     */
    static Field.Type getPowerUp() {
        Random generator = new Random();
        double probability = 10 * generator.nextDouble();
//        System.out.printf("POWER_UP: %f", probability);

        if (probability > 9) {
            return Field.Type.CUBA_LIBRE;
        } else if (probability > 8) {
            return Field.Type.LIVES;
        } else if (probability > 6) {
            return Field.Type.BOMBS;
        } else if (probability > 4) {
            return Field.Type.BOOTS;
        } else if (probability > 2) {
            return Field.Type.TRUMP_BLESSING;
        } else {
            return Field.Type.RANGE;
        }
    }
}
