package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaSmarrimenti {

    private JPanel panel1;
    private JButton indietroButton;
    private JButton visualizzaButton;
    private JTable table1;
    private static JFrame frame;
    private static JFrame frameChiamante;
    private Controller controller;

    public VisualizzaSmarrimenti(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("VisualizzaSmarrimenti");
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

        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Ottieni i dati dal Controller, senza accedere direttamente agli oggetti di model
                ArrayList<Integer> codiciBagagliSmarriti = controller.getCodiciBagagliSmarriti();

                String[] colonne = {"Codice Bagaglio"};
                Object[][] dati = new Object[codiciBagagliSmarriti.size()][1];

                for (int i = 0; i < codiciBagagliSmarriti.size(); i++) {
                    dati[i][0] = codiciBagagliSmarriti.get(i);
                }

                table1.setModel(new DefaultTableModel(dati, colonne));
            }
        });
    }
}
