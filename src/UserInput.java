import java.util.Scanner;
public class UserInput {

    static Scanner sc = new Scanner(System.in);

    public static int getMenuInput() {
        int num;
        do {
            System.out.print("\nPlease select menu point! (Input 1, 2, or 3): ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid input!");
                System.out.print("\nPlease select menu point! (Input 1, 2, or 3): ");
            }
            num = sc.nextInt();
            if(num < 1 || num > 3)
                System.out.println("Invalid input!");
        } while(num < 1 || num > 3);
        return num;
    }

    public static String getUserDataInput() {
        return sc.next();
    }
}