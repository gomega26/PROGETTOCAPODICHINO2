package gui;// finito

import controller.Controller;
import model.ClasseVolo;
import model.Volo;
import model.VoloInPartenza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Finestra grafica che consente a un utente di prenotare un volo disponibile.
 * <p>
 * Viene mostrata una tabella con tutti i voli attivi e un form di compilazione per inserire
 * i dati del passeggero, il numero di posto e la classe di volo.
 * Al termine, la prenotazione viene confermata tramite il {@link Controller}.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class PrenotaVolo {
    private JPanel panel1;
    private JTextField textFieldCodiceVolo; // Codice del volo da prenotare
    private JTextField textFieldPosto; // Posto desiderato
    private JTextField textFieldClasseVolo; // (non utilizzato visivamente)
    private JTextField textFieldNome; // Nome passeggero
    private JTextField textFieldCognome; // Cognome passeggero
    private JTextField textFieldNumerodiTelefono; // Telefono di contatto
    private JTextField textFieldDocumento; // Numero documento passeggero
    private JTextField textFieldDatadiNascita; // Data di nascita
    private JComboBox<String> comboBox1; // Sesso del passeggero (M/F)
    private JButton button1; // Pulsante "Indietro"
    private JButton confermaPrenotazioneButton; // Conferma prenotazione
    private JTextField textField1; // Numero di bagagli
    private JComboBox comboBox2; // Classe di volo (Economy, Business, FirstClass)
    private JTable table1; // Tabella che mostra tutti i voli disponibili
    private JFrame frame;

    /**
     * Costruisce e mostra la finestra per prenotare un volo.
     *
     * @param frameChiamante finestra precedente da riattivare al termine
     * @param controller controller centrale per l'interazione logica con il sistema
     */
    public PrenotaVolo(JFrame frameChiamante, Controller controller) {

        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{" ", "M", "F"}));

        comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{" ", "Economy", "Business", "FirstClass"}));

        frame = new JFrame("Prenota Volo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(800, 600);



        //INIZIALIZZA TABELLA DEI VOLI

        String[] colonne = {"Volo", "compagnia aerea", "tipologia", "località", "data", "orario partenza", "orario arrivo", "durata", "stato", "R", "Gate"};

        DefaultTableModel model = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String tipologia;
        String localita;
        String numGate;

        ArrayList<Volo> voli=new ArrayList<>();

        controller.getVoli(voli);

        for(Volo v : voli){

            if(v.getClass().getSimpleName().equals("VoloInPartenza")) {

                tipologia = "in partenza per";
                localita = v.getDestinazione();
                numGate = String.valueOf(((VoloInPartenza) v).getNumGate());
                if (numGate.equals("0"))
                    numGate = "-";


                model.addRow(new Object[]{v.getCodice(), v.getCompagniaAerea(), tipologia, localita, v.getDataPartenza(), v.getOrarioPartenza(), v.getOrarioArrivo(), v.getDurata(), v.getStato().toString().toUpperCase(), v.getRitardo(), numGate});
            }
        }

        table1.setModel(model);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        confermaPrenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceVolo = textFieldCodiceVolo.getText().trim();

                String posto = textFieldPosto.getText().trim();
                String classe = comboBox2.getSelectedItem().toString();
                String nome = textFieldNome.getText().trim();
                String cognome = textFieldCognome.getText().trim();
                String telefono = textFieldNumerodiTelefono.getText().trim();
                String documento = textFieldDocumento.getText().trim();
                String sesso = (comboBox1.getSelectedItem() != null) ? comboBox1.getSelectedItem().toString() : "";
                String dataNascita = textFieldDatadiNascita.getText().trim();
                int NumBagagli = Integer.parseInt(textField1.getText());

                int esito = controller.prenotaVolo(codiceVolo, posto, classe, nome, cognome, telefono, documento, sesso.charAt(0), dataNascita, NumBagagli);

                if(esito ==-1)
                    JOptionPane.showMessageDialog(confermaPrenotazioneButton, "Il volo non è disponibile, spiacenti");

                else if(esito==0) {
                    JOptionPane.showMessageDialog(confermaPrenotazioneButton, "Inserire Volo esistente");
                }

                else if (esito==-2){
                    JOptionPane.showMessageDialog(confermaPrenotazioneButton, "Inserire data di nascita valida");
                }

                else {
                    JOptionPane.showMessageDialog(confermaPrenotazioneButton, "Prenotazione effettuata - Codice :" + esito);

                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }
            }
        });
    }
}