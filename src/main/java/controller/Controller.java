package controller;

import ImplementazioneDAO.*;
import dao.*;
import model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Controller {

    private int codice; //CODICE DI SICUREZZA PER AMMINISTRATORI

    private static int num_biglietto=0;

    private Utente user;

    public Controller () {

        user = new Utente(null, null, null);
        codice = 123;
    }

    //SIGN IN UTENTE GENERICO - fatto con DAO

    public void inizializzaUtenteGenerico(String login, String password, String email) {

        UtenteGenericoDAO u = new ImplementazioneUtenteGenericoDAO();
        u.signIn(email, login, password);

    }

    //SIGN IN AMMINISTRATORE - fatto con DAO

    public boolean inizializzaAmministratore(int codice, String login, String password, String email) {

        if(codice==this.codice) {

            AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
            a.signIn(email, login, password);

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


    //LOG-IN - fatto con DAO

    public boolean logIn(String classe, String login, String password) {

        Utente utente = null;

        if (classe.equals("Amministratore")) {

            AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
            utente = a.logIn(login, password);
        }

        else {

            UtenteGenericoDAO ug = new ImplementazioneUtenteGenericoDAO();
            utente = ug.logIn(login, password);
        }

        if(utente!=null){

            user=utente;
            return true;
        }

        else
            return false;
    }

    //LOG-OUT - fatto con DAO

    public void logOut(){

        user=null;
    }

    //RICERCA VOLO - fatto con DAO

    public ArrayList<Volo> ricercaVoli(String tipo, String compagniaAerea, String codice, String dataPartenza, String destinazione) {

        ArrayList<Volo> voli = new ArrayList<>();

        VoloDAO v=new ImplementazioneVoloDAO();
        v.getAll(voli);


        return user.ricercaVolo(voli, tipo, compagniaAerea, codice, dataPartenza, destinazione);
    }

    //MONITORA BAGAGLIO - fatto con DAO

    public Bagaglio monitoraBagaglioUtenteGenerico(int codice){ //UTENTE GENERICO

        Bagaglio bagaglio=null;

        BagaglioDAO b = new ImplementazioneBagaglioDAO();
        bagaglio = b.getBagagliPerUtenteGenerico(user.getId(), codice);

        return bagaglio;
    }

    public Bagaglio monitoraBagaglioAmministratore(int codice){ //AMMINISTRATORE

        Bagaglio bagaglio=null;

        BagaglioDAO b = new ImplementazioneBagaglioDAO();
        bagaglio = b.getBagagliPerAmministartore(codice);

        return bagaglio;
    }

    //CERCA PRENOTAZIONE - fatto con DAO

    public ArrayList<Prenotazione> cercaPrenotazioneUtenteGenerico(String codiceVolo, String dataVolo, String orarioPartenza) {

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
        p.getPrenotazioniPerUtenteGenerico(user.getId(), prenotazioni);


        return user.cercaPrenotazione(prenotazioni, codiceVolo, dataVolo, orarioPartenza);
    }

    public ArrayList<Prenotazione> cercaPrenotazioneAmministratore(String codiceVolo, String dataVolo, String orarioPartenza){

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
        p.getAll(prenotazioni);


        return user.cercaPrenotazione(prenotazioni, codiceVolo, dataVolo, orarioPartenza);
    }


    //UTENTE GENERICO

    //PRENOTA UN VOLO - fatto con DAO

    public int prenotaVolo(String codiceVolo, String posto, String classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita, int NumBagagli) {

        Volo volo = null;
        int codice;

        VoloDAO v= new ImplementazioneVoloDAO();
        volo = v.getVoloPerId(codiceVolo);


        if (volo != null)

            codice = 0;

        else if (!volo.getStato().equals(StatoVolo.Programmato))

            codice = -1;

        else {

            String id_volo = volo.getCodice();

            int id_passeggero;
            int id_prenotazione;

            PasseggeroDAO passeggero = new ImplementazionePasseggeroDAO();
            id_passeggero = passeggero.create(nome, cognome, numTelefono, numDocumento, sesso, dataNascita);


            PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
            id_prenotazione=p.create(posto, classe, id_passeggero, id_volo, user.getId());



            BagaglioDAO bagaglio = new ImplementazioneBagaglioDAO();

            for (int i = 0; i < NumBagagli; i++)
                bagaglio.create(id_prenotazione);

            volo.setPrenotazione(id_prenotazione);

            codice = id_prenotazione;

        }

        return codice;
    }

    //CHECK IN - fatto con DAO

    public String checkIn(int codicePrenotazione) {

        Prenotazione prenotazione = null;

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
        prenotazione= p.getPerIdUtenteGenerico(user.getId(), codicePrenotazione);

        if(prenotazione!=null) {
            p.checkIn(codicePrenotazione, num_biglietto);
            prenotazione.setNumBiglietto(num_biglietto);
            prenotazione.setStato(StatoPrenotazione.Confermata);

            num_biglietto++;

            return prenotazione.toString();
        }

        else
            return " ";
    }

    //SEGNALA SMARRIMENTO - fatto con DAO

    public boolean segnalaSmarrimento(int codice) {

        BagaglioDAO b = new ImplementazioneBagaglioDAO();

        if(b.segnalaSmarrimento(user.getId(), codice))
            return true;

        else
            return false;
    }

    //MODIFICA PRENOTAZIONE - fatto con DAO

    public boolean modificaPrenotazione(int codicePrenotazione, String posto, String classeVolo, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero, char sessoPasseggero, int Numbagagli ) {

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();

        Prenotazione pr = p.getPerIdAmministratore(codicePrenotazione);

        if(pr!=null){
            p.modifica(codicePrenotazione, posto, classeVolo);

            PasseggeroDAO passeggero = new ImplementazionePasseggeroDAO();
            passeggero.modifica(codicePrenotazione, nomePasseggero, cognomePasseggero, numDocumentoPasseggero, sessoPasseggero);

            BagaglioDAO bagaglio = new ImplementazioneBagaglioDAO();

            for(int i=0; i<Numbagagli; i++)
                bagaglio.create(codicePrenotazione);

            return true;
        }

        else
            return false;
    }


    //AMMINISTRATORE

    //CERCA PASSEGGERO - fatto con DAO

    public void cercaPasseggero(ArrayList<Passeggero> passeggeri, ArrayList<Prenotazione> prenotazioni, ArrayList<Volo> voli, String nome, String cognome, String numDocumento, char sesso) {

        ArrayList<Passeggero> passeggeriTotali = new ArrayList<>();

        PasseggeroDAO pas = new ImplementazionePasseggeroDAO();
        pas.getAll(passeggeriTotali);

        PrenotazioneDAO prenotazione = new ImplementazionePrenotazioneDAO();
        VoloDAO volo = new ImplementazioneVoloDAO();

        passeggeri= ((Amministratore) user).cercaPasseggero(passeggeriTotali, nome, cognome, numDocumento, sesso);

        int id_prenotazione, id_volo;

        for(Passeggero p: passeggeri){

            id_prenotazione= pas.getIdPrenotazione(p.getId());
            prenotazioni.add(prenotazione.getPerIdAmministratore(id_prenotazione));
        }

        for(Prenotazione pr : prenotazioni){

            id_volo= prenotazione.getIdVolo(pr.getId());
            voli.add(volo.getPerId(id_volo));
        }

    }

    //INSERISCI VOLO - fatto con DAO

    public boolean inserisciVolo(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoVoloString) {

        if(this.isDate(dataPartenza) && this.isTime(orarioPartenza) && this.isTime(orarioArrivo) && (origine.equals("Napoli") || destinazione.equals("Napoli"))){

            VoloDAO v = new ImplementazioneVoloDAO();
            v.create(compagniaAerea, codice, destinazione, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, stato);

            AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
            a.inserisciVolo(user.getId(), idVolo);

            return true;
        }

        else
            return false;
    }

    //MODIFICA VOLO - fatto con DAO

    public boolean aggiornaVolo(String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo) {

        ArrayList<Volo> voli= new ArrayList<>();

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getVoliPerAmministratore(user.getId(), voli);




        for(Volo volo: voli){

            if(volo.getCodice().equals(codiceVolo)) {
                v.aggiornaVolo(volo, luogo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);
                return true;

            }
        }

        return false;
    }

    //ASSEGNA GATE - fatto con DAO

    public boolean assegnaGate(String codiceVolo, int gate) {

        ArrayList<Volo> voli= new ArrayList<>();

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getVoliPerAmministratore(user.getId(), voli);


         */

        for(Volo volo: voli){

            if(volo.getCodice().equals(codiceVolo)) {

                v.setGate(codiceVolo, gate);
                return true;

            }
        }

        return false;
    }

    //AGGIORNA STATO BAGAGLIO - fatto con DAO

    public void aggiornaStatoBagaglio(int codice, String statoBagaglio) {

        BagaglioDAO b = new ImplementazioneBagaglioDAO();

        b.setStato(stato, codice);
    }

    //VISUALIZZA SMARRIMENTI - fatto con DAO

    public ArrayList<Bagaglio> visualizzaBagagliSmarriti() {

        ArrayList<Bagaglio> bagagliSmarriti = new ArrayList<>();

        BagaglioDAO b = new ImplementazioneBagaglioDAO();


        b.getSmarriti(bagagliSmarriti);

        return bagagliSmarriti;
    }

    public Utente getUser() {
        return user;
    }

    public void setUser(Utente user) {
        this.user = user;
    }
}
