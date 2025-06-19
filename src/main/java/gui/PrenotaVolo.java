package gui;// finito

import controller.Controller;
import model.ClasseVolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton button1;
    private JButton confermaPrenotazioneButton;

    private JFrame frame;

    public PrenotaVolo(JFrame frameChiamante, Controller controller) {

        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{" ", "M", "F"}));

        frame = new JFrame("Prenota Volo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        confermaPrenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceVolo = textFieldCodiceVolo.getText().trim();
                String posto = textFieldPosto.getText().trim();
                ClasseVolo classe = ClasseVolo.valueOf(textFieldClasseVolo.getText().trim());
                String nome = textFieldNome.getText().trim();
                String cognome = textFieldCognome.getText().trim();
                String telefono = textFieldNumerodiTelefono.getText().trim();
                String documento = textFieldDocumento.getText().trim();
                String sesso = (comboBox1.getSelectedItem() != null) ? comboBox1.getSelectedItem().toString() : "";
                String dataNascita = textFieldDatadiNascita.getText().trim();

                controller.prenotaVolo(codiceVolo, posto, classe, nome, cognome, telefono, documento, sesso.charAt(0), dataNascita);
            }
        });
    }
}