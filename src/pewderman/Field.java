package pewderman;

import java.awt.*;
import java.util.Random;


class Field {
    Point cord;

    public enum TypeFamily {WALL, POWER_UP}

    public enum Type {BREAKABLE_WALL, UNBREAKABLE_WALL, NO_WALL, FIRE, RANGE, BOMBS, LIVES, CUBA_LIBRE, GHOST, BOOTS, TRUMP_BLESSING}

    private Type fieldType;
    private TypeFamily fieldTypeFamily;

    /**
     * Initializes a Field object
     *
     * @param fieldType       type of the Field to create
     * @param fieldTypeFamily family type of the Field to create
     * @param x               position on X axis
     * @param y               position on Y axis
     */
    Field(Type fieldType, TypeFamily fieldTypeFamily, int x, int y) {
        this.fieldType = fieldType;
        this.fieldTypeFamily = fieldTypeFamily;
        cord = new Point(x, y);

//        System.out.printf("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: created as %s%n", fieldType);
    }

    /**
     * Returns a Type of the Field
     *
     * @return Field Type
     */
    Type getFieldType() {
        return fieldType;
    }

    /**
     * Checks if the Field is a WALL
     *
     * @return 'true' if WALL, 'false' otherwise
     */
    boolean isAWall() {
        return fieldTypeFamily == TypeFamily.WALL;
    }

    /**
     * Checks if the Field is a NO_WALL
     *
     * @return 'true' if NO_WALL, 'false' otherwise
     */
    boolean isEmpty() {
        return fieldType == Type.NO_WALL;
    }

    /**
     * Returns a FamilyType of the Field
     *
     * @return Field FamilyType
     */
    TypeFamily getFieldTypeFamily() {
        return fieldTypeFamily;
    }

    /**
     * Sets a Field on fire for a set amount of time, then changes it to a given Type
     *
     * @param postExplosion a Type to which the field changes after FIRE ended
     */
    void setOnFire(Type postExplosion) {
        this.fieldType = Type.FIRE;

        new Thread(() -> {
            try {
                Thread.sleep(250);
                this.fieldType = postExplosion;
            } catch (InterruptedException e) {
                this.fieldType = postExplosion;
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Changes Fields type to BREAKABLE_WALL
     */
    void buildAWall() {
        fieldType = Type.BREAKABLE_WALL;
    }

    /**
     * Changes all Types of Fields to NO_WALL
     */
    private void destroyAllTypes() {
        fieldType = Type.NO_WALL;
        fieldTypeFamily = TypeFamily.WALL;
//        System.out.printf("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: changed state from %s to NO_WALL\n", fieldType);
    }

    /**
     * Changes DESTROYABLE_WALLs to NO_WALL or POWER_UPs
     */
    private void destroyDestroyableWall() {
        double probability;
        Random generator = new Random();
        probability = generator.nextDouble();

        if (probability * 10 < 4) {
            setOnFire(PowerUp.getPowerUp());
            fieldTypeFamily = TypeFamily.POWER_UP;
//            System.out.printf("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: changed state from DESTROYABLE_WALL to %s%n", fieldType);
        } else {
            setOnFire(Type.NO_WALL);
//            System.out.println("pewderman.Field [x: " + cord.x + ", y: " + cord.y + " ]: changed state from DESTROYABLE_WALL to NO_WALL");
        }
    }

    /**
     * Handles Fields destruction based on it's TypeFamily
     */
    void destroy() {
        switch (fieldTypeFamily) {
            case WALL: {
                if (fieldType == Type.BREAKABLE_WALL) destroyDestroyableWall();
                else return;
                return;
            }
            case POWER_UP: {
                destroyAllTypes();
            }
        }
    }
}
