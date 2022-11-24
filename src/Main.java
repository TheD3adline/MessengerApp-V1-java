/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            Messenger App with simple Caesar message encryption.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final File userData = new File("config\\config.txt");
    static final File cacheFolder = new File("cache\\");
    static ArrayList<String> rawUserList = new ArrayList<>();
    static ArrayList<String> userNamesList = new ArrayList<>();
    static ArrayList<String> userPassList = new ArrayList<>();
    static ArrayList<String> userRawContactsList = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static User activeUser = null;
    static User activeRecipient = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {



        getUserData();
        loadUserData();


        UserInterface.printMainUI();
        UserInterface.welcomeNote();
        while(true) {
            switch(UserInput.getMenuInput()) {
                    case 1:
                        System.out.println("PLACEHOLDER"); break;
                    case 2:
                        loginMenu(); break;
                    case 3:
                        UserInterface.exitNote(); System.exit(0); break;
                    default:
                        System.out.println("UI Error!");
            }
        }
    }

    public static void loginMenu() {
        UserInterface.printLoginUI();
        while(true) {
            UserInterface.loginPrompt();
            String uName = UserInput.getStringInput();
            if(uName.charAt(0) == '3' && uName.length() == 1)
                return;
            else {
                UserInterface.passPrompt();
                String uPass = UserInput.getStringInput();
                if(uPass.charAt(0) == '3' && uPass.length() == 1) {
                    UserInterface.printMainUI();
                    return;
                } else {
                    for(User user : users) {
                        if(user.getUserName().equals(uName) && user.getUserPass().equals(uPass)) {
                            activeUser = user;
                            UserInterface.correctUserData();
                            userMenu();
                            return;
                        }
                    }
                    UserInterface.incorrectUserData();
                }
            }
        }
    }

    public static void userMenu() {
        UserInterface.printUserUI();
        while(true) {
            switch(UserInput.getMenuInput()) {
                case 1:
                    newMSGMenu(); break;
                case 2:
                    ArrayList<String> inboxList = UserInterface.selectMSG();
                    StringBuilder path = new StringBuilder(inboxList.get(UserInput.getContactSelection(inboxList)));
                    for(int i = 0; i < 4; i++) {
                        path.deleteCharAt(0);
                    }
                    readMessage("cache\\" + path);
                    break;
                case 3:
                    UserInterface.printMainUI();
                    activeUser = null;
                    return;
                default:
                    System.out.println("UI Error!");
            }
        }
    }

    public static void newMSGMenu() {
        UserInterface.printNewMSGUI();
        while(true) {
            switch(UserInput.getMenuInput()) {
                case 1:
                    System.out.println("Create New Contact PLACEHOLDER"); break;
                case 2:
                    UserInterface.selectRecipient();
                    String selector = activeUser.getContactList().get(UserInput.getContactSelection(activeUser.getContactList()));
                    for(int i = 0; i < users.size(); i++) {
                        if(selector.equals(users.get(i).getUserName())) {
                            activeRecipient = users.get(i);
                            writeMessage();
                            break;
                        }
                        if(i == (users.size() - 1)) {
                            System.out.println("Error, Contact could not be found!");
                        }
                    }
                    activeRecipient = null;
                    break;
                case 3:
                    UserInterface.printUserUI(); return;
                default:
                    System.out.println("UI Error!");
            }
        }
    }

    /**
     * Calls ReadFiles.getFileInfo() and ReadFiles.saveToContainer()
     * to load the raw user data saved in config\config.txt into the program.
     */
    public static void getUserData() {
        if(ReadFiles.getFileInfo(userData))
            ReadFiles.saveToContainer(userData, rawUserList);
    }

    /**
     * Splices the user data stored in rawUserList into userNamesList, userPassList, and userRawContactsList.
     */
    public static void loadUserData() {
        for(int i = 0; i < rawUserList.size(); i++) {
            spliceUserData(userNamesList, i);
            spliceUserData(userPassList, i);

            userRawContactsList.add(rawUserList.get(i));

            users.add(new User(userNamesList.get(i), userPassList.get(i), makeContactList(userRawContactsList.get(i))));
        }


    }

    /**
     * The sub-method to splice rawUserList.
     * @param list given list to add spliced data.
     * @param index given working index from for loop in higher-method, loadUserData().
     */
    public static void spliceUserData(ArrayList<String> list, int index) {
        list.add(rawUserList.get(index).substring(0, rawUserList.get(index).indexOf(':')));
        rawUserList.set(index, rawUserList.get(index).substring(rawUserList.get(index).indexOf(':') + 1));
    }

    /**
     * Splices the contact data of the users from the given Strings.
     * @param contacts given String.
     * @return ArrayList that stores contact data for each user individually.
     */
    public static ArrayList<String> makeContactList(String contacts) {
        ArrayList<String> data = new ArrayList<>();
        while(contacts.charAt(0) != ';') {
            data.add(contacts.substring(0, contacts.indexOf(',')));
            contacts = contacts.substring(contacts.indexOf(',') + 1);
        }
        return data;
    }

    /**
     * Instantiates a new message with all the relevant data, sender, recipient, and a query for the actual message.
     */
    public static void writeMessage() {
        System.out.print("Enter your message: ");
        new Message(activeUser, activeRecipient, sc.nextLine());
    }

    /**
     * Instantiates message to be read from specified path.
     * @param path the specified path to where the message file is stored.
     */
    public static void readMessage(String path) {
        System.out.println("Select message to read: ");
        new Message(path, 4);
    }
}