package model;

import java.util.ArrayList;

public class Prenotazione {

    private int id;
    private int numBiglietto;
    private int numBagagli;
    private String posto;
    private StatoPrenotazione stato;
    private ClasseVolo classeVolo;

    //COSTRUTTORE
    public Prenotazione(int id, int numBagagli, String posto, String classe, String stato){

        this.id = id;
        this.posto = posto;
        this.classeVolo = ClasseVolo.valueOf(classe);
        this.stato = StatoPrenotazione.valueOf(stato);
        this.numBagagli = numBagagli;
    }

    public String toString(){

        return "\n\nPrenotazione: " + this.id + " " +this.stato+
        "\n\nCLASSE: " + this.classeVolo + "  POSTO: " + this.posto+
        "\n\nNumero bagagli: "+this.numBagagli+"\n\nNUMERO BIGLIETTO :" +this.numBiglietto;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumBagagli() {
        return numBagagli;
    }

    public void setNumBagagli(int numBagagli) {
        this.numBagagli = numBagagli;
    }
}
