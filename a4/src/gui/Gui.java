package gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;

public class Gui extends Application {

    private BorderPane root = new BorderPane();
    private BorderPane second = new BorderPane();
    private Button edit = new Button("Edit");
    private TextArea description = new TextArea();
    private ComboBox<String> spaces = new ComboBox<>();
    private ComboBox<String> doors = new ComboBox<>();
    private Menu menu = new Menu("File");
    private MenuItem save = new MenuItem("Save");
    private MenuItem load = new MenuItem("Load");
    private Button doorClose = new Button("Close");
    private Stage stage;
    private Controller controller;
    private Popup doorPopup;
    private Popup editPopup;
    private TextField treasureInput = new TextField();
    private TextField monsterInput = new TextField();
    private TextArea treasureOptions = new TextArea();
    private TextArea monsterOptions = new TextArea();
    private Button addMonster = new Button();
    private Button addTreasure = new Button();
    private Button saveEditButton = new Button();
    private Button cancelEditButton = new Button();
    private TextArea removeTreasureArea = new TextArea();
    private TextArea removeMonsterArea = new TextArea();
    private Button removeMonsterButton = new Button();
    private Button removeTreasureButton = new Button();
    private TextField removeMonsterField = new TextField();
    private TextField removeTreasureField = new TextField();
    private FileChooser chooser = new FileChooser();
    private MenuBar menuBar;
    private Scene scene;

    /**
     * Runs the main program but this one gets called by the launcher.
     * @param args Command line input.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void getDisplay() {
        root.setBottom(second);
        description.setEditable(false);
        menu.setText("File");
        save.setText("Save");
        load.setText("Load");
        stage.setTitle("Dungeon Editor.");
        menu.getItems().addAll(save, load);
        menuBar = new MenuBar(menu);
        scene = new Scene(root, 500, 250);
        setLocations();
    }

    private void setLocations() {
        second.setLeft(edit);
        root.setTop(menuBar);
        root.setLeft(spaces);
        root.setCenter(description);
        root.setRight(doors);
        stage.setScene(scene);
    }

    /**
     * Starts the GUI.
     * @param assignedStage The stage that gets created when the GUI starts.
     */
    @Override
    public void start(Stage assignedStage) {
        controller = new Controller(this);
        stage = assignedStage;
        controller.setUpLevel();
        getDisplay();
        setDefaultText();
        getActions();

        finalDisplay();
    }

    private void getActions() {
        addMonster.setOnAction((ActionEvent e) -> {
            controller.addMonster(Integer.parseInt(monsterInput.getText()));
            removeMonsterArea.setText(controller.getMonstersDesc());
        });

        addTreasure.setOnAction(((ActionEvent e) -> {
            controller.addTreasure(Integer.parseInt(treasureInput.getText()));
            removeTreasureArea.setText(controller.getTreasuresDesc());
        }));

        removeMonsterButton.setOnAction((ActionEvent e) -> {
            controller.removeMonster(Integer.parseInt(removeMonsterField.getText()));
            removeMonsterArea.setText(controller.getMonstersDesc());
        });

        removeTreasureButton.setOnAction((ActionEvent e) -> {
            controller.removeTreasure((Integer.parseInt(removeTreasureField.getText())));
            removeTreasureArea.setText(controller.getTreasuresDesc());
        });

        saveEditButton.setOnAction((ActionEvent e) -> {
            controller.addListsToSpace();
            controller.setSpaceText(spaces.getValue());
            editPopup.hide();
        });

        cancelEditButton.setOnAction((ActionEvent e) -> editPopup.hide());

        edit.setOnAction((ActionEvent e) -> {
            controller.freshMonsters(spaces.getValue());
            controller.freshTreasures(spaces.getValue());
            editPopup = controller.makeEditPopup();
            editPopup.show(stage);
        });

        save.setOnAction((ActionEvent e) -> {
            File file = chooser.showSaveDialog(stage);
            String path = file.getAbsolutePath();
            controller.serialOut(path);
        });

        load.setOnAction((ActionEvent e) -> {
            File file = chooser.showOpenDialog(stage);
            String path = file.getAbsolutePath();
            controller.serialIn(path);
            controller.loadLevel();
            spaces.getSelectionModel().selectFirst();
            doors.getSelectionModel().selectFirst();
            controller.setSpaceText(spaces.getValue());
        });

        spaces.setOnAction(e -> {
            controller.setSpaceText(spaces.getValue());
            doors.getSelectionModel().selectFirst();
        });

        doors.setOnHiding(e -> {
            if (doors.getValue() != null) {
                if (doorPopup != null) {
                    doorPopup.hide();
                }
                doorPopup = controller.doorPopup(doors.getValue(), doorClose);
                doorPopup.show(stage);
            }
        });
        doorClose.setOnAction(e -> doorPopup.hide());

    }

