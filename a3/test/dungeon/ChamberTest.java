package dungeon;

import dnd.die.D20;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.DnDElement;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChamberTest {
    private D20 d20;

    public ChamberTest() {
    }

    @Before
    public void setup(){
        d20 = new D20();
    }



    @Test
    public void testGetDescription(){
        System.out.println("getDescription()");
        int shapeRoll = d20.roll(), contentRoll = d20.roll();
        Chamber instance = new Chamber(shapeRoll, contentRoll);
        if (1 <= shapeRoll && shapeRoll <= 8){
            if (!instance.getDescription().contains("Square")){
                fail();
            }
        }
        else if (9 <= shapeRoll && shapeRoll <= 19){
            if (!instance.getDescription().contains("Rectangle")){
                fail();
            }
        }
        else {
            if (instance.getDescription().contains("length")){
                fail();
            }
        }
        if (1 <= contentRoll && contentRoll <= 12) {
            if (!instance.getDescription().contains("nothing")){
                fail();
            }
        }
        else if (13 <= contentRoll && contentRoll <= 17){
            if (!instance.getDescription().contains("monster")){
                fail();
            }
        }
        if (15 <= contentRoll && contentRoll <=17 || contentRoll == 20){
            if (!instance.getDescription().contains("treasure")){
                fail();
            }
        }
        if (contentRoll == 18){
            if (!instance.getDescription().contains("stairs")){
                fail();
            }
        }
        if (contentRoll == 19){
            if(!instance.getDescription().contains("trap")){
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    public void testSetDoor(){
        System.out.println("setDoor()");
        Door door = new Door();
        Chamber instance = new Chamber(1,1);
        int pre = instance.getDoors().size();
        instance.setDoor(door);
        assertEquals(pre ,instance.getDoors().size() - 1);
    }

    @Test
    public void testGetDoors(){
        System.out.println("getDoors()");
        ChamberShape shape = ChamberShape.selectChamberShape(1);
        ChamberContents contents = new ChamberContents();
        Chamber instance = new Chamber(contents, shape);
        assertEquals(shape.getNumExits(), instance.getDoors().size());
    }

    @Test
    public void testSetChamberNumber(){
        System.out.println("setChamberNumber()");
        Chamber instance = new Chamber(1, 1);
        instance.setChamberNumber(1);
        assertEquals(1, instance.getChamberNumber());
    }

    @Test
    public void testGetChamberNumber(){
        System.out.println("getChamberNumber()");
        Chamber instance = new Chamber(1, 1);
        instance.setChamberNumber(4);
        assertEquals(4, instance.getChamberNumber());
    }

    @Test
    public void testChamberIntInt() {
        System.out.println("Chamber(int, int)");
        Chamber instance = new Chamber(1 , 1);
        assertTrue(instance.getDescription().contains("Chamber 0: The room has a Square shape"));
    }

    @Test
    public void testChamberInput() {
        System.out.println("Chamber(ChamberContents, ChamberShape)");
        ChamberContents contents = new ChamberContents();
        ChamberShape shape = ChamberShape.selectChamberShape(1);
        contents.chooseContents(1);
        Chamber instance = new Chamber(contents , shape);
        assertTrue(instance.getDescription().contains("Chamber 0: The room has a Square shape"));
    }

}