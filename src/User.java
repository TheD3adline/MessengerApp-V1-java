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
    private ArrayList<String> contactList;

    public User(String userName, String userPass, ArrayList<String> contactList) {
        this.userName = userName;
        this.userPass = userPass;
        this.contactList = contactList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public ArrayList<String> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<String> contactList) {
        this.contactList = contactList;
    }
}
