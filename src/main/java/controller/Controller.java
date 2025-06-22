package controller;

import model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Controller {

    //LISTA VOLI
    private ArrayList<Volo> voli;

    //LISTA UTENTI
    private ArrayList<Utente> utenti;

    int codice; //CODICE DI SICUREZZA PER AMMINISTRATORI

    Utente user;

    public Controller () {
        voli = new ArrayList<Volo>();
        utenti = new ArrayList<Utente>();
        user = new Utente(null, null, null);
        codice = 123;
    }

    public void inizializzaUtenteGenerico(String login, String password, String email) {

        user = new UtenteGenerico(login, password, email);

        utenti.add(user);
    }

    public boolean inizializzaAmministratore(int codice, String login, String password, String email) {

        if(codice==this.codice) {

            user = new Amministratore(login, password, email);

            utenti.add(user);

            return true;
        }

        else
            return false;
    }

    //CONTROLLI SU DATA E ORARI

    public boolean isDate(String date){

        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isTime(String time){

        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    //UTENTE

    public boolean isAutenticato(){

        return user.isAutenticato();
    }

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

    //LOG-OUT

    public boolean logOut(){

        return user.logOut();
    }

    //RICERCA VOLO

    public ArrayList<Volo> ricercaVoli(String tipo, String compagniaAerea, String codice, String dataPartenza, String destinazione) {
        return user.ricercaVolo(this.voli, tipo, compagniaAerea, codice, dataPartenza, destinazione);
    }

    //MONITORA BAGAGLIO

    public Bagaglio monitoraBagaglioUtenteGenerico(int codice){ //UTENTE GENERICO

        ArrayList<Bagaglio> bagagli= new ArrayList<>();

        for (Prenotazione p: ((UtenteGenerico)user).getPrenotazioniEffetuate()) {
            for(Bagaglio b: p.getBagagli()){
                bagagli.add(b);
            }
        }

        return user.monitoraBagaglio(bagagli, codice);
    }

    public Bagaglio monitoraBagaglioAmministratore(int codice){ //AMMINISTRATORE

        ArrayList<Bagaglio> bagagli= new ArrayList<>();

        for(Volo v: ((Amministratore)user).getVoliGestiti()) {
            for (Prenotazione p : v.getPrenotazioni()) {
                for(Bagaglio b: p.getBagagli()){

                    bagagli.add(b);
                }
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

    public int prenotaVolo(String codiceVolo, String posto, ClasseVolo classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita, int NumBagagli){

        Volo volo=null;
        int codice=0;

        for (Volo v : voli) {
            if (v.getCodice().equals(codiceVolo))
                volo = v;
        }

        if(volo!=null) {

            System.out.println("VOlo trovato");

            codice = ((UtenteGenerico) user).prenotaVolo(volo, posto, classe, nome, cognome, numTelefono, numDocumento, sesso, dataNascita, NumBagagli);
        }

        return codice;
    }

    //CHECK IN

    public String checkIn(int codicePrenotazione) {

        Prenotazione prenotazione = null;

        for(Prenotazione p : ((UtenteGenerico)user).getPrenotazioniEffetuate()){

            if(p.getId()==codicePrenotazione)
                prenotazione = p;
        }

        if(prenotazione!=null)
            return ((UtenteGenerico)user).checkIn(prenotazione);

        else
            return " ";
    }

    //SEGNALA SMARRIMENTO

    public boolean segnalaSmarrimento(int numeroBagaglio) {

        Bagaglio bagaglio = null;

        for(Prenotazione p : ((UtenteGenerico)user).getPrenotazioniEffetuate()){

            for(Bagaglio b: p.getBagagli()){

                if(b.getCodice()==numeroBagaglio)
                    bagaglio=b;
            }
        }

        if(bagaglio!=null) {
            ((UtenteGenerico) user).segnalaSmarrimento(bagaglio);
            return true;
        }

        else
            return false;
    }

    //MODIFICA PRENOTAZIONE

    public boolean modificaPrenotazione(int codicePrenotazione, String posto, ClasseVolo classeVolo, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero, char sessoPasseggero, int Numbagagli ) {

        Prenotazione prenotazione = null;

        ArrayList<Bagaglio> bagagli = new ArrayList<>();

        for(Prenotazione p : ((UtenteGenerico)user).getPrenotazioniEffetuate()){

            if(p.getId()==codicePrenotazione)
                prenotazione = p;
        }

        for(int i=0; i<Numbagagli; i++) {
            bagagli.add(new Bagaglio());
        }

        if(prenotazione!=null) {
            ((UtenteGenerico) user).modificaPrenotazione(prenotazione, posto, classeVolo, nomePasseggero, cognomePasseggero, numDocumentoPasseggero, sessoPasseggero, bagagli);
            return true;
        }

        else
            return false;
    }


    //AMMINISTRATORE

    //CERCA PASSEGGERO

    public ArrayList<Prenotazione> cercaPasseggero(String nome, String cognome, String numDocumento, char sesso) {

        return ((Amministratore) user).cercaPasseggero(nome, cognome, numDocumento, sesso);
    }

    //INSERISCI VOLO

    public boolean inserisciVolo(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoVoloString) {

        StatoVolo stato = StatoVolo.valueOf(statoVoloString);

        Volo nuovoVolo=null;

        if(this.isDate(dataPartenza) && this.isTime(orarioPartenza) && this.isTime(orarioArrivo) && (origine.equals("Napoli") || destinazione.equals("Napoli"))) {

            if (origine.equals("Napoli"))
                nuovoVolo = new VoloInPartenza(compagniaAerea, codice, destinazione, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, stato, 0);

            else
                nuovoVolo = new VoloInArrivo(compagniaAerea, codice, origine, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, stato);

            voli.add(nuovoVolo);

            ((Amministratore) user).inserisciVolo(nuovoVolo);

            return true;
        }

        else
            return false;
    }

    //MODIFICA VOLO

    public boolean aggiornaVolo(String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo) {

        Volo volo=null;

        for(Volo v: ((Amministratore)user).getVoliGestiti()){

            if(v.getCodice().equals(codiceVolo))
                volo=v;
        }

        if(volo!=null) {
            ((Amministratore)user).aggiornaVolo(volo, luogo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);
            return true;
        }

        else
            return false;
    }

    //ASSEGNA GATE

    public boolean assegnaGate(String codiceVolo, int gate) {

        Volo volo = null;

        for(Volo v: ((Amministratore)user).getVoliGestiti()){

            if(v.getCodice().equals(codiceVolo))
                volo=v;
        }

        if(volo!=null) {
            ((Amministratore)user).assegnaGate(gate, (VoloInPartenza) volo);
            return true;
        }

        else
            return false;
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