package gui;

import model.Bagaglio;
import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitoraBagaglio {
    private JTextField textField1;
    private JPanel panel1;
    private JButton button1;
    private JButton monitoraBagaglioButton;
    private JTextArea textArea1;
    private JButton modificaButton;
    private Bagaglio b = null;

    private JFrame frame;

    public MonitoraBagaglio(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Monitora Bagaglio");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(400, 400);

        monitoraBagaglioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codiceText = textField1.getText().trim();
                int codice = Integer.parseInt(codiceText);

                if(controller.getUser().getClass().getSimpleName().equals("UtenteGenerico"))
                    b = controller.monitoraBagaglioUtenteGenerico(codice);

                else
                    b = controller.monitoraBagaglioAmministratore(codice);


                if(b==null)
                    JOptionPane.showMessageDialog(monitoraBagaglioButton, "Bagaglio non trovato");

                else
                    textArea1.setText("Codice bagaglio: " + b.getCodice() + " - " + b.getStato());


            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });

        modificaButton.setVisible(false);

        //DISPONIBILE SOLO SE USER E' UN AMMINISTRATORE

        if(controller.getUser().getClass().getSimpleName().equals("Amministratore")){

            modificaButton.setVisible(true);
            modificaButton.setEnabled(false);

            if(b!=null)
                modificaButton.setEnabled(true);
        }

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AggiornaStatoBagaglio frame7 = new AggiornaStatoBagaglio(frame, controller, b.getCodice());
                frame.setVisible(false);
            }
        });
    }
}