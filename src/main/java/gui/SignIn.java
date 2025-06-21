package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn {
    private JPanel panel1;
    private JButton button1;
    private JTextField LogintextField1;
    private JTextField EmailtextField2;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    private JPasswordField codiceSicurezzapasswordField3;
    private JButton signInButton;
    private JLabel codiceDiSIcurezzaLable;
    private static JFrame frame;

    public SignIn(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("SignIn");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);

        comboBox1.setModel(new DefaultComboBoxModel(new Object[]{" ", "Amministratore", "Utente generico"}));

        codiceDiSIcurezzaLable.setVisible(false);
        codiceSicurezzapasswordField3.setVisible(false);

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

                String login = LogintextField1.getText();
                String email = EmailtextField2.getText();
                String password = new String(passwordField1.getPassword());
                String classe = comboBox1.getSelectedItem().toString();

                if(classe.equals("Amministratore")) {

                    int codice = Integer.parseInt(new String(codiceSicurezzapasswordField3.getPassword()));

                    boolean esito = controller.inizializzaAmministratore(codice, login, password, email);

                    if(esito)
                        JOptionPane.showMessageDialog(signInButton, "Sign In effettuato");
                    else
                        JOptionPane.showMessageDialog(signInButton, "Codice di sicurezza non valido");
                }

                else
                    controller.inizializzaUtenteGenerico(login, password, email);

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBox1.getSelectedItem().toString().equals("Amministratore")){

                    codiceSicurezzapasswordField3.setVisible(true);
                    codiceDiSIcurezzaLable.setVisible(true);
                }

                else{

                    codiceSicurezzapasswordField3.setVisible(false);
                    codiceDiSIcurezzaLable.setVisible(false);
                }
            }
        });
    }
}
