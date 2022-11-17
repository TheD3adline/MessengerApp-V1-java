public class UserInterface {

    private static final String mainUIMenu = "Main Menu";
    private static final String registerUIComp = "1.) Register User (PH)";
    private static final String loginUIComp = "2.) Login";
    private static final String endUIComp = "3.) Exit Program";
    private static final String loginUI = "Login Menu";
    private static final String backToMenu = "3.) Back to Main Menu";
    private static final String userUIMenu = "User Menu";
    private static final String writeMSGPoint = "1.) New Message";
    private static final String inboxPoint = "2.) Inbox";
    private static final String logoutPoint = "3.) Logout";
    private static final String writeMSGUI = "New Message Menu";
    private static final String newContactPoint = "1.) Enter new Contact";
    private static final String selectRecipientPoint = "2.) Select Recipient";
    private static final String backToUserMenu = "3.) Back to User Menu";
    private static final String backToUserMenuX = "X.) Back to User Menu";
    private static final String inboxUI = "Inbox Menu";

    public static void printMainUI() {
        System.out.println("-".repeat(40));
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + mainUIMenu + " ".repeat((34 - mainUIMenu.length())) + "|");
        System.out.println("|" + " ".repeat(4) + registerUIComp + " ".repeat((34 - registerUIComp.length())) + "|");
        System.out.println("|" + " ".repeat(4) + loginUIComp + " ".repeat((34 - loginUIComp.length())) + "|");
        System.out.println("|" + " ".repeat(4) + endUIComp + " ".repeat((34 - endUIComp.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.print("-".repeat(40));
    }

    public static void printLoginUI() {
        System.out.println("-".repeat(40));
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + loginUI + " ".repeat((34 - loginUI.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + backToMenu + " ".repeat((34 - backToMenu.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.print("-".repeat(40));
    }

    public static void printUserUI() {
        System.out.println("-".repeat(40));
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + userUIMenu + " ".repeat((34 - userUIMenu.length())) + "|");
        System.out.println("|" + " ".repeat(4) + writeMSGPoint + " ".repeat((34 - writeMSGPoint.length())) + "|");
        System.out.println("|" + " ".repeat(4) + inboxPoint + " ".repeat((34 - inboxPoint.length())) + "|");
        System.out.println("|" + " ".repeat(4) + logoutPoint + " ".repeat((34 - logoutPoint.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.print("-".repeat(40));
    }

    public static void printNewMSGUI() {
        System.out.println("-".repeat(40));
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + writeMSGUI + " ".repeat((34 - writeMSGUI.length())) + "|");
        System.out.println("|" + " ".repeat(4) + newContactPoint + " ".repeat((34 - newContactPoint.length())) + "|");
        System.out.println("|" + " ".repeat(4) + selectRecipientPoint + " ".repeat((34 - selectRecipientPoint.length())) + "|");
        System.out.println("|" + " ".repeat(4) + backToUserMenu + " ".repeat((34 - backToUserMenu.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.print("-".repeat(40));
    }

    public static void printInboxMenu() {
        System.out.println("-".repeat(40));
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + inboxUI + " ".repeat((34 - inboxUI.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + backToUserMenuX + " ".repeat((34 - backToUserMenuX.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.print("-".repeat(40));
    }


    public static void welcomeNote() {
        System.out.println("\nWelcome to the Julius Caesar Messenger App (or short JCM)!");
    }

    public static void exitNote() {
        System.out.print("\nExiting program.");
    }

    public static void loginPrompt() {
        System.out.print("\nEnter Username (or enter 3 to go back): ");
    }

    public static void passPrompt() {
        System.out.print("\nEnter Password (or enter 3 to go back): ");
    }

    public static void incorrectUserData() {
        System.out.print("\nUsername or Password Incorrect!");
    }

    public static void correctUserData() {
        System.out.print("\nLogin Successful. Welcome back " + Main.activeUser.getUserName() + " !\n");
    }

    public static void newMSG() {
        System.out.print("\nYou have new messages in your inbox!");
    }

    public static void noMSG() {
        System.out.print("\nYou have no new messages.");
    }

    public static void enterNewContact() {
        System.out.print("\nEnter new Contact: ");
    }

    public static void selectRecipient() {
        //method to print contact list
    }

    public static void enterMSG() {
        System.out.print("\nEnter your message: ");
    }

    public static void sentMSG() {
        System.out.print("\n Your message was sent!");
        //to Message.recipient.getUserName()
    }

    public static void selectMSG() {
        //method to print all unread messages
    }
}