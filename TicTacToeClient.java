import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToeClient implements ActionListener, Runnable {

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Button pressed");

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerTurn == false) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setText("O");
                        pr.println(i);
                        pr.flush();
                        checkWin();
                        playerTurn = true;
                    }
                }
            }
        }

    }

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel playArea = new JPanel();
    JLabel Title = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean playerTurn;
    boolean playerHasWon = false;
    // net
    PrintWriter pr;
    Socket s;
    InputStreamReader in;
    BufferedReader bf;

    TicTacToeClient() throws UnknownHostException, IOException {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setLayout(new BorderLayout());
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setTitle("you are o");

        Title.setBackground(Color.darkGray);
        Title.setForeground(Color.white);
        Title.setFont(new Font("Arial", Font.BOLD, 50));
        Title.setHorizontalAlignment(JLabel.CENTER);
        Title.setText("Tic-Tac-Toe Client");
        Title.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(Title);

        playArea.setLayout(new GridLayout(3, 3));
        playArea.setBackground(Color.darkGray);
        frame.add(playArea);
        frame.setVisible(true);

        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton();
            playArea.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 120));
            buttons[i].setBackground(Color.darkGray);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setEnabled(false);

        }

        // net
        s = new Socket("localhost", 4999);
        pr = new PrintWriter(s.getOutputStream());
        in = new InputStreamReader(s.getInputStream());
        bf = new BufferedReader(in);


        firstTurn();

    }

    public void firstTurn() throws IOException {

        pr.println("GUI and network set up, waiting for first turn randomizer");
        pr.flush();

        String str = bf.readLine();
        System.out.println("Server: " + str);

        int random_num = Integer.parseInt(bf.readLine());
        System.out.println("Caught number " + random_num);

        if (random_num == 0) {
            playerTurn = true;
        } else if (random_num == 1) {
            playerTurn = false;
        }

        game();
    }

    public void game() throws IOException {

        while (true) {
            if (playerHasWon == true) {
                break;
            }
            if (playerTurn == true) {
                Title.setText("X turn");
                for (int i = 0; i < 9; i++) {
                    buttons[i].setEnabled(false);
                }
                String str = bf.readLine();
                System.out.println(Integer.parseInt(str));
                buttons[Integer.parseInt(str)].setText("X");
                checkWin();
                playerTurn = false;
            } else if (playerTurn == false) {
                Title.setText("O turn");
                for (int i = 0; i < 9; i++) {
                    buttons[i].setEnabled(true);
                }
            }
        }
    }

    public void checkWin() {

        // check X win conditions
        if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
            xWin(0, 1, 2);
        } else if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
            xWin(3, 4, 5);
        } else if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
            xWin(6, 7, 8);
        } else if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
            xWin(0, 3, 6);
        } else if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
            xWin(1, 4, 7);
        } else if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
            xWin(2, 5, 8);
        } else if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
            xWin(0, 4, 8);
        } else if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
            xWin(2, 4, 6);
        }
        // check O win conditions
        else if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
            oWin(0, 1, 2);
        } else if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
            oWin(3, 4, 5);
        } else if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
            oWin(6, 7, 8);
        } else if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
            oWin(0, 3, 6);
        } else if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
            oWin(1, 4, 7);
        } else if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
            oWin(2, 5, 8);
        } else if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
            oWin(0, 4, 8);
        } else if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
            oWin(2, 4, 6);
        } else {
        }

    }

    public void xWin(int a, int b, int c) {

        playerHasWon = true;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        Title.setText("X Wins!");

        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

    }

    public void oWin(int a, int b, int c) {

        playerHasWon = true;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        Title.setText("O Wins!");

        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
