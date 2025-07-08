package gui;

import controller.Controller;
import model.StatoVolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiornaVolo {

    private JPanel panel1;
    private JTextField codicevolotext;
    private JComboBox<String> comboBox1; // Usare String invece di StatoVolo
    private JTextField luogotext;
    private JTextField duratatext;
    private JTextField orariopartenzatext;
    private JTextField orarioarrivotext;
    private JButton button1;
    private JButton modificaButton;
    private JTextField ritardotext;
    private JTextField datatext;
    private JComboBox comboBox2;
    private static JFrame frame;
    private static JFrame frameChiamante;
    private Controller controller;

    public AggiornaVolo(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("AggiornaVolo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        this.controller = controller; // Inizializzazione del controller

        // Popola la comboBox con stringhe invece di StatoVolo
        comboBox1.addItem("Programmato");
        comboBox1.addItem("Decollato");
        comboBox1.addItem("In ritardo");
        comboBox1.addItem("Atterrato");
        comboBox1.addItem("Cancellato");

        comboBox2.addItem("Origine");
        comboBox2.addItem("Destinazione");

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codiceVolo = codicevolotext.getText();

                String orarioPartenza = orariopartenzatext.getText();
                String orarioArrivo = orarioarrivotext.getText();
                String dataPartenza = datatext.getText();
                String durata = duratatext.getText();
                int ritardo = Integer.parseInt(ritardotext.getText());
                String statoDelVolo = comboBox1.getSelectedItem().toString();
                String luogo = luogotext.getText();

                boolean esito = controller.aggiornaVolo(codiceVolo, luogo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);

                if(esito){

                    JOptionPane.showMessageDialog(modificaButton, "Volo aggiornato con successo!");

                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }

                else
                    JOptionPane.showMessageDialog(modificaButton, "Inserire codice di un volo esistente");
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