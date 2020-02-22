package main;

import dnd.die.D20;
import dnd.die.Die;
import dnd.exceptions.NotProtectedException;
import dnd.exceptions.UnusualShapeException;
import dnd.models.Treasure;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Monster;

import java.util.Scanner;

final class Room {
    /**
    * A random number gererator that simulates a 20 sided die.
     */
    private Die d20 = new D20();
    /**
     * An instance of the treasure class.
     * The treasure has a description.
     * The treasure can have a container and protection.
     */
    private Treasure treasure = new Treasure();
    /**
     * An instance of the ChamberContents class.
     * It has a description of the contents of the room.
     */
    private ChamberContents contents = new ChamberContents();
    /**
     * An instance of the ChamberShape class.
     * It has a description of the possible shapes of the chamber.
     */
    private ChamberShape shape = new ChamberShape();
    /**
     * An instance of the Monster class.
     * It has a description of the possible monster in the chamber.
     */
    private Monster monster = new Monster();
    /**
     * An instance of the Trap class.
     * It has a description of the possible trap in the chamber.
     */
    private Trap trap = new Trap();
    /**
     * An instance of the Stairs class.
     *      * It has a description of the possible stairs in the chamber.
     */
    private Stairs stairs = new Stairs();
    /**
     * An instance of the Scanner class.
     * It scans for input of strings and integers in this class.
     */
    private Scanner in = new Scanner(System.in);
    /**
     * This is a string for the input from the scanner.
     */
    private String input;
    /**
     * This is an int for the input from the scanner.
     */
    private int num;
    /**
     * This boolean determines if the treasure is contained within something.
     */
    private boolean contained;

    /**
     * Generates a random chamber shape.
     * @return whether the room is not a square or rectangle
     */
    public boolean randomShape() {
        int roll = d20.roll();
        shape.setShape(roll);
        boolean weirdRoom = (roll >= 18 && roll <= 20);
        shape.setShape(roll);
        return weirdRoom;
    }

    /**
     * Common print and input reduced to one line.
     * @param string This is the element the prompt is asking about.
     * @return Returns the input given by the user.
     */
    public String getInput(final String string) {
        System.out.println("Would you like to randomly generate the "
                + string + "? (y/n)");
        input = in.next();
        return input;
    }

    /**
     * Sets the a random treasure.
     */
    private void randomTreasure() {
        treasure.setDescription();
    }

    /**
     * Makes random contents.
     */
    public void randomContents() {
        contents.setDescription();
    }

    /**
     * Sets a random shape.
     * @return Returns whether the room is not a square or rectangle.
     */
    public boolean setShape() {
        boolean weirdRoom = false;
        System.out.println("Enter the desired number");
        System.out.println("Die\tChamber Shape and Area");
        System.out.println("1-2\tSquare, 10'x10'");
        System.out.println("3-4\tSquare, 20'x20'");
        System.out.println("5-6\tSquare, 30'x30'");
        System.out.println("7-8\tSquare, 40'x40'");
        System.out.println("9-10\tRectangular, 10'x30'");
        System.out.println("11-13\tRectangular, 20'x30'");
        System.out.println("14-15\tRectangular, 20'x40'");
        System.out.println("16-17\tRectangular, 30'x40'");
        System.out.println("18-20\tUnusual Shape and size");
        do {
            num = in.nextInt();
            if (num <= 20 && num >= 1) {
                shape.setShape(num);
                weirdRoom = (num >= 18 && num <= 20);
                shape.setShape(num);
            } else {
                System.out.println("Enter a number within range");
            }
        } while (!(num <= 20 && num >= 1));

        return weirdRoom;
    }

    /**
     * Asks the user to input for the chamber contents.
     */
    public void setContents() {
        System.out.println("Enter the desired number");
        System.out.println("Die\tContents");
        System.out.println("1-12\tEmpty");
        System.out.println("13-14\tMonster only");
        System.out.println("15-17\tMonster and treasure");
        System.out.println("18\tStairs");
        System.out.println("19\tTrick/trap");
        System.out.println("20\tTreasure");
        do {
            num = in.nextInt();
            if (num <= 20 && num >= 1) {
                contents.setDescription(num);
            } else {
                System.out.println("Enter a number within range");
            }
        } while (!(num <= 20 && num >= 1));
    }

    /**
     * Asks the user to input for the treasure.
     */
    private void setTreasure() {
        System.out.println("Enter the desired number");
        System.out.println("TREASURE d%");
        System.out.println("Die\tTreasure");
        System.out.println("01-25\t1000 copper pieces/level");
        System.out.println("26-50\t1000 silver pieces/level");
        System.out.println("51-65\t750 electrum pieces/level");
        System.out.println("66-80\t100 platinum pieces/level");
        System.out.println("91-94\t1-4 gems/level");
        System.out.println("95-97\t1 piece jewelry/level");
        System.out.println("98-100\tMagic Item "
                + "(roll once on Magic Items Table");
        do {
            num = in.nextInt();
            if (num <= 100 && num >= 1) {
                treasure.setDescription(num);
            } else {
                System.out.println("Enter a number within range");
            }
        } while (!(num <= 100 && num >= 1));
    }

