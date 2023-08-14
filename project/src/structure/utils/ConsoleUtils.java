package structure.utils;

import java.util.Scanner;

public class ConsoleUtils {
    public static int getInputNumberValue(Scanner scanner, String error) {

        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(error);
            }
        }
    }

}
