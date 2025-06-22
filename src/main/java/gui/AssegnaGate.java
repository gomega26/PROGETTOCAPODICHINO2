package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssegnaGate {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton assegnaButton;
    private static JFrame frame;

    public AssegnaGate(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("AssegnaGate");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        assegnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceVolo = textField1.getText();
                int numeroGate;

                numeroGate = Integer.parseInt(textField2.getText());

                boolean esito = controller.assegnaGate(codiceVolo, numeroGate);

                if(esito) {
                    JOptionPane.showMessageDialog(assegnaButton, "Gate assegnato!");

                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(assegnaButton, "Inserire codice di un volo esistente!");
            }
        });
    }
}
