package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra grafica che consente all'utente di effettuare il check-in per una prenotazione.
 * <p>
 * L'interfaccia richiede l'inserimento del numero di prenotazione e, tramite il {@link Controller},
 * invia la richiesta di check-in. In base all'esito, mostra un messaggio di conferma o errore
 * e torna alla finestra precedente.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class CheckIn {
    private JPanel panel1;
    private JTextField textFieldNumeroPrenotazione;
    private JButton buttonCheckIn;
    private JButton button1;
    private JTable table1;
    private JFrame frame;


    /**
     * Costruisce e visualizza la finestra per effettuare il check-in.
     *
     * @param frameChiamante la finestra precedente da riattivare al termine
     * @param controller il controller per gestire la logica dell'operazione
     */
    public CheckIn(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Check-In");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(200, 200);
// Listener per il pulsante "Check-In":
        // Recupera il numero della prenotazione e invia la richiesta al controller.
        buttonCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int prenotazione = Integer.parseInt(textFieldNumeroPrenotazione.getText().trim());

                String messaggio= controller.checkIn(prenotazione);

                if(!messaggio.isEmpty()) {
                    JOptionPane.showMessageDialog(buttonCheckIn, messaggio);
                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }

                else
                    JOptionPane.showMessageDialog(buttonCheckIn, "Operazione fallita");
            }
        });
        // Listener per il pulsante "Indietro":
        // Chiude questa finestra e riattiva quella chiamante.
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
