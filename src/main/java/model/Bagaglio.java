package model;

/**
 * Rappresenta un bagaglio associato a una prenotazione.
 * <p>
 * Ogni bagaglio ha un codice identificativo univoco e uno stato che ne descrive la disponibilità
 * (es. {@code Ritirabile}, {@code Smarrito}, {@code InTransito}, ecc.).
 * I codici vengono assegnati automaticamente a partire da 101.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class Bagaglio {

    private static int cont = 101;

    private int codice;
    private StatoBagaglio statoBagaglio;

    /**
     * Costruisce un nuovo bagaglio con codice generato automaticamente.
     * Lo stato iniziale è impostato su {@code StatoBagaglio.Ritirabile}.
     */
    public Bagaglio() {
        this.codice = cont;
        this.statoBagaglio = StatoBagaglio.Ritirabile;
        cont++;
    }

    /**
     * Imposta il codice identificativo del bagaglio.
     * <p>
     * Usato in caso di override manuale (non raccomandato per codici generati).
     * </p>
     *
     * @param codice il codice da assegnare
     */
    public void setCodice(int codice) {
        this.codice = codice;
    }

    /**
     * Restituisce il codice identificativo del bagaglio.
     *
     * @return il codice del bagaglio
     */
    public int getCodice() {
        return codice;
    }

    /**
     * Restituisce lo stato attuale del bagaglio.
     *
     * @return lo stato del bagaglio
     */
    public StatoBagaglio getStato() {
        return statoBagaglio;
    }

    /**
     * Imposta lo stato attuale del bagaglio.
     *
     * @param statoBagaglio nuovo stato del bagaglio
     */
    public void setStato(StatoBagaglio statoBagaglio) {
        this.statoBagaglio = statoBagaglio;
    }

    /**
     * Restituisce lo stato attuale del bagaglio.
     * <p>
     * Questo metodo è fornito per garantire compatibilità con versioni precedenti
     * o convenzioni di denominazione alternative. Tuttavia, si consiglia di utilizzare
     * direttamente {@link #getStato()} per una nomenclatura più coerente.
     * </p>
     *
     * @return stato corrente del bagaglio
     * @deprecated Usa il metodo {@link #getStato()} al suo posto.
     */
    @Deprecated
    public StatoBagaglio getStatoBagaglio() {
        return getStato();
    }

    /**
     * Imposta un nuovo stato per il bagaglio.
     * <p>
     * Questo metodo è mantenuto per compatibilità con codice legacy, ma si consiglia
     * di utilizzare {@link #setStato(StatoBagaglio)} per chiarezza e consistenza.
     * </p>
     *
     * @param statoBagaglio nuovo stato da assegnare al bagaglio
     * @deprecated Usa il metodo {@link #setStato(StatoBagaglio)} al suo posto.
     */
    @Deprecated
    public void setStatoBagaglio(StatoBagaglio statoBagaglio) {
        setStato(statoBagaglio);
    }
}
