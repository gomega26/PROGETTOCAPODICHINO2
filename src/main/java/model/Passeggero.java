package model;

import java.util.ArrayList;

public class Passeggero {

    private int id;
    private String nome;
    private String cognome;
    private String numTelefono;
    private String numDocumento;
    private char sesso;
    private String dataNascita;


    //COSTRUTTORE
    public Passeggero(int id, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita) {

        this.nome = nome;
        this.cognome = cognome;
        this.numTelefono = numTelefono;
        this.numDocumento = numDocumento;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
    }

    //SETTERS E GETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
