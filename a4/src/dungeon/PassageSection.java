package dungeon;

import java.io.Serializable;
import java.util.HashMap;

/* Represents a 10 ft section of passageway */

public class PassageSection implements Serializable {
    /**
     * The table to set up the Door table.
     */
    private HashMap<Integer, String> table = new HashMap<>();


    /**
     * The description for this section.
     */
    private StringBuilder desc = new StringBuilder();


    /**
     * The default Constructor for the PassageSection Class.
     * It sets up the table.
     * It gets a random Passage Section.
     * @param roll The roll for the passageSection
     */
    public PassageSection(int roll) {
        this.setUpTables();
        this.desc.append(this.table.get(roll)).append(" ");
    }

    /**
     * Returns the description for this section.
     * @return The description for this section.
     */
    public String getDescription() {
        return desc.toString();
    }

    private void setUpTables() {
        this.table.put(1, "passage goes straight for 10ft");
        this.table.put(2, "passage goes straight for 10ft");
        this.table.put(3, "passage ends in door to a chamber");
        this.table.put(4, "passage ends in door to a chamber");
        this.table.put(5, "passage ends in door to a chamber");
        this.table.put(6, "archway (door) to right (main passage continues straight for 10ft)");
        this.table.put(7, "archway (door) to right (main passage continues straight for 10ft)");
        this.table.put(8, "archway (door) to left (main passage continues straight for 10ft)");
        this.table.put(9, "archway (door) to left (main passage continues straight for 10ft)");
        this.table.put(10, "passage turns to the left and continues for 10ft");
        this.table.put(11, "passage turns to the left and continues for 10ft");
        this.table.put(12, "passage turns to the right and continues for 10ft");
        this.table.put(13, "passage turns to the right and continues for 10ft");
        this.table.put(14, "passage ends in archway (door) to chamber");
        this.table.put(15, "passage ends in archway (door) to chamber");
        this.table.put(16, "passage ends in archway (door) to chamber");
        this.table.put(17, "stairs (passage continues for 10ft)");
        this.table.put(18, "dead end");
        this.table.put(19, "dead end");
        this.table.put(20, "Wandering Monster (passage continues straight for 10ft");
    }
}
