package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiornaStatoBagaglio {

    private JPanel panel1;
    private JButton indietroButton;
    private JButton aggiornaButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private static JFrame frame;
    private static JFrame frameChiamante;
    private Controller controller;

    public AggiornaStatoBagaglio(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("AggiornaStatoBagaglio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        aggiornaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int codiceBagaglio = Integer.parseInt(textField1.getText()); // Ottieni il codice del bagaglio
                    String statoBagaglio = (String) comboBox1.getSelectedItem(); // Stato selezionato nella combobox

                    boolean aggiornato = controller.aggiornaStatoBagaglio(codiceBagaglio, statoBagaglio);

                    if (aggiornato) {
                        JOptionPane.showMessageDialog(frame, "Stato del bagaglio aggiornato con successo!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Errore: Bagaglio non trovato o stato non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Inserisci un numero valido per il codice bagaglio!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}