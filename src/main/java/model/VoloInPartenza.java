package model;

import java.util.ArrayList;
/**
 * Rappresenta un volo in partenza dall'aeroporto di Napoli.
 * <p>
 * Estende la classe {@link Volo} aggiungendo l'attributo specifico {@code numGate},
 * che indica il gate di partenza assegnato.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see Volo
 */

public class VoloInPartenza extends Volo {

    private int numGate;

    /**
     * Instantiates a new Volo in partenza.
     */
    public VoloInPartenza(){};

    /**
     * Crea un oggetto {@code VoloInPartenza} con tutti i dettagli specificati.
     *
     * @param compagniaAerea nome della compagnia aerea
     * @param codice codice identificativo del volo
     * @param destinazione città di destinazione del volo
     * @param orarioPartenza orario stimato di partenza
     * @param orarioArrivo orario stimato di arrivo
     * @param dataPartenza data prevista del volo
     * @param durata durata prevista del volo
     * @param ritardo minuti di ritardo
     * @param statoDelVolo stato attuale del volo
     * @param numGate numero del gate assegnato alla partenza
     */

    public VoloInPartenza(String compagniaAerea, String codice, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo, int numGate) {
        // Inizializza i campi comuni tramite il costruttore della superclasse
        super(compagniaAerea,codice,orarioPartenza,orarioArrivo,dataPartenza,durata,ritardo,statoDelVolo);
        this.origine = "Napoli";
        this.numGate = numGate;
        this.destinazione = destinazione;
    }
    /**
     * Restituisce il numero del gate associato alla partenza del volo.
     *
     * @return il numero del gate associato alla partenza del volo
     */
    public int getNumGate() {
        return numGate;
    }


    /**
     * Imposta il numero del gate di partenza per questo volo.
     *
     * @param numGate il numero del gate da assegnare
     */
    public void setNumGate(int numGate) {
        this.numGate = numGate;
    }
}