package gui;

import controller.Controller;

import javax.swing.*;

public class HomePageUtenteGenerico {
    private JPanel panel1;
    private JButton segnalaSmarrimentoButton;
    private JButton prenotaVoloButton;
    private JButton modificaPrenotazioneButton;
    private JButton checkInButton;
    private JButton cercaPrenotazioneButton;
    private JButton buttonIndietro;
    private JButton buttonMonitoraBagaglio;
    private JLabel label1;

    private JFrame frame;

    public HomePageUtenteGenerico(JFrame frameChiamante, Controller controller) {

        label1.setText("BENVENUTO " + controller.getUser().getLogin());

        // Crea e mostra il frame
        frame = new JFrame("Home Page Utente");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Listener bottoni
        prenotaVoloButton.addActionListener(e -> {
            frame.setVisible(false);
            PrenotaVolo frame1 = new PrenotaVolo(frame, controller);
        });

        cercaPrenotazioneButton.addActionListener(e -> {
            frame.setVisible(false);
            CercaPrenotazione frame2 = new CercaPrenotazione(frame, controller);
        });

        modificaPrenotazioneButton.addActionListener(e -> {
            frame.setVisible(false);
            ModificaPrenotazione frame3 = new ModificaPrenotazione(frame, controller);
        });

        segnalaSmarrimentoButton.addActionListener(e -> {
            frame.setVisible(false);
            SegnalaSmarrimento frame4 = new SegnalaSmarrimento(frame, controller);
        });

        checkInButton.addActionListener(e -> {
            frame.setVisible(false);
            CheckIn frame5 = new CheckIn(frame, controller);
        });

        buttonMonitoraBagaglio.addActionListener(e -> {
            frame.setVisible(false);
            MonitoraBagaglio frame6 = new MonitoraBagaglio(frame, controller);
        });

        buttonIndietro.addActionListener(e -> {

            frame.setVisible(false);
            frameChiamante.setVisible(true);
        });
    }
}