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

                ArrayList<Prenotazione> prenotazioni= new ArrayList<>();
                ArrayList<Volo> voli=new ArrayList<>();
                ArrayList<Passeggero> passeggeri = new ArrayList<>();

                controller.cercaPasseggero(passeggeri, prenotazioni, voli, nome, cognome, numeroDocumento, sesso);

                model.setRowCount(0);

                String tipologia;
                String localita;

                for (int i = 0; i < passeggeri.size(); i++) {
                    Passeggero p = passeggeri.get(i);
                    Prenotazione pr = prenotazioni.get(i);
                    Volo v = voli.get(i);

                    if(v.getClass().getSimpleName().equals("VoloInPartenza")){

                        tipologia = "in partenza per";
                        localita = v.getDestinazione();
                    }

                    else {

                        tipologia = "in arrivo da";
                        localita = v.getOrigine();
                    }


                    Object[] riga = {
                            p.getNome(), p.getCognome(), p.getNumDocumento(), p.getSesso(),
                            pr.getId(), pr.getStatoPrenotazione(),
                            v.getCodice(), tipologia, localita, v.getDataPartenza()
                    };

                    model.addRow(riga);
                }
            }
        });
    }
}