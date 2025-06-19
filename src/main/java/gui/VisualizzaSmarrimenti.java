package gui;

import controller.Controller;
import model.Bagaglio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaSmarrimenti {

    private JPanel panel1;
    private JButton button1;
    private JButton visualizzaButton;
    private JTable table1;
    private static JFrame frame;

    public VisualizzaSmarrimenti(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("VisualizzaSmarrimenti");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);

        DefaultTableModel model;

        String[] colonne = {"Bagagli"};

        model= new DefaultTableModel(colonne, 0);

        ArrayList<Bagaglio> bagagliSmarriti = controller.visualizzaBagagliSmarriti();

        for (Bagaglio b : bagagliSmarriti) {
            model.addRow(new Object[]{b.getCodice()});
        }

        table1.setModel(model);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
