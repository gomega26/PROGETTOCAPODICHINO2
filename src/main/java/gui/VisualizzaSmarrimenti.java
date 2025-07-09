package gui;

import controller.Controller;
import model.Bagaglio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Finestra grafica che consente agli amministratori di visualizzare
 * tutti i bagagli attualmente segnalati come smarriti nel sistema.
 * <p>
 * I dati vengono recuperati dal {@link Controller} e visualizzati in tabella.
 * È presente anche un pulsante per tornare alla finestra precedente.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class VisualizzaSmarrimenti {

    private JPanel panel1;
    private JButton button1; // Pulsante "Indietro"
    private JButton visualizzaButton; // (non utilizzato visivamente, potrebbe essere rimosso o valorizzato)
    private JTable table1; // Tabella per mostrare i codici dei bagagli smarriti
    private static JFrame frame;

    /**
     * Costruisce e visualizza la finestra con l’elenco dei bagagli smarriti.
     *
     * @param frameChiamante finestra chiamante da riattivare al termine
     * @param controller controller dell'applicazione per il recupero dei dati
     */
    public VisualizzaSmarrimenti(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("VisualizzaSmarrimenti");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);

        DefaultTableModel model;
        // Inizializza tabella con intestazione
        String[] colonne = {"Bagagli"};

        model= new DefaultTableModel(colonne, 0);
        // Recupera bagagli smarriti tramite il controller
        ArrayList<Bagaglio> bagagliSmarriti = controller.visualizzaBagagliSmarriti();
// Popola la tabella con i codici dei bagagli
        for (Bagaglio b : bagagliSmarriti) {
            model.addRow(new Object[]{b.getCodice()});
        }

        table1.setModel(model);
        // Listener per il pulsante "Indietro":
        // Chiude la finestra corrente e riattiva quella chiamante
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
