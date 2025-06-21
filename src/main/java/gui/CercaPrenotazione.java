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

                ArrayList<Prenotazione> lista = new ArrayList<>();

                if(controller.getUser().getClass().getSimpleName().equals("UtenteGenerico"))
                    lista = controller.cercaPrenotazioneUtenteGenerico(codiceVolo, dataVolo, orarioPartenza);

                else
                    lista = controller.cercaPrenotazioneAmministratore(codiceVolo, dataVolo, orarioPartenza);

                for (Prenotazione p : lista) {

                    String tipologia;
                    String localita;

                    if(p.getClass().getSimpleName().equals("VoloInArrivo")) {
                        tipologia = "in arrivo da";
                        localita = p.getVolo().getOrigine();
                    }
                    else {
                        tipologia = "in partenza per";
                        localita = p.getVolo().getDestinazione();
                    }

                    model.addRow(new Object[]{p.getPasseggero().getNome(),
                                                p.getPasseggero().getCognome(),
                                                p.getVolo().getCodice(),
                                                tipologia,
                                                localita,
                                                p.getVolo().getDataPartenza(),
                                                p.getVolo().getOrarioPartenza(),
                                                p.getVolo().getOrarioArrivo(),
                                                p.getVolo().getStato().toString().toUpperCase(),
                                                p.getClasseVolo().toString(),
                                                p.getPosto(),
                                                p.getBagagli().size()});
                                                p.getStatoPrenotazione().toString().toUpperCase();
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
