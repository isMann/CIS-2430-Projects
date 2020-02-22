package main;
import dnd.die.D10;
import dnd.die.D20;
import dnd.die.D6;
import dnd.models.Exit;
import dnd.models.Trap;


import java.util.ArrayList;
public class Door {
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
    private ArrayList<Space> spaces = new ArrayList<>(2);
    /**
     * The Exit that this Door is located at.
     */
    private Exit exit;

    /**
     * Sets the defaults for this door.
     * Randomly assigns trapped unlocked and if it's an archway.
     */
    public Door() {
        //needs to set defaults
        exit = new Exit();
        setTrapped();
        setUnlocked();
        setArchway();
    }

    /**
     * Sets the Door based on the exit.
     * @param theExit The exit the door is based on.
     */
    public Door(Exit theExit) {
        //sets up the door based on the Exit from the tables
        exit = theExit;
    }

    /**
     * Sets if the door is trapped.
     * @param flag The boolean of whether the door is trapped, if there is a roll this is ignored.
     * @param roll If a roll is given it will set a trap, otherwise it will randomly decide.
     */
    public void setTrapped(boolean flag, int... roll) {
        // true ==trapped.  Trap must be rolled if no integer is given
       trapped = flag;
        if (roll.length == 1) {
            setTrap(roll[0]);
        } else {
            if (trapped) {
                setTrap();
            }
        }
    }

    private void setTrap(int i) {
        trap.setDescription(i);
    }

    private void setTrapped(int... roll) {
        D20 d20 = new D20();
        if (roll.length == 1) {
            trapped = roll[0] == 1;
        } else {
            trapped = d20.roll() == 1;
        }
        if (trapped) {
            setTrap();
        }
    }

    private void setTrap() {
        trap.setDescription();
    }

    /**
     * Sets if this door is open if it is an archway it is always open.
     * @param flag a boolean for whether the door is open or not.
     */
    void setOpen(boolean flag) {
        //true == open
        if (!archway) {
            open = flag;
        } else {
            open = true;
        }
    }

    private void setUnlocked() {
        D6 d6 = new D6();
        unlocked = d6.roll() != 1;
    }

    /**
     * Sets if the door is an archway, if it is sets the defaults of the other booleans
     * to their respective values.
     * @param flag The boolean of whether the door is an archway.
     */
    void setArchway(boolean flag) {
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

    private void setArchway() {
        D10 d10 = new D10();
        archway = d10.roll() == 1;
        if (archway) {
            unlocked = true;
            trapped = false;
        }
    }

    /**
     * Gets if the door is trapped.
     * @return True if trapped false if not trapped.
     */
    boolean isTrapped() {
        return trapped;
    }

    /**
     * Gets if the door is open.
     * @return True if opened false if closed.
     */
    boolean isOpen() {
        return open;
    }

    /**
     * Gets if the door is an archway.
     * @return True if an archway false if not.
     */
    boolean isArchway() {
        return archway;
    }

    /**
     * Gets the trap description.
     * @return The trap description
     */
    String getTrapDescription() {
        return trap.getDescription();
    }

    /**
     * Gets the spaces array for this door.
     * @return The spaces array.
     */
    ArrayList<Space> getSpaces() {
        //returns the two spaces that are connected by the door
        return spaces;
    }

    /**
     * Assigns two spaces to this door.
     * @param spaceOne The initial space.
     * @param spaceTwo The second space.
     */
    void setSpaces(Space spaceOne, Space spaceTwo) {
        //identifies the two spaces with the door
        // this method should also call the addDoor method from Space
        spaces.add(0, spaceOne);
        spaces.add(1, spaceTwo);
    }


    /**
     * Gets the description of this door.
     * @return The description of this door.
     */
    public String getDescription() {
        StringBuilder string = new StringBuilder();
        string.append("The door is");
        if (isArchway()) {
            string.append(" an archway ");
        } else {
            string.append(" a normal door ");
        }
        string.append("that is ");
        if (isTrapped()) {
            string.append("trapped. ");
            string.append("The trap is a").append(getTrapDescription());
            string.append(". The door is ");
        }
        if (isOpen()) {
            string.append("closed.");
        } else {
            string.append("open.");
        }
        return string.toString();
    }

}
