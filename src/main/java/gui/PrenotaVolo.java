package gui;// finito

import controller.Controller;
import model.ClasseVolo;

import javax.swing.*;

public class PrenotaVolo {
    private JPanel panel1;
    private JTextField textFieldCodiceVolo;
    private JTextField textFieldPosto;
    private JTextField textFieldClasseVolo;
    private JTextField textFieldNome;
    private JTextField textFieldCognome;
    private JTextField textFieldNumerodiTelefono;
    private JTextField textFieldDocumento;
    private JTextField textFieldDatadiNascita;
    private JComboBox<String> comboBox1;
    private JButton prenotaVoloButton;
    private JButton buttonIndietro;

    private JFrame frame;
    private JFrame frameChiamante;
    private Controller controller;

    public PrenotaVolo(JFrame frameChiamante, Controller controller) {
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{" ", "Maschio", "Femmina"}));

        frame = new JFrame("Prenota Volo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        prenotaVoloButton.addActionListener(e -> prenotaVolo());

        buttonIndietro.addActionListener(e -> {
            frame.dispose();
            if (frameChiamante != null) {
                frameChiamante.setVisible(true);
            }
        });
    }

    public void prenotaVolo() {
        String codiceVoloText = textFieldCodiceVolo.getText().trim();
        String posto = textFieldPosto.getText().trim();
        String classeVoloText = textFieldClasseVolo.getText().trim();
        String nome = textFieldNome.getText().trim();
        String cognome = textFieldCognome.getText().trim();
        String telefono = textFieldNumerodiTelefono.getText().trim();
        String documento = textFieldDocumento.getText().trim();
        String sesso = (comboBox1.getSelectedItem() != null) ? comboBox1.getSelectedItem().toString() : "";
        String dataNascita = textFieldDatadiNascita.getText().trim();

        // Controllo campi obbligatori
        if (codiceVoloText.isEmpty() || nome.isEmpty() || cognome.isEmpty() || telefono.isEmpty() || documento.isEmpty()) {
            JOptionPane.showMessageDialog(panel1, "Compila tutti i campi obbligatori.");
            return;
        }

        try {
            int codiceVolo = Integer.parseInt(codiceVoloText);

            // Normalizzazione della classe volo
            ClasseVolo classe;
            try {
                classe = convertiClasseVolo(classeVoloText);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(panel1, "Errore: Classe di volo non valida! Usa Economy, Business, FirstClass o NessunaClasse.");
                return;
            }

            boolean esito = controller.prenotaVolo(codiceVolo, posto, classe, nome, cognome, telefono, documento, sesso.charAt(0), dataNascita);

            if (esito) {
                JOptionPane.showMessageDialog(panel1, "Prenotazione confermata! Volo: " + codiceVolo);
            } else {
                JOptionPane.showMessageDialog(panel1, "Errore: Il volo " + codiceVolo + " non è disponibile. Controlla il codice inserito.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel1, "Inserisci un codice volo numerico valido!");
        }
    }


    // *Metodo per normalizzare il nome della classe di volo*
    private ClasseVolo convertiClasseVolo(String input) {
        switch (input.trim().toLowerCase()) {
            case "economy":
                return ClasseVolo.Economy;
            case "business":
                return ClasseVolo.Business;
            case "firstclass":
                return ClasseVolo.FirstClass;
            case "nessunaclasse":
                return ClasseVolo.NessunaClasse;
            default: return ClasseVolo.NessunaClasse;
        }
    }
}
