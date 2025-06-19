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
    private JRadioButton bagaglioRadioButton;

    private JFrame frame;

    public CheckIn(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Check-In");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String numeroPrenotazione = textFieldNumeroPrenotazione.getText().trim();
                int prenotazione = Integer.parseInt(numeroPrenotazione);

                if (numeroPrenotazione.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Inserisci il numero prenotazione!");
                    return;
                }

                boolean bagaglio=false;

                if (bagaglioRadioButton.isSelected()) {
                    bagaglio = true;
                } else if (bagaglioRadioButton.isSelected()) {
                    bagaglio = false;
                }

                controller.checkIn(prenotazione, bagaglio);
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
