package gui; //finito

import controller.Controller;
import model.ClasseVolo;
import model.Bagaglio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificaPrenotazione {

    private JPanel panel1;;
    private JRadioButton bagaglioRadioButton;
    private JTextField numeroPrenotazioneTextField;
    private JTextField postoTextField;
    private JTextField classeVoloTextField;
    private JTextField nomePasseggeroTextField;
    private JTextField cognomePasseggeroTextField;
    private JTextField numeroDocumentoPasseggeroTextField;
    private JButton buttonModificaPrenotazione;
    private JComboBox comboBox1;
    private JButton button1;
    private JFrame frame;

    public ModificaPrenotazione(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Modifica Prenotazione");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{" ", "M", "F"}));

        buttonModificaPrenotazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int numeroPrenotazione = Integer.parseInt(numeroPrenotazioneTextField.getText());
                String nome = nomePasseggeroTextField.getText().trim();
                String cognome = cognomePasseggeroTextField.getText().trim();
                String posto = postoTextField.getText().trim();
                ClasseVolo classeVolo = ClasseVolo.valueOf(classeVoloTextField.getText().trim());
                String numDocumento = numeroDocumentoPasseggeroTextField.getText().trim();
                char sesso = comboBox1.getSelectedItem().toString().charAt(0);

                boolean bagaglio = false;

                if(bagaglioRadioButton.isSelected())
                    bagaglio=true;


                controller.modificaPrenotazione(numeroPrenotazione, posto, classeVolo, nome, cognome, numDocumento, sesso, bagaglio);
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