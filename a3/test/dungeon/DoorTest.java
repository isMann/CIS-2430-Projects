package dungeon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



public class DoorTest {

    Passage passage = new Passage();
    Chamber chamber = new Chamber(1,1);
    
    public DoorTest() {
    }
    
   @Test
    public void testAddSpace(){
       System.out.println("addSpace()");
       Door instance = new Door();
       instance.addSpace(passage);
       assertEquals(1, instance.getSpaces().size());
   }

   @Test
    public void testGetSpaces(){
       System.out.println("getSpaces()");
       Door instance = new Door();
       instance.addSpace(passage);
       assertEquals(passage, instance.getSpaces().get(0));
   }

   @Test
    public void testGetDoorNumber(){
       System.out.println("getDoorNumber()");
       Door instance = new Door();
       instance.setDoorNumber(1);
       assertEquals(1, instance.getDoorNumber());
   }

   @Test
    public void testSetDoorNumber(){
       System.out.println("setDoorNumber()");
       Door instance = new Door();
       instance.setDoorNumber(1);
       assertEquals(1, instance.getDoorNumber());
   }

   @Test
    public void testSetChamberNumber(){
       System.out.println("setChamberNumber()");
       Door instance = new Door();
       instance.setChamber(chamber);
       chamber.setChamberNumber(1);
       instance.setChamberNumber(chamber.getChamberNumber());
       assertEquals(chamber.getChamberNumber(), instance.getChamberNumber());
   }

   @Test
    public void testGetChamberNumber(){
       System.out.println("getChamberNumber()");
       Door instance = new Door();
       instance.setChamberNumber(1);
       assertEquals(1, instance.getChamberNumber());
   }

   @Test
    public void testAddChamber(){
       System.out.println("addChamber()");
       Door instance = new Door();
       instance.setChamber(chamber);
       assertEquals(chamber, instance.getChamber());
   }

   @Test
    public void testGetChamber(){
       System.out.println("addChamber()");
       Door instance = new Door();
       instance.setChamber(chamber);
       assertEquals(chamber, instance.getChamber());
   }

   @Test
   public void testDoor(){
      System.out.println("Door()");
      Door instance = new Door();
      assertTrue(instance.getDescription().contains("Door 0:"));
   }

   @Test
   public void testGetDescription(){
      System.out.println("getDescription()");
      Door instance = new Door();
      assertTrue(instance.getDescription().contains("Door 0:"));
   }
}
