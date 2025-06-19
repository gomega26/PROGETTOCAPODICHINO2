package gui; //finito

import controller.Controller;
import model.ClasseVolo;
import model.Bagaglio;

import javax.swing.*;

public class ModificaPrenotazione {

    private JPanel panel1;;
    private JRadioButton bagaglioRadioButton;
    private JTextField numeroPrenotazioneTextField;
    private JTextField postoTextField;
    private JTextField classeVoloTextField;
    private JTextField nomePasseggeroTextField;
    private JTextField cognomePasseggeroTextField;
    private JTextField numeroDocumentoPasseggeroTextField;
    private JButton buttonModificaPrenotazione;
    private JComboBox comboBox1;
    private JButton button1;
    private JFrame frame;

    public ModificaPrenotazione(JFrame frameChiamante, Controller controller) {


        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"", "Maschio", "Femmina"}));

        frame = new JFrame("Modifica Prenotazione");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonModificaPrenotazione.addActionListener(e -> {
            String numeroPrenotazioneText = numeroPrenotazioneTextField.getText().trim();
            if (numeroPrenotazioneText.isEmpty()) {
                JOptionPane.showMessageDialog(panel1, "Inserisci il numero della prenotazione.");
                return;
            }

            try {
                int numeroPrenotazione = Integer.parseInt(numeroPrenotazioneText);
                String nome = nomePasseggeroTextField.getText().trim();
                String cognome = cognomePasseggeroTextField.getText().trim();
                String posto = postoTextField.getText().trim();
                ClasseVolo classeVolo = ClasseVolo.valueOf(classeVoloTextField.getText().trim());
                Bagaglio bagaglio = new Bagaglio();
                String numDocumento = numeroDocumentoPasseggeroTextField.getText().trim();
                char sesso = comboBox1.getSelectedItem().toString().charAt(0);

                // Chiamata diretta al Controller (ora il Controller cerca la prenotazione)
                boolean esito = controller.modificaPrenotazione(numeroPrenotazione, posto, classeVolo, nome, cognome, numDocumento, sesso, bagaglio);

                if (esito) {
                    JOptionPane.showMessageDialog(panel1, "Prenotazione modificata con successo!");
                } else {
                    JOptionPane.showMessageDialog(panel1, "Errore: Prenotazione non trovata!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel1, "Inserisci un numero valido per la prenotazione!");
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