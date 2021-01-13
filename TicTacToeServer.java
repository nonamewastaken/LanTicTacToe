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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeServer implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Button pressed");

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerTurn == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setText("X");
                        pr.println(i);
                        pr.flush();
                        checkWin();
                        playerTurn = false;
                    }
                }
            }
        }

    }

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel playArea = new JPanel();
    JLabel title = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean playerTurn;
    boolean playerHasWon = false;
    // net

    Socket s;
    InputStreamReader in;
    BufferedReader bf;
    PrintWriter pr;
    ServerSocket ss;

    TicTacToeServer() throws IOException, UnknownHostException {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setLayout(new BorderLayout());
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setTitle("you are x");

        title.setBackground(Color.darkGray);
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setText("Tic-Tac-Toe Server");
        title.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(title);

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
        Runnable runnable = new Runnable() {
            public void run() {
                // net
                try {
                    ss = new ServerSocket(4999);
                    s = ss.accept();
                    in = new InputStreamReader(s.getInputStream());
                    bf = new BufferedReader(in);
                    pr = new PrintWriter(s.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    firstTurn();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();

    }

    public void firstTurn() throws IOException {

        String str = bf.readLine();
        System.out.println("Client: " + str);

        pr.println("generating random number");
        pr.flush();

        int random_num = random.nextInt(2);

        pr.println(random_num);
        pr.flush();

        System.out.println("generated number " + random_num);

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
                title.setText("X turn");
                for (int i = 0; i < 9; i++) {
                    buttons[i].setEnabled(true);
                }
            } else if (playerTurn == false) {
                title.setText("O turn");
                for (int i = 0; i < 9; i++) {
                    buttons[i].setEnabled(false);
                }
                String str = bf.readLine();
                System.out.println(Integer.parseInt(str));
                buttons[Integer.parseInt(str)].setText("O");
                checkWin();
                playerTurn = true;
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

        title.setText("X Wins!");

        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        JOptionPane.showMessageDialog(null, "Restart?", "Game over!", JOptionPane.OK_OPTION);

        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(Color.darkGray);
            buttons[i].setEnabled(true);
        }

    }

    public void oWin(int a, int b, int c) {

        playerHasWon = true;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        title.setText("O Wins!");

        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        JOptionPane.showMessageDialog(null, "Restart?", "Game over!", JOptionPane.OK_OPTION);

        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(Color.darkGray);
            buttons[i].setEnabled(true);
        }

    }
}
