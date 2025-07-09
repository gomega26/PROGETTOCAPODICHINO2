package gui;

import controller.Controller;
import model.Volo;
import model.VoloInPartenza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Finestra grafica che consente a un amministratore di assegnare un gate a un volo in partenza.
 * <p>
 * Mostra una tabella dei voli disponibili e permette di specificare codice volo e numero di gate
 * tramite appositi campi di input. L'operazione viene trasmessa al {@link Controller} e notificata
 * tramite un messaggio a schermo.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class AssegnaGate {

    private JPanel panel1;
    private JTextField textField1; // Campo per inserire il codice del volo
    private JTextField textField2; // Campo per inserire il numero del gate
    private JButton button1; // Pulsante per tornare indietro
    private JButton assegnaButton; // Pulsante per confermare l'assegnazione
    private JTable table1; // Tabella che elenca i voli in partenza
    private static JFrame frame; // Finestra corrente

    /**
     * Costruisce e visualizza l'interfaccia per assegnare un gate a un volo.
     *
     * @param frameChiamante finestra precedente da riattivare
     * @param controller controller dell'applicazione per delegare l'operazione
     */
    public AssegnaGate(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("AssegnaGate");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 700);

        //INIZIALIZZA TABELLA

        String[] colonne = {"Volo", "compagnia aerea", "destinazione", "data", "orario partenza", "orario arrivo", "durata", "stato", "R", "Gate"};

        DefaultTableModel model = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Recupera i voli in partenza tramite il controller
        ArrayList<Volo> voli=new ArrayList<>();

        controller.getVoliGestiti(voli);
        // Popola la tabella con i dati dei voli
        for(Volo v : voli){

            if(v.getClass().getSimpleName().equals("InPartenza"))
                model.addRow(new Object[]{v.getCodice(), v.getCompagniaAerea(), v.getDestinazione(), v.getDataPartenza(), v.getOrarioPartenza(), v.getOrarioArrivo(), v.getDurata(), v.getStato().toString().toUpperCase(), v.getRitardo(), ((VoloInPartenza)v).getNumGate()});
        }

        table1.setModel(model);


        // Listener per il pulsante "Indietro":
        // Nasconde la finestra corrente e riporta visibile quella chiamante.

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
        // Listener per il pulsante "Assegna":
        // Ottiene i dati inseriti, chiama il controller e mostra il risultato all'utente.
        assegnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceVolo = textField1.getText();
                int numeroGate;

                numeroGate = Integer.parseInt(textField2.getText());

                boolean esito = controller.assegnaGate(codiceVolo, numeroGate);

                if(esito) {
                    JOptionPane.showMessageDialog(assegnaButton, "Gate assegnato!");

                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(assegnaButton, "Inserire codice di un volo esistente!");
            }
        });
    }
}
