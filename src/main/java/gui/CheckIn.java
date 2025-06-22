package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckIn {
    private JPanel panel1;
    private JTextField textFieldNumeroPrenotazione;
    private JButton buttonCheckIn;
    private JButton button1;
    private JFrame frame;

    public CheckIn(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Check-In");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(200, 200);

        buttonCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int prenotazione = Integer.parseInt(textFieldNumeroPrenotazione.getText().trim());

                String messaggio= controller.checkIn(prenotazione);

                if(!messaggio.isEmpty()) {
                    JOptionPane.showMessageDialog(buttonCheckIn, messaggio);
                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }

                else
                    JOptionPane.showMessageDialog(buttonCheckIn, "Operazione fallita");
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
