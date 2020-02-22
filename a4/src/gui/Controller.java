package gui;

import dnd.models.Monster;
import dnd.models.Treasure;
import dungeon.Chamber;
import dungeon.Level;
import dungeon.Passage;
import dungeon.Door;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.util.ArrayList;

public class Controller {
    private Gui gui;
    private Level level = new Level();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Treasure> treasures = new ArrayList<>();

    Controller(Gui assignedGui) {
        gui = assignedGui;
    }


    /**
     * Makes a new Level and prints it to the GUI.
     */
    public void setUpLevel() {
        level.makeChambers(5);
        level.linkDoors();
        setUpChambers();
        setUpPassages();
        findChamber(level.getChambers().get(0));
    }

    /**
     * Loads a old level and prints it to the GUI.
     */
    public void loadLevel() {
        setUpChambers();
        setUpPassages();
        findChamber(level.getChambers().get(0));
    }

    private void setUpPassages() {
        for (Passage p:level.getPassages()) {
            gui.getSpaces().getItems().add("Passage " + p.getPassageNumber());
        }
    }

    private void setUpChambers() {
        for (Chamber c:level.getChambers()) {
            gui.getSpaces().getItems().add("Chamber " + c.getChamberNumber());
        }
    }

    /**
     * Sets the text area text of the center TextArea.
     * @param val The Space and Space number.
     */
    public void setSpaceText(String val) {
        if (val.contains("Chamber")) {
            gui.setAreaText(getChamberDesc(val));
        } else if (val.contains("Passage")) {
            gui.setAreaText(getPassageDesc(val));
        }
    }

    private String getPassageDesc(String val) {
        String[] split = val.split(" ");
        String desc = "";
        int num = Integer.parseInt(split[1]);
        for (Passage p:level.getPassages()) {
            if (num == p.getPassageNumber()) {
                desc = p.getDescription();
                findPassage(p);
            }
        }
        return desc;
    }

    private void findPassage(Passage p) {
        if (gui.getDoors().getItems().size() != 0) {
            gui.getDoors().getItems().clear();
        }
        for (Door d:p.getDoors()) {
            gui.getDoors().getItems().add("Chamber " + d.getChamberNumber() + " Door " + d.getDoorNumber());
        }
    }

    private String getChamberDesc(String val) {
        String[] split = val.split(" ");
        String desc = "";
        int num;
        num = Integer.parseInt(split[1]);
        for (Chamber c:level.getChambers()) {
            if (num == c.getChamberNumber()) {
                desc = c.getDescription();
                findChamber(c);
            }
        }
        return desc;
    }

    private void findChamber(Chamber c) {
        gui.getDoors().getItems().clear();
        for (Door d:c.getDoors()) {
            gui.getDoors().getItems().add("Chamber " + d.getChamberNumber() + " Door " + d.getDoorNumber());
        }
    }

    /**
     * Makes a Popup for the Door description and connections.
     * @param value The Chamber, Chamber number, Door and Door number.
     * @param button The closing button.
     * @return The created Popup.
     */
     public Popup doorPopup(String value, Button button) {
        Popup popup = new Popup();
        BorderPane pane = new BorderPane();
        TextArea area = new TextArea();
        String[] split = value.split(" ");
        int cNum = Integer.parseInt(split[1]);
        int dNum = Integer.parseInt(split[3]);
        area.setEditable(false);
        popup.setX(0);
        popup.setY(0);
        Door d = level.getChambers().get(cNum).getDoors().get(dNum);
        area.setText(doorAreaText(d, cNum, dNum));
        popup.getContent().add(pane);
        pane.setCenter(area);
        pane.setBottom(button);
        return popup;
    }

    private String doorAreaText(Door d, int cNum, int dNum) {
        StringBuilder string = new StringBuilder();
        string.append(level.getChambers().get(cNum).getDoors().get(dNum).getDescription());
        string.append("\nIt is connected to Chamber ").append(cNum);
        for (int i = 0; i < d.getSpaces().size(); i++) {
            string.append(i < d.getSpaces().size() - 1 ? ", " : " and ");
            string.append("Passage ");
            string.append(((Passage) d.getSpaces().get(i)).getPassageNumber());
        }
        string.append(".\n");
        return string.toString();
    }

    /**
     * makes the Editing Popup.
     * @return The created Popup.
     */
    public Popup makeEditPopup() {
        return gui.makeEditPopup();
    }

    /**
     * Adds a Monster to the monsters list.
     * @param i The monster number chosen.
     */
    public void addMonster(int i) {
        Monster monster = new Monster();
        monster.setType(i);
        monsters.add(monster);
    }

    /**
     * Adds a Treasure to the treasures list.
     * @param i The treasure chosen.
     */
    public void addTreasure(int i) {
        Treasure treasure = new Treasure();
        treasure.chooseTreasure(i);
        treasures.add(treasure);
    }

    /**
     * Adds the monsters and treasures lists to the current space.
     */
    public void addListsToSpace() {
        String[] split = gui.getCurrentSpace().split(" ");
        int num = Integer.parseInt(split[1]);
        if (split[0].equals("Chamber")) {
            Chamber c = getCurrentChamber(num);
            addListsToChamber(c);
        } else {
            Passage p = getCurrentPassage(num);
            addListsToPassage(p);
        }
    }

    private void addListsToPassage(Passage p) {
        p.getMonsters().clear();
        p.getTreasures().clear();
        p.addMonsters(monsters);
        p.addTreasures(treasures);
    }

    private void addListsToChamber(Chamber c) {
        c.getMonsters().clear();
        c.getTreasures().clear();
        c.addTreasures(treasures);
        c.addMonsters(monsters);
    }

    private Chamber getCurrentChamber(int num) {
        for (Chamber c:level.getChambers()) {
            if (c.getChamberNumber() == num) {
                return c;
            }
        }
        return null;
    }

    private Passage getCurrentPassage(int num) {
        for (Passage c:level.getPassages()) {
            if (c.getPassageNumber() == num) {
                return c;
            }
        }
        return null;
    }

    /**
     * Gets the description the monsters currently in the Space.
     * @return The description.
     */
    public String getMonstersDesc() {
        StringBuilder string = new StringBuilder();
        string.append("Monsters currently in the space.");
        string.append(" Enter an index to remove.\nIndex\t\tMonster\n");
        for (int i = 0; i < monsters.size(); i++) {
            string.append(i).append("\t").append(monsters.get(i).getDescription());
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Gets the description the treasures currently in the Space.
     * @return The description.
     */
    public String getTreasuresDesc() {
        StringBuilder string = new StringBuilder();
        string.append("Treasures currently in the space.");
        string.append(" Enter and index to remove.\nIndex\t\tTreasure\n");
        for (int i = 0; i < treasures.size(); i++) {
            string.append(i).append("\t").append(treasures.get(i).getWholeDescription());
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Clears the list of monsters and gets the list of monsters from the Space.
     * @param string The current Space and Space number.
     */
    public void freshMonsters(String string) {
        String[] split = string.split(" ");
        int num = Integer.parseInt(split[1]);
        monsters.clear();
        if (split[0].equals("Chamber")) {
            Chamber c = getCurrentChamber(num);
            monsters.addAll(c.getMonsters());
        } else {
            Passage p = getCurrentPassage(num);
            monsters.addAll(p.getMonsters());
        }
    }

    /**
     * Clears the list of treasures and gets the list of treasures from the Space.
     * @param string The current Space and Space number.
     */
    public void freshTreasures(String string) {
        String[] split = string.split(" ");
        int num = Integer.parseInt(split[1]);
        treasures.clear();
        if (split[0].equals("Chamber")) {
            Chamber c = getCurrentChamber(num);
            treasures.addAll(c.getTreasures());
        } else {
            Passage p = getCurrentPassage(num);
            treasures.addAll(p.getTreasures());
        }
    }

    /**
     * Removes a Monster from the list of monsters.
     * @param i The index of the Monster.
     */
    public void removeMonster(int i) {
        monsters.remove(i);
    }

    /**
     * Removes a Treasure from the list of treasures.
     * @param i The index of the Treasure.
     */
    public void removeTreasure(int i) {
        treasures.remove(i);
    }

    /**
     * Gets a roll table of all the possible Treasures.
     * @return The description of the roll table.
     */
    public String getTreasure() {
        Treasure treasure = new Treasure();
        StringBuilder string = new StringBuilder();
        string.append("Treasure Roll Table, input the number of the desired");
        string.append(" monster in the text field below\n");
        for (int i = 1; i <= 100; i++) {
            treasure.chooseTreasure(i);
            string.append(i).append(": ").append(treasure.getDescription());
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Gets a roll table of all the possible Monsters.
     * @return The description of the roll table.
     */
    public String getMonsters() {
        Monster monster = new Monster();
        StringBuilder string = new StringBuilder();
        string.append("Monster Roll Table, input the number of the desired");
        string.append(" monster in the text field below\n");
        for (int i = 1; i <= 100; i++) {
            monster.setType(i);
            string.append(i).append(": ").append(monster.getDescription());
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Loads a file.
     * @param file the file name.
     */
    public void serialIn(String file) {
        //Code in this function was inspired from this link
        //https://www.tutorialspoint.com/java/java_serialization.htm
        try {
            FileInputStream fInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fInput);
            level = (Level) in.readObject();
            in.close();
            fInput.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a file.
     * @param file the file name.
     */
    public void serialOut(String file) {
        //Code in this function was inspired from this link
        //https://www.tutorialspoint.com/java/java_serialization.htm
        try {
            FileOutputStream fOutput = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fOutput);
            out.writeObject(level);
            out.close();
            fOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
