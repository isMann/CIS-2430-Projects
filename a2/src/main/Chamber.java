package main;
import dnd.exceptions.NotProtectedException;
import dnd.exceptions.UnusualShapeException;
import dnd.models.ChamberShape;
import dnd.models.ChamberContents;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.models.Exit;

import java.util.ArrayList;

public class Chamber extends Space {

    /**
     * The contents of the chamber.
     */
    private ChamberContents contents;
    /**
     * The shape of the Chamber.
     */
    private ChamberShape shape;
    /**
     * The Monsters in this chamber.
     */
    private ArrayList<Monster> monsters;
    /**
     * The treasures in this Chamber.
     */
    private ArrayList<Treasure> treasures;
    /**
     * The doors in this chamber.
     */
    private ArrayList<Door> doors = new ArrayList<>();

    /**
     * The default constructor for Chamber.
     * It randomly assigns contents, shape, monsters, and treasure.
     * It sets the Doors associated with this chamber.
     */
    Chamber() {
        contents = new ChamberContents();
        shape = new ChamberShape();
        monsters = new ArrayList<>();
        treasures = new ArrayList<>();
        contents.setDescription();
        shape.setShape();
        if (contents.getDescription().contains("monster")) {
            addMonster();
        }
        if (contents.getDescription().contains("treasure")) {
            addTreasure();
        }
        setDoors();
    }

    /**
     * The Constructor for Chamber if the Shape and contents are predefined.
     * @param theShape The predefined shape of the chamber.
     * @param theContents The predefined contents of the chamber.
     */
    Chamber(ChamberShape theShape, ChamberContents theContents) {
        monsters = new ArrayList<>();
        treasures = new ArrayList<>();
        contents = theContents;
        shape = theShape;
        setDoors();
    }

    /**
     * Sets the Shape of the chamber to be the shape defined by theShape.
     * @param theShape The new shape of the chamber.
     */
    void setShape(ChamberShape theShape) {
        shape = theShape;
        setDoors();
    }

    /**
     * Returns the array of doors in this chamber.
     * @return The array of doors in this chamber.
     */
    ArrayList<Door> getDoors() {
        return doors;
    }

    private void addMonster() {
        Monster monster = new Monster();
        monster.setType();
        monsters.add(monster);
    }


    /**
     * Adds a preset monster to the room.
     * @param theMonster The monster to be added.
     */
    void addMonster(Monster theMonster) {
        monsters.add(theMonster);
    }

    /**
     * Returns the array of monsters.
     * @return The array of monsters.
     */
    ArrayList<Monster> getMonsters() {
        return monsters;
    }

    private void addTreasure() {
        Treasure treasure = new Treasure();
        treasure.setDescription();
        treasure.setContainer();

        treasures.add(treasure);
    }

    /**
     * Adds a predefined treasure to the Chamber.
     * @param theTreasure The treasure to be added.
     */
    void addTreasure(Treasure theTreasure) {
        treasures.add(theTreasure);
    }

    /**
     * Gets the array of treasure in this chamber.
     * @return The array of treasure in this chamber.
     */
    ArrayList<Treasure> getTreasureList() {
        return treasures;
    }

    /**
     * Gets the array of exits in this Chamber.
     * @return The array of Exits.
     */
    ArrayList<Exit> getExits() {
        return shape.getExits();
    }

    /**
     * Gets the description of this Chamber.
     * @return The string that is the description of this Chamber.
     */
    @Override
    public String getDescription() {
        StringBuilder string = new StringBuilder();
        string.append("It contains ");
        string.append(contents.getDescription()).append("\n");
        //chamber shape
        string.append("It has the shape ");
        string.append(shape.getShape());
        string.append(" ");
        try {
            string.append(shape.getLength());
            string.append("'x");
            string.append(shape.getWidth());
            string.append("'.\n");
        } catch (UnusualShapeException e) {
            string.append("with an area of approximately ");
            string.append(shape.getArea());
            string.append(".\n");
        }

        //treasures in room
        if (treasures.size() > 0) {
            string.append("It has the treasures ");
            for (Treasure treasure : treasures) {
                string.append(treasure.getDescription());
                string.append(" inside ");
                string.append(treasure.getContainer());
                string.append(" protected by ");
                try {
                    string.append(treasure.getProtection()).append(".\n");
                } catch (NotProtectedException e) {
                    string.append("nothing.\n");
                }
            }
        } else {
            string.append("It contains no treasure.\n");
        }

        //room monsters
        if (monsters.size() > 0) {
            string.append("It has ");
            for (Monster monster : monsters) {
                string.append("between ");
                string.append(monster.getMinNum());
                string.append(" and ");
                string.append(monster.getMaxNum());
                string.append(" ");
                string.append(monster.getDescription()).append("s");
            }
            string.append(".\n");
        } else {
            string.append("It has no monsters.\n");
        }

        return string.toString();
    }

    /**
     * Sets this chamber to be a space in the door.
     * @param newDoor The door the chamber is to be added to.
     */
    @Override
    public void setDoor(Door newDoor) {
        //should add a door connection to this room
        newDoor.getSpaces().add(this);
        doors.add(newDoor);
    }


    /**
     * Sets the exits for this Chamber.
     * If there is an integer it uses that integer to generate Exits.
     * Otherwise it randomly decides.
     * @param i The amount of exits for this section otherwise it should be left blank.
     */
    void setExits(int... i) {
        if (i.length == 0) {
            shape.setNumExits();
        } else {
            shape.setNumExits(i);
        }

        setDoors();
    }

    private void setDoors() {
        Door door;
        doors = new ArrayList<>();
        if (getExits().size() == 0) {
            setExits(1);
            doors = new ArrayList<>();
        }
        for (int i = 0; i < shape.getNumExits(); i++) {
            door = new Door();
            doors.add(door);
        }
    }
}
