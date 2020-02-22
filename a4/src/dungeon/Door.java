package dungeon;
import dnd.die.D10;
import dnd.die.D20;
import dnd.die.D4;
import dnd.die.D6;

import dnd.models.Trap;


import java.io.Serializable;
import java.util.ArrayList;


public class Door implements Serializable {

    /**
     * Determines if the door is trapped.
     */
    private boolean trapped;
    /**
     * Determines if the door is unlocked.
     */
    private boolean unlocked;
    /**
     * Determines if the door is open.
     */
    private boolean open;
    /**
     * Determines if the door is an archway.
     */
    private boolean archway;
    /**
     * The trap for the door.
     */
    private Trap trap = new Trap();
    /**
     * The spaces the door attaches.
     */
    private ArrayList<Space> spaces = new ArrayList<>();
    /**
     * The description of this Door.
     */
    private StringBuilder description = new StringBuilder();

    /**
     * The number of the chamber that this door is connected to.
     * For printing and organization purposes.
     */
    private int chamberNumber;
    /**
     * The number of the door that the Door is associated with.
     * This number is only unique to the specific Chamber not the entire level.
     */
    private int doorNumber;
    /**
     * The Chamber that this Door is connected to.
     */
    private Chamber chamber;

    /**
     * Sets the defaults for this door.
     * Randomly assigns trapped unlocked and if it's an archway.
     */
    public Door() {
        D20 d20 = new D20();
        setTrapped(d20.roll() == 1);
        D6 d6 = new D6();
        setUnlocked(d6.roll() != 1);
        D10 d10 = new D10();
        setArchway(d10.roll() == 1);
        D4 d4 = new D4();
        setOpen(d4.roll() <= 2);
    }

    private void setTrap(int i) {
        trap.chooseTrap(i);
    }

    private void setTrapped(boolean flag) {
        D20 d20 = new D20();
        trapped = flag;
        if (trapped) {
            setTrap(d20.roll());
        }
    }


    /**
     * Sets if this door is open if it is an archway it is always open.
     * @param flag a boolean for whether the door is open or not.
     */
    private void setOpen(boolean flag) {
        if (!unlocked) {
            open = false;
        } else if (!archway) {
            open = flag;
        } else {
            open = true;
        }
    }

    /**
     * Sets if the door is an archway, if it is sets the defaults of the other booleans
     * to their respective values.
     * @param flag The boolean of whether the door is an archway.
     */
    private void setArchway(boolean flag) {
        if (flag) {
            archway = true;
            setUnlocked(true);
            setOpen(true);
            setTrapped(false);
        } else {
            archway = false;
        }
    }

    private void setUnlocked(boolean flag) {
        unlocked = flag;
    }

    /**
     * Adds a Space to the ArrayList of Spaces.
     * @param space The Space to be added.
     */
    public void addSpace(Space space) {
        spaces.add(space);
    }

    /**
     * Gets if the door is trapped.
     * @return True if trapped false if not trapped.
     */
    private boolean isTrapped() {
        return trapped;
    }

    /**
     * Gets if the door is open.
     * @return True if opened false if closed.
     */
    private boolean isOpen() {
        return open;
    }

    /**
     * Gets if the door is an archway.
     * @return True if an archway false if not.
     */
    private boolean isArchway() {
        return archway;
    }

    private boolean isUnlocked() {
        return unlocked;
    }

    /**
     * Gets the trap description.
     * @return The trap description
     */
    private String getTrapDescription() {
        return trap.getDescription();
    }

    /**
     * Gets the spaces array for this door.
     * @return The spaces array.
     */
    public ArrayList<Space> getSpaces() {
        //returns the two spaces that are connected by the door
        return spaces;
    }


    /**
     * Gets the description of this door.
     * @return The description of this door.
     */
    public String getDescription() {
        description.setLength(0);
        description.append("Door ").append(doorNumber);
        description.append(": ");
        getArchwayDescription();
        description.append("that is ");
        getTrappedDescription();
        getUnlockedDescription();
        description.append(" and ");
        getOpenDescription();
        return description.toString();
    }


    private void getOpenDescription() {
        if (isOpen()) {
            description.append("open. ");
        } else {
            description.append("closed. ");
        }
    }

    private void getUnlockedDescription() {
        if (isUnlocked()) {
            description.append("unlocked ");
        } else {
            description.append("locked ");
        }
    }

    private void getTrappedDescription() {
        if (isTrapped()) {
            description.append("trapped. The trap is a ");
            description.append(getTrapDescription());
            description.append(". The door is ");
        }
    }

    private void getArchwayDescription() {
        description.append("The door is");
        if (isArchway()) {
            description.append(" an archway ");
        } else {
            description.append(" a normal door ");
        }
    }

    /**
     * Sets the number of the chamber that this Door is connected to.
     * @param num the number of the Chamber.
     */
    public void setChamberNumber(int num) {
        chamberNumber = num;
    }

    /**
     * Gets the number of the Chamber that the Door is connected to.
     * @return The number of the Chamber.
     */
    public int getChamberNumber() {
        return chamberNumber;
    }

    /**
     * Sets the number of the Door.
     * @param num The number of the Door.
     */
    public void setDoorNumber(int num) {
        doorNumber = num;
    }

    /**
     * Gets the number of the Door.
     * @return The number of the Door.
     */
    public int getDoorNumber() {
        return doorNumber;
    }

    /**
     * Sets the Chamber connection to the Door.
     * @param newChamber The Chamber the Door is to be connected to.
     */
    public void setChamber(Chamber newChamber) {
        chamber = newChamber;
    }

    /**
     * Returns the Chamber the Door is connected to.
     * @return The Chamber the Door is connected to.
     */
    public Chamber getChamber() {
        return chamber;
    }
}
