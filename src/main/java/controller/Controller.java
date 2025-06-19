package controller;

import model.*;

import java.util.ArrayList;

public class Controller {

    //LISTA VOLI
    private ArrayList<Volo> voli;

    //LISTA UTENTI
    private ArrayList<Utente> utenti;

    Utente user;

    public Controller () {
        voli = new ArrayList<Volo>();
        utenti = new ArrayList<Utente>();
        user = new Utente(null, null, null);
    }

    public void inizializzaUtenteGenerico(String login, String password, String email) {

        UtenteGenerico user = new UtenteGenerico(login, password, email);

        utenti.add(user);
    }

    public void inizializzaAmministratore(String login, String password, String email, String nome, String cognome) {

        Amministratore user = new Amministratore(login, password, email, nome, cognome);

        utenti.add(user);
    }

    //UTENTE

    //LOG-IN

    public boolean logIn(String login, String password) {

        for(Utente u: utenti){

            if(u.logIn(login, password)) {
                user = u;
                return true;
            }
        }

        return false;
    }

    public boolean isAutenticato() {
        return user.isAutenticato();
    }

    //RICERCA VOLO

    public ArrayList<Volo> ricercaVoli(String tipo, String compagniaAerea, String codice, String dataPartenza, String destinazione) {
        return user.ricercaVolo(this.voli, tipo, compagniaAerea, codice, dataPartenza, destinazione);
    }

    //MONITORA BAGAGLIO

    public Bagaglio monitoraBagaglioUtenteGenerico(int codice){ //UTENTE GENERICO

        ArrayList<Bagaglio> bagagli= new ArrayList<>();

        for (Prenotazione p: ((UtenteGenerico)user).getPrenotazioniEffetuate()) {
            bagagli.add(p.getBagaglio());
        }

        return user.monitoraBagaglio(bagagli, codice);
    }

    public Bagaglio monitoraBagaglioAmministratore(int codice){ //AMMINISTRATORE

        ArrayList<Bagaglio> bagagli= new ArrayList<>();

        for(Volo v: ((Amministratore)user).getVoliGestiti()) {
            for (Prenotazione p : v.getPrenotazioni()) {
                bagagli.add(p.getBagaglio());
            }
        }

        return user.monitoraBagaglio(bagagli, codice);
    }

    //CERCA PRENOTAZIONE

    public ArrayList<Prenotazione> cercaPrenotazioneUtenteGenerico(String codiceVolo, String dataVolo, String orarioPartenza) {

        ArrayList<Prenotazione> prenotazioni = ((UtenteGenerico)user).getPrenotazioniEffetuate();

        return user.cercaPrenotazione(prenotazioni, codiceVolo, dataVolo, orarioPartenza);
    }

    public ArrayList<Prenotazione> cercaPrenotazioneAmministratore(String codiceVolo, String dataVolo, String orarioPartenza){

        ArrayList<Prenotazione> prenotazioni = null;

        for(Volo v: ((Amministratore)user).getVoliGestiti()){

            prenotazioni=v.getPrenotazioni();
        }

        return user.cercaPrenotazione(prenotazioni, codiceVolo, dataVolo, orarioPartenza);
    }


    //UTENTE GENERICO

    //PRENOTA UN VOLO

    public void prenotaVolo(String codiceVolo, String posto, ClasseVolo classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita){

        Volo volo=null;

        for (Volo v : voli) {
            if (v.getCodice().equals(codiceVolo)) {
                volo = v;
            }
        }

        ((UtenteGenerico)user).prenotaVolo(volo, posto,classe,nome,cognome,numTelefono,numDocumento, sesso,dataNascita);
    }

    //CHECK IN

    public void checkIn(int codicePrenotazione, boolean bagaglio) {

        Prenotazione prenotazione = null;

        for(Prenotazione p : ((UtenteGenerico)user).getPrenotazioniEffetuate()){

            if(p.getId()==codicePrenotazione)
                prenotazione = p;
        }

        ((UtenteGenerico)user).checkIn(prenotazione, bagaglio);
    }

    //SEGNALA SMARRIMENTO

    public void segnalaSmarrimento(int numeroBagaglio) {

        Bagaglio bagaglio = null;

        for(Prenotazione p : ((UtenteGenerico)user).getPrenotazioniEffetuate()){

            if(p.getBagaglio().getCodice()==numeroBagaglio)
                bagaglio=p.getBagaglio();
        }

        ((UtenteGenerico)user).segnalaSmarrimento(bagaglio);
    }

    //MODIFICA PRENOTAZIONE

    public void modificaPrenotazione(int codicePrenotazione, String posto, ClasseVolo classeVolo, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero, char sessoPasseggero, boolean bagaglio ) {

        Prenotazione prenotazione = null;

        Bagaglio b = null;

        for(Prenotazione p : ((UtenteGenerico)user).getPrenotazioniEffetuate()){

            if(p.getId()==codicePrenotazione)
                prenotazione = p;
        }

        if(bagaglio) {
            b = new Bagaglio();
        }

        ((UtenteGenerico)user).modificaPrenotazione(prenotazione,posto, classeVolo, nomePasseggero,cognomePasseggero,numDocumentoPasseggero, sessoPasseggero, b);
    }


    //AMMINISTRATORE

    //CERCA PASSEGGERO

    public ArrayList<Passeggero> cercaPasseggero(String nome, String cognome, String numDocumento, char sesso) {

        return ((Amministratore) user).cercaPasseggero(nome, cognome, numDocumento, sesso);
    }

    //INSERISCI VOLO

    public void inserisciVolo(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoVoloString) {

        StatoVolo stato = StatoVolo.valueOf(statoVoloString);

        Volo nuovoVolo;

        if (origine.equals("Napoli"))
            nuovoVolo = new VoloInPartenza(compagniaAerea, codice, destinazione, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, stato, 0);

        else
            nuovoVolo = new VoloInArrivo(compagniaAerea, codice, origine, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, stato);

        voli.add(nuovoVolo);

        ((Amministratore) user).inserisciVolo(nuovoVolo);
    }

    //MODIFICA VOLO

    public void aggiornaVolo(String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo) {

        Volo volo=null;

        for(Volo v: ((Amministratore)user).getVoliGestiti()){

            if(v.getCodice().equals(codiceVolo))
                volo=v;
        }

        ((Amministratore)user).aggiornaVolo(volo, luogo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);
    }

    //ASSEGNA GATE

    public void assegnaGate(String codiceVolo, int gate) {

        Volo volo = null;

        for(Volo v: ((Amministratore)user).getVoliGestiti()){

            if(v.getCodice().equals(codiceVolo))
                volo=v;
        }

        ((Amministratore)user).assegnaGate(gate, (VoloInPartenza) volo);
    }

    //AGGIORNA STATO BAGAGLIO

    public void aggiornaStatoBagaglio(Bagaglio bagaglio, StatoBagaglio statoBagaglio) {

        ((Amministratore)user).aggiornaStatoBagaglio(bagaglio, statoBagaglio);
    }

    //VISUALIZZA SMARRIMENTI

    public ArrayList<Bagaglio> visualizzaBagagliSmarriti() {

        return ((Amministratore) user).visualizzaSmarrimenti();
    }


    public ArrayList<Volo> getVoli() {
        return voli;
    }

    public void setVoli(ArrayList<Volo> voli) {
        this.voli = voli;
    }

    public Utente getUser() {
        return user;
    }

    public void setUser(Utente user) {
        this.user = user;
    }
}