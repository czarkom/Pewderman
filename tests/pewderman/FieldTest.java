package pewderman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    private Field fieldBreakableWall;
    private Field fieldNoWall;

    @BeforeEach
    void setUp() {
        fieldBreakableWall = new Field(Field.Type.BREAKABLE_WALL, Field.TypeFamily.WALL, 11, 11);
        fieldNoWall = new Field(Field.Type.NO_WALL, Field.TypeFamily.WALL, 100, 10);
    }

    @Test
    void getFieldTypeFamily() {
        assertEquals(fieldBreakableWall.getFieldTypeFamily(), Field.TypeFamily.WALL);
    }

    @Test
    void isAWall() {
        assertTrue(fieldBreakableWall.isAWall());
    }

    @Test
    void isEmpty() {
        assertTrue( fieldNoWall.isEmpty());
    }

    @Test
    void destroy(){
        fieldBreakableWall.destroy(fieldBreakableWall.getFieldTypeFamily());
        assertNotEquals(fieldBreakableWall.getFieldType(), Field.Type.BREAKABLE_WALL);
    }

    @Test
    void isTileSetOnFire(){
        fieldBreakableWall.setOnFire(fieldBreakableWall.getFieldType());
        assertEquals(fieldBreakableWall.getFieldType(), Field.Type.FIRE);
    }

}