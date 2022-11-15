import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFiles {

    /**
     * Takes data from given String and writes it to file specified by path
     * @param path the path to the file
     * @param msg the given String
     * @return boolean true if message was successfully written to file
     */
    public static boolean writeDataToFile(String path, String msg) {
        try {
            FileWriter msgWriter = new FileWriter(path);
            msgWriter.write(msg);
            msgWriter.flush();
            msgWriter.close();
            return true;
        } catch(IOException e) {
            System.out.println("An error occurred during file writing.");
            e.printStackTrace();
            return false;
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

    /**
     * Creates the output file at the specified
     * @param path , and
     * @return boolean true if file created or
     * false when it already exists.
     */
    public static boolean createFile(String path) {
        try {
            File message = new File(path);
            if(message.createNewFile()) {
                return true;
            } else {
                System.out.println("An Error occurred during file creation.");
                return false;
            }
        } catch(IOException e) {
            System.out.println("IOException occurred during file creation.");
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteFile(String path) {
        try {
            File f = new File(path);
            f.delete();

        } catch(Exception e) {
            System.out.println("Exception occurred during file deletion.");
        }
    }
}