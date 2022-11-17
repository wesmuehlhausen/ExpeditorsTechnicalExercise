import java.util.*;

/**
 * A Household contains an ArrayList of the Occupants that live there
 */
public class Household {

    //Private member variables
    ArrayList<Occupant> occupants; //A list of all the occupants in a given household
    private String id; //ID for the household. A combination of the address, state, and city
    private String readableID;//A readable version of the ID for printing

    //Public functions

    /**
     * Default constructor
     */
    public Household() {
        occupants = new ArrayList<>(); //Create the arraylist
    }

    /**
     * Constructor given ID, readableID, and the first occupant that will be added to the list of occupants
     * It adds the occupant to the empty arraylist
     */
    public Household(String id, String readableID, Occupant occupant) {
        this.id = id;
        this.readableID = readableID;
        occupants = new ArrayList<>();
        occupants.add(occupant);//Add first occupant
    }

    /**
     * Sorts the occupants arraylist based on the collections sort method
     * Sort by Last Name -> First Name -> Age
     */
    public void sortHousehold(){
        Collections.sort(occupants, new Occupant());
    }

    /**
     * @return a formatted string with the address, and number of occupants
     */
    @Override
    public String toString() {
        return "Household: " + readableID + " [Occupants: " + occupants.size() + "]";
    }

    /**
     * Takes in an occupant and adds it to the list of occupants
     */
    public void addOccupant(Occupant occupant) {
        occupants.add(occupant);
    }

    //Public getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReadableID() {
        return readableID;
    }

    public void setReadableID(String readableID) {
        this.readableID = readableID;
    }
}
