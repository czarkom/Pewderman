package pewderman;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestField {

    private Field fieldBreakableWall;
    private Field fieldNoWall;

    @Before
    public void setUp() {
        fieldBreakableWall = new Field(Field.Type.BREAKABLE_WALL, Field.TypeFamily.WALL, 10, 10);
        fieldNoWall = new Field(Field.Type.NO_WALL, Field.TypeFamily.WALL, 10, 10);
    }
    @Test
    public void isAWallTest(){
        assertTrue(fieldBreakableWall.isAWall());
    }

    @Test
    public void isEmptyTest(){
        assertTrue( fieldNoWall.isEmpty());
    }

    @Test
    public void getFieldTypeFamilyTest(){
        assertEquals(fieldBreakableWall.getFieldTypeFamily(), Field.TypeFamily.WALL);
    }

}
