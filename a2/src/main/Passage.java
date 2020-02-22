package main;

import dnd.models.Monster;

import java.util.ArrayList;



public class Passage extends Space {
    //these instance variables are suggestions only
    //you can change them if you wish.

    /**
     * The array of sections for this passage.
     */
    private ArrayList<PassageSection> sections = new ArrayList<>();
    /**
     * The array of doors in this passage.
     */
    private ArrayList<Door> doors = new ArrayList<>();


    Passage() {

    }

    /**
     * Randomly generates the sections for this passage.
     * If it reaches the end of a passage it stops.
     * If it reaches 9 sections, the tenth will be a door to a chamber.
     */
    void makeSections() {
        for (int i = 0; i < 10; i++) {
            if (i == 9) {
                addPassageSection("passage ends in door to a chamber");
            } else {
                addPassageSection();
            }
            if (sections.get(i).getDescription().contains("door")) {
                addDoor();
                if (sections.get(i).getDescription().contains("archway")) {
                    sections.get(i).setArchway(true);
                }
            }
            if (sections.get(i).getDescription().contains("end")) {
                break;
            }
        }
    }

    /**
     * This gets the array of doors in this Passage.
     * @return The array of doors in this Passage.
     */
    ArrayList<Door> getDoors() {
//gets all of the doors in the entire passage
        return this.doors;
    }

    /**
     * Gets a specific door in the passage.
     * @param i The index of the passage section with the door.
     * @return The door in the specific passage section.
     */
    Door getDoor(int i) {
        //returns the door in section 'i'. If there is no door, returns null
        return i < doors.size() ? doors.get(i) : null;
    }

    /**
     * Adds a monster to the passage.
     * @param theMonster The monster to be added.
     * @param i The specific passage section the monster is to be added to.
     */
    void addMonster(Monster theMonster, int i) {
        // adds a monster to section 'i' of the passage
        this.sections.get(i).setMonster(theMonster);
    }

    /**
     * Gets a monster from a specific passage section.
     * @param i The passage section that the monster is in.
     * @return The monster.
     */
    Monster getMonster(int i) {
        //returns Monster door in section 'i'. If there is no Monster, returns null
        return this.sections.get(i).getMonster();
    }


    /**
     * Adds a passage section to the array list.
     * @param toAdd The passage section to be added.
     */
    void addPassageSection(PassageSection toAdd) {
        //adds the passage section to the passageway
        this.sections.add(toAdd);
    }

    /**
     * Adds a new random passage section to the array.
     */
    private void addPassageSection() {
        PassageSection section = new PassageSection();
        this.sections.add(section);
    }

    /**
     * Adds a passage section to the array with a specific description.
     * @param description The description of the passage section.
     */
    public void addPassageSection(String description) {
        PassageSection section = new PassageSection(description);
    }


    /**
     * Gets the array of passage sections in this passage.
     * @return The array of passage section.
     */
    ArrayList<PassageSection> getPassageSections() {
        return this.sections;
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
     * adds a new door to the door array list.
     */
    void addDoor() {
        Door door = new Door();
        doors.add(door);
    }

    /**
     * Gets the description of this passage.
     * @return The description of this passage.
     */
    @Override
    public String getDescription() {
        StringBuilder string = new StringBuilder();
        for (PassageSection s: this.sections) {
            string.append(s.getDescription());
        }

        return string.toString();
    }

    /**
     * Returns the array of monsters in this passage.
     * @return The array of Monsters.
     */
    ArrayList<Monster> getMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < getPassageSections().size(); i++) {
            if (getMonster(i) != null) {
                monsters.add(getMonster(i));
            }
        }
        return monsters;
    }
}
