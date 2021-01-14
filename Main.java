import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {

    static TicTacToeClient client;
    static TicTacToeServer server;
    static String ip;

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

        askForAction();

    }

    public static void askForAction() {

        System.out.println("What do you wnat to do? \n1) Start server \n2) Start client \n3) exit");
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
            startServer();
        } else if (Double.parseDouble(input) == 2) {

            // net
            ip = JOptionPane.showInputDialog(null, "Please specify which IP adress you wnat to connect to");
            startClient();
        }

        scanner.close();
    }

    public static void startServer() {

        try {
            server = new TicTacToeServer();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void startClient() {

        try {
            client = new TicTacToeClient(ip);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}