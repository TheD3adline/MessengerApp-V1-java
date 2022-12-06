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
    static ArrayList<String> inboxPaths = new ArrayList<>();
    static ArrayList<String> rawInboxMSGs = new ArrayList<>();
    static ArrayList<Message> inbox = new ArrayList<>();
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
            switch(UserInput.getMenuInput()) {          //main method includes looped main menu
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

    /**
     * UI menu for login function.
     */
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

    /**
     * UI menu for the user, can go to newMSGMenu, Inbox, or logout to previous login menu.
     */
    public static void userMenu() {
        loadInbox();
        UserInterface.printUserUI();
        while(true) {
            switch(UserInput.getMenuInput()) {
                case 1:
                    newMSGMenu(); break;
                case 2:
                    UserInterface.printInboxMenu();
                    UserInterface.printInbox(inbox);
                    readMessage();
                    UserInterface.printUserUI();
                    break;
                case 3:
                    UserInterface.printMainUI();
                    clearInbox();
                    activeUser = null;
                    return;
                default:
                    System.out.println("UI Error!");
            }
        }
    }

    /**
     * UI menu for writing a new message, can select contact data from user's stored contacts.
     */
    public static void newMSGMenu() {
        UserInterface.printNewMSGUI();
        while(true) {
            switch(UserInput.getMenuInput()) {
                case 1:
                    System.out.println("Create New Contact PLACEHOLDER"); break;
                case 2:
                    UserInterface.selectRecipient();
                    activeRecipient = activeUser.getContactList().get(UserInput.getListSelection(activeUser.getContactList().size()));
                    writeMessage();
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

            users.add(new User(userNamesList.get(i), userPassList.get(i)));
        }
        for(int i = 0; i < users.size(); i++) {
            users.get(i).setContactList(loadContactData(i));
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
     * @return ArrayList that stores contact data for each user individually.
     */
    public static ArrayList<User> loadContactData(int index) {
        ArrayList<User> contacts = new ArrayList<>();
        while(userRawContactsList.get(index).charAt(0) != ';') {
            String temp = userRawContactsList.get(index).substring(0, userRawContactsList.get(index).indexOf(','));
            userRawContactsList.set(index, userRawContactsList.get(index).substring(userRawContactsList.get(index).indexOf(',') + 1));
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getUserName().equals(temp)) {
                    contacts.add(users.get(i));
                }
            }
        }
        return contacts;
    }

    /**
     * Reads files from inbox cache folder and loads their data into several lists to be processed into actual Message objects
     */
    public static void loadInbox() {
        inboxPaths = ReadFiles.listFilesFromFolder(cacheFolder);
        for(int i = 0; i < inboxPaths.size(); i++) {
            File currFile = new File(inboxPaths.get(i));
            if(ReadFiles.getFileInfo(currFile)) {
                rawInboxMSGs.add(ReadFiles.readFileToString(currFile));

                String message = rawInboxMSGs.get(i).substring(0, rawInboxMSGs.get(i).indexOf("["));
                rawInboxMSGs.set(i, rawInboxMSGs.get(i).substring(rawInboxMSGs.get(i).indexOf("[") + 1));

                String sender = rawInboxMSGs.get(i).substring(0, rawInboxMSGs.get(i).indexOf("]"));
                rawInboxMSGs.set(i, rawInboxMSGs.get(i).substring(rawInboxMSGs.get(i).indexOf("]") + 2));

                String recipient = rawInboxMSGs.get(i);
                recipient = recipient.replace("]", "");

                for(int j = 0; j < users.size(); j++) {
                    if(users.get(j).getUserName().equals(sender)) {
                        for(int k = 0; k < users.size(); k++) {
                            if(users.get(k).getUserName().equals(recipient)) {
                                inbox.add(new Message(users.get(j), users.get(k), message, inboxPaths.get(i)));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Clears all lists that are involved with reading the message cache folder.
     */
    public static void clearInbox() {
        inboxPaths.clear();
        rawInboxMSGs.clear();
        inbox.clear();
    }

    /**
     * Instantiates a new message with all the relevant data, sender, recipient, a query for the actual message and the
     * desired offset for the cipher.
     */
    public static void writeMessage() {
        System.out.print("Enter your message: ");
        new Message(activeUser, activeRecipient, sc.nextLine(), UserInput.getCipherOffset());
    }

    /**
     * Asks the user to select stored message from inbox, then asks for the cipher offset to decrypt the message.
     */
    public static void readMessage() {
        System.out.println("\nPlease enter the Cipher offset (1 - 26) ");
        System.out.println("\nThe deciphered message is: \n" +
                Cipher.decipherMessage(inbox.get(UserInput.getListSelection(inbox.size())).getMessage(), UserInput.getCipherOffset()));
    }
}