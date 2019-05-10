package pewderman;

import java.awt.*;
import java.util.Random;


public class Field {
    public Point cord;

    enum Type {BREAKABLE_WALL, UNBREAKABLE_WALL, NO_WALL, FIRE, RANGE, BOMBS, LIFES, CUBA_LIBRE}

    public Type field_type;

    public Field(Type _field_type, int x, int y) {
        field_type = _field_type;
        cord = new Point(x, y);
    }

    public void destroy() {
        System.out.println("pewderman.Field: The wall has been destroyed");
        if (this.field_type == Type.BREAKABLE_WALL) {
            double probability;
            Random generator = new Random();
            probability = generator.nextDouble();
            if (probability * 10 < 4) {
                PowerUp powerUp = new PowerUp();

                this.field_type = powerUp.getPowerUp();
            } else {
                this.field_type = Type.NO_WALL;
            }
        }

        this.field_type = Type.NO_WALL;
    }
}
