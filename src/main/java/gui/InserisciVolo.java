package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciVolo {

    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField codicetextfield;
    private JTextField originetextField;
    private JTextField destinazionetextField;
    private JTextField compagniatextField;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton aggiungiButton;
    private JTextField textField8;
    private JTextField textField9;
    private static JFrame frame;

    public InserisciVolo(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("InserisciVolo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codice = codicetextfield.getText().trim();
                String origine = originetextField.getText().trim();
                String destinazione = destinazionetextField.getText().trim();
                String compagnia = compagniatextField.getText().trim();
                String partenza = textField5.getText().trim();
                String arrivo = textField6.getText().trim();
                String durata = textField7.getText().trim();
                String stato = comboBox1.getSelectedItem().toString();
                String ritardo = textField8.getText().trim();
                String dataPartenza = textField9.getText().trim();

                int ritardoInt = Integer.parseInt(ritardo);

                controller.inserisciVolo(compagnia, codice, origine, destinazione, partenza, arrivo, dataPartenza, durata, ritardoInt, stato);

                JOptionPane.showMessageDialog(aggiungiButton, "Volo aggiunto con successo!");

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
