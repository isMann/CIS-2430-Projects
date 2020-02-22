Class  Door

| method signature | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| public Door() | Sets defaults for the Door | | setTrapped() setUnlocked() setOpen() setArchway() | D20.roll() D4.roll D6.roll  D10.roll | 8 |
| private void setTrap(int) | Sets a trap for the Door | Trap trap | | Trap.chooseTrap() | 1 |
| private void setTrapped(boolean) | Sets a trap if there is a trap if boolean is true | boolean trapped | setTrap() | D20.roll() | 5 |
| private void setOpen(boolean) | Sets the whether door open if it is under right conditions | boolean open boolean unlocked boolean archway | | | 7 |
| private void setArchway(boolean) | Sets the door to be and archway | boolean archway | setUnlocked() setOpen() setTrapped() | | 8 |
| private void setUnlocked(boolean) | Sets the door to be unlocked or locked | boolean unlocked | | | 1 |
| public void addSpace(Space) | Adds a space to the ArrayList | ArrayList\<Space> spaces | | ArrayList.add() | 1 |
| private boolean isTrapped() | Determines if the door is trapped | boolean trapped | | | 1 |
| private boolean isOpen() | Determines if the door is open | boolean open | | | 1 |
| private boolean isUnlocked() | Determines if the door is unlocked | boolean unlocked | | | 1 |
| private boolean isArchway() | Determines if the door is an archway | boolean archway | | | 1 |
| private String getTrapDescription() | Adds the trap description to the Door description | Trap trap | | Trap.getDescription() | 1 |
| public ArrayList<Space> getSpaces() | Gets the spaces this door is connecting | ArrayList<Space> spaces | | | 1 |
| public String getDescription() | Gets the description of the Door | StringBuilder description int doorNumber | getArchwayDescription() getTrappedDescription() getUnlockedDescription() getOpenDescription() | StringBuilder.append() StringBuilder.toString() | 9 |
| private void getOpenDescription() | Adds a description based on if it's open | StringBuilder description | isOpen() | StringBuilder.append() | 5 |
| private void getUnlockedDescription() | Adds a description based on if it's unlocked | StringBuilder description | isUnlocked() | StringBuilder.append() | 5 |
| private void getArchwayDescription() | Adds a description based on if it's an archway | StringBuilder description | isArchway() | StringBuilder.append() | 5 |
| private void getTrappedDescription() | Adds a description based on if it's Trapped | StringBuilder description | isTrapped() getTrapDescription() | StringBuilder.append() | 5 |
| public void setChamberNumber() | Sets the number of the Chamber it's connected to | int chamberNumber | | | 1 |
| public int getChamberNumber () | Gets the number of the Chamber that the Door is connected to. | int chamberNumber | | | 1 |
| public void setDoorNumber() | Sets the number of the Door. | int doorNumber | | | 1 |
| public int getDoorNumber() | Gets the number of the Door. | int doorNumber | | | 1 | 
| public void setChamber(Chamber) | Sets the Chamber connection to the Door. | Chamber chamber | | | 1 |
| public Chamber getChamber | Gets the Chamber the Door is connected to. | Chamber chamber | | | 1 |