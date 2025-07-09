package model;

import java.util.ArrayList;


 /**
 * Rappresenta una prenotazione associata a un volo e a un passeggero.
 * <p>
 * Ogni prenotazione contiene informazioni sul posto assegnato, lo stato (es.annullata),
 * la classe di volo selezionata (es. Economy, Business), e il numero di bagagli registrati.
 * Include inoltre un numero identificativo univoco e un eventuale numero di biglietto.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class Prenotazione {

    private int id;
    private int numBiglietto;
    private int numBagagli;
    private String posto;
    private StatoPrenotazione stato;
    private ClasseVolo classeVolo;

     /**
      * Costruisce un oggetto {@code Prenotazione} con i dati specificati.
      *
      * @param id identificativo univoco della prenotazione
      * @param numBagagli numero di bagagli associati alla prenotazione
      * @param posto codice del posto selezionato (es. "12B")
      * @param classe classe del volo (stringa che verrà convertita in {@link ClasseVolo})
      * @param stato stato della prenotazione (stringa che verrà convertita in {@link StatoPrenotazione})
      */
//COSTRUTTORE
    public Prenotazione(int id, int numBagagli, String posto, String classe, String stato){

        this.id = id;
        this.posto = posto;
        this.classeVolo = ClasseVolo.valueOf(classe);
        this.stato = StatoPrenotazione.valueOf(stato);
        this.numBagagli = numBagagli;
    }
     /**
      * Restituisce una rappresentazione testuale della prenotazione,
      * utile per visualizzazioni o debugging.
      *
      * @return descrizione della prenotazione
      */
    public String toString(){

        return "\n\nPrenotazione: " + this.id + " " +this.stato+
        "\n\nCLASSE: " + this.classeVolo + "  POSTO: " + this.posto+
        "\n\nNumero bagagli: "+this.numBagagli+"\n\nNUMERO BIGLIETTO :" +this.numBiglietto;
    }

     /**
      * Restituisce il numero di biglietto associato.
      *
      * @return numero biglietto
      */
//GETTERS E SETTERS
    public int getNumBiglietto() {
        return numBiglietto;
    }
     /**
      * Imposta il numero del biglietto.
      *
      * @param numBiglietto nuovo numero biglietto
      */
    public void setNumBiglietto(int numBiglietto) {
        this.numBiglietto = numBiglietto;
    }

     /**
      * Restituisce il posto assegnato.
      *
      * @return codice del posto
      */
    public String getPosto() {
        return posto;
    }


     /**
      * Imposta il posto assegnato.
      *
      * @param posto codice posto
      */
     public void setPosto(String posto) {
         this.posto = posto;
     }
     /**
      * Restituisce lo stato corrente della prenotazione.
      *
      * @return stato della prenotazione
      */
    public StatoPrenotazione getStatoPrenotazione() {
        return stato;
    }

     /**
      * Imposta lo stato della prenotazione (alias di {@link #setStato}).
      *
      * @param stato nuovo stato
      */
    public void setStatoVolo(StatoPrenotazione stato) {
        this.stato = stato;
    }

     /**
      * Restituisce la classe di volo selezionata.
      *
      * @return classe del volo
      */

    public ClasseVolo getClasseVolo() {
        return classeVolo;
    }

     /**
      * Imposta la classe di volo.
      *
      * @param classeVolo nuova classe
      */
    public void setClasseVolo(ClasseVolo classeVolo) {
        this.classeVolo = classeVolo;
    }


     /**
      * Restituisce lo stato corrente della prenotazione.
      *
      * @return stato della prenotazione
      */
    public StatoPrenotazione getStato() {
        return stato;
    }


     /**
      * Imposta lo stato della prenotazione.
      *
      * @param stato nuovo stato
      */
    public void setStato(StatoPrenotazione stato) {
        this.stato = stato;
    }


     /**
      * Restituisce l'identificativo univoco della prenotazione.
      *
      * @return id prenotazione
      */
    public int getId() {
        return id;
    }

     /**
      * Imposta l'identificativo univoco della prenotazione.
      *
      * @param id nuovo identificativo
      */
    public void setId(int id) {
        this.id = id;
    }

     /**
      * Restituisce il numero di bagagli associati.
      *
      * @return numero bagagli
      */
    public int getNumBagagli() {
        return numBagagli;
    }


     /**
      * Imposta il numero di bagagli.
      *
      * @param numBagagli nuovo numero
      */
    public void setNumBagagli(int numBagagli) {
        this.numBagagli = numBagagli;
    }
}
