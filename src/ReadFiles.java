/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            CLass for the file reading operations.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
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

    /**
     * Returns the contents of the given file as String
     * @param currentFile given file
     * @return imported data as String
     */
    public static String readFileToString(File currentFile) {
        try {
            Scanner scanner = new Scanner(currentFile);
            // Read Data String
            String msg = scanner.nextLine();
            // Edit Data String
            msg = msg.replace("\\","");
            msg = msg.replace("}", "");
            msg = msg.replace("\n", "");
            scanner.close();
            // Return cleaned String
            return msg;
        } catch(FileNotFoundException e) {
            System.out.println("An error occurred during file reading.");
            e.printStackTrace();
        }
        return "";
    }

    public static ArrayList<String> listFilesFromFolder(File folder) {
        ArrayList<String> r = new ArrayList<>();
        for(File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if(fileEntry.isDirectory()) {
                listFilesFromFolder(fileEntry);
            } else {
                r.add(String.valueOf(fileEntry));
            }
        }
        return r;
    }
}