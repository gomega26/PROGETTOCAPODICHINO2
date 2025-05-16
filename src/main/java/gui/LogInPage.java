package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPage {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private static JFrame frame;


    private JFrame frameChiamante;
    private Controller controller;

    public LogInPage(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("LogInPage");
        frame.setContentPane(new LogInPage(frameChiamante, controller).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(controller.utente.logIn(textField1.getText(), passwordField1.getPassword())) {

                    if (controller.utente.getClass().toSimpleName().equals("Amministratore")) {

                        HomePageAmministratore frame3 = new HomePageAmministratore(frame, controller);
                        frame.setVisible(false);
                    } else if (controller.utente.getClass().toSimpleName().equals("UtenteGenerico")) {

                        HomePageUtenteGenerico frame3 = new HomePageUtenteGenerico(frame, controller);
                        frame.setVisible(false);
                    }
                }

                else{

                    JOptionPane.showMessageDialog(logInButton, "Nome utente o password errati");
                }
            }
        });
    }
}
