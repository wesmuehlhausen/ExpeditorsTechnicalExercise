import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Holds methods for reading the input data given to us and for writing output data to a file.
 */
public class FileIO {

    /**
     * Loads a file from the "resources" directory. Once loaded, we scan each line in the file and insert
     * each line as a string into an ArrayList
     * @param filename is the name of the file within the resources directory that we want to read
     * @return an ArrayList with all the lines of the file in the form of an ArrayList of strings
     */
    public static ArrayList<String> loadFile(String filename){
        ArrayList<String> rawDataLines = new ArrayList<>();
        try{
            File inputFile = new File("resources/" + filename); //File object
            Scanner fileReader = new Scanner(inputFile);

            //For every line in the file, scan and add to the ArrayList
            while(fileReader.hasNext()){
                rawDataLines.add(fileReader.nextLine());
            }
        }
        catch(Exception e){
            System.out.println("Error reading input file. Program terminated");
            e.printStackTrace(); //Optional Stacktrace print to console
            System.exit(1);
        }
        return rawDataLines;
    }

    /**
     * This function takes in an array of strings. Each string is a line that will be put into the file.
     * It also takes in the name of the file that we want to write to.
     * @param data arraylist of data to be written to the file
     * @param filename name of file
     */
    public static void exportData(ArrayList<String> data, String filename){
        try {
            FileWriter outputData = new FileWriter("resources/" + filename);

            //For string in the ArrayList, write to the file
            for(String line : data)
                outputData.write(line + "\n");
            outputData.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred while exporting data to output.txt");
            e.printStackTrace();
        }
    }
}
