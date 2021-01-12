import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class StartMenu implements ActionListener {

    JButton startServerButton;
    JButton clientStarButton;
    JFrame frame;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startServerButton) {

           // startServerButton.setEnabled(false);
            //clientStarButton.setEnabled(false);
            //frame.dispose();

            try {
                new TicTacToeServer();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == clientStarButton) {
           // clientStarButton.setEnabled(false);
            //frame.dispose();
            try {
                new TicTacToeClient();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }

    StartMenu() throws IOException {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JLabel Title = new JLabel();
        Title.setText("TicTacToe");
        Title.setForeground(Color.white);
        Title.setFont(new Font("Arial", Font.BOLD, 40));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(Title, BorderLayout.NORTH);

        startServerButton = new JButton();
        startServerButton.setText("Start Server");
        startServerButton.setFont(new Font("Arial", Font.PLAIN, 40));
        startServerButton.setFocusable(false);
        startServerButton.setBackground(Color.darkGray);
        startServerButton.setForeground(Color.white);
        startServerButton.addActionListener(this);

        clientStarButton = new JButton();
        clientStarButton.setText("Start Client");
        clientStarButton.setFont(new Font("Arial", Font.PLAIN, 40));
        clientStarButton.setFocusable(false);
        clientStarButton.setBackground(Color.darkGray);
        clientStarButton.setForeground(Color.white);
        clientStarButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startServerButton);
        buttonPanel.add(clientStarButton);
        buttonPanel.setLayout(new GridLayout(2, 0));
        buttonPanel.setBackground(Color.darkGray);
        frame.add(buttonPanel);
        frame.setVisible(true);

        System.out.println("Ran through Start menu class");

    }

}