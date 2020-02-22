package main;

import dnd.die.D20;
import dungeon.Chamber;
import dungeon.Door;
import dungeon.Passage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Level {

    /**
     * A die rolling simulation that gives numbers between 1 and 20.
     */
    private D20 d20 = new D20();
    /**
     * The HashMap of all the Doors and the Chambers that they connect to not including
     * The chamber it's a part of.
     */
    private HashMap<Door, ArrayList<Chamber>> doorMap = new HashMap<>();
    /**
     * An array of all of the doors in the Level.
     */
    private ArrayList<Door> doors = new ArrayList<>();
    /**
     * An array of all the Chambers in the Level.
     */
    private ArrayList<Chamber> chambers = new ArrayList<>();
    /**
     * The total number of Passages in the Level.
     * Used to set the Passages' specific number.
     */
    private int numPassages = 0;
    /**
     * The array of passages on this level.
     */
    private ArrayList<Passage> passages = new ArrayList<>();
    /**
     * The description String of this level.
     */
    private StringBuilder desc = new StringBuilder();

    /**
     * Makes a number of chambers based on the input number.
     * @param num The number of chambers to be created.
     */
    public void makeChambers(int num) {
        Chamber chamber;
        for (int i = 0; i < num; i++) {
            chamber = new Chamber(d20.roll(), d20.roll());
            chamber.setChamberNumber(i);
            setDoorNumbers(chamber, i);
            doors.addAll(chamber.getDoors());
            chambers.add(chamber);
        }
    }

    private void setDoorNumbers(Chamber chamber, int num) {
        for (Door d: chamber.getDoors()) {
            d.setChamberNumber(num);
        }
    }

    /**
     * Links one Door to another Door in a different Chamber.
     * Continues until all the Doors have a connection.
     * If a door already has a connection it skips that one.
     */
    public void linkDoors() {
        for (int i = 0; i < doors.size(); i++) {
            Door door1 = doors.get(i);
            if (doorMap.containsKey(door1)) {
                continue;
            }
            Door door2 = findDoor2(door1);
            link2(door1, door2);
        }
    }

    private Door findDoor2(Door door1) {
        Door door2;
        Random r = new Random();
        do {
            door2 = doors.get(r.nextInt(doors.size()));
        } while (door2.getChamberNumber() == door1.getChamberNumber());
        return door2;
    }

    private void link2(Door current, Door target) {
        Passage linking = new Passage();
        passageLinking(linking, current, target);
        mapInsert(current, target);
    }

    private void mapInsert(Door current, Door target) {
        insertToMap(current, target);
        insertToMap(target, current);
    }

    private void passageLinking(Passage linking, Door current, Door target) {
        linking.makeBasicPassage();
        linking.setPassageNumber(numPassages);
        linking.addDoor(current);
        linking.addDoor(target);
        numPassages++;
        passages.add(linking);
        current.addSpace(linking);
        target.addSpace(linking);
    }

    private void insertToMap(Door current, Door target) {
        if (doorMap.containsKey(current)) {
            doorMap.get(current).add(target.getChamber());
        } else {
            ArrayList<Chamber> list = new ArrayList<>();
            list.add(target.getChamber());
            doorMap.put(current, list);
        }
    }

    /**
     * Makes and gets the description for this level.
     * @return the description for this level.
     */
    public String getDescription() {
        makeDescription();
        return desc.toString();
    }

    private void makeDescription() {
        getChamberDescriptions();
        getPassageDescriptions();
        }

    private void getPassageDescriptions() {
        for (Passage p:passages) {
            desc.append(p.getDescription());
        }
    }

    private void getChamberDescriptions() {
        for (Chamber c:chambers) {
            desc.append(c.getDescription());
        }
    }
}