    /**
     * prints the number of exits and the orientation.
     */
    private void printExits() {
        System.out.println("The room has " + shape.getNumExits() + " exits.");
        for (int i = 0; i < shape.getNumExits(); i++) {
            if (i == 0) {
                System.out.print("One located ");
            } else {
                System.out.print("Another located ");
            }
            System.out.println("at the " + shape.getExits().get(i).getLocation()
                    + " facing "
                    + shape.getExits().get(i).getDirection() + ".");
        }
    }

    /**
     * Makes a random monster.
     */
    public void generateMonster() {
        monster.setType();
    }

    /**
     * Makes random stairs.
     */
    public void generateStairs() {
        stairs.setType();
    }

    /**
     * Makes a random trap.
     */
    public void generateTrap() {
        trap.setDescription();
    }

    /**
     * Makes treasure and asks user if they want it random or to choose.
     */
    public void generateTreasure() {
        do {
            //generate treasure
            input = getInput("treasure");
            if (input.equalsIgnoreCase("y")) {
                randomTreasure();
            } else if (input.equalsIgnoreCase("n")) {
                setTreasure();
            } else {
                System.out.println("Enter a valid character");
            }

        } while (!(input.equalsIgnoreCase("y")
                || input.equalsIgnoreCase("n")));

        System.out.println("Do you wish to set a container for the treasure?");
        do {
            input = in.next();
            if (input.equalsIgnoreCase("y")) {
                do {
                    //generate treasure
                    input = getInput("container for the treasure");
                    if (input.equalsIgnoreCase("y")) {
                        randomContainer();
                    } else if (input.equalsIgnoreCase("n")) {
                        setContainer();
                    } else {
                        System.out.println("Enter a valid character");
                    }
                    contained = true;
                } while (!(input.equalsIgnoreCase("y")
                        || input.equalsIgnoreCase("n")));
            } else if (input.equalsIgnoreCase("n")) {
                contained = false;
            }
        } while (!(input.equalsIgnoreCase("y")
                || input.equalsIgnoreCase("n")));

    }

    /**
     * Makes a container chosen by the user.
     */
    private void setContainer() {
        System.out.println("Die\tResult");
        System.out.println("1-2\tBags");
        System.out.println("3-4\tSacks");
        System.out.println("5-6\tSmall Coffers");
        System.out.println("7-8\tChests");
        System.out.println("9-10\tHuge Chests");
        System.out.println("11-12\tPottery Jars");
        System.out.println("13-14\tMetal Urns");
        System.out.println("15-16\tStone Containers");
        System.out.println("17-18\tIron Trunks");
        System.out.println("19-20\tLoose");
        do {
            num = in.nextInt();
            if (!(num <= 20 && num >= 1)) {
                System.out.println("Invalid Number.");
            }
        } while (!(num <= 20 && num >= 1));
        treasure.setContainer(num);
    }

    /**
     * Makes a random container.
     */
    private void randomContainer() {
        treasure.setContainer();
    }

    /**
     * Prints all the information for the room.
     */
    public void printRoom() {
        try {
            System.out.println("The room is a " + shape.getShape()
                    + " that's " + shape.getLength()
                    + "'x" + shape.getWidth()
                    + " with a total area of " + shape.getArea()
                    + " square feet");
        } catch (UnusualShapeException e) {
            System.out.println("The room is a " + shape.getShape()
                    + " with an approximate area of "
                    + shape.getArea() + " square feet.");
        }
        printExits();
        System.out.print("The room contains ");
        if (contents.getDescription().equalsIgnoreCase("empty")) {
            System.out.println("nothing.");
        } else {
            System.out.println(contents.getDescription() + ".");
        }

        if (contents.getDescription()
                .equalsIgnoreCase("monster only")) {
            System.out.println("The monster is "
                    + monster.getDescription() + ".");
        } else if (contents.getDescription()
                .equalsIgnoreCase("monster and treasure")) {
            System.out.println("The monster is "
                    + monster.getDescription() + ".");
            printTreasure();
        } else if (contents.getDescription()
                .equalsIgnoreCase("trap")) {
            System.out.println("The trap is " + trap.getDescription() + ".");
        } else if (contents.getDescription()
                .equalsIgnoreCase("treasure")) {
            printTreasure();
        } else if (contents.getDescription()
                .equalsIgnoreCase("stairs")) {
            System.out.println("The stairs are "
                    + stairs.getDescription() + ".");
        }
    }

    /**
     * Prints all the relevant information to the treasure.
     */
    public void printTreasure() {
        System.out.println("The treasure is "
                + treasure.getDescription() + ".");
        if (contained) {
            printContainer();
        }
        System.out.print("The treasure is protected by ");
        try {
            System.out.println(treasure.getProtection());
        } catch (NotProtectedException e) {
            System.out.println("nothing.");
        }

    }

    /**
     * Prints all the relevant information for the container.
     */
    private void printContainer() {
        if (treasure.getContainer().equalsIgnoreCase("loose")) {
            System.out.println("The treasure is loose.");
        } else {
            System.out.println("The treasure is contained within "
                    + treasure.getContainer() + ".");
        }
    }

    /**
     * Returns the description of the chamber contents.
     * @return Returns the description of the chamber contents.
     */
    public String getContentsDescription() {
        return contents.getDescription();
    }
}
