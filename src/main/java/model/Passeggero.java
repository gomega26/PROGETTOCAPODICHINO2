package model;

import java.util.ArrayList;

/**
 * Rappresenta un passeggero all'interno del sistema.
 * <p>
 * Ogni passeggero è identificato da informazioni anagrafiche, un numero di telefono,
 * un documento identificativo e la data di nascita. Questa classe viene utilizzata per
 * associare un passeggero a una prenotazione e a un volo specifico.
 * </p>
 * @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class Passeggero {

    private int id;
    private String nome;
    private String cognome;
    private String numTelefono;
    private String numDocumento;
    private char sesso;
    private String dataNascita;


    /**
     * Costruisce un nuovo oggetto {@code Passeggero} con i dati forniti.
     *
     * @param id identificativo univoco del passeggero
     * @param nome nome di battesimo
     * @param cognome cognome del passeggero
     * @param numTelefono numero di telefono per contatti
     * @param numDocumento numero del documento identificativo (es. passaporto)
     * @param sesso genere del passeggero ('M', 'F', ecc.)
     * @param dataNascita data di nascita nel formato "yyyy-MM-dd"
     */
//COSTRUTTORE
    public Passeggero(int id, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita) {

        this.nome = nome;
        this.cognome = cognome;
        this.numTelefono = numTelefono;
        this.numDocumento = numDocumento;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
    }

    /**
     * Restituisce una rappresentazione testuale del passeggero.
     *
     * @return stringa in formato "Mr./Ms. Nome Cognome"
     */
    @Override
    public String toString() {

        return "Mr./Ms. " + this.getNome() +" " +this.getCognome();
    }
//SETTERS E GETTERS
    /**
     * Restituisce il nome del passeggero.
     *
     * @return il nome del passeggero
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta un nuovo nome per il passeggero.
     *
     * @param nome nuovo nome del passeggero
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome del passeggero.
     *
     * @return il cognome del passeggero
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta un nuovo cognome per il passeggero.
     *
     * @param cognome nuovo cognome del passeggero
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }



    /**
     * Restituisce il numero di telefono del passeggero.
     *
     * @return numero di telefono del passeggero
     */
    public String getNumTelefono() {
        return numTelefono;
    }

    /**
     * Imposta un nuovo numero di telefono per il passeggero.
     *
     * @param numTelefono nuovo numero di telefono
     */
    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    /**
     * Restituisce il numero del documento identificativo del passeggero.
     *
     * @return numero del documento identificativo
     */
    public String getNumDocumento() {
        return numDocumento;
    }


    /**
     * Imposta il numero del documento identificativo del passeggero.
     *
     * @param numDocumento nuovo numero documento
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * Restituisce il sesso del passeggero.
     *
     * @return sesso del passeggero ('M', 'F', ecc.)
     */
    public char getSesso() {
        return sesso;
    }


    /**
     * Imposta il sesso del passeggero.
     *
     * @param sesso nuovo valore per il sesso del passeggero (es. 'M' o 'F')
     */
    public void setSesso(char sesso) {
        this.sesso = sesso;
    }


    /**
     * Restituisce la data di nascita del passeggero.
     *
     * @return data di nascita del passeggero
     */
    public String getDataNascita() {
        return dataNascita;
    }



    /**
     * Imposta la data di nascita del passeggero.
     *
     * @param dataNascita nuova data di nascita in formato stringa (es. "1990-05-23")
     */
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }


    /**
     * Restituisce l'identificativo numerico del passeggero.
     *
     * @return identificativo numerico del passeggero
     */
    public int getId() {
        return id;
    }


    /**
     * Imposta l'identificativo univoco del passeggero.
     *
     * @param id nuovo identificativo univoco del passeggero
     */
    public void setId(int id) {
        this.id = id;
    }

}
