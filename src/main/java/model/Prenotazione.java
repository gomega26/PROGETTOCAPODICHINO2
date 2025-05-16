package model;

import interfaces.Volo;

public class Prenotazione {

    private int numBiglietto;
    private String posto;
    private StatoPrenotazione stato;
    private ClasseVolo classeVolo;
    private Passeggero passeggero;
    private Volo volo;
    private Bagaglio bagaglio;

    //COSTRUTTORE
    public Prenotazione(String posto, ClasseVolo classe){

        this.stato = StatoPrenotazione.InAttesa;
        this.posto = posto;
        this.classeVolo = classe;
    }

    //GETTERS E SETTERS
    public int getNumBiglietto() {
        return numBiglietto;
    }

    public void setNumBiglietto(int numBiglietto) {
        this.numBiglietto = numBiglietto;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public StatoPrenotazione getStatoPrenotazione() {
        return stato;
    }

    public void setStatoVolo(StatoPrenotazione stato) {
        this.stato = stato;
    }

    public ClasseVolo getClasseVolo() {
        return classeVolo;
    }

    public void setClasseVolo(ClasseVolo classeVolo) {
        this.classeVolo = classeVolo;
    }

    public StatoPrenotazione getStato() {
        return stato;
    }

    public void setStato(StatoPrenotazione stato) {
        this.stato = stato;
    }

    public Passeggero getPasseggero() {
        return passeggero;
    }

    public void setPasseggero(Passeggero passeggero) {
        this.passeggero = passeggero;
    }

    public Volo getVolo() {
        return volo;
    }

    public void setVolo(Volo volo) {
        this.volo = volo;
    }

    public Bagaglio getBagaglio() {
        return bagaglio;
    }

    public void setBagaglio(Bagaglio bagaglio) {
        this.bagaglio = bagaglio;
    }
}
