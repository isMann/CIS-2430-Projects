Class  Chamber

| method signature | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| public Chamber(int, int) | Sets defaults with integers | | setShape() setContents() | | 2 |
| public Chamber(ChamberContents ChamberShape) | Sets defaults with objects | ChamberContents contents ChamberShape shape | setDoors() | | 3 |
| private void setContents(int) | Sets the contents of the Chamber | ChamberContents contents | addMonster() addTreasure() addStairs() addDoors() addTrap() | ChamberContents.chooseContents() | 6 |
| private void setShape(int) | Sets the Shape of the Chamber | ChamberShape shape | | ChamberShape.selectChamberShape() | 1 |
| private void addMonster() | Adds a monster if one exists | ChamberContents contents ArrayList\<Monster> monsters  Percentile d100 | | ChamberContents.getDescription() String.contains() D100.roll() ArrayList.add() Monster.setType Monster.Monster() | 5 |
| private void addTreasure() | Adds a treasure if one exists | ChamberContents contents ArrayList\<Treasure> treasures Percentile d100 D20 d20 | | ChamberContents.getDescription() String.contains() D100.roll() D20.roll ArrayList.add() Treasure.chooseTreasure() Treasure.setContainer() Treasure.Treasure() | 6 |
| private void addTrap() | Adds a trap if one exists | ChamberContents contents Trap trap D20 d20 | | ChamberContents.getDescription() String.contains() Trap.chooseTrap() D20.roll() | 3 |
| private void addStairs() | Adds stairs if they exist | ChamberContents contents Stairs stairs D20 d20 | | ChamberContents.getDescription() String.contains() Stairs.setType() D20.roll() | 3 |
| private int getNumExits() | Gets the number of exits in the Chamber | ChamberShape shape | | ChamberShape.getNumExits() | 1 |
| public String getDescription() | Gets the description of the Chamber | StringBuilder description | getShapeDescription() getContentsDescription() getDoorsDescription() | StringBuilder.toString() StringBuilder.append() | 8 |
| private void getDoorsDescription() | Adds the description of the Doors to the description | StringBuilder description ArrayList\<Door> doors | | StringBuilder.append() | 4 |
| public ArrayList<Door> getDoors() | Returns the ArrayList of Doors | ArrayList\<Door> doors | | | 1 |
| private void getContentsDescription() | Adds the Contents of the Chamber to the description | StringBuilder description | getRoomContentDescription() getMonsterDescription() getTreasureDescription() getTrapDescription() getStairsDescription() | StringBuilder.append() | 6 |
| private void getStairsDescription() | Adds the Stairs description to the Chamber description if they exist | ChamberContents contents StringBuilder description Stairs stairs | | Stairs.getDescription ChamberContents.getDescription() String.contains() StringBuilder.append() | 4 |
| private void getRoomContentDescription() | Adds what the room contains to the description | StringBuilder description ChamberContents contents | | ChamberContents.getDescription String.equals() StringBuilder.append() | 6 |
| private void getTrapDescription() | Adds the Trap description to the Chamber description if it exists | StringBuilder description ChamberContents contents Trap trap | | StringBuilder.append() trap.getDescription String.contains ChamberContents.getDescription() | 5 |
| private void getTreasureDescription() | Adds the Treasure description to the Chamber description if it exists | StringBuilder description ChamberContents contents ArrayList\<Treasure> treasures | | StringBuilder.append ChamberContents.getDescription() String.contains() Treasure.getWholeDescription() | 7 |
| private void getMonsterDescription() | Adds the Monster description to the Chamber description if it exists | StringBuilder description ChamberContents contents ArrayList\<Monster> monsters | ChamberContents.getDescription() String.contains() StringBuilder.append() Monster.getDescription() Monster.getMinNum() Monster.getMaxNum() | 9 |
| private void getShapeDescription | Adds the Shape description to the Chamber description | StringBuilder description ChamberShape shape | addRegularShapeDescription() addUnusualShapeDescription() | StringBuilder.append() ChamberShape.getShape() | 7 |
| private void getRegularShapeDescription | Adds the Length and Width to the Chamber description | StringBuilder description ChamberShape shape | | StringBuilder.append() ChamberShape.getLength() ChamberShape.getWidth() | 4 |
| private void getUnusual ShapeDescription | Adds the Area to the Chamber description | StringBuilder description ChamberShape shape | | StringBuilder.append() ChamberShape.getArea() | 2 |
| public void setDoor(Door) | Adds a door to the ArrayList setting defaults | ArrayList\<Door> doors int chamberNumber | | Door.setChamber() Door.setDoorNumber() ArrayList.size() Door.setChamberNumber() ArrayList.add() | 4 |
| private void setDoors() | Adds a door for every Exit in the Chamber | ArrayList\<Door> doors | Door.Door() ArrayList.ArrayList() | setDoor() getNumExits() | 6 |
| public void setChamberNumber() | Sets the number for the Chamber for organization purposes | int chamberNumber | | | 1|
| public void getChamberNumber() | Gets the Number of the Chamber | int chamberNumber | | | 1 |