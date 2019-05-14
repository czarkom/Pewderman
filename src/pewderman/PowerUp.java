package pewderman;

import java.util.Random;

public class PowerUp {

    private Field.Type powerUp;

    public PowerUp() {

        double probability;

        this.powerUp = Field.Type.RANGE;

        Random generator = new Random();

        probability = 10 * generator.nextDouble();

        if (probability > 9) {
            this.powerUp = Field.Type.CUBA_LIBRE;
        } else if (probability > 8) {
            this.powerUp = Field.Type.LIFES;
        } else if (probability > 4) {
            this.powerUp = Field.Type.BOMBS;
        } else if(probability > 2){
            this.powerUp = Field.Type.GHOST;
        } else if(probability > 1){
            this.powerUp = Field.Type.BOOTS;
        }

    }

    public Field.Type getPowerUp() {

        return this.powerUp;
    }
}
