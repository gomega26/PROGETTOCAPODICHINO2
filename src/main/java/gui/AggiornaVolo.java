package gui;

import controller.Controller;
import model.StatoVolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra grafica che consente a un amministratore di aggiornare i dati di un volo esistente.
 * <p>
 * Permette la modifica di parametri come luogo, orari, durata, ritardo e stato del volo. I campi
 * sono compilati manualmente e validati tramite il {@link Controller}. La modifica avviene
 * premendo un pulsante di conferma, mentre un secondo pulsante permette di tornare alla finestra precedente.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class AggiornaVolo {

    private JPanel panel1;
    private JTextField codicevolotext; // Campo per inserire il codice identificativo del volo
    private JComboBox<String> comboBox1; // Stato del volo (Programmato, In ritardo, ecc.)
    private JTextField luogotext; // Aeroporto di origine o destinazione
    private JTextField duratatext; // Durata del volo (es. "1h45m")
    private JTextField orariopartenzatext; // Orario di partenza previsto
    private JTextField orarioarrivotext; // Orario di arrivo previsto
    private JButton button1; // Pulsante per tornare indietro senza modifiche
    private JButton modificaButton; // Pulsante per confermare l’aggiornamento
    private JTextField ritardotext; // Ritardo in minuti
    private JTextField datatext; // Data del volo
    private JComboBox comboBox2; // Tipologia di volo: Origine o Destinazione
    private static JFrame frame; // Finestra corrente
    private static JFrame frameChiamante; // Finestra precedente da riattivare
    private Controller controller; // Controller dell'app per la logica di aggiornamento

    /**
     * Costruisce e mostra l'interfaccia per aggiornare le informazioni di un volo.
     *
     * @param frameChiamante finestra precedente da riattivare al termine
     * @param controller controller applicativo per gestire la logica di aggiornamento
     */
    public AggiornaVolo(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("AggiornaVolo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        this.controller = controller; // Inizializzazione del controller

        // Popola la comboBox con stringhe invece di StatoVolo
        comboBox1.addItem("Programmato");
        comboBox1.addItem("Decollato");
        comboBox1.addItem("In ritardo");
        comboBox1.addItem("Atterrato");
        comboBox1.addItem("Cancellato");

        comboBox2.addItem("Origine");
        comboBox2.addItem("Destinazione");
    // Listener per il pulsante "Modifica":
        // Preleva i dati inseriti e invia la richiesta di aggiornamento al controller.
        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codiceVolo = codicevolotext.getText();

                String orarioPartenza = orariopartenzatext.getText();
                String orarioArrivo = orarioarrivotext.getText();
                String dataPartenza = datatext.getText();
                String durata = duratatext.getText();
                int ritardo = Integer.parseInt(ritardotext.getText());
                String statoDelVolo = comboBox1.getSelectedItem().toString();
                String luogo = luogotext.getText();

                boolean esito = controller.aggiornaVolo(codiceVolo, luogo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);

                if(esito){

                    JOptionPane.showMessageDialog(modificaButton, "Volo aggiornato con successo!");

                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }

                else
                    JOptionPane.showMessageDialog(modificaButton, "Inserire codice di un volo esistente");
            }
        });
// Listener per il pulsante "Indietro":
        // Nasconde la finestra corrente e riattiva quella chiamante.
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}