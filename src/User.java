/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            Class to store all user information.
 */

import java.util.ArrayList;

public class User {
    private String userName;
    private String userPass;
    private ArrayList<User> contactList;

    /**
     * constructor for user instantiation
     * @param userName given username
     * @param userPass given password
     */
    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public ArrayList<User> getContactList() {
        return contactList;
    }

    /**
     * sets the contact list for the individual user
     * @param contactList given contact list
     */
    public void setContactList(ArrayList<User> contactList) {
        this.contactList = contactList;
    }
}
