package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssegnaGate {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton indietroButton;
    private JButton assegnaButton;
    private static JFrame frame;
    private static JFrame frameChiamante;
    private Controller controller;

    public AssegnaGate(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("AssegnaGate");
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

        assegnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceVolo = textField1.getText();
                int numeroGate;

                try {
                    numeroGate = Integer.parseInt(textField2.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Inserisci un numero di gate valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = controller.assegnaGate(codiceVolo, numeroGate);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Gate assegnato correttamente!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Volo non trovato! Controlla il codice inserito.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
