Class  Level

| method signature | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| public void makeChambers(int) | Create the specified number of Chambers| D20 d20 ArrayList\<Door> doors ArrayList\<Chamber> chambers | setDoorNumbers() | Chamber.Chamber() D20.roll() ArrayList.addAll() ArrayList.add() Chamber.getDoors() Chamber.setChamberNumber() | 8 |
| private void setDoorNumbers(Chamber, int) | Set the door numbers for each door in the Chamber |  |  | Chamber.getDoors() Door.setChamberNumber() | 3 |
| public void linkDoors() | Link 2 Doors in separate chambers | ArrayList\<Door> doors HashMap\<Door, ArrayList\<Chamber>> doorMap | link2() setDoor2() | ArrayList.size() ArrayList.get() HashMap.containsKey() | 8 |
| private Door findDoor2(Door) | Find a random Door for the first door to connect to | ArrayList\<Door> doors | | Random.Random() ArrayList.get() Random.nextInt() Door.getChamberNumber() ArrayList.size() | 6 |
| private void link2(Door, Door) | Links 2 Doors to a new Passage | | passageLinking() mapInsert() | Passage.Passage() | 3 |
| private void mapInsert(Door, Door) | Inserts 2 Doors to the HashMap | | insertToMap() | | 2 |
| private void passageLinking(Passage, Door, Door) | Links the Doors to the Passage | int numPassages ArrayList\<Passage> passages | | Passage.makeBasicPassage() Passage.setPassageNumber() Passage.addDoor() Door.addSpace() | 8 |
| private void insertToMap(Door, Door) | Adds the target Door's Chamber to the HashMap location | HashMap\<Door, ArrayList\<Chamber>> doorMap | | HashMap.containsKey() HashMap.get() ArrayList.add() ArrayList\<>().ArrayList\<>() ArrayList.add() Door.getChamber() HashMap.put() | 7 |
| public String getDescription() | Returns the String of the Level's description | StringBuilder desc | makeDescription() | StringBuilder.toString() | 2 |
| private void makeDescription() | Makes the description for the level | | getChamberDescriptions() getPassageDescriptions() | | 2 |
| private void getChamberDescription() | Adds the Chamber descriptions to the description | ArrayList<Chamber> chambers StringBuilder desc | | StringBuilder.append() Chamber.getDescription() | 3 |
| private void getPassageDescription() | Adds the Passage descriptions to the description | ArrayList<Passage> passages StringBuilder desc | | StringBuilder.append() Passage.getDescription() | 3 |
