package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPage {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton button1;
    private JButton signInButton;
    private static JFrame frame;

    public LogInPage(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("LogInPage");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);

        textField1.setText("");
        passwordField1.setText("");

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = textField1.getText();
                String password = new String(passwordField1.getPassword());

                if(controller.logIn(login, password)){

                    if (controller.getUser().getClass().getSimpleName().equals("Amministratore")) {

                        HomePageAmministratore frame3 = new HomePageAmministratore(frame, controller);
                        frame.setVisible(false);

                    } else {

                        HomePageUtenteGenerico frame3 = new HomePageUtenteGenerico(frame, controller);
                        frame.setVisible(false);
                    }
                }

                else{

                    JOptionPane.showMessageDialog(logInButton, "Nome utente o password errati");
                }

                textField1.setText("");
                passwordField1.setText("");
            }
        });

        //BOTTONE PER TORNARE INDIETRO

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SignIn frame3=new SignIn(frameChiamante, controller);
                frame.setVisible(false);
            }
        });
    }
}

