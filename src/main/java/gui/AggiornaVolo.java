package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiornaVolo {

    private JPanel panel1;
    private JTextField textField1;
    private JComboBox<String> comboBox1; // Usare String invece di StatoVolo
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton indietroButton;
    private JButton modificaButton;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
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

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codiceVolo = textField1.getText();

                if (codiceVolo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Errore: Inserisci il codice del volo.");
                    return;
                }

                if (!controller.verificaVolo(codiceVolo)) {
                    JOptionPane.showMessageDialog(frame, "Errore: Codice volo non valido!");
                    return;
                }

                try {
                    String orarioPartenza = textField2.getText();
                    String orarioArrivo = textField3.getText();
                    String dataPartenza = textField4.getText();
                    String durata = textField5.getText();
                    int ritardo = Integer.parseInt(textField7.getText());
                    String statoDelVolo = comboBox1.getSelectedItem().toString(); // Ora è una stringa

                    // Chiamata al Controller per aggiornare il volo
                    controller.aggiornaVolo(codiceVolo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);

                    JOptionPane.showMessageDialog(frame, "Volo aggiornato con successo!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Errore: Inserisci un valore numerico corretto per il ritardo.");
                }
            }
        });
    }
}