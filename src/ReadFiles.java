import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFiles {
    /**
     * gets file info of the given file.
     * true if file exists, false if not.
     * @return true/false
     */
    public static boolean getFileInfo(File currentFile) {
        if(currentFile.exists())
        {
            return true;
        }
        System.out.println("The file does not exist.");
        return false;
    }

    /**
     * Saves the contents of the given file to the given ArrayList.
     * @param currentFile  given file
     * @param currentList  given ArrayList
     */
    public static void saveToContainer(File currentFile, ArrayList<String> currentList) {
        try {
            Scanner scanner = new Scanner(currentFile);
            while (scanner.hasNextLine()) {
                // Read Data String
                String data = scanner.nextLine();
                // Edit Data String
                data = data.replace("\\","");
                data = data.replace("}", "");
                data = data.replace("\n", "");
                // Add cleaned Data String to ArrayList
                currentList.add(data);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred during file reading.");
            e.printStackTrace();
        }
    }
}
