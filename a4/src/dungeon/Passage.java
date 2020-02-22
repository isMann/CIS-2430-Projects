package dungeon;

import dnd.models.Monster;
import dnd.models.Treasure;

import java.util.ArrayList;



public class Passage extends Space {

    /**
     * The description of the Passage.
     */
    private StringBuilder desc = new StringBuilder();

    /**
     * The array of sections for this passage.
     */
    private ArrayList<PassageSection> sections = new ArrayList<>();
    /**
     * The array of doors in this passage.
     */
    private ArrayList<Door> doors = new ArrayList<>();

    /**
     * The number assigned to this Passage for printing purposes.
     */
    private int passageNumber;
    private ArrayList<Treasure> treasures = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();

    /**
     * Makes a basic passage that goes straight for 10ft.
     * After another 10ft. then ends in a door to a chamber
     */
    public void makeBasicPassage() {
        addPassageSection(1);
        addPassageSection(3);
    }

    /**
     * Gets the number of the passage.
     * @return The passage Number.
     */
    public int getPassageNumber() {
        return passageNumber;
    }

    private void addPassageSection(int roll) {
        PassageSection section = new PassageSection(roll);
        sections.add(section);
    }


    /**
     * Adds this passage to the door.
     * @param newDoor The door this passage is being added to.
     */
    @Override
    public void setDoor(Door newDoor) {
        //should add a door connection to the current Passage Section
        newDoor.getSpaces().add(this);
        doors.add(newDoor);
    }

    /**
     * Gets the ArrayList of treasures.
     * @return The ArrayList of treasures.
     */
    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    /**
     * Gets the ArrayList of Monsters.
     * @return The ArrayList of Monsters.
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Returns the ArrayList of Doors in this Passage.
     * @return The ArrayList of Doors in this Passage.
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }

    /**
     * Gets the description of this passage.
     * It builds the description before returning the string.
     * Note it should only ever be called after assigning 2 doors otherwise the program will crash.
     * An IndexOutOfBounds exception will run if there are not enough Doors.
     * @return The description of this passage.
     */
    @Override
    public String getDescription() {
        desc.setLength(0);
        desc.append("Passage ").append(passageNumber);
        desc.append(": ");
        for (PassageSection s: this.sections) {
            desc.append(s.getDescription());
        }
        desc.append("\nIt connects ");
        addDoorLocations(0);
        desc.append(" to ");
        addDoorLocations(1);
        desc.append("\n");
        getMonsterDescription();
        getTreasureDescription();
        return desc.toString();
    }



    private void addDoorLocations(int doorNum) {
        desc.append("Chamber ");
        desc.append(doors.get(doorNum).getChamber().getChamberNumber());
        desc.append(" Door ");
        desc.append(doors.get(doorNum).getDoorNumber());
    }

    /**
     * Sets the number associated with this Passage.
     * @param num The number to abe assigned to this Passage.
     */
    public void setPassageNumber(int num) {
        passageNumber = num;
    }

    /**
     * Adds a door to this Passage.
     * @param newDoor The door to be added to the Passage.
     */
    public void addDoor(Door newDoor) {
        doors.add(newDoor);
    }

    /**
     * Adds an ArrayList of Treasures to the Passage.
     * @param toAdd The ArrayList to be added.
     */
    public void addTreasures(ArrayList<Treasure> toAdd) {
        treasures.addAll(toAdd);
    }

    /**
     * Adds an ArrayList of Monsters to the Passage.
     * @param toAdd The ArrayList to be added.
     */
    public void addMonsters(ArrayList<Monster> toAdd) {
        monsters.addAll(toAdd);
    }

    private void getMonsterDescription() {
        for (Monster m : monsters) {
            desc.append("A monster is a ");
            desc.append(m.getDescription());
            desc.append(" and there is between ");
            desc.append(m.getMinNum()).append(" and ");
            desc.append(m.getMaxNum()).append(" of them\n");
        }
    }

    private void getTreasureDescription() {
        for (Treasure t : treasures) {
            desc.append("A treasure is ");
            desc.append(t.getWholeDescription());
            desc.append("\n");
        }
    }
}
