package main;

import dnd.die.D20;
import dnd.models.Monster;


import java.util.HashMap;

/* Represents a 10 ft section of passageway */

public class PassageSection {
    /**
     * The table to set up the Door table.
     */
    private HashMap<Integer, String> table = new HashMap<>();
    /**
     * The monster variable for this section.
     */
    private Monster monster;
    /**
     * The door for this section.
     */
    private Door door = new Door();

    /**
     * The description for this section.
     */
    private StringBuilder desc = new StringBuilder();


    /**
     * The default Constructor for the PassageSection Class.
     * It sets up the table.
     * It gets a random Passage Section.
     */
    PassageSection() {
        //sets up the 10 foot section with default settings
        D20 d20 = new D20();
        this.setUpTables();
        this.desc.append(this.table.get(d20.roll())).append(" ");
        if (desc.toString().contains("Monster")) {
            setMonster();
        } else {
            monster = null;
        }
    }

    /**
     * This is the PassageSection constructor for testing.
     * It gets a description instead of a random one.
     * @param description The desired description for this section.
     */
    PassageSection(String description) {
        //sets up a specific passage based on the values sent in from
        //modified table 1
        this.setUpTables();
        this.desc.append(description);
        if (desc.toString().contains("Monster")) {
            setMonster();
        }
    }

    /**
     * Sets the door to be the same as the flag.
     * @param flag boolean that determines whether the door is an archway.
     */
    public void setArchway(boolean flag) {
        door.setArchway(flag);
    }

    /**
     * Sets the Monster for the section randomly.
     */
    private void setMonster() {
        monster = new Monster();
        monster.setType();
    }

    /**
     * Sets the Monster for the room to be the desired monster.
     * @param theMonster The monster that is set for this passage section.
     */
    void setMonster(Monster theMonster) {
        monster = theMonster;
        desc.append(" between ").append(monster.getMinNum()).append(" and ");
        desc.append(monster.getMaxNum()).append(" ").append(monster.getDescription());
        desc.append("s. ");
    }

    /**
     * Returns the Door in this section.
     * @return The Door in this section.
     */
    public Door getDoor() {
        //returns the door that is in the passage section, if there is one
        return door;
    }

    /**
     * Returns the Monster in this section.
     * @return The Monster in this section.
     */
    Monster getMonster() {
        //returns the monster that is in the passage section, if there is one
        return monster;
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
