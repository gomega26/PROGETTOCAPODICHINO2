package gui;

import controller.Controller;
import model.Passeggero;
import model.Prenotazione;
import model.Volo;
import model.VoloInPartenza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Finestra grafica che consente a un amministratore di cercare passeggeri registrati nel sistema
 * in base a criteri come nome, cognome, numero documento e sesso.
 * <p>
 * I risultati vengono mostrati in una tabella che associa ogni passeggero alla sua prenotazione
 * e al relativo volo. L'interfaccia offre anche un pulsante per tornare alla finestra precedente.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class CercaPasseggero {

    private JPanel panel1;
    private JButton button1; // Pulsante "Indietro"
    private JTextField textField1; // Campo: nome
    private JTextField textField2; // Campo: cognome
    private JTextField textField3; // Campo: numero documento
    private JComboBox comboBox1; // Selezione sesso (M/F)
    private JTable table1; // Tabella risultati
    private JButton cercaButton; // Pulsante "Cerca"
    private static JFrame frame;


    /**
     * Costruisce l'interfaccia per cercare passeggeri nel sistema.
     *
     * @param frameChiamante finestra chiamante da riattivare dopo l'operazione
     * @param controller controller dell'applicazione per gestire la logica di ricerca
     */
    public CercaPasseggero(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("CercaPasseggero");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 600);
        // Inizializza comboBox per sesso
        comboBox1.addItem(" ");
        comboBox1.addItem("M");
        comboBox1.addItem("F");

        //TABELLA

        String[] colonne = {"Nome", "Cognome", "sesso", "Num. documento", "Num. telefono", "Data di nascita", "Prenotazione", "stato prenotazione"};

        DefaultTableModel model = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        ArrayList<Passeggero> passeggeri = new ArrayList<>();

        controller.getPasseggeri(prenotazioni, passeggeri);

        for(int i=0; i<passeggeri.size(); i++){

            try {
                Prenotazione p = prenotazioni.get(i);
                Passeggero pas = passeggeri.get(i);

                model.addRow(new Object[]{pas.getNome(), pas.getCognome(), pas.getSesso(), pas.getNumDocumento(), pas.getNumTelefono(), pas.getDataNascita(), p.getId(), p.getStato()});
            }
            catch(NullPointerException e){
                System.out.println("Errore: "+e.getMessage());
            }
        }

        table1.setModel(model);

        // Listener pulsante "Indietro":
        // Torna alla finestra precedente
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        // Listener per pulsante "Cerca":
        // Recupera i criteri inseriti e aggiorna la tabella con i risultati
        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = textField1.getText().trim();
                String cognome = textField2.getText().trim();
                String numeroDocumento = textField3.getText().trim();
                char sesso = comboBox1.getSelectedItem().toString().charAt(0);

                ArrayList<Prenotazione> prenotazioni= new ArrayList<>();
                ArrayList<Passeggero> passeggeri = new ArrayList<>();

                controller.cercaPasseggero(passeggeri, prenotazioni, nome, cognome, numeroDocumento, sesso);

                model.setRowCount(0);

                for(int i=0; i<passeggeri.size(); i++){

                    Prenotazione p = prenotazioni.get(i);
                    Passeggero pas = passeggeri.get(i);

                    model.addRow(new Object[]{pas.getNome(), pas.getCognome(), pas.getSesso(), pas.getNumDocumento(), pas.getNumTelefono(), pas.getDataNascita(), p.getId(), p.getStato()});
                }

                table1.setModel(model);
            }
        });
    }
}