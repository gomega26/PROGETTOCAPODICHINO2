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
    private static JFrame frame;


    private JFrame frameChiamante;
    private Controller controller;

    public LogInPage(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("LogInPage");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);

       logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = textField1.getText();
                String password = passwordField1.getPassword().toString();

                if(controller.logIn(login, password)){

                    if (controller.getUser().getClass().getSimpleName().equals("Amministratore")) {

                        HomePageAmministratore frame3 = new HomePageAmministratore(frame, controller);
                        frame.setVisible(false);

                    } else if (controller.getUser().getClass().getSimpleName().equals("UtenteGenerico")) {

                        HomePageUtenteGenerico frame3 = new HomePageUtenteGenerico(frame, controller);
                        frame.setVisible(false);
                    }
                }

                else{

                    JOptionPane.showMessageDialog(logInButton, "Nome utente o password errati");
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}

