package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaccia grafica principale per l'amministratore.
 * <p>
 * Consente all'amministratore autenticato di accedere a tutte le funzionalità gestionali
 * del sistema, come l'inserimento e modifica dei voli, assegnazione dei gate, gestione bagagli
 * e monitoraggio dei passeggeri. Ogni pulsante apre una nuova finestra specifica.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class HomePageAmministratore {
    private JPanel panel1;
    private JButton cercaPasseggeroButton; // Cerca un passeggero registrato
    private JButton inserisciVoloButton; // Inserisce un nuovo volo nel sistema
    private JButton aggiornaVoloButton; // Aggiorna i dati di un volo esistente
    private JButton assegnaGateButton; // Assegna un gate a un volo in partenza
    private JButton visualizzaSmarrimentiButton; // Mostra la lista dei bagagli smarriti
    private JButton monitoraBagaglioButton; // Traccia un bagaglio inserendo il codice
    private JButton indietroButton; // Torna alla schermata precedente
    private JLabel lable1; // Label di benvenuto
    private JButton logOutButton; // Esegue il logout dell’utente
    private static JFrame frame;

    /**
     * Costruisce e visualizza la home page dedicata all'amministratore.
     *
     * @param frameChiamante finestra precedente da mostrare al logout o al ritorno
     * @param controller controller condiviso per la gestione dell'applicazione
     */
    public HomePageAmministratore(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HomePageAmministratore");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 600);
    // Mostra messaggio di benvenuto personalizzato
        lable1.setText("BENVEUTO " + controller.getUser().getLogin());
        lable1.setFont(new Font("Courier", Font.PLAIN, 18));
        // Apre finestra per cercare passeggeri
        cercaPasseggeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CercaPasseggero frame1 = new CercaPasseggero(frame, controller);
                frame.setVisible(false);
            }
        });
// Apre finestra per aggiornare volo
        inserisciVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InserisciVolo frame2 = new InserisciVolo(frame, controller);
                frame.setVisible(false);
            }
        });
// Apre finestra per assegnare gate a volo
        aggiornaVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AggiornaVolo frame3 = new AggiornaVolo(frame, controller);
                frame.setVisible(false);
            }
        });
        // Apre finestra per assegnare gate a volo
        assegnaGateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AssegnaGate(frame, controller);
                frame.setVisible(false);
            }
        });
// Apre finestra per visualizzare smarrimenti
        visualizzaSmarrimentiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VisualizzaBagagliSmarriti frame5 = new VisualizzaBagagliSmarriti(frame, controller);
                frame.setVisible(false);
            }
        });
// Apre finestra per monitorare un bagaglio
        monitoraBagaglioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MonitoraBagaglio frame6 = new MonitoraBagaglio(frame, controller);
                frame.setVisible(false);
            }
        });
        // Torna alla finestra precedente
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
        // Esegue il logout e ritorna alla schermata chiamante
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