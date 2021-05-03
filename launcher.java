import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class launcher implements ActionListener {

    static TicTacToeClient client;
    static TicTacToeServer server;
    JButton serverBtn;
    JButton clientBtn;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if(e.getSource() == serverBtn){
            startServer();
        }else if(e.getSource() == clientBtn){
            startClient();
        }
    }

    public launcher(){

        serverBtn = new JButton();
        serverBtn.setLocation(0,0);
        serverBtn.setSize(100, 100);
        serverBtn.addActionListener(this);

        clientBtn = new JButton();
        clientBtn.setLocation(200,0);
        clientBtn.setSize(100, 100);
        clientBtn.addActionListener(this);

        JFrame frame = new JFrame();
        frame.setSize(420, 420);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setTitle("Tic-Tac-Toe");
        frame.setLayout(null);
        frame.add(serverBtn);
        frame.add(clientBtn);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static Thread clientThread;
    static Thread serverThread;

    public static void startServer() {

        server = new TicTacToeServer();
        serverThread = new Thread(server);
        serverThread.start();

    }

    interface killClientCallback {
        default void killClientCallback() {
            clientThread.interrupt();
        };
    }

    interface killServerCallback {
        default void killServerCallback(){
            serverThread.interrupt();
        };
    }

    public static void startClient() {

        client = new TicTacToeClient();
        clientThread = new Thread(client);
        clientThread.start();

    }
}