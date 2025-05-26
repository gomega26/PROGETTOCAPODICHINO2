package model;

import java.util.ArrayList;

public class Volo {

    protected String codice;
    protected String origine;
    protected String destinazione;
    protected String durata;
    protected String compagniaAerea;

    protected String orarioPartenza;
    protected String orarioArrivo;

    protected String dataPartenza;

    protected int ritardo;

    protected StatoVolo stato;

    protected ArrayList<Prenotazione> prenotazioni;

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

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getCompagniaAerea() {
        return compagniaAerea;
    }

    public void setCompagniaAerea(String compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }

    public String getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(String orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public String getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(String orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public String getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(String dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public int getRitardo() {
        return ritardo;
    }

    public void setRitardo(int ritardo) {
        this.ritardo = ritardo;
    }

    public StatoVolo getStato() {
        return stato;
    }


    public void setStato(StatoVolo stato) {
        this.stato = stato;
    }


    public ArrayList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }


    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
