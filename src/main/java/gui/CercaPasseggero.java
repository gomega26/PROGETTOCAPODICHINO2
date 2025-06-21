package gui;

import controller.Controller;
import model.Passeggero;
import model.Prenotazione;
import model.VoloInPartenza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CercaPasseggero {

    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton cercaButton;
    private static JFrame frame;
    private DefaultTableModel model;

    public CercaPasseggero(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("CercaPasseggero");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);

        comboBox1.addItem(" ");
        comboBox1.addItem("M");
        comboBox1.addItem("F");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        String[] colonne = {"Nome", "Cognome", "Numero documento", "Sesso", "Prenotazione", "Stato prenotazione", "Volo", "Tipologia", "Localita", "Data"};

        model = new DefaultTableModel(colonne, 0);

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = textField1.getText().trim();
                String cognome = textField2.getText().trim();
                String numeroDocumento = textField3.getText().trim();
                char sesso = comboBox1.getSelectedItem().toString().charAt(0);

                ArrayList<Prenotazione> risultati = controller.cercaPasseggero(nome, cognome, numeroDocumento, sesso);

                model.setRowCount(0);

                String tipologia;
                String localita;

                for (Prenotazione p : risultati) {

                    if(p.getVolo().getClass().getSimpleName().equals("VoloInPartenza")){

                        tipologia = "in partenza per";
                        localita = p.getVolo().getDestinazione();
                    }

                    else {

                        tipologia = "in arrivo da";
                        localita = p.getVolo().getOrigine();
                    }

                    model.addRow(new Object[]{p.getPasseggero().getNome(),
                                                p.getPasseggero().getCognome(),
                                                p.getPasseggero().getNumDocumento(),
                                                p.getPasseggero().getSesso(),
                                                p.getId(),
                                                p.getStatoPrenotazione(),
                                                p.getVolo().getCodice(),
                                                tipologia,
                                                localita,
                                                p.getVolo().getDataPartenza()});
                }
            }
        });
    }
}