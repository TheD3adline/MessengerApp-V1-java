import java.util.Scanner;
public class UserInput {

    static Scanner sc = new Scanner(System.in);

    public static int getMenuInput(int menuPoints) {
        int num;
        StringBuilder listInputPoints = new StringBuilder();
        for(int i = 0; i < menuPoints; i++) {
            listInputPoints.append(" ").append(i + 1).append(",");
        }
        do {
            System.out.print("\nPlease select menu point! (Input" + listInputPoints + "): ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid input!");
                System.out.print("\nPlease select menu point! (Input" + listInputPoints + "): ");
            }
            num = sc.nextInt();
            if(num < 1 || num > menuPoints)
                System.out.println("Invalid input!");
        } while(num < 1 || num > menuPoints);
        return num;
    }

    public static String getUserDataInput() {
        return sc.next();
    }
}