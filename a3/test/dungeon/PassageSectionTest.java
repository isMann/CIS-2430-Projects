package dungeon;

import dnd.models.Exit;
import dnd.models.Monster;
import org.junit.Test;
import static org.junit.Assert.*;


public class PassageSectionTest {
    
/* set up similar to the sample in PassageTest.java */

    @Test
    public void testGetDescription(){
        PassageSection section = new PassageSection(1);
        assertEquals("passage goes straight for 10ft ", section.getDescription());
    }

    @Test
    public void testPassageSection(){
        PassageSection instance = new PassageSection(3);
        assertEquals("passage ends in door to a chamber ", instance.getDescription());
    }
   
    
}
