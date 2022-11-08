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
}
