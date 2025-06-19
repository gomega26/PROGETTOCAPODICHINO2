package gui;

import controller.Controller;
import javax.swing.*;

public class SegnalaSmarrimento {
    private JPanel panel1;
    private JTextField textFieldBagaglio;
    private JButton buttonSegnalaSmarrimento;
    private JButton buttonIndietro;
    private JTextArea textAreaRisultato;

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;

    public SegnalaSmarrimento(JFrame frameChiamante, Controller controller) {


        frame = new JFrame("Segnala Smarrimento");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonSegnalaSmarrimento.addActionListener(e -> {
            String numeroBagaglioText = textFieldBagaglio.getText().trim();

            if (numeroBagaglioText.isEmpty()) {
                JOptionPane.showMessageDialog(panel1, "Inserisci il numero del bagaglio.");
                return;
            }

            try {
                int numeroBagaglio = Integer.parseInt(numeroBagaglioText);

                // Chiama il Controller e verifica il risultato
                boolean esito = controller.segnalaSmarrimento(numeroBagaglio);

                if (esito) {
                    textAreaRisultato.setText("Bagaglio " + numeroBagaglio + " segnalato come smarrito.");
                } else {
                    JOptionPane.showMessageDialog(panel1, "Errore: Bagaglio non trovato!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel1, "Inserisci un numero valido per il bagaglio!");
            }
        });
        buttonIndietro.addActionListener(e -> {
            frame.dispose();
            if (frameChiamante != null) {
                frameChiamante.setVisible(true);
            }
        });
    }
}
