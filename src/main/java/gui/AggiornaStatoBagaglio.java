package gui;

import controller.Controller;
import model.Bagaglio;
import model.StatoBagaglio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra grafica che consente a un amministratore di aggiornare lo stato di un bagaglio selezionato.
 * <p>
 L'interfaccia fornisce un menu a tendina per scegliere il nuovo stato
 * ({@code Caricato}, {@code Ritirabile}, {@code Smarrito}) e un pulsante per confermare l'aggiornamento.
 * L'operazione viene inviata al {@link Controller} che si occupa della logica di business.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class AggiornaStatoBagaglio {

    private JPanel panel1;
    private JButton button1;// Pulsante "Annulla" o "Indietro"
    private JButton aggiornaButton;// Pulsante per confermare la modifica
    private JComboBox comboBox1;// Menu a tendina per selezionare lo stato
    private static JFrame frame;


    /**
     * Costruisce e mostra l'interfaccia per aggiornare lo stato di un bagaglio.
     *
     * @param frameChiamante finestra precedente da cui è stata aperta questa
     * @param controller istanza del controller per aggiornare il modello
     * @param codice codice identificativo del bagaglio da aggiornare
     */
    public AggiornaStatoBagaglio(JFrame frameChiamante, Controller controller, int codice) {
        frame = new JFrame("AggiornaStatoBagaglio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 450);

        comboBox1.addItem("Caricato");
        comboBox1.addItem("Ritirabile");
        comboBox1.addItem("Smarrito");
// Listener per il pulsante "Annulla" o "Indietro":
// Chiude la finestra corrente e riporta visibile quella chiamante.
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
// Listener per il pulsante "Aggiorna":
// Recupera lo stato selezionato dal menu a tendina, aggiorna il bagaglio tramite il controller
// e notifica l’utente del successo dell’operazione.
        aggiornaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String stato = comboBox1.getSelectedItem().toString();

                controller.aggiornaStatoBagaglio(codice, stato);

                JOptionPane.showMessageDialog(aggiornaButton, "Operazione completata con successo");

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}