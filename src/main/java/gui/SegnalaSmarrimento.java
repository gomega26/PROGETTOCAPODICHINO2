package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Finestra grafica che consente all’utente di segnalare lo smarrimento di un bagaglio.
 * <p>
 * Inserendo il codice identificativo del bagaglio, la richiesta viene inoltrata al {@link Controller},
 * che si occupa di aggiornare lo stato nel sistema. L’esito viene comunicato tramite un messaggio a schermo.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class SegnalaSmarrimento {

    private JPanel panel1;
    private JTextField textFieldBagaglio; // Campo per inserire il codice del bagaglio
    private JButton buttonSegnalaSmarrimento; // Pulsante per segnalare lo smarrimento
    private JButton button1; // Pulsante per tornare alla finestra precedente
    private JFrame frame;


    /**
     * Costruisce e mostra l’interfaccia per segnalare uno smarrimento.
     *
     * @param frameChiamante finestra precedente da riattivare al termine
     * @param controller controller centralizzato per gestire la logica dell’operazione
     */
    public SegnalaSmarrimento(JFrame frameChiamante, Controller controller) {

        frame = new JFrame("Segnala Smarrimento");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(300, 300);
// Listener per il pulsante "Segnala smarrimento":
        // Invia il numero di bagaglio al controller per aggiornare lo stato
        buttonSegnalaSmarrimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String numeroBagaglioText = textFieldBagaglio.getText().trim();
                int numeroBagaglio = Integer.parseInt(numeroBagaglioText);

                boolean esito = controller.segnalaSmarrimento(numeroBagaglio);

                if(esito)
                    JOptionPane.showMessageDialog(buttonSegnalaSmarrimento, "Smarrimento segnalato");
                else
                    JOptionPane.showMessageDialog(buttonSegnalaSmarrimento, "Inserire codice bagaglio valido!");
            }
        });
        // Listener per il pulsante "Indietro":
        // Chiude la finestra corrente e riattiva quella chiamante
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
