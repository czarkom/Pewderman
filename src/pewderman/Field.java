package pewderman;

import java.awt.*;
import java.util.Random;


public class Field {
    public Point cord;

    public enum TypeFamily {WALL, POWER_UP}
    public enum Type {BREAKABLE_WALL, UNBREAKABLE_WALL, NO_WALL, FIRE, RANGE, BOMBS, LIFES, CUBA_LIBRE, GHOST, BOOTS}

    private Type fieldType;
    private TypeFamily fieldTypeFamily;

    public Field(Type fieldType, TypeFamily fieldTypeFamily, int x, int y) {
        this.fieldType = fieldType;
        this.fieldTypeFamily = fieldTypeFamily;
        cord = new Point(x, y);

        System.out.println("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: created");
    }

    public Type getFieldType() {
        return fieldType;
    }

    public TypeFamily getFieldTypeFamily() {
        return fieldTypeFamily;
    }

    private void destroyAllTypes() {
        fieldType = Type.NO_WALL;
        fieldTypeFamily = TypeFamily.WALL;
        System.out.println("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: changed state to NO_WALL");
    }

    private void destroyDestroyableWall() {
        double probability;
        Random generator = new Random();
        probability = generator.nextDouble();

        if (probability * 10 < 4) {
            PowerUp powerUp = new PowerUp();
            fieldType = powerUp.getPowerUp();
            fieldTypeFamily = TypeFamily.POWER_UP;
            System.out.println("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: changed state to POWER_UP");
        } else {
            fieldType = Type.NO_WALL;
            System.out.println("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: changed state to NO_WALL");
        }
    }

    public boolean destroy(TypeFamily typeFamily, boolean force) {
        switch (typeFamily) {
            case WALL: {
                if (fieldType == Type.BREAKABLE_WALL) destroyDestroyableWall();
                else if (fieldType == Type.UNBREAKABLE_WALL && force) destroyAllTypes();
                else return false;
                return true;
            }
            case POWER_UP: {
                destroyAllTypes();
                return true;
            }
        }
        return false;
    }
}
