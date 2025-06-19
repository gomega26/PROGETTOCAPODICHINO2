package gui;

import model.Bagaglio;
import controller.Controller;

import javax.swing.*;

public class MonitoraBagaglio {
    private JTextField textField1;
    private JPanel panel1;
    private JButton buttonIndietro;
    private JButton monitoraBagaglioButton;
    private JTextArea textAreaRisultato;

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;

    public MonitoraBagaglio(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Monitora Bagaglio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        monitoraBagaglioButton.addActionListener(e -> {
            String codiceText = textField1.getText().trim();
            if (codiceText.isEmpty()) {
                JOptionPane.showMessageDialog(panel1, "Inserisci il codice del bagaglio!");
                return;
            }

            try {
                int codice = Integer.parseInt(codiceText);

                // Chiamata al Controller con il tipo corretto
                Bagaglio bagaglio = controller.monitoraBagaglioUtenteGenerico(codice);

                if (bagaglio != null) {
                    textAreaRisultato.setText("Bagaglio trovato! Codice: " + bagaglio.getCodice() +
                            ", Stato: " + bagaglio.getStato());
                } else {
                    JOptionPane.showMessageDialog(panel1, "Errore: Bagaglio non trovato!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel1, "Inserisci un numero valido per il codice del bagaglio!");
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