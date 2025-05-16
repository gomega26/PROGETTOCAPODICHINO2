package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {

    private JButton logInButton;
    private JPanel panel1;

    private static JFrame frame;
    private Controller controller;

    public HomePage() {

        controller = new Controller();


        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LogInPage frame2 = new LogInPage(frame, controller);

                frame.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("HomePage");
        frame.setContentPane(new HomePage().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