    private void finalDisplay() {
        spaces.getSelectionModel().selectFirst();
        doors.getSelectionModel().selectFirst();
        controller.setSpaceText(spaces.getValue());
        stage.show();
    }

    private void setDefaultText() {
        addMonster.setText("Add Monster");
        addTreasure.setText("Add Treasure");
        removeMonsterButton.setText("Remove Monster");
        removeTreasureButton.setText("Remove Treasure");
        saveEditButton.setText("Save");
        cancelEditButton.setText("Cancel");
    }


    /**
     * Gets the spaces ComboBox.
     * @return Returns the spaces ComboBox.
     */
    public ComboBox<String> getSpaces() {
        return spaces;
    }

    /**
     * Sets the main text area text with the Space description.
     * @param text The text that it should be.
     */
    public void setAreaText(String text) {
        description.setText(text);
    }

    /**
     * Gets the doors ComboBox.
     * @return Returns the doors ComboBox.
     */
    public ComboBox<String> getDoors() {
        return doors;
    }

    /**
     * Makes the Popup for the Door description.
     * @return The Popup created.
     */
    public Popup makeEditPopup() {
        Popup popup = new Popup();
        popup.setY(0);
        popup.setX(0);
        GridPane pane = new GridPane();
        setUpPopupTexts();
        pane.setVgap(8);
        pane.setHgap(8);
        addColumn0(pane);
        addColumn1(pane);
        popup.getContent().add(pane);
        return popup;
    }

    private void addColumn1(GridPane pane) {
        pane.add(treasureOptions, 1, 0);
        pane.add(treasureInput, 1, 1);
        pane.add(addTreasure, 1, 2);
        pane.add(removeTreasureArea, 1, 3);
        pane.add(removeTreasureField, 1, 4);
        pane.add(removeTreasureButton, 1, 5);
        pane.add(cancelEditButton, 1, 6);
    }

    private void addColumn0(GridPane pane) {
        pane.add(monsterOptions, 0, 0);
        pane.add(monsterInput, 0, 1);
        pane.add(addMonster, 0, 2);
        pane.add(removeMonsterArea, 0, 3);
        pane.add(removeMonsterField, 0, 4);
        pane.add(removeMonsterButton, 0, 5);
        pane.add(saveEditButton, 0, 6);
    }

    private void setUpPopupTexts() {
        monsterOptions.setEditable(false);
        monsterOptions.setText(controller.getMonsters());
        removeMonsterArea.setEditable(false);
        removeMonsterArea.setText(controller.getMonstersDesc());
        treasureOptions.setEditable(false);
        treasureOptions.setText(controller.getTreasure());
        removeTreasureArea.setEditable(false);
        removeTreasureArea.setText(controller.getTreasuresDesc());
    }


    /**
     * Gets the String that the current space of the spaces ComboBox.
     * @return The String.
     */
    public String getCurrentSpace() {
        return spaces.getValue();
    }
}
