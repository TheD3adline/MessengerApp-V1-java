/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            Methods for the different types of user inputs with validity verification and loops.
 */

import java.util.Scanner;
public class UserInput {

    static Scanner sc = new Scanner(System.in);

    /**
     * looped input validation for integer data types and menu points
     * @return integer from verified input
     */
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

    /**
     * simple String input
     * @return String from input
     */
    public static String getStringInput() {
        return sc.next();
    }

    /**
     * Very similar to getMenuInput, but uses given list size as constraints and returns int subtracted by 1 for list indices
     * @param listSize given int for size of list
     * @return int subtracted by 1 for program control via indices
     */
    public static int getListSelection(int listSize) {
        int num;
        do {
            System.out.print("\nPlease select Entry! (Input 1, 2, 3, 4, etc.): ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid input!");
                System.out.print("\nPlease select Entry! (Input 1, 2, 3, 4, etc.): ");
            }
            num = sc.nextInt();
            if(num < 1 || num > listSize)
                System.out.println("Invalid input!");
        } while(num < 1 || num > listSize);
        return num - 1;
    }

    /**
     * input validation to be used with the cipher, constraint is the size of the alphabet
     * @return integer for cipher
     */
    public static int getCipherOffset() {
        int num;
        do {
            System.out.print("\nPlease enter offset for the cipher (1 - 26): ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid input!");
                System.out.print("\nPlease enter offset for the cipher (1 - 26): ");
            }
            num = sc.nextInt();
            if(num < 1 || num > 26)
                System.out.println("Invalid input!");
        } while(num < 1 || num > 26);
        return num;
    }
}