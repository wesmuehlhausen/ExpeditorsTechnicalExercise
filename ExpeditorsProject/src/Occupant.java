import java.util.Comparator;

/**
 * An object that represents an occupant. Uses the comparator interface for sorting Occupants
 */
public class Occupant implements Comparator<Occupant> {

    //Private member variables
    private String firstName, lastName, address, city, state, age, id;

    //Public functions

    /**
     * Empty constructor
     */
    public Occupant() {}

    /**
     * Constructor that takes in formatted input. If the string isn't the correct size or another error occurs,
     * then we can print the stack trace. It doesn't really need a try/catch but this could be a problematic part of the
     * code since we need specific sized string array
     * @param formattedInput includes all the corresponding fields to populate the private member variables
     */
    public Occupant(String[] formattedInput){
        try{
            this.firstName = formattedInput[0];
            this.lastName = formattedInput[1];
            this.address = formattedInput[2];
            this.city = formattedInput[3];
            this.state = formattedInput[4];
            this.age = formattedInput[5];
            this.id = generateID(); //Calls private helper function
        }
        catch(Exception e){
            System.out.println("Error creating Occupant");
            if(formattedInput.length != 6)
                System.out.println("Invalid array size");
            e.printStackTrace();
        }
    }

    /**
     * Implements the compare method from the Comparator Interface. This way we can compare Occupant Objects
     * First based off of the last name field, then based on the first name field, then the age field
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return returns a t/f based on if the comparison
     */
    public int compare(Occupant o1, Occupant o2) {
        //Sort by Last name, then first name, and age just because we can
        int lastNameCompare = o1.lastName.compareTo(o2.lastName);
        if(lastNameCompare == 0) { //If they are the same
            int firstNameCompare = o1.firstName.compareTo(o2.firstName);
            if(firstNameCompare == 0)
                return o1.age.compareTo(o2.age);//Compare by age, only if first and last names are the same
            else
                return firstNameCompare;//Compare by first name, only if last names are the same
        }
        return lastNameCompare; //Compare by last name
    }

    /**
     * Returns a formatted string of the Occupant member variables
     */
    @Override
    public String toString() {
        return "-->" + firstName + " " + lastName + ", " + address + " [Age: " + age + "]";
    }

    /**
     * Checks to see if the age of this Occupant is over the age of 18
     */
    public boolean isOldEnough(){
        if(Integer.parseInt(age) > 18)//Converts String age into integer to compare
            return true;
        else
            return  false;
    }

    //Public getter and setter methods
    public String getReadableID(){
        return address + " " + city + " " + state;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Generates ID that we can associate with the corresponding household
    private String generateID(){
        String output = address + city + state;
        return output.replaceAll("[ .,-]", ""); //Removes empty spaces
    }
}
