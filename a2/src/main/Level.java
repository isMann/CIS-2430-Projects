package main;

import dnd.models.Monster;

import java.util.ArrayList;

public class Level {
    /**
     * The array of Chambers on this level.
     */
    private ArrayList<Chamber> chambers = new ArrayList<>();
    /**
     * The array of passages on this level.
     */
    private ArrayList<Passage> passages = new ArrayList<>();
    /**
     * the array of doors associated with chambers on this level.
     */
    private ArrayList<ArrayList<Door>> chamberDoors = new ArrayList<>();
    /**
     * The array of doors associated with passages on this level.
     */
    private ArrayList<ArrayList<Door>> passageDoors = new ArrayList<>();
    /**
     * The description String of this level.
     */
    private StringBuilder desc = new StringBuilder();
    /**
     * The number of Chambers with a connection to a passage.
     */
    private int connectedChambers;
    /**
     * The Number of passages on this level.
     * The name refers to how deep into the recursive function
     * the algorithm went.
     */
    private int deepest;

    Level() {
        connectedChambers = 1;
        deepest = 0;
    }

    /**
     * Makes all the passages and connects them.
     */
    void loopPassage() {
        for (int i = 0; i < connectedChambers; i++) {
            for (int j = 0; j < chamberDoors.get(i).size(); j++) {
                desc.append("\nChamber ").append(i + 1).append(" door ");
                desc.append(j + 1).append(" leads to:\n");
                makePassages(deepest);
                deepest++;
            }
        }
    }

    /**
     * Makes a number of chambers based on the input number.
     * @param num The number of chambers to be created.
     */
    void makeChambers(int num) {
        Chamber chamber;
        for (int i = 0; i < num; i++) {
            chamber = new Chamber();
            chambers.add(chamber);
            if (i == 0) {
                if (chambers.get(i).getExits().size() == 0) {
                    chambers.get(i).setExits(1);
                }
            }
            chamberDoors.add(chambers.get(i).getDoors());
        }
        addFirstChamberDescription();
    }

    private void addFirstChamberDescription() {
        desc.append("The entrance to the first door is an archway\n");
        makeChamberDescription(0);
    }

    private void makePassages(int passNum) {
        int doorNum = 0;
        int last = 0;
        Passage passage = new Passage();
        passage.makeSections();
        passages.add(passage);
        passageDoors.add(passage.getDoors());
        makePassageDescription(passNum);
        for (int i = 0; i < passage.getPassageSections().size() - 1; i++) {
            if (passage.getPassageSections().get(i).getDescription().contains("door")) {
                desc.append("\nPassage ").append(passNum + 1).append(" door ").append(doorNum + 1);
                doorNum++;
                desc.append(" leads to\n");
                makePassages(passNum + 1);
            }
            last++;
        }
        if (passage.getPassageSections().get(last).getDescription().contains("door") && chambersConnected() < 5) {
            passage.addDoor();
            passage.setDoor(passage.getDoor(passage.getDoors().size() - 1));
            desc.append("Passage ").append(passNum + 1).append(" ends at\n");
            makeChamberDescription(connectedChambers);
            connectedChambers++;
        }
        deepest = Math.max(passNum, deepest);
    }

    /**
     * Gets the number of chambers that are connected to passages.
     * @return The number of chambers that are connected to passages.
     */
    int chambersConnected() {
        return connectedChambers;
    }

    /**
     * Gets the description of this level.
     * @return The description of this level.
     */
    public String getDescription() {
        //TODO append the descripiton to the string builder
//        for (int i = 0; i < chambers.size(); i++) {
//            makeChamberDescription(i);
//        }
//        for (int i = 0; i < passages.size(); i++) {
//            description.append("Passage ").append(i + 1).append(": ");
//            description.append(passages.get(i).getDescription()).append("\n");
//        }
        return desc.toString();
    }

    private void makePassageDescription(int passNum) {
        desc.append("Passage ").append(passNum + 1).append(": ");
        desc.append(passages.get(passNum).getDescription()).append("\n");
        if (passages.get(passNum).getDescription().contains("Monster")) {
            ArrayList<Monster> monsters = passages.get(passNum).getMonsters();
            for (int i = 0; i < monsters.size(); i++) {
                desc.append("Monster ").append(i + 1).append(" is ");
                desc.append("between ").append(monsters.get(i).getMinNum());
                desc.append(" and ").append(monsters.get(i).getMaxNum());
                desc.append(" ").append(monsters.get(i).getDescription()).append("s\n");
            }
        }
        for (int i = 0; i < passageDoors.get(passNum).size(); i++) {
            desc.append("Door ").append(i + 1).append(": ");
            desc.append(passageDoors.get(passNum).get(i).getDescription()).append("\n");
        }
    }

    private void makeChamberDescription(int i) {
        desc.append("\nChamber ").append(i + 1).append(":\n");
        desc.append(chambers.get(i).getDescription());
        desc.append("It has ").append(chambers.get(i).getExits().size());
        if (chambers.get(i).getExits().size() == 1) {
            desc.append(" other door.\n");
        } else {
            desc.append(" other doors.\n");
        }
        for (int j = 0; j < chambers.get(i).getExits().size(); j++) {
            if (j == 0) {
                desc.append("One located ");
            } else {
                desc.append("Another located ");
            }
            desc.append("at the ");
            desc.append(chambers.get(i).getExits().get(j).getLocation());
            desc.append(" facing ");
            desc.append(chambers.get(i).getExits().get(j).getDirection());
            desc.append(".\n");
            desc.append(chamberDoors.get(i).get(j).getDescription()).append("\n");
        }
        desc.append("\n");
    }
}
