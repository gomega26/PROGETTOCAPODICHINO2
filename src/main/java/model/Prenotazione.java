package model;

import java.util.ArrayList;

public class Prenotazione {

    private static int cont=101;
    private int id;
    private int numBiglietto;
    private String posto;
    private StatoPrenotazione stato;
    private ClasseVolo classeVolo;
    private Passeggero passeggero;
    private Volo volo;
    private ArrayList<Bagaglio> bagagli;

    //COSTRUTTORE
    public Prenotazione(String posto, ClasseVolo classe){

        this.id = cont;
        this.stato = StatoPrenotazione.InAttesa;
        this.posto = posto;
        this.classeVolo = classe;
        this.bagagli=new ArrayList<>();

        cont++;
    }

    public String toString(){

        return "Prenotazione: " + this.id + " " +this.stato+"\n\nMr./Ms. " + this.passeggero.getNome() +" " +this.passeggero.getCognome()+ "\n\n"+
        this.volo.toString()+"\n\nCLASSE: " + this.classeVolo + "  POSTO: " + this.posto+
        "\n\nNumero bagagli: "+this.bagagli.size()+"\n\nNUMERO BIGLIETTO :" +this.numBiglietto;
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

    public Volo getVolo() {
        return volo;
    }

    public void setVolo(Volo volo) {
        this.volo = volo;
    }

    public int getId() {
        return id;
    }

    public Passeggero getPasseggero() {
        return passeggero;
    }

    public void setPasseggero(Passeggero passeggero) {
        this.passeggero = passeggero;
    }

    public ArrayList<Bagaglio> getBagagli() {
        return bagagli;
    }

    public void setBagagli(ArrayList<Bagaglio> bagagli) {
        this.bagagli = bagagli;
    }
}
