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

/**
 * Finestra grafica per la ricerca di prenotazioni in base a codice volo, data e orario di partenza.
 * <p>
 * L'interfaccia consente a un operatore o amministratore di visualizzare i passeggeri associati
 * a un volo specifico e i dettagli relativi a ciascuna prenotazione. I risultati vengono mostrati in una tabella.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito

 */
public class CercaPrenotazione {


    private JPanel panel1;
    private JTextField codiceVoloTextField; // Campo per inserire il codice del volo
    private JTextField dataVoloTextField; // Campo per la data del volo
    private JTextField orarioPartenzaTextField; // Campo per l’orario di partenza
    private JButton buttonCercaPrenotazione; // Pulsante "Cerca"
    private JButton button1; // Pulsante "Indietro"
    private JTable tablePrenotazioni; // Tabella che mostra i risultati
    private JScrollPane scrollPane1;

    private JFrame frame;
    private DefaultTableModel model; // Modello dati della tabella

    /**
     * Costruisce e visualizza l'interfaccia per cercare prenotazioni legate a un volo specifico.
     *
     * @param frameChiamante finestra chiamante da riattivare al termine
     * @param controller controller per delegare la logica di business
     */
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
// Listener per il pulsante "Cerca":
        // Recupera codice volo, data e orario; esegue la ricerca e aggiorna la tabella.
        buttonCercaPrenotazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceVolo = codiceVoloTextField.getText().trim();
                String dataVolo = dataVoloTextField.getText().trim();
                String orarioPartenza = orarioPartenzaTextField.getText().trim();

                model.setRowCount(0);

                ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<>();
                ArrayList<Volo> listaVoli = new ArrayList<>();
                ArrayList<Passeggero> listaPasseggeri = new ArrayList<>();

                controller.cercaPrenotazione(listaPrenotazioni, listaVoli, listaPasseggeri, codiceVolo, dataVolo, orarioPartenza);

                model.setRowCount(0);// Pulisce righe precedenti

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
        // Listener per il pulsante "Indietro":
        // Chiude la finestra corrente e riattiva quella chiamante.
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
