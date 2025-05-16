package model;

import interfaces.Volo;

import java.util.ArrayList;

public class VoloInArrivo implements Volo {

    private String codice;
    private String origine;
    private String destinazione;
    private String durata;
    private String compagniaAerea;

    private String orarioPartenza;
    private String orarioArrivo;

    private String dataPartenza;

    private int ritardo;

    private StatoVolo stato;

    private ArrayList<Prenotazione> prenotazioni;
    private int numGate;

    public VoloInArrivo(String compagniaAerea, String codice, String origine, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo, int numGate) {

            this.codice = codice;
            this.orarioPartenza = orarioPartenza;
            this.orarioArrivo = orarioArrivo;
            this.dataPartenza = dataPartenza;
            this.durata = durata;
            this.ritardo = ritardo;
            this.compagniaAerea = compagniaAerea;
            this.stato = statoDelVolo;
            this.prenotazioni = new ArrayList<Prenotazione>();
            this.origine = origine;
            this.numGate = numGate;
            this.destinazione = "Napoli";
        }

        public int getNumGate() {
            return numGate;
        }

        public void setNumGate(int numGate) {
            this.numGate = numGate;
        }

        @Override
        public String getCodice() {
            return codice;
        }

        @Override
        public void setCodice(String codice) {
            this.codice = codice;
        }

        @Override
        public String getOrigine() {
            return origine;
        }

        @Override
        public void setOrigine(String origine) {
            this.origine = origine;
        }

        @Override
        public String getDestinazione() {
            return destinazione;
        }

        @Override
        public void setDestinazione(String destinazione) {
            this.destinazione = destinazione;
        }

        @Override
        public String getDurata() {
            return durata;
        }

        @Override
        public void setDurata(String durata) {
            this.durata = durata;
        }

        @Override
        public String getCompagniaAerea() {
            return compagniaAerea;
        }

        @Override
        public void setCompagniaAerea(String compagniaAerea) {
            this.compagniaAerea = compagniaAerea;
        }

        @Override
        public String getOrarioPartenza() {
            return orarioPartenza;
        }

        @Override
        public void setOrarioPartenza(String orarioPartenza) {
            this.orarioPartenza = orarioPartenza;
        }

        @Override
        public String getOrarioArrivo() {
            return orarioArrivo;
        }

        @Override
        public void setOrarioArrivo(String orarioArrivo) {
            this.orarioArrivo = orarioArrivo;
        }

        @Override
        public String getDataPartenza() {
            return dataPartenza;
        }

        @Override
        public void setDataPartenza(String dataPartenza) {
            this.dataPartenza = dataPartenza;
        }

        @Override
        public int getRitardo() {
            return ritardo;
        }

        @Override
        public void setRitardo(int ritardo) {
            this.ritardo = ritardo;
        }

        @Override
        public StatoVolo getStatoDelVolo() {
            return null;
        }

        @Override
        public void setStatoDelVolo(StatoVolo statoDelVolo) {

        }

        @Override
        public StatoVolo getStato() {
            return stato;
        }

        @Override
        public void setStato(StatoVolo stato) {
            this.stato = stato;
        }

        @Override
        public ArrayList<Prenotazione> getPrenotazioni() {
            return prenotazioni;
        }

        @Override
        public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
            this.prenotazioni = prenotazioni;
        }
}

