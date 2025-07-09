package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaccia grafica per l'autenticazione di un utente.
 * <p>
 * Consente a un utente di eseguire il login fornendo le proprie credenziali. In caso di successo,
 * viene reindirizzato alla propria home page in base al ruolo (Amministratore o UtenteGenerico).
 * Offre inoltre la possibilità di accedere alla schermata di registrazione (sign-in)
 * o di tornare alla finestra precedente.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class LogInPage {
    private JPanel panel1;
    private JTextField textField1; // Campo per inserire il login (nome utente)
    private JPasswordField passwordField1; // Campo per inserire la password
    private JButton logInButton; // Pulsante per inviare le credenziali
    private JButton button1; // Pulsante per tornare alla schermata precedente
    private JButton signInButton; // Pulsante per accedere alla schermata di registrazione
    private static JFrame frame;

    /**
     * Costruisce e mostra la finestra di login dell'applicazione.
     *
     * @param frameChiamante finestra precedente da riattivare dopo il login o annullamento
     * @param controller controller principale per gestire l'autenticazione e il routing dell'interfaccia
     */
    public LogInPage(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("LogInPage");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);

        textField1.setText("");
        passwordField1.setText("");
// Listener per il pulsante "Login":
        // Verifica le credenziali e reindirizza l’utente alla home appropriata
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
        // Listener per il pulsante "Indietro":
        // Torna alla schermata precedente senza effettuare alcuna operazione
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
// Listener per il pulsante "Sign In":
        // Apre la schermata di registrazione per un nuovo utente
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SignIn frame3=new SignIn(frameChiamante, controller);
                frame.setVisible(false);
            }
        });
    }
}

