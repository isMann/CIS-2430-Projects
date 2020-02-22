package dungeon;

import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;


public class PassageTest {
    //you don't have to use instance variables but it is easier
    // in many cases to have them and use them in each test
    private Chamber chamber1;
    private Chamber chamber2;
    private Door door1;
    private Door door2;

    public PassageTest() {
        chamber1 = new Chamber(1, 1);
        chamber1.setChamberNumber(0);
        chamber2 = new Chamber(1, 1);
        chamber2.setChamberNumber(1);
        door1 = chamber1.getDoors().get(0);
        door2 = chamber2.getDoors().get(0);
    }

    @Before
    public void setup(){
        //set up any instance variables here so that they have fresh values for every test
    }

    @Test
    public void testMakeBasicPassage() {
        System.out.println("makeBasicPassage()");
        Passage instance = new Passage();
        instance.makeBasicPassage();
        instance.addDoor(door1);
        instance.addDoor(door2);
        assertTrue(instance.getDescription().contains("passage goes straight for 10ft") &&
            instance.getDescription().contains("passage ends in door to a chamber"));
    }

    @Test
    public void testAddDoor(){
        System.out.println("addDoor()");
        Passage instance = new Passage();
        instance.addDoor(door1);
        assertEquals(door1, instance.getDoors().get(0));
    }

    @Test
    public void testSetDoor(){
        System.out.println("setDoor()");
        Passage instance = new Passage();
        Door door = new Door();
        instance.setDoor(door);
        assertEquals(1, instance.getDoors().size());
    }

    @Test
    public void testGetDoors(){
        System.out.println("getDoors()");
        Passage instance = new Passage();
        Door door = new Door();
        instance.setDoor(door);
        assertEquals(1, instance.getDoors().size());
    }

    @Test
    public void testGetDescription(){
        System.out.println("getDescription()");
        Passage instance = new Passage();
        instance.makeBasicPassage();
        instance.addDoor(door1);
        instance.addDoor(door2);
        assertTrue(instance.getDescription().contains("Passage 0") && instance.getDescription().contains("It connects Chamber 0 Door 0 to Chamber 1 Door 0"));
    }

    @Test
    public void testSetPassageNumber(){
        System.out.println("setPassageNumber()");
        Passage instance = new Passage();
        instance.makeBasicPassage();
        instance.addDoor(door1);
        instance.addDoor(door2);
        instance.setPassageNumber(1);
        assertTrue(instance.getDescription().contains("Passage 1"));
    }
}
