package pewderman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    private Field fieldBreakableWall;
    private Field fieldNoWall;

    @Before
    public void setUp() {
        fieldBreakableWall = new Field(Field.Type.BREAKABLE_WALL, Field.TypeFamily.WALL, 11, 11);
        fieldNoWall = new Field(Field.Type.NO_WALL, Field.TypeFamily.WALL, 100, 10);
    }

    @Test
    public void getFieldTypeFamily() {
        assertEquals(fieldBreakableWall.getFieldTypeFamily(), Field.TypeFamily.WALL);

    }

    @Test
    public void isAWall() {
        assertTrue(fieldBreakableWall.isAWall());
    }

    @Test
    public void isEmpty() {
        assertTrue( fieldNoWall.isEmpty());
    }

}