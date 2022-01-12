package HolidayMaker1;

import java.util.Scanner;

public class Extras {

    public static Scanner scanner = new Scanner(System.in);

    public static void threadSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pause () {
        System.out.println("Press any key to go back to main menu");
        String menuOption = scanner.nextLine();
    }


    public static void emptyScreen() {
        System.out.println("\n".repeat(50));

    }

}
