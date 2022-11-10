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

        UserInterface.printMainUI();

        UserInterface.printLoginUI();

        UserInterface.printUserUI();

        UserInterface.printNewMSGUI();

        UserInterface.printInboxMenu();

        getUserData();
        loadUserData();

        for(int i = 0; i < userNamesList.size(); i++)
            System.out.println(users.get(i).getUserName() + " " + users.get(i).getUserPass() + " " + users.get(i).getContactList());

        System.out.println(users.get(0).getContactList().get(0));
        System.out.println(users.get(3).getContactList().get(0));

        System.out.println(Cipher.cipherMessage(sc.next(), 4));

        System.out.println(Cipher.decipherMessage(sc.next(), 4));
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
        new Message(activeUser, activeRecipient, sc.next());
    }
}