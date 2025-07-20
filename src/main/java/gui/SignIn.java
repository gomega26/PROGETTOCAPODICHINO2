package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra grafica per la registrazione (sign-in) di un nuovo utente.
 * <p>
 * Permette la creazione di un account come {@code Utente generico} o {@code Amministratore}.
 * Nel caso di amministratore, viene richiesto un codice di sicurezza per validare l’autenticazione.
 * I dati inseriti vengono gestiti dal {@link Controller}.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class SignIn {
    private JPanel panel1;
    private JButton button1; // Pulsante "Indietro"
    private JTextField LogintextField1; // Campo per il nome utente
    private JTextField EmailtextField2; // Campo per l'indirizzo email
    private JPasswordField passwordField1; // Campo per la password
    private JComboBox comboBox1; // Selezione del tipo di utente
    private JPasswordField codiceSicurezzapasswordField3; // Campo per il codice di sicurezza (amministratori)
    private JButton signInButton; // Pulsante per confermare la registrazione
    private JLabel codiceDiSIcurezzaLable; // Etichetta per il codice di sicurezza
    private static JFrame frame;

    /**
     * Costruisce e mostra l'interfaccia per la registrazione di un nuovo utente.
     *
     * @param frameChiamante finestra da riattivare al termine o in caso di annullamento
     * @param controller controller per la gestione della logica applicativa
     */
    public SignIn(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("SignIn");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 600);

        comboBox1.setModel(new DefaultComboBoxModel(new Object[]{" ", "Amministratore", "Utente generico"}));

        codiceDiSIcurezzaLable.setVisible(false);
        codiceSicurezzapasswordField3.setVisible(false);
        // Listener per "Indietro": torna alla schermata precedente
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
// Listener per "Sign In":
        // In base alla tipologia selezionata, registra un amministratore o utente generico
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

                    if(!esito)

                        JOptionPane.showMessageDialog(signInButton, "Codice di sicurezza non valido");

                    else{

                        JOptionPane.showMessageDialog(signInButton, "Sign in effettuato!");

                        frame.setVisible(false);
                        frameChiamante.setVisible(true);
                    }

                }

                else{

                    controller.inizializzaUtenteGenerico(login, password, email);

                    JOptionPane.showMessageDialog(signInButton, "Sign in effettuato!");

                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }
            }
        });
        // Listener per comboBox:
        // Mostra il campo codice sicurezza se l'utente seleziona "Amministratore"
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
