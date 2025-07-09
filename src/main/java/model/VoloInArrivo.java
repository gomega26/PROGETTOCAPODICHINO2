package model;

/**
 * Rappresenta un volo in arrivo all'aeroporto di Napoli.
 * <p>
 * Estende {@link Volo} e imposta automaticamente la destinazione su "Napoli".
 * L'origine viene specificata come parametro. Questa classe non introduce nuovi
 * attributi rispetto alla superclasse, ma rappresenta un comportamento semantico distinto.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see Volo
 */
public class VoloInArrivo extends Volo {

    /**
     * Instantiates a new Volo in arrivo.
     */
    public VoloInArrivo(){} //Costruttore vuoto; le informazioni verranno aggiunte dal database

    /**
     * Crea un oggetto {@code VoloInArrivo} con i dati forniti.
     * La destinazione viene impostata automaticamente su "Napoli".
     *
     * @param compagniaAerea compagnia aerea responsabile del volo
     * @param codice codice identificativo univoco del volo
     * @param origine aeroporto da cui il volo ha origine
     * @param orarioPartenza orario pianificato di partenza
     * @param orarioArrivo orario stimato di arrivo
     * @param dataPartenza data del volo
     * @param durata durata stimata del viaggio
     * @param ritardo ritardo previsto in minuti
     * @param statoDelVolo stato attuale del volo (es. ATTIVO, RITARDATO)
     */
}

