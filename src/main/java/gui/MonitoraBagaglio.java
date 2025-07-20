package gui;

import model.Bagaglio;
import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra grafica che consente di monitorare lo stato di un bagaglio.
 * <p>
 * Gli utenti possono inserire un codice identificativo per visualizzare lo stato attuale del bagaglio.
 * Se l’utente è un {@code Amministratore}, può anche aggiornare manualmente lo stato del bagaglio
 * tramite un pulsante dedicato.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class MonitoraBagaglio {
    private JTextField textField1; // Campo per inserire il codice del bagaglio
    private JPanel panel1;
    private JButton button1; // Pulsante per tornare alla schermata precedente
    private JButton monitoraBagaglioButton; // Avvia la ricerca del bagaglio
    private JTextArea textArea1; // Serve per mostrare lo stato del bagaglio
    private JButton modificaButton; // Pulsante per modificare lo stato (solo per amministratori)
    private JLabel lable1;
    private JLabel lable2;
    private Bagaglio b = null;
    private JFrame frame;
    private JComboBox comboBox1;

    /**
     * Costruisce e mostra la finestra per il monitoraggio dei bagagli.
     *
     * @param frameChiamante finestra precedente da riattivare
     * @param controller controller per l’interazione con il modello
     */
    public MonitoraBagaglio(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Monitora Bagaglio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(400, 400);

        comboBox1.addItem(" ");
        comboBox1.addItem("Caricato");
        comboBox1.addItem("Ritirabile");
        comboBox1.addItem("Smarrito");

        modificaButton.setEnabled(false);

        // Visibile solo per amministratori
        comboBox1.setVisible(false);
        lable1.setVisible(false);
        lable2.setVisible(false);
        textArea1.setEditable(false);


        //DISPONIBILE SOLO SE USER È UN AMMINISTRATORE
// Abilita il pulsante "Modifica" se l'utente è un amministratore
        if(controller.getUser().getClass().getSimpleName().equals("Amministratore")){

            comboBox1.setVisible(true);
            comboBox1.setEnabled(false);
            lable1.setVisible(true);
            lable2.setVisible(true);
        }

        //disponibile solo se lo user è un utente generico- abilita il bottone per segnalare uno smrrimento

        else
            modificaButton.setText("Segnala smarrimento");


// Listener per il pulsante "Monitora":
        // Recupera lo stato del bagaglio tramite il controller
        monitoraBagaglioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceText = textField1.getText().trim();
                int codice = Integer.parseInt(codiceText);

                if(controller.getUser().getClass().getSimpleName().equals("UtenteGenerico"))
                    b = controller.monitoraBagaglioUtenteGenerico(codice);

                else
                    b = controller.monitoraBagaglioAmministratore(codice);


                if(b==null)
                    JOptionPane.showMessageDialog(monitoraBagaglioButton, "Bagaglio non trovato");

                else {
                    textArea1.setText("Codice bagaglio: " + b.getCodice() + " - " + b.getStato());
                    modificaButton.setEnabled(true);
                    comboBox1.setEnabled(true);
                }
            }
        });

// Listener per il pulsante "Indietro":
        // Torna alla finestra chiamante
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        // Listener per il pulsante "Modifica":
        // Apre la finestra per aggiornare lo stato del bagaglio selezionato
        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Segnala lo smarrimento per utente generico

                if(controller.getUser().getClass().getSimpleName().equals("UtenteGenerico")){

                    boolean esito = controller.segnalaSmarrimento(b.getCodice());

                    if(esito) {
                        JOptionPane.showMessageDialog(modificaButton, "Smarrimento segnalato");
                        frame.setVisible(false);
                        frameChiamante.setVisible(true);
                    }
                    else
                        JOptionPane.showMessageDialog(modificaButton, "Inserire codice bagaglio valido!");
                }


                //Modifica lo statodel bagaglio per amministratore

                else {

                    String stato = comboBox1.getSelectedItem().toString();

                    boolean esito = controller.aggiornaStatoBagaglio(b.getCodice(), stato);

                    if(esito) {
                        JOptionPane.showMessageDialog(modificaButton, "Operazione completata con successo");

                        frame.setVisible(false);
                        frameChiamante.setVisible(true);
                    }

                    else
                        JOptionPane.showMessageDialog(modificaButton, "Non è possibile modificare questo bagaglio");
                }
            }
        });
    }
}