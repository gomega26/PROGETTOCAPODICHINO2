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

        user = null;
        codice = 123;
    }

    //RESTITUISCE TUTTI I VOLI

    public void getVoli(ArrayList<Volo> voli){ //TESTATO -COMPLETO

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getAll(voli);
    }

    //RESTITUISCE TUTTI I VOLI IN PARTENZA

    public void getVoliInPartenza(ArrayList<VoloInPartenza> voli){ //TESTATO -COMPLETO

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getVoliInPartenza(voli);
    }

    //SIGN IN UTENTE GENERICO - //TESTATO -COMPLETO

    public void inizializzaUtenteGenerico(String login, String password, String email) {

        UtenteGenericoDAO u = new ImplementazioneUtenteGenericoDAO();
        u.signIn(email, login, password);

    }

    //SIGN IN AMMINISTRATORE - //TESTATO -COMPLETO

    public boolean inizializzaAmministratore(int codice, String login, String password, String email) {

        if(codice==this.codice) {

            AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
            a.signIn(email, login, password);

            return true;
        }

        else
            return false;
    }

    //CONTROLLI SU DATA E ORARI -TESTATO -COMPLETO

    public boolean isDate(String date){

        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Errore sul controllo della data");
            return false;
        }
    }

    public boolean isTime(String time){

        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Errore sul controllo dell'orario");
            return false;
        }
    }


    //UTENTE


    //LOG-IN - TESTATO -COMPLETO

    public boolean logIn(String login, String password) {

        AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
        user = a.logIn(login, password);

        if(user==null){

            UtenteGenericoDAO ug = new ImplementazioneUtenteGenericoDAO();
            user = ug.logIn(login, password);
        }

        if(user!=null)
            return true;

        else
            return false;
    }

    //LOG-OUT - TESTATO -COMPLETO

    public void logOut(){

        user=null;
    }

    //RICERCA VOLO - TESTATO -COMPLETO

    public ArrayList<Volo> ricercaVoli(ArrayList<Volo> voliTrovati, String tipo, String compagniaAerea, String codice, String dataPartenza, String destinazione) {

        ArrayList<Volo> voli = new ArrayList<>();

        VoloDAO voloDAO=new ImplementazioneVoloDAO();
        voloDAO.getAll(voli);

        for(Volo v : voli){

            if(!tipo.isEmpty() && !v.getClass().getSimpleName().equals(tipo))
                continue;
            if(!compagniaAerea.isEmpty() && !v.getCompagniaAerea().equals(compagniaAerea))
                continue;
            if(!codice.isEmpty() && !v.getCodice().equals(codice))
                continue;
            if(!dataPartenza.isEmpty() && !v.getDataPartenza().equals(dataPartenza))
                continue;
            if(!destinazione.isEmpty() && !v.getDestinazione().equals(destinazione))
                continue;

            voliTrovati.add(v);
        }

        return voliTrovati;
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

    public void cercaPrenotazione(ArrayList<Prenotazione> listaPrenotazioni, ArrayList<Volo> listaVoli, ArrayList<Passeggero> listaPaseggeri, String codiceVolo, String dataVolo, String orarioPartenza){

        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        ArrayList<Volo> voli = new ArrayList<>();
        ArrayList<Volo> voliTrovati = new ArrayList<>();

        int idPasseggro=0;

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();

        if(user.getClass().getSimpleName().equals("Amministratore"))
            p.getAll(prenotazioni);
        else
            p.getPrenotazioniPerUtenteGenerico(user.getId(), prenotazioni);

        VoloDAO v= new ImplementazioneVoloDAO();
        v.getAll(voli);

        PasseggeroDAO pas = new ImplementazionePasseggeroDAO();

        for(Volo volo: voli){

            if(!volo.getCodice().equals(codiceVolo))
                continue;
            if(!volo.getDataPartenza().equals(dataVolo))
                continue;
            if(volo.getOrarioPartenza().equals(orarioPartenza))
                continue;

            voliTrovati.add(volo);
        }

        for(Volo volo : voliTrovati){

            for(Prenotazione prenotazione : prenotazioni){

                if(p.getIdVolo(prenotazione.getId()).equals(volo.getCodice())) {
                    listaPrenotazioni.add(prenotazione);
                    listaVoli.add(v.getVoloPerId(volo.getCodice()));

                    idPasseggro=p.getIdPasseggero(prenotazione.getId());
                    listaPaseggeri.add(pas.getPerId(idPasseggro));
                }
            }
        }
    }

    //UTENTE GENERICO

    //PRENOTA UN VOLO - fatto con DAO

    public int prenotaVolo(String codiceVolo, String posto, String classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita, int numBagagli) {

        Volo volo = null;
        int codice;

        VoloDAO v= new ImplementazioneVoloDAO();
        volo = v.getVoloPerId(codiceVolo);


        if (volo != null)

            codice = 0;

        else if (!volo.getStato().equals(StatoVolo.Programmato))

            codice = -1;

        else {

            int id_passeggero;
            int id_prenotazione;

            PasseggeroDAO passeggero = new ImplementazionePasseggeroDAO();
            id_passeggero = passeggero.create(nome, cognome, numTelefono, numDocumento, sesso, dataNascita);


            PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
            p.create(posto, classe, id_passeggero, codiceVolo, user.getId(), numBagagli);
            id_prenotazione= p.getIdPerPasseggero(id_passeggero);

            BagaglioDAO bagaglio = new ImplementazioneBagaglioDAO();

            for (int i = 0; i < numBagagli; i++)
                bagaglio.create(id_prenotazione);

            codice = id_prenotazione;

        }

        return codice;
    }

    //CHECK IN - fatto con DAO

    public String checkIn(int codicePrenotazione) {

        Prenotazione prenotazione = null;
        Volo volo = null;
        Passeggero passeggero = null;

        String idVolo="";
        int idPasseggero=0;

        VoloDAO v = new ImplementazioneVoloDAO();
        PasseggeroDAO pas = new ImplementazionePasseggeroDAO();

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
        prenotazione= p.getPerIdUtenteGenerico(user.getId(), codicePrenotazione);

        if(prenotazione!=null) {
            p.checkIn(codicePrenotazione, num_biglietto);
            prenotazione.setNumBiglietto(num_biglietto);
            prenotazione.setStato(StatoPrenotazione.Confermata);

            num_biglietto++;

            idVolo=p.getIdVolo(prenotazione.getId());
            idPasseggero=p.getIdPasseggero(prenotazione.getId());

            volo = v.getVoloPerId(idVolo);
            passeggero = pas.getPerId(idPasseggero);

            return passeggero.toString() + prenotazione.toString() + volo.toString() ;
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

    public boolean modificaPrenotazione(int codicePrenotazione, String posto, String classeVolo, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero, char sessoPasseggero, int numbagagli ) {

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();

        Prenotazione pr = p.getPerId(codicePrenotazione);

        if(pr!=null){
            p.modifica(codicePrenotazione, posto, classeVolo, numbagagli);

            PasseggeroDAO passeggero = new ImplementazionePasseggeroDAO();
            passeggero.modifica(codicePrenotazione, nomePasseggero, cognomePasseggero, numDocumentoPasseggero, sessoPasseggero);

            BagaglioDAO bagaglio = new ImplementazioneBagaglioDAO();

            for(int i=0; i<numbagagli; i++)
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

        int id_prenotazione;
        String id_volo;

        for(Passeggero p: passeggeri){

            id_prenotazione= prenotazione.getIdPerPasseggero(p.getId());
            prenotazioni.add(prenotazione.getPerId(id_prenotazione));
        }

        for(Prenotazione pr : prenotazioni){

            id_volo= prenotazione.getIdVolo(pr.getId());
            voli.add(volo.getVoloPerId(id_volo));
        }

    }

    //INSERISCI VOLO - fatto con DAO

    public boolean inserisciVolo(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String stato) {

        if(this.isDate(dataPartenza) && this.isTime(orarioPartenza) && this.isTime(orarioArrivo) && (origine.equals("Napoli") || destinazione.equals("Napoli"))){

            VoloDAO v = new ImplementazioneVoloDAO();

            v.create(compagniaAerea,  codice,  origine,  destinazione,  orarioPartenza,  orarioArrivo,  dataPartenza,  durata,  ritardo,  stato);


            AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
            a.inserisciVolo(user.getId(), codice);

            return true;
        }

        return false;
    }

    //MODIFICA VOLO - fatto con DAO

    public boolean aggiornaVolo(String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoDelVolo) {

        ArrayList<Volo> voli= new ArrayList<>();

        String tipologia;

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getVoliPerAmministratore(user.getId(), voli);

        for(Volo volo: voli){

            if(volo.getCodice().equals(codiceVolo)) {

                tipologia= volo.getClass().getSimpleName();

                v.aggiornaVolo(tipologia, codiceVolo, luogo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);
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

        ArrayList<Bagaglio> bagagli = new ArrayList<>();

        BagaglioDAO b = new ImplementazioneBagaglioDAO();

        b.getBagagliPerAmministratore(user.getId(), bagagli);

        for(Bagaglio bagaglio : bagagli){

            if(bagaglio.getCodice()==codice)
                b.setStato(codice, statoBagaglio);
        }
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
