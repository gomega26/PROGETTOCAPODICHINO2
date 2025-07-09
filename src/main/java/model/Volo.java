package model;

import java.util.ArrayList;
/**
 * Classe astratta che rappresenta le informazioni comuni di un volo,
 * come orari, stato, compagnia aerea, tratte e prenotazioni associate.
 * <p>
 * Questa classe viene estesa da {@link VoloInPartenza} e {@link VoloInArrivo}.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */

public class Volo {

    /**
     * Codice identificativo del volo.
     */
    protected String codice;
    /**
     * Città o aeroporto di origine del volo.
     */
    protected String origine;
    /**
     * Città o aeroporto di destinazione del volo.
     */
    protected String destinazione;
    /**
     * Durata prevista del volo, espressa come stringa (es. "2h 15m").
     */
    protected String durata;

    /**
     * Compagnia aerea che opera il volo (es. "Alitalia", "Ryanair").
     */
    protected String compagniaAerea;

    /**
     * Orario programmato di partenza del volo, in formato "HH:mm".
     */
    protected String orarioPartenza;

    /**
     * Orario stimato di arrivo del volo, in formato "HH:mm".
     */
    protected String orarioArrivo;

    /**
     * Data programmata per la partenza del volo, in formato "dd/MM/yyyy".
     */
    protected String dataPartenza;

    /**
     * Ritardo attuale del volo in minuti. Un valore pari a 0 indica puntualità.
     */
    protected int ritardo;

    /**
     * Stato corrente del volo (es. Programmato, Decollato, Atterrato).
     */
    protected StatoVolo stato;

    /**
     * Elenco delle prenotazioni associate a questo volo.
     */
    protected ArrayList<Prenotazione> prenotazioni;


    /**
     * Instantiates a new Volo.
     */
    public Volo(){};
    /**
     * Instantiates a new Volo.
     *
     /**
     * Costruttore completo del volo.
     *
     * @param compagniaAerea compagnia aerea responsabile del volo
     * @param codice codice univoco del volo
     * @param orarioPartenza orario previsto di partenza
     * @param orarioArrivo orario previsto di arrivo
     * @param dataPartenza data programmata del volo
     * @param durata durata stimata del volo (es. "2h30m")
     * @param ritardo eventuale ritardo in minuti
     * @param statoDelVolo stato attuale del volo (ATTIVO, CANCELLATO, etc.)
     */

    public Volo(String compagniaAerea, String codice, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo) {

        this.codice = codice;
        this.orarioPartenza = orarioPartenza;
        this.orarioArrivo = orarioArrivo;
        this.dataPartenza = dataPartenza;
        this.durata = durata;
        this.ritardo = ritardo;
        this.compagniaAerea = compagniaAerea;
        this.stato = statoDelVolo;
        this.prenotazioni = new ArrayList<Prenotazione>();

    }
    /**
     * Restituisce una rappresentazione testuale leggibile del volo,
     * includendo codice, compagnia, orari e tratte.
     *
     * @return stringa formattata con i dettagli del volo
     */

    public String toString(){

        return "\n\nVOLO: " + this.codice +" " + this.compagniaAerea+
                "\n\nIn Partenza da " + this.origine+ " in data "+this.dataPartenza+" alle "+this.orarioPartenza+
                "\n\nIn arrivo a" + this.destinazione+" alle " +this.orarioArrivo+
                "\n\n(" + this.durata+")";
    }

    /**
     * Restituisce il codice identificativo del volo.
     *
     * @return codice del volo
     */
    public String getCodice() {
        return codice;
    }
    /**
     * Imposta il codice identificativo del volo.
     *
     * @param codice codice univoco del volo
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     * Restituisce la città o l'aeroporto di origine del volo.
     *
     * @return origine del volo
     */
    public String getOrigine() {
        return origine;
    }

    /**
     * Imposta l'origine del volo.
     *
     * @param origine città o aeroporto da cui parte il volo
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * Restituisce la destinazione del volo.
     *
     * @return città o aeroporto di destinazione
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     * Imposta la destinazione del volo.
     *
     * @param destinazione città o aeroporto di arrivo del volo
     */
    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    /**
     * Restituisce la durata stimata del volo.
     *
     * @return durata del volo (es. "2h30m")
     */
    public String getDurata() {
        return durata;
    }

    /**
     * Imposta la durata stimata del volo.
     *
     * @param durata durata del volo (formato stringa)
     */
    public void setDurata(String durata) {
        this.durata = durata;
    }

    /**
     * Restituisce il nome della compagnia aerea che opera il volo.
     *
     * @return compagnia aerea
     */
    public String getCompagniaAerea() {
        return compagniaAerea;
    }

    /**
     * Imposta la compagnia aerea del volo.
     *
     * @param compagniaAerea nome della compagnia aerea
     */
    public void setCompagniaAerea(String compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }

    /**
     * Restituisce l'orario di partenza programmato.
     *
     * @return orario di partenza (formato "HH:mm")
     */
    public String getOrarioPartenza() {
        return orarioPartenza;
    }

    /**
     * Imposta l'orario di partenza del volo.
     *
     * @param orarioPartenza orario previsto di partenza (formato "HH:mm")
     */
    public void setOrarioPartenza(String orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    /**
     * Restituisce l'orario di arrivo previsto.
     *
     * @return orario di arrivo (formato "HH:mm")
     */
    public String getOrarioArrivo() {
        return orarioArrivo;
    }

    /**
     * Imposta l'orario di arrivo del volo.
     *
     * @param orarioArrivo orario previsto di arrivo (formato "HH:mm")
     */
    public void setOrarioArrivo(String orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    /**
     * Restituisce la data di partenza del volo.
     *
     * @return data di partenza (formato "dd/MM/yyyy")
     */
    public String getDataPartenza() {
        return dataPartenza;
    }

    /**
     * Imposta la data di partenza del volo.
     *
     * @param dataPartenza data del volo (formato "dd/MM/yyyy")
     */
    public void setDataPartenza(String dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    /**
     * Restituisce il ritardo attuale del volo in minuti.
     *
     * @return ritardo in minuti (0 se puntuale)
     */
    public int getRitardo() {
        return ritardo;
    }

    /**
     * Imposta il ritardo attuale del volo.
     *
     * @param ritardo ritardo espresso in minuti
     */
    public void setRitardo(int ritardo) {
        this.ritardo = ritardo;
    }

    /**
     * Restituisce lo stato corrente del volo.
     *
     * @return stato del volo (es. Programmato, Atterrato)
     */
    public StatoVolo getStato() {
        return stato;
    }

    /**
     * Imposta lo stato del volo.
     *
     * @param stato nuovo stato del volo
     */
    public void setStato(StatoVolo stato) {
        this.stato = stato;
    }

    /**
     * Restituisce la lista delle prenotazioni associate al volo.
     *
     * @return elenco prenotazioni del volo
     */
    public ArrayList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Imposta l'elenco delle prenotazioni associate al volo.
     *
     * @param prenotazioni lista di prenotazioni
     */
    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

}

