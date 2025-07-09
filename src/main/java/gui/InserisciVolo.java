package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra grafica per l’inserimento manuale di un nuovo volo nel sistema.
 * <p>
 * L’amministratore può specificare i dati di partenza, arrivo, compagnia, codice,
 * stato e altre informazioni. In caso di volo in ritardo, si abilita il campo per
 * specificare i minuti di ritardo.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class InserisciVolo {

    private JPanel panel1;
    private JButton button1; // Pulsante "Indietro"
    private JTextField ritardoTextField; // Minuti di ritardo (attivo solo se selezionato "InRitardo")
    private JTextField dataTextField; // Data partenza del volo
    private JTextField orarioArrivoTextField; // Orario di arrivo previsto
    private JTextField destinazioneTextField; // Aeroporto di destinazione
    private JTextField origineTextField; // Aeroporto di origine
    private JComboBox comboBox1; // Stato del volo (es. Programmato, Decollato, ecc.)
    private JTextField durataTextField; // Durata complessiva del volo
    private JTextField orarioPartenzaTextField; // Orario previsto di partenza
    private JButton aggiungiButton; // Pulsante per confermare inserimento
    private JTextField compagniaAereaTextField; // Nome della compagnia aerea
    private JTextField codiceVoloTextField; // Codice identificativo del volo
    private JLabel ritardoLable; // Etichetta associata al campo "ritardo"
    private static JFrame frame;

    /**
     * Costruisce e mostra la finestra per inserire un nuovo volo.
     *
     * @param frameChiamante finestra precedente da riattivare dopo l'operazione
     * @param controller controller centralizzato per gestire la logica
     */
    public InserisciVolo(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("InserisciVolo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 800);

        ritardoTextField.setEditable(false);

        comboBox1.setModel(new DefaultComboBoxModel(new Object[]{"Programmato","Decollato", "InRitardo","Atterrato","Cancellato"}));


// Listener per il pulsante "Indietro":
        // Chiude questa finestra e torna a quella chiamante
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
// Listener per il pulsante "Aggiungi":
        // Recupera i dati inseriti e tenta di inserire il volo tramite il controller
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codice = codiceVoloTextField.getText();
                String origine = origineTextField.getText();
                String destinazione = destinazioneTextField.getText();
                String compagnia = compagniaAereaTextField.getText();
                String partenza = orarioPartenzaTextField.getText();
                String arrivo = orarioArrivoTextField.getText();
                String durata = durataTextField.getText();
                String stato = comboBox1.getSelectedItem().toString();
                String dataPartenza = dataTextField.getText();
                int ritardoInt;

                if(!ritardoTextField.isEditable())
                    ritardoInt=0;

                else
                    ritardoInt= Integer.parseInt(ritardoTextField.getText());

                boolean esito = controller.inserisciVolo(compagnia, codice, origine, destinazione, partenza, arrivo, dataPartenza, durata, ritardoInt, stato);

                if(esito) {
                    JOptionPane.showMessageDialog(aggiungiButton, "Volo aggiunto con successo!");
                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }

                else
                    JOptionPane.showMessageDialog(aggiungiButton, "Operazione fallita");
            }
        });
        // Listener per la comboBox degli stati:
        // Abilita il campo "ritardo" solo se lo stato selezionato è "InRitardo"
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBox1.getSelectedItem().toString().equals("InRitardo"))
                    ritardoTextField.setEditable(true);
                else
                    ritardoTextField.setEditable(false);
            }
        });
    }
}
