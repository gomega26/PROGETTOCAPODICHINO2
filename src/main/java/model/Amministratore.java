package model;

import java.util.ArrayList;

public class Amministratore extends Utente {

    private String nome;
    private String cognome;
    private ArrayList<Volo> voliGestiti;

    //DATI PERSONALI
    private String codiceFiscale;
    private String dataDiNascita;
    private int stipendio;
    private char sesso;
    private String numTelefono;
    private String dataAssunzione;
    private String indirizzo;

    //COSTRUTTORE
    public Amministratore(String login, String password, String email, String nome, String cognome){

        super(login, password, email);
        this.autenticato = false;
        this.nome = nome;
        this.cognome = cognome;
        this.voliGestiti = new ArrayList<Volo>();
    }

    //INSERISCI DATI PERSONALI
    public void inserisciDati(int stipendio, char sesso, String codiceFiscale, String dataDiNascita, String numTelefono, String dataAssunzione, String indirizzo)throws NonAutenticato{

        if(this.autenticato) {
            this.stipendio = stipendio;
            this.sesso = sesso;
            this.codiceFiscale = codiceFiscale;
            this.dataDiNascita = dataDiNascita;
            this.numTelefono = numTelefono;
            this.dataAssunzione = dataAssunzione;
            this.indirizzo = indirizzo;
        }

        else{
            throw new NonAutenticato("Eseguire log-in");
        }
    }

    //CERCA PASSEGGERO
    public ArrayList<Passeggero> cercaPasseggero(String nome, String cognome, String numDocumento, char sesso) throws NonAutenticato{

        if(this.autenticato) {

            ArrayList<Passeggero> passeggeriTrovati = new ArrayList<Passeggero>();

            for (Volo v : voliGestiti) {

                for (Prenotazione p : v.prenotazioni) {

                    if (!nome.isEmpty() && !p.getPasseggero().getNome().equals(nome))
                        continue;
                    if (!cognome.isEmpty() && !p.getPasseggero().getCognome().equals(cognome))
                        continue;
                    if (!numDocumento.isEmpty() && !p.getPasseggero().getNumDocumento().equals(numDocumento))
                        continue;
                    if (sesso != '-' && p.getPasseggero().getSesso() != sesso)
                        continue;

                    passeggeriTrovati.add(p.getPasseggero());
                }
            }

            return passeggeriTrovati;
        }

        else
            throw new NonAutenticato("Eseguire log-in");

    }

    //INSERISCE UN NUOVO VOLO NELLA LISTA DEI VOLI
    public void inserisciVolo(Volo v) throws NonAutenticato {

        if (this.autenticato)
            voliGestiti.add(v);

        else
            throw new NonAutenticato("Eseguire log-in");

    }

    //MODIFICA UN VOLO
    public void aggiornaVolo(Volo volo, String orarioPartenza, String orarioArrivo, String dataPartenza, String dataArrivo, String durata, int ritardo, StatoVolo statoDelVolo) throws NonAutenticato {

        if (this.autenticato) {

            for (Volo v : voliGestiti) {

                if (v.equals(volo)) {


                    if (!orarioPartenza.isEmpty())
                        v.setOrarioPartenza(orarioPartenza);
                    if (!orarioArrivo.isEmpty())
                        v.setOrarioArrivo(orarioArrivo);
                    if (!dataPartenza.isEmpty())
                        v.setDataArrivo(dataArrivo);
                    if (!durata.isEmpty())
                        v.setDurata(durata);
                    if (ritardo != 0)
                        v.setRitardo(ritardo);
                    if (!statoDelVolo.toString().isEmpty())
                        v.setStato(statoDelVolo);
                }
            }
        }

        else
            throw new NonAutenticato("Eseguire log-in");

    }

    //ASSEGNA UN GATE AD UN VOLO IN PARTENZA
    public void assegnaGate(int gate, VoloInPartenza volo) throws NonAutenticato{

        if (this.autenticato) {

            for (Volo v : voliGestiti) {
                if (v.equals(volo))
                    ((VoloInPartenza) v).setNumGate(gate);
            }
        }

        else
            throw new NonAutenticato("Eseguire log-in");
    }

    //AGGIORNA LO STATO DI UN BAGAGLIO
    public void aggiornaStatoBagaglio(Bagaglio bagaglio, int codice, StatoBagaglio stato)throws NonAutenticato {

        if (this.autenticato){

            for (Volo v : voliGestiti) {
                for (Prenotazione p : v.getPrenotazioni()) {
                    if (p.getBagaglio().equals(bagaglio))
                        p.getBagaglio().setStatoBagaglio(stato);
                }
            }
        }

        else
            throw new NonAutenticato("Eseguire log-in");
    }

    //VISUALIZZA LA LISTA DEI BAGAGLI SMARRITI
    public ArrayList<Bagaglio> visualizzaSmarrimenti()throws NonAutenticato{

        ArrayList<Bagaglio> bagagliSmarriti = new ArrayList<Bagaglio>();

        if (this.autenticato) {

            for (Volo v: voliGestiti) {
                for(Prenotazione p : v.getPrenotazioni()) {
                    if (p.getBagaglio().getStato() == StatoBagaglio.Smarrito)
                        bagagliSmarriti.add(p.getBagaglio());
                }
            }

            return bagagliSmarriti;
        }
        else
            throw new NonAutenticato("Eseguire log-in");
    }

    //CERCA PRENOTAZIONE
    public ArrayList<Prenotazione> cercaPrenotazione(String codiceVolo, String dataVolo, String orarioPartenza, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero) throws NonAutenticato{

        ArrayList<Prenotazione> prenotazioniTrovate = new ArrayList<Prenotazione>();

        if (this.autenticato) {

            for (Volo v : voliGestiti) {
                for(Prenotazione p : v.getPrenotazioni()) {
                    if (!codiceVolo.isEmpty() && !p.getVolo().codice.equals(codiceVolo))
                        continue;
                    if (!dataVolo.isEmpty() && !p.getVolo().getDataPartenza().equals(dataVolo))
                        continue;
                    if (!orarioPartenza.isEmpty() && !p.getVolo().getOrarioPartenza().equals(orarioPartenza))
                        continue;
                    if (!nomePasseggero.isEmpty() && !p.getPasseggero().getNome().equals(nomePasseggero))
                        continue;
                    if (!cognomePasseggero.isEmpty() && !p.getPasseggero().getCognome().equals(cognomePasseggero))
                        continue;
                    if (!numDocumentoPasseggero.isEmpty() && !p.getPasseggero().getNumDocumento().equals(numDocumentoPasseggero))
                        continue;

                    prenotazioniTrovate.add(p);
                }
            }

            return prenotazioniTrovate;
        }

        else
            throw new NonAutenticato("Eseguire log-in");
    }

    //GETTERS
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public int getStipendio() {
        return stipendio;
    }

    public char getSesso() {
        return sesso;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public String getDataAssunzione() {
        return dataAssunzione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setStipendio(int stipendio) {
        this.stipendio = stipendio;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public void setDataAssunzione(String dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
