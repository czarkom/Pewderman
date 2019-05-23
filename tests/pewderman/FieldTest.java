package pewderman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private Field fieldBreakableWall;
    private Field fieldNoWall;
    private Field fieldUnbreakableWall;

    @BeforeEach
    void setUp() {
        fieldBreakableWall = new Field(Field.Type.BREAKABLE_WALL, Field.TypeFamily.WALL, 11, 11);
        fieldNoWall = new Field(Field.Type.NO_WALL, Field.TypeFamily.WALL, 10, 10);
        fieldUnbreakableWall = new Field(Field.Type.UNBREAKABLE_WALL, Field.TypeFamily.WALL, 12, 12);
    }

    @Test
    void shouldBeFieldTypeFamily() {
        assertEquals(fieldBreakableWall.getFieldTypeFamily(), Field.TypeFamily.WALL);
    }

    @Test
    void shouldBeFieldType() {
        assertEquals(fieldBreakableWall.getFieldType(), Field.Type.BREAKABLE_WALL);
    }
    @Test
    void shouldBeAWall() {
        assertTrue(fieldBreakableWall.isAWall());
    }

    @Test
    void shouldBeEmpty() {
        assertTrue( fieldNoWall.isEmpty());
    }

    @Test
    void shouldDestroyBreakableWall(){
        fieldBreakableWall.destroy();
        assertNotEquals(fieldBreakableWall.getFieldType(), Field.Type.BREAKABLE_WALL);
    }
    @Test
    void shouldNotDestroyUnbreakableWall(){
        fieldUnbreakableWall.destroy();
        assertEquals(fieldUnbreakableWall.getFieldType(), Field.Type.UNBREAKABLE_WALL);
    }

    @Test
    void isTileSetOnFire() {
        fieldBreakableWall.setOnFire(fieldBreakableWall.getFieldType());
        assertEquals(fieldBreakableWall.getFieldType(), Field.Type.FIRE);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotEquals(fieldBreakableWall.getFieldType(), Field.Type.FIRE);
    }

}