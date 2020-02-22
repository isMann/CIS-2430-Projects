package dungeon;

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

    /**
     * Makes a basic passage that goes straight for 10ft.
     * After another 10ft. then ends in a door to a chamber
     */
    public void makeBasicPassage() {
        addPassageSection(1);
        addPassageSection(3);
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
}
