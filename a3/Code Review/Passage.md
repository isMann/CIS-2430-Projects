Class  Passage

| method signature | responsibility | instance variables used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| public void makeBasicPassage() | Make a passage that goes straight for 10ft. and ends at a Door | | addPassageSection() | | 2 |
| private void addPassageSection(int) | Add a passage section to the Passage | ArrayList\<PassageSection> sections | | PassageSection.PassageSection() ArrayList.add() | 2 |
| public void SetDoor(Door) | Adds a door to the Passage | ArrayList\<Door> doors | | Door.getSpaces() ArrayList.add() | 2 |
| public void getDoors() | Gets the array of doors in this passage | ArrayList\<Door> doors | | | 1 |
| public String getDescription() | Get the description of the Passage | StringBuilder desc int passageNumber ArrayList\<PassageSection> sections | addDoorLocations() | PassageSection.getDescription() StringBuilder.append() StringBuilder.toString() | 10 |
| private void addDoorLocations(int) | Adds the description of what Door in which Chamber to the description | ArrayList<Door> doors StringBuilder desc | | StringBuilder.append() Door.gerChamber() ArrayList.get() Chamber.getChamberNumber() Door.getDoorNumber() | 4 |
| public void setPassageNumber(int) | Sets the Passage number | int passageNumber | | | 1 |
| public void addDoor(Door) | Adds a door to the Array | ArrayList<Door> doors | | ArrayList.add | 1 |