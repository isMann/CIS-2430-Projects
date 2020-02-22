package dungeon;
import dnd.die.D20;
import dnd.exceptions.UnusualShapeException;
import dnd.die.Percentile;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Treasure;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;

import java.util.ArrayList;

public class Chamber extends Space {

    /**
     * A die rolling simulation that gives numbers between 1 and 100.
     */
    private Percentile d100 = new Percentile();
    /**
     * A die rolling simulation that gives numbers between 1 and 20.
     */
    private D20 d20 = new D20();
    /**
     * The string that is the description for the chamber.
     * The description is built upon calling getDescription().
     */
    private StringBuilder description = new StringBuilder();
    /**
     * The number of this particular chamber.
     * Each chamber has a number for printing and organization purposes.
     */
    private int chamberNumber;
    /**
     * The contents of the chamber.
     */
    private ChamberContents contents = new ChamberContents();
    /**
     * The shape of the Chamber.
     */
    private ChamberShape shape;
    /**
     * The Monsters in this chamber.
     */
    private ArrayList<Monster> monsters = new ArrayList<>();
    /**
     * The treasures in this Chamber.
     */
    private ArrayList<Treasure> treasures = new ArrayList<>();
    /**
     * The doors in this chamber.
     */
    private ArrayList<Door> doors = new ArrayList<>();

    /**
     * The trap in this Chamber.
     */
    private Trap trap = new Trap();

    /**
     * The stairs in this Chamber.
     */
    private Stairs stairs = new Stairs();

    /**
     * A constructor for Chamber.
     * It chooses a chamber's shape anc contents based on the parameters.
     * @param chooseShape The number passed to setShape that will choose the chamber's shape.
     * @param chooseContents The number passed to setContents that will choose the chamber's contents.
     */
    public Chamber(int chooseShape, int chooseContents) {
        setShape(chooseShape);
        setContents(chooseContents);
    }

    /**
     * A constructor for Chamber.
     * It sets the chamber's contents and shape to the parameters.
     * @param newContents The contents for the chamber.
     * @param newShape The shape for the chamber.
     */
    public Chamber(ChamberContents newContents, ChamberShape newShape) {
        contents = newContents;
        shape = newShape;
        setDoors();
    }

    private void setContents(int chooseContents) {
        contents.chooseContents(chooseContents);
        addMonster();
        addTreasure();
        addTrap();
        addStairs();
        setDoors();
    }

    private void setShape(int chooseShape) {
        shape = ChamberShape.selectChamberShape(chooseShape);
    }



    private void addMonster() {
        if (contents.getDescription().contains("monster")) {
            Monster monster = new Monster();
            monster.setType(d100.roll());
            monsters.add(monster);
        }
    }


    private void addTreasure() {
        if (contents.getDescription().contains("treasure")) {
            Treasure treasure = new Treasure();
            treasure.chooseTreasure(d100.roll());
            treasure.setContainer(d20.roll());
            treasures.add(treasure);
        }
    }

    private void addTrap() {
        if (contents.getDescription().contains("trap")) {
            trap.chooseTrap(d20.roll());
        }
    }

    private void addStairs() {
        if (contents.getDescription().contains("stairs")) {
            stairs.setType(d20.roll());
        }
    }

    private int getNumExits() {
        return shape.getNumExits();
    }

    /**
     * Gets the description of this Chamber.
     * @return The string that is the description of this Chamber.
     */
    @Override
    public String getDescription() {
        description.append("Chamber ").append(chamberNumber);
        description.append(": ");
        getShapeDescription();
        description.append("\n");
        getContentsDescription();
        getDoorsDescription();
        description.append("\n");
        return description.toString();
    }

    private void getDoorsDescription() {
        for (Door d: doors) {
            description.append(d.getDescription());
            description.append("\n");
        }
    }

    /**
     * A function that returns the ArrayList of doors in this chamber.
     * @return The ArrayList of Doors in this chamber.
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }

    private void getContentsDescription() {
        description.append("The room contains ");
        getRoomContentDescription();
        getMonsterDescription();
        getTreasureDescription();
        getTrapDescription();
        getStairsDescription();
    }

    private void getStairsDescription() {
        if (contents.getDescription().contains("stairs")) {
            description.append("The stairs go ");
            description.append(stairs.getDescription());
            description.append("\n");
        }
    }
    private void getRoomContentDescription() {
        if (contents.getDescription().equals("empty")) {
            description.append("nothing");
        } else {
            description.append(contents.getDescription());
        }
        description.append("\n");
    }
    private void getTrapDescription() {
        if (contents.getDescription().contains("trap")) {
            description.append("The trap is ");
            description.append(trap.getDescription());
            description.append("\n");
        }
    }

    private void getTreasureDescription() {
        if (contents.getDescription().contains("treasure")) {
            description.append("A treasure is ");
            for (Treasure t : treasures) {
                description.append(t.getWholeDescription());
                description.append("\n");
            }
        }
    }

    private void getMonsterDescription() {
        if (contents.getDescription().contains("monster")) {
            for (Monster m : monsters) {
                description.append("A monster is a ");
                description.append(m.getDescription());
                description.append(" and there is between ");
                description.append(m.getMinNum()).append(" and ");
                description.append(m.getMaxNum()).append(" of them\n");
            }
        }
    }

    private void getShapeDescription() {
        description.append("The room has a ");
        description.append(shape.getShape()).append(" shape");
        try {
            addRegularShapeDescription();
        } catch (UnusualShapeException e) {
            addUnusualShapeDescription();
        }
    }

    private void addUnusualShapeDescription() {
        description.append(" with an area of ").append(shape.getArea());
        description.append(" square ft.");
    }

    private void addRegularShapeDescription() {
        shape.getLength();
        description.append(" with a length of ").append(shape.getLength());
        description.append("ft. and a width of ").append(shape.getWidth());
        description.append("ft.");
    }

    /**
     * Sets this chamber to be a space in the door.
     * @param newDoor The door the chamber is to be added to.
     */
    @Override
    public void setDoor(Door newDoor) {
        newDoor.setChamber(this);
        newDoor.setDoorNumber(doors.size());
        newDoor.setChamberNumber(chamberNumber);
        doors.add(newDoor);
    }

    private void setDoors() {
        Door door;
        doors = new ArrayList<>();
        for (int i = 0; i < getNumExits(); i++) {
            door = new Door();
            setDoor(door);
        }
    }

    /**
     * Sets the number assigned to this Chamber.
     * @param num The number to be assigned to this Chamber.
     */
    public void setChamberNumber(int num) {
        chamberNumber = num;
    }

    /**
     * Gets the number assigned to this Chamber.
     * @return The number assigned to this Chamber.
     */
    public int getChamberNumber() {
        return chamberNumber;
    }
}
