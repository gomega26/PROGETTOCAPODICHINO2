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

public class HomePage {

    private JButton homePageButton;
    private JPanel panel1;
    private JTable table1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton cercaButton;
    private JLabel lable4;
    private JLabel lable5;
    private DefaultTableModel model;
    private static JFrame frame;
    private Controller controller;

    private String tipologia;
    private String localita;
    private String numGate;

    public HomePage() {

        controller = new Controller();

        controller.getVoli().add(new VoloInPartenza("Ryanair", "FR9021", "Madrid", "09:45", "12:15", "15/06/2025", "2 ore 30 min", 15, StatoVolo.Atterrato, 4));
        controller.getVoli().add(new VoloInPartenza("Lufthansa", "LH2210", "Berlino", "07:10", "09:20", "15/06/2025", "2 ore 10 min", 0, StatoVolo.Decollato, 5));
        controller.getVoli().add(new VoloInPartenza("ITA Airways", "AZ1403", "Milano", "18:00", "19:10", "15/06/2025", "1 ora 10 min", 0, StatoVolo.Programmato, 3));
        controller.getVoli().add(new VoloInPartenza("Air France", "AF1624", "Parigi", "13:20", "15:30", "15/06/2025", "2 ore 10 min", 0, StatoVolo.Programmato, 1));
        controller.getVoli().add(new VoloInPartenza("KLM", "KL1810", "Amsterdam", "06:45", "09:00", "15/06/2025", "2 ore 15 min", 5, StatoVolo.Decollato, 2));
        controller.getVoli().add(new VoloInArrivo("Vueling", "VY7332", "Barcellona", "10:30", "12:35", "15/06/2025", "2 ore 5 min", 0, StatoVolo.Programmato));
        controller.getVoli().add(new VoloInArrivo("British Airways", "BA407", "Londra", "16:15", "18:35", "15/06/2025", "2 ore 20 min", 10, StatoVolo.Programmato));
        controller.getVoli().add(new VoloInArrivo("Wizz Air", "W68412", "Bucarest", "08:00", "10:40", "15/06/2025", "2 ore 40 min", 0, StatoVolo.Programmato));
        controller.getVoli().add(new VoloInArrivo("TAP Air Portugal", "TP851", "Lisbona", "11:50", "14:20", "15/06/2025", "2 ore 30 min", 0, StatoVolo.Cancellato));
        controller.getVoli().add(new VoloInArrivo("Swiss", "LX1712", "Zurigo", "17:30", "19:00", "15/06/2025", "1 ora 30 min", 0, StatoVolo.Programmato));

        comboBox1.setModel(new DefaultComboBoxModel(new Object[]{" ", "VoloInPartenza", "VoloInArrivo"}));

        //INIZIALIZZA TABELLA

        String[] colonne = {"Volo", "compagnia aerea", "tipologia", "località", "data", "orario partenza", "orario arrivo", "durata", "stato", "R", "Gate"};

        model = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for(Volo v : controller.getVoli()){

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

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tipo = comboBox1.getSelectedItem().toString().trim();
                String compagnia =  textField2.getText().trim();
                String codice = textField1.getText().trim();
                String data = textField4.getText().trim();
                String destinazione = textField3.getText().trim();

                ArrayList<Volo> voliTrovati = controller.ricercaVoli(tipo, compagnia, codice, data, destinazione);

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

    public static JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        frame = new JFrame("HomePage");
        frame.setContentPane(new HomePage().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1300, 800);
    }
}