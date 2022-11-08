public class UserInterface {

    private static final String registerUIComp = "1.) Register User (PH)";
    private static final String loginUIComp = "2.) Login";
    private static final String endUIComp = "3.) Exit Program";

    public static void printUI() {
        System.out.println("-".repeat(40));
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("|" + " ".repeat(4) + registerUIComp + " ".repeat((34 - registerUIComp.length())) + "|");
        System.out.println("|" + " ".repeat(4) + loginUIComp + " ".repeat((34 - loginUIComp.length())) + "|");
        System.out.println("|" + " ".repeat(4) + endUIComp + " ".repeat((34 - endUIComp.length())) + "|");
        System.out.println("|" + " ".repeat(38) + "|");
        System.out.println("-".repeat(40));
    }
}
