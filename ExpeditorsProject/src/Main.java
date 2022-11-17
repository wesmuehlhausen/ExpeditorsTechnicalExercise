import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        //This runs the data that was given to me "data.txt" and outputs to "output_data.txt" and to the console
        HouseholdHandler handler = new HouseholdHandler();
        handler.getRawInputData("data.txt");//Read from file
        handler.parseData();//Format the data
        handler.sortHouseHolds();//Sort the data
        handler.prettyPrint();//Print data to console
        handler.exportData("data_output.txt");//Write data to file

        //This runs the data file that I created "more_data.txt" with different and writes to "more_data_output.txt"
        // but does not print to terminal
        handler = new HouseholdHandler();
        handler.getRawInputData("more_data.txt");
        handler.parseData();
        handler.sortHouseHolds();
        handler.exportData("more_data_output.txt");
    }
}

/*
TODO Exercise Summary:

        This Developer Design and Development exercise is used in the evaluation process for potential new hire candidates.
        Please approach this exercise as you would approach a design and development project.
        Please plan to allocate somewhere between 2-6 hours to complete the exercise.
        Any documentation or explanations about your approach and assumptions are helpful.  Please e-mail your completed exercise back to your recruiting contact at Expeditors when complete.

        Requirements:
        Write a program using Java or an object oriented language of your preference.  (Java is the primary dev language at Expeditors).
        Given the provided input data, print the expected output to the console or write to a text file.
        Please include the code that you wrote to solve the exercise.

        Input data:
        "Dave","Smith","123 main st.","seattle","wa","43"
        "Alice","Smith","123 Main St.","Seattle","WA","45"
        "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
        "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
        "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
        "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
        "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
        "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
        "Ian","Smith","123 main st ","Seattle","Wa","18"
        "Jane","Smith","123 Main St.","Seattle","WA","13"

        Expected output:
        Each household and number of occupants, followed by:
        Each First Name, Last Name, Address and Age sorted by Last Name then First Name where the occupant(s) is older than 18
*/