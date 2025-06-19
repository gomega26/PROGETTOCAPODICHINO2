package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.Prenotazione;
import controller.Controller;

public class CercaPrenotazione {


    private JPanel panel1;
    private JTextField codiceVoloTextField;
    private JTextField dataVoloTextField;
    private JTextField orarioPartenzaTextField;
    private JButton buttonCercaPrenotazione;
    private JButton buttonIndietro;
    private JTable tablePrenotazioni;
    private JScrollPane scrollPane1;

    private JFrame frame;
    private DefaultTableModel model;

    public CercaPrenotazione(JFrame frameChiamante, Controller controller) {


        frame = new JFrame("Cerca Prenotazione");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //TABELLA

        model = new DefaultTableModel(new String[]{"Codice Volo", "Data", "Destinazione", "Classe", "Posto", "Bagaglio"}, 0);
        tablePrenotazioni.setModel(model);

        buttonIndietro.addActionListener(e -> {
            frame.dispose(); // Chiude la finestra attuale
            if (frameChiamante != null) {
                frameChiamante.setVisible(true); // Torna alla HomePageUtenteGenerico
            }
        });


        buttonCercaPrenotazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceVolo = codiceVoloTextField.getText().trim();
                String dataVolo = dataVoloTextField.getText().trim();
                String orarioPartenza = orarioPartenzaTextField.getText().trim();

                model.setRowCount(0);

                ArrayList<Prenotazione> lista = controller.cercaPrenotazioneAmministratore(codiceVolo, dataVolo, orarioPartenza);

                for (Prenotazione p : lista) {
                    model.addRow(new Object[]{p.getVolo().getCodice(), p.getVolo().getDataPartenza(), p.getVolo().getDestinazione(), p.getClasseVolo(), p.getPosto(), (p.getBagaglio() != null) ? "Sì" : "No"});
                }
            }
        });
    }
}
