package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaccia grafica principale per l’utente generico autenticato.
 * <p>
 * Consente di accedere a tutte le funzionalità disponibili per un utente standard,
 * come la prenotazione, modifica e ricerca di voli, gestione bagagli e check-in.
 * Ogni pulsante avvia una finestra dedicata.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class HomePageUtenteGenerico {
    private JPanel panel1;
    private JButton segnalaSmarrimentoButton; // Segnala un bagaglio smarrito
    private JButton prenotaVoloButton; // Prenota un nuovo volo
    private JButton modificaPrenotazioneButton; // Modifica una prenotazione esistente
    private JButton checkInButton; // Effettua il check-in per una prenotazione
    private JButton cercaPrenotazioneButton; // Cerca informazioni su una prenotazione
    private JButton buttonIndietro; // Torna alla finestra precedente
    private JButton buttonMonitoraBagaglio; // Consulta lo stato di un bagaglio
    private JLabel label1; // Etichetta di benvenuto
    private JButton logOutButton; // Esegue il logout
    private JFrame frame;

    /**
     * Costruisce e mostra la homepage dedicata all'utente generico.
     *
     * @param frameChiamante la finestra precedente da riattivare al logout o all’indietro
     * @param controller controller condiviso per la gestione delle operazioni
     */
    public HomePageUtenteGenerico(JFrame frameChiamante, Controller controller) {

        label1.setText("BENVENUTO " + controller.getUser().getLogin());

        // Crea e mostra il frame
        frame = new JFrame("Home Page Utente");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(800, 800);

        // Listener per prenotazione volo
        prenotaVoloButton.addActionListener(e -> {
            frame.setVisible(false);
            PrenotaVolo frame1 = new PrenotaVolo(frame, controller);
        });
// Listener per Cercare una prenotazione
        cercaPrenotazioneButton.addActionListener(e -> {
            frame.setVisible(false);
            CercaPrenotazione frame2 = new CercaPrenotazione(frame, controller);
        });
//Listener per modificare prenotazione
        modificaPrenotazioneButton.addActionListener(e -> {
            frame.setVisible(false);
            ModificaPrenotazione frame3 = new ModificaPrenotazione(frame, controller);
        });
//Listener per segnalare smarrimento del bagaglio
        segnalaSmarrimentoButton.addActionListener(e -> {
            frame.setVisible(false);
            SegnalaSmarrimento frame4 = new SegnalaSmarrimento(frame, controller);
        });
// Listener per Check-In
        checkInButton.addActionListener(e -> {
            frame.setVisible(false);
            CheckIn frame5 = new CheckIn(frame, controller);
        });

        // Listener per monitoraggio bagaglio
        buttonMonitoraBagaglio.addActionListener(e -> {
            frame.setVisible(false);
            MonitoraBagaglio frame6 = new MonitoraBagaglio(frame, controller);
        });
// Listener per ritorno alla schermata chiamante
        buttonIndietro.addActionListener(e -> {

            frame.setVisible(false);
            frameChiamante.setVisible(true);
        });
        // Listener per logout e ritorno al frame chiamante
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.logOut();

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}