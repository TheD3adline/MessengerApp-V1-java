import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFiles {

    /**
     * Takes data from given ArrayList and writes it to file specified by path
     * @param path the path to the file
     * @param data the given ArrayList
     */
    public static void writeDataToFile(String path, ArrayList<String> data) {
        try {
            FileWriter txtWriter = new FileWriter(path);
            for(String str : data) {
                txtWriter.write(str);
            }
            txtWriter.flush();
            txtWriter.close();
        } catch(IOException e) {
            System.out.println("An error occurred during file writing.");
            e.printStackTrace();
        }
    }

    /**
     * Deletes all content from file specified by path
     * @param path to the file
     * @return true when successfully deleted file content, else false
     */
    public static boolean deleteFileContent(String path) {
        try {
            new FileWriter(path, false).close();
            return true;
        } catch(IOException e) {
            System.out.println("An error occurred during file content deletion.");
            e.printStackTrace();
        }
        return false;
    }
}