package gui;

import controller.Controller;
import model.Volo;
import model.VoloInPartenza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssegnaGate {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton assegnaButton;
    private JTable table1;
    private static JFrame frame;

    public AssegnaGate(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("AssegnaGate");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);

        //INIZIALIZZA TABELLA

        String[] colonne = {"Volo", "compagnia aerea", "destinazione", "data", "orario partenza", "orario arrivo", "durata", "stato", "R", "Gate"};

        DefaultTableModel model = new DefaultTableModel(colonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        ArrayList<VoloInPartenza> voli=new ArrayList<>();

        controller.getVoliInPartenza(voli);

        for(VoloInPartenza v : voli){

            model.addRow(new Object[]{v.getCodice(), v.getCompagniaAerea(), v.getDestinazione(), v.getDataPartenza(), v.getOrarioPartenza(), v.getOrarioArrivo(), v.getDurata(), v.getStato().toString().toUpperCase(), v.getRitardo(), v.getNumGate()});
        }

        table1.setModel(model);



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
