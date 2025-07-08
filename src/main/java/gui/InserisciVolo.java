package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciVolo {

    private JPanel panel1;
    private JButton button1;
    private JTextField ritardoTextField;
    private JTextField dataTextField;
    private JTextField orarioArrivoTextField;
    private JTextField destinazioneTextField;
    private JTextField origineTextField;
    private JComboBox comboBox1;
    private JTextField durataTextField;
    private JTextField orarioPartenzaTextField;
    private JButton aggiungiButton;
    private JTextField compagniaAereaTextField;
    private JTextField codiceVoloTextField;
    private JLabel ritardoLable;
    private static JFrame frame;

    public InserisciVolo(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("InserisciVolo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 800);

        ritardoTextField.setEditable(false);

        comboBox1.setModel(new DefaultComboBoxModel(new Object[]{"Programmato","Decollato", "InRitardo","Atterrato","Cancellato"}));



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

                String codice = codiceVoloTextField.getText();
                String origine = origineTextField.getText();
                String destinazione = destinazioneTextField.getText();
                String compagnia = compagniaAereaTextField.getText();
                String partenza = orarioPartenzaTextField.getText();
                String arrivo = orarioArrivoTextField.getText();
                String durata = durataTextField.getText();
                String stato = comboBox1.getSelectedItem().toString();
                String dataPartenza = dataTextField.getText();
                int ritardoInt;

                if(!ritardoTextField.isEditable())
                    ritardoInt=0;

                else
                    ritardoInt= Integer.parseInt(ritardoTextField.getText());

                boolean esito = controller.inserisciVolo(compagnia, codice, origine, destinazione, partenza, arrivo, dataPartenza, durata, ritardoInt, stato);

                if(esito) {
                    JOptionPane.showMessageDialog(aggiungiButton, "Volo aggiunto con successo!");
                    frame.setVisible(false);
                    frameChiamante.setVisible(true);
                }

                else
                    JOptionPane.showMessageDialog(aggiungiButton, "Operazione fallita");
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBox1.getSelectedItem().toString().equals("InRitardo"))
                    ritardoTextField.setEditable(true);
                else
                    ritardoTextField.setEditable(false);
            }
        });
    }
}
