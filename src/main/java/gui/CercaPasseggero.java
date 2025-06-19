package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CercaPasseggero {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton indietroButton;
    private JButton cercaButton;
    private static JFrame frame;
    private static JFrame frameChiamante;
    private Controller controller;
    private DefaultTableModel model;

    public CercaPasseggero(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("CercaPasseggero");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);

        String[] colonne = {"Nome", "Cognome", "Numero documento", "Sesso"};

        model = new DefaultTableModel(colonne, 0);

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = textField1.getText().trim();
                String cognome = textField2.getText().trim();
                String numeroDocumento = textField3.getText().trim();
                char sesso = comboBox1.getSelectedItem().toString().charAt(0);

                ArrayList<String[]> risultati = controller.cercaPasseggero(nome, cognome, numeroDocumento, sesso);

                model.setRowCount(0); // Pulizia della tabella
                for (String[] dati : risultati) {
                    model.addRow(dati);
                }
            }
        });
    }
}