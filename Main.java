import java.io.IOException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(" _____ _     _____          _____           ");
        Thread.sleep(200);
        System.out.println("/__   (_) __/__   \\__ _  __/__   \\___   ___ ");
        Thread.sleep(200);
        System.out.println("  / /\\/ |/ __|/ /\\/ _` |/ __|/ /\\/ _ \\ / _ \\");
        Thread.sleep(200);
        System.out.println(" / /  | | (__/ / | (_| | (__/ / | (_) |  __/");
        Thread.sleep(200);
        System.out.println(" \\/   |_|\\___\\/   \\__,_|\\___\\/   \\___/ \\___|");
        Thread.sleep(200);
        System.out.println("by nonamewastaken\n");
        Thread.sleep(200);

        System.out.println("What do you wnat to do? \n1) Start server \n2) Start client");
        Scanner scanner = new Scanner(System.in);

        String input;
        input = scanner.nextLine();

        while (true) {

            try {
                Double.parseDouble(input);
                if (Double.parseDouble(input) != 1 && Double.parseDouble(input) != 2) {

                    System.out.println("You must enter a valid number!");
                } else {
                    break;
                }

            } catch (NumberFormatException nfe) {
                System.out.println("You must enter a valid number!");
            }
        }
        if (Double.parseDouble(input) == 1) {

            new TicTacToeServer();
        } else if (Double.parseDouble(input) == 2) {

            new TicTacToeClient();

        }

        scanner.close();

    }

}