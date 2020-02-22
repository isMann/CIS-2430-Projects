package dungeon;


import java.io.Serializable;

public abstract class Space implements Serializable {

    /**
     * This function returns the description of the space.
     * @return The description of the space.
     */
    public abstract  String getDescription();

    /**
     * Adds this space to the door spaces.
     * @param theDoor The door to set this space to.
     */
    public abstract void setDoor(Door theDoor);

}
