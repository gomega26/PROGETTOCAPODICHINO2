package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Passeggero;
import model.Prenotazione;
import controller.Controller;
import model.Volo;

public class CercaPrenotazione {


    private JPanel panel1;
    private JTextField codiceVoloTextField;
    private JTextField dataVoloTextField;
    private JTextField orarioPartenzaTextField;
    private JButton buttonCercaPrenotazione;
    private JButton button1;
    private JTable tablePrenotazioni;
    private JScrollPane scrollPane1;

    private JFrame frame;
    private DefaultTableModel model;

    public CercaPrenotazione(JFrame frameChiamante, Controller controller) {


        frame = new JFrame("Cerca Prenotazione");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //TABELLA

        model = new DefaultTableModel(new String[]{"Nome", "Cognome", "Codice Volo", "Tipologia", "Località", "Data", "Orario partenza", "Orario arrivo","Stato volo", "Classe", "Posto", "Bagagli", "Stato prenotazione"}, 0);
        tablePrenotazioni.setModel(model);

        buttonCercaPrenotazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Entrato in cerca prenotaaione");

                String codiceVolo = codiceVoloTextField.getText().trim();
                String dataVolo = dataVoloTextField.getText().trim();
                String orarioPartenza = orarioPartenzaTextField.getText().trim();

                model.setRowCount(0);

                ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<>();
                ArrayList<Volo> listaVoli = new ArrayList<>();
                ArrayList<Passeggero> listaPasseggeri = new ArrayList<>();

                controller.cercaPrenotazione(listaPrenotazioni, listaVoli, listaPasseggeri, codiceVolo, dataVolo, orarioPartenza);

                model.setRowCount(0);

                String tipologia;
                String localita;

                for (int i = 0; i < listaPasseggeri.size(); i++) {
                    Passeggero p = listaPasseggeri.get(i);
                    Prenotazione pr = listaPrenotazioni.get(i);
                    Volo v = listaVoli.get(i);

                    if (v.getClass().getSimpleName().equals("VoloInPartenza")) {

                        tipologia = "in partenza per";
                        localita = v.getDestinazione();
                    } else {

                        tipologia = "in arrivo da";
                        localita = v.getOrigine();
                    }

                    Object[] riga = {
                            p.getNome(),
                            p.getCognome(),
                            v.getCodice(),
                            tipologia,
                            localita,
                            v.getDataPartenza(),
                            v.getOrarioPartenza(),
                            v.getOrarioArrivo(),
                            v.getStato().toString().toUpperCase(),
                            pr.getClasseVolo().toString(),
                            pr.getPosto(),
                            pr.getNumBagagli(),
                            pr.getStatoPrenotazione().toString().toUpperCase()
                    };

                    model.addRow(riga);
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
