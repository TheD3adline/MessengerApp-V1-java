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
    static File userData = new File("config\\config.txt");
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
            String uName = UserInput.getUserDataInput();
            if(uName.charAt(0) == '3' && uName.length() == 1)
                return;
            else {
                UserInterface.passPrompt();
                String uPass = UserInput.getUserDataInput();
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
                    System.out.println("2"); break;
                case 3:
                    UserInterface.printMainUI(); return;
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
                    System.out.println("1"); break;
                case 2:
                    System.out.println("2"); break;
                case 3:
                    UserInterface.printUserUI(); return;
                default:
                    System.out.println("UI Error!");
            }
        }
    }

    public static void getUserData() {
        if(ReadFiles.getFileInfo(userData))
            ReadFiles.saveToContainer(userData, rawUserList);
    }

    public static void loadUserData() {
        for(int i = 0; i < rawUserList.size(); i++) {
            spliceUserData(userNamesList, i);
            spliceUserData(userPassList, i);

            userRawContactsList.add(rawUserList.get(i));

            users.add(new User(userNamesList.get(i), userPassList.get(i), makeContactList(userRawContactsList.get(i))));
        }


    }

    public static void spliceUserData(ArrayList<String> list, int index) {
        list.add(rawUserList.get(index).substring(0, rawUserList.get(index).indexOf(':')));
        rawUserList.set(index, rawUserList.get(index).substring(rawUserList.get(index).indexOf(':') + 1));
    }

    public static ArrayList<String> makeContactList(String contacts) {
        ArrayList<String> data = new ArrayList<>();
        while(contacts.charAt(0) != ';') {
            data.add(contacts.substring(0, contacts.indexOf(',')));
            contacts = contacts.substring(contacts.indexOf(',') + 1);
        }
        return data;
    }

    public static void writeMessage() {
        System.out.print("Enter your message: ");
        new Message(activeUser, activeRecipient, sc.nextLine());
    }

    public static void readMessage(String path) {
        System.out.println("Select message to read: ");
        new Message(path);

    }
}