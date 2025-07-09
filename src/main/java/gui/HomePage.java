package gui;

import controller.Controller;
import model.StatoVolo;
import model.Volo;
import model.VoloInArrivo;
import model.VoloInPartenza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Finestra principale dell'applicazione per la consultazione dei voli.
 * <p>
 * Mostra una tabella riepilogativa con tutti i voli attivi (sia in arrivo che in partenza)
 * e permette di filtrare i risultati tramite parametri come compagnia aerea, codice volo,
 * destinazione e data. Dalla home è anche possibile accedere all'interfaccia utente o amministratore.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class HomePage {

    private JButton homePageButton; // Pulsante per accedere al login o pagina profilo
    private JPanel panel1;
    private JTable table1; // Tabella che mostra i voli
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1; // Codice volo
    private JTextField textField2; // Compagnia aerea
    private JComboBox comboBox1; // Tipologia volo: partenza/arrivo
    private JTextField textField3; // Località
    private JTextField textField4; // Data partenza
    private JButton cercaButton; // Pulsante per eseguire ricerca voli
    private JLabel lable4;
    private JLabel lable5;
    private static JFrame frame;
    private Controller controller;


    private String tipologia;
    private String localita;
    private String numGate;

    /**
     * Costruisce la finestra home e inizializza tutti i componenti dell'interfaccia.
     * Popola automaticamente la tabella con i voli disponibili tramite il {@link Controller}.
     */
    public HomePage() {

        controller = new Controller();

        comboBox1.setModel(new DefaultComboBoxModel(new Object[]{" ", "VoloInPartenza", "VoloInArrivo"}));

        //INIZIALIZZA TABELLA

        String[] colonne = {"Volo", "compagnia aerea", "tipologia", "località", "data", "orario partenza", "orario arrivo", "durata", "stato", "R", "Gate"};

        DefaultTableModel model = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        ArrayList<Volo> voli=new ArrayList<>();

        controller.getVoli(voli);

        for(Volo v : voli){

            if(v.getClass().getSimpleName().equals("VoloInPartenza")){

                tipologia = "in partenza per";
                localita = v.getDestinazione();
                numGate = String.valueOf(((VoloInPartenza)v).getNumGate());
                if(numGate.equals("0"))
                    numGate="-";
            }

            else {

                tipologia = "in arrivo da";
                localita = v.getOrigine();
                numGate= "";
            }

            model.addRow(new Object[]{v.getCodice(), v.getCompagniaAerea(), tipologia, localita, v.getDataPartenza(), v.getOrarioPartenza(), v.getOrarioArrivo(), v.getDurata(), v.getStato().toString().toUpperCase(), v.getRitardo(), numGate});
        }

        table1.setModel(model);
        // Listener per il pulsante "homePageButton":
        // Se non autenticato, apre il login. Altrimenti reindirizza alla homepage utente o admin.
        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(controller.getUser()==null) {

                    LogInPage frame2 = new LogInPage(frame, controller);
                }

                else{

                    if (controller.getUser().getClass().getSimpleName().equals("Amministratore")) {

                        HomePageAmministratore frame3 = new HomePageAmministratore(frame, controller);
                        frame.setVisible(false);

                    } else if (controller.getUser().getClass().getSimpleName().equals("UtenteGenerico")) {

                        HomePageUtenteGenerico frame3 = new HomePageUtenteGenerico(frame, controller);
                        frame.setVisible(false);
                    }
                }

                frame.setVisible(false);
            }
        });
// Listener per il pulsante "Cerca":
        // Filtra i voli in base ai criteri inseriti dall’utente
        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tipo = comboBox1.getSelectedItem().toString().trim();
                String compagnia =  textField2.getText().trim();
                String codice = textField1.getText().trim();
                String data = textField4.getText().trim();
                String destinazione = textField3.getText().trim();

                ArrayList<Volo> voliTrovati=new ArrayList<>();

                controller.ricercaVoli(voliTrovati, tipo, compagnia, codice, data, destinazione);

                if(voliTrovati.isEmpty())
                    JOptionPane.showMessageDialog(cercaButton, "Nessun volo trovato");

                else {

                    model.setRowCount(0);

                    for (Volo v : voliTrovati) {
                        if(v.getClass().getSimpleName().equals("VoloInPartenza")){

                            tipologia = "in partenza per";
                            localita = v.getDestinazione();
                            numGate = String.valueOf(((VoloInPartenza)v).getNumGate());
                        }

                        else {

                            tipologia = "in arrivo da";
                            localita = v.getOrigine();
                            numGate= "-";
                        }

                        model.addRow(new Object[]{v.getCodice(), v.getCompagniaAerea(), tipologia, localita, v.getDataPartenza(), v.getOrarioPartenza(), v.getOrarioArrivo(), v.getDurata(), v.getStato().toString().toUpperCase(), v.getRitardo(), numGate});
                    }
                }
            }
        });
    }

    /**
     * Restituisce il frame della homepage.
     *
     * @return oggetto {@link JFrame} della finestra principale
     */
    public static JFrame getFrame() {
        return frame;
    }


    /**
     * Punto di ingresso dell'applicazione.
     *
     * @param args eventuali argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        frame = new JFrame("HomePage");
        frame.setContentPane(new HomePage().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1300, 800);
    }
}