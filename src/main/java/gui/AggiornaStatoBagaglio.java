package gui;

import controller.Controller;
import model.Bagaglio;
import model.StatoBagaglio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggiornaStatoBagaglio {

    private JPanel panel1;
    private JButton button1;
    private JButton aggiornaButton;
    private JComboBox comboBox1;
    private static JFrame frame;

    public AggiornaStatoBagaglio(JFrame frameChiamante, Controller controller, Bagaglio b) {
        frame = new JFrame("AggiornaStatoBagaglio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 450);

        comboBox1.addItem("Caricato");
        comboBox1.addItem("Ritirabile");
        comboBox1.addItem("Smarrito");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        aggiornaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StatoBagaglio stato = StatoBagaglio.valueOf(comboBox1.getSelectedItem().toString());

                controller.aggiornaStatoBagaglio(b, stato);
            }
        });
    }
}