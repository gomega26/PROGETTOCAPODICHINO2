package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SegnalaSmarrimento {
    private JPanel panel1;
    private JTextField textFieldBagaglio;
    private JButton buttonSegnalaSmarrimento;
    private JButton button1;

    private JFrame frame;

    public SegnalaSmarrimento(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Segnala Smarrimento");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(300, 300);

        buttonSegnalaSmarrimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String numeroBagaglioText = textFieldBagaglio.getText().trim();
                int numeroBagaglio = Integer.parseInt(numeroBagaglioText);

                boolean esito = controller.segnalaSmarrimento(numeroBagaglio);

                if(esito)
                    JOptionPane.showMessageDialog(buttonSegnalaSmarrimento, "Smarrimento segnalato");
                else
                    JOptionPane.showMessageDialog(buttonSegnalaSmarrimento, "Inserire codice bagaglio valido!");
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
