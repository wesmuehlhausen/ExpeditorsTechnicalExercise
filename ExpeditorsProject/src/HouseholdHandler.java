import java.util.ArrayList;
import java.util.HashMap;


/**
 * HouseholdHandler uses a HashMap to store all the Household objects. A hashmap works better for this
 * program than an ArrayList because households can be uniquely identified by a combination of address, city and state.
 * With this id/key, we can look up if a household already exists rather than iterating through an arraylist.
 * HouseholdHandler contains a number of functions that can be called to "handle" a collection of household. Such as
 * getting data, creating household objects, and printing everything
 */
public class HouseholdHandler {

    //Private Member Variables
    private HashMap<String, Household> households; //Stores all HouseHold objects based on String ID as a key
    private ArrayList<String> rawInputData; //Stores all lines form input file in the form of strings

    //Public Functions

    /**
     * Default constructor. Creates a new hashmap
     */
    public HouseholdHandler() {
       households = new HashMap<>();
    }

    /**
     * Gets all lines from input file in the form of a String ArrayList stored in a member variable
     */
    public void getRawInputData(String filename){
        rawInputData = FileIO.loadFile(filename);
    }

    /**
     * Takes in raw data inputs in teh form of a string array an arraylist. For each line, it formats the string
     * into usable fields and creates an occupant from it
     */
    public void parseData(){
        //Parse through the data and create households and occupants
        for(String line : rawInputData){
            String[] formattedInput = formatInput(line);//Get formatted string
            createOccupant(formattedInput);//Create an occupant object
        }
    }

    /**
     * Sorts every household by Last Name, then First Name, then Age
     */
    public void sortHouseHolds(){
        //Iterate through all HouseHold objects and sort
        for(String key : households.keySet()) {
            Household household = households.get(key);
            household.sortHousehold();
        }
    }

    /**
     * Prints out every household, the number of occupants, and each occupant
     * NOTE that occupants "where the occupant(s) is older than 18" will be printed.
     */
    public void prettyPrint(){
        //For every household, get the household object based on hash key
        for(String key : households.keySet()){
            Household household = households.get(key);
            System.out.println("\n" + household.toString());

            for(Occupant occupant : household.occupants){
                //Check to see if occupant is old enough
                if(occupant.isOldEnough())
                    System.out.println(occupant.toString());
            }
        }
    }

    /**
     * Take a filename and creates an arraylist of the toString methods we want tp print. Uses FileIO method
     * to with the ArrayList we created to write to the file
     * @param filename is the name of the file being exported
     */
    public void exportData(String filename){
        ArrayList<String> output = new ArrayList<>();
        for(String key : households.keySet()){
            //Add Household line
            Household household = households.get(key);
            output.add(household.toString());

            for(Occupant occupant : household.occupants){
                //Check to see if occupant is old enough
                if(occupant.isOldEnough())
                    output.add(occupant.toString());
            }
        }
        FileIO.exportData(output, filename);
    }

    //PRIVATE HELPER FUNCTIONS

    /**
     * Takes an input string and splits it into substrings and removes unwanted symbols and spaces. This covers
     * cases of invalid addresses but there could be a lot more formatting checks such as replacing key terms
     * such as Street->st Place->pl, etc.
     * @param inputString a single line from the input file in the form of a string
     * @return returns a string array of an input that is properly formatted
     */
    private String[] formatInput(String inputString){
        //Split String into substrings based on commas that aren't inside the double quotes
        String[] splitInput = inputString.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        //Remove the quotation marks, commas, periods, and put everything uppercase
        for(int i = 0; i < splitInput.length; ++i)
            splitInput[i] = splitInput[i].replaceAll("\"", "").replaceAll("[,.]", "").toUpperCase();

        //Make sure addresses have the correct number of spaces. Only between words, and only once space
        splitInput[2] = splitInput[2].trim().replaceAll(" +", " ");
        splitInput[3] = splitInput[3].trim().replaceAll(" +", " ");

        //Remove spaces, and unwanted characters from State, and Age
        splitInput[4] = splitInput[4].replaceAll("[ .,-]", "");
        splitInput[5] = splitInput[5].replaceAll("[ .,-]", "");

        return splitInput;
    }

    /**
     * Creates an Occupant object with data from a string that was previously parsed.
     * If there is a Household at the persons address, we add them to it. If not, then we create a new Household
     * and do the same
     * @param formattedInput an array of string fields that correspond to the attributes of an Occupant
     *                       like first name, last name, age, address, etc.
     */
    private void createOccupant(String[] formattedInput){
        Occupant occupant = new Occupant(formattedInput);//Create the occupant
        String key = occupant.getId(); //Get occupant key

        //Check to see if household exists
        if(households.containsKey(key))//If yes, then add them to the household
            households.get(key).addOccupant(occupant);
        else{//If no, then create a household and add them
            Household household = new Household(key, occupant.getReadableID(), occupant);
            households.put(key, household);
        }
    }
}
