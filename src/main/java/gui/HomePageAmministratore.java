package gui;

import controller.Controller;
import model.Amministratore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageAmministratore {
    private JPanel panel1;
    private JButton cercaPasseggeroButton;
    private JButton inserisciVoloButton;
    private JButton aggiornaVoloButton;
    private JButton assegnaGateButton;
    private JButton visualizzaSmarrimentiButton;
    private JButton monitoraBagaglioButton;
    private JButton aggiornaStatoBagaglioButton;
    private JButton indietroButton;
    private JLabel lable1;
    private static JFrame frame;

    public HomePageAmministratore(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HomePageAmministratore");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);

        lable1.setText("BENVENUTO " + ((Amministratore)controller.getUser()).getNome());

        cercaPasseggeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CercaPasseggero frame1 = new CercaPasseggero(frame, controller);
                frame.setVisible(false);
            }
        });

        inserisciVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InserisciVolo frame2 = new InserisciVolo(frame, controller);
                frame.setVisible(false);
            }
        });

        aggiornaVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AggiornaVolo frame3 = new AggiornaVolo(frame, controller);
                frame.setVisible(false);
            }
        });

        assegnaGateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AssegnaGate frame4 = new AssegnaGate(frame, controller);
                frame.setVisible(false);
            }
        });

        visualizzaSmarrimentiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VisualizzaSmarrimenti frame5 = new VisualizzaSmarrimenti(frame, controller);
                frame.setVisible(false);
            }
        });

        monitoraBagaglioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MonitoraBagaglio frame6 = new MonitoraBagaglio(frame, controller);
                frame.setVisible(false);
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}