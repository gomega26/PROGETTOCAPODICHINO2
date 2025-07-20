package controller;

import ImplementazioneDAO.*;
import dao.*;
import model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Classe di controllo centrale dell'applicazione.
 * <p>
 * Si occupa di orchestrare l'interazione tra l'interfaccia grafica, il livello DAO
 * e i modelli di dominio. Gestisce autenticazione, registrazione, validazione e
 * recupero dati da utilizzare nelle viste.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class Controller {

    private int codice; // Codice di sicurezza richiesto per registrare un amministratore
    private static int num_biglietto = 0; // Contatore per l'emissione progressiva dei biglietti
    private Utente user; // Utente attualmente autenticato (può essere null)

    /**
     * Costruisce un nuovo {@code Controller} inizializzando lo stato interno.
     * Imposta il codice di sicurezza per gli amministratori.
     */
    public Controller() {

        user = null;
        codice = 123;
    }

    //RESTITUISCE TUTTI I VOLI

    /**
     * Recupera l'elenco completo dei voli presenti nel sistema.
     *
     * @param voli lista da riempire con i voli estratti dal database
     */
    public void getVoli(ArrayList<Volo> voli){ //TESTATO -COMPLETO

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getAll(voli);
        v.closeConnection();
    }

    //RESTITUISCE TUTTI I VOLI GESTITI DALL'AMMINISTRATORE

    /**
     * Recupera tutti i voli attualmente in partenza.
     *
     * @param voli lista da riempire con i voli in partenza
     */
    public void getVoliGestiti(ArrayList<Volo> voli){ //TESTATO -COMPLETO

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getVoliPerAmministratore(user.getId(), voli);
        v.closeConnection();
    }

    /**
     * Fornisce tutti i passeggeri e le relative prenotazioni
     * @param prenotazioni
     * @param passeggeri
     */

    public void getPasseggeri(ArrayList<Prenotazione> prenotazioni, ArrayList<Passeggero> passeggeri){

        PasseggeroDAO pas = new ImplementazionePasseggeroDAO();
        PrenotazioneDAO p= new ImplementazionePrenotazioneDAO();

        pas.getAll(passeggeri);

        for(Passeggero passeggero: passeggeri) {

            prenotazioni.add(p.getPerIdPasseggero(passeggero.getId()));
        }

        p.closeConnection();
        pas.closeConnection();
    }

    /**
     * Fornisce tutte le prenotazioni per l'utente registrato, con relativi voli e info passeggero
     *
     * @param prenotazioni
     * @param voli
     * @param passeggeri
     *
     *
     */

    public void getPrenotazioniPerUtenteGenerico( ArrayList<Prenotazione> prenotazioni, ArrayList<Volo> voli, ArrayList<Passeggero> passeggeri){

        VoloDAO v = new ImplementazioneVoloDAO();
        PrenotazioneDAO p= new ImplementazionePrenotazioneDAO();
        PasseggeroDAO pas = new ImplementazionePasseggeroDAO();

        String idVolo;
        int idPasseggero;

        p.getPrenotazioniPerUtenteGenerico(user.getId(), prenotazioni);

        for(Prenotazione prenotazione : prenotazioni){

            idVolo = p.getIdVolo(prenotazione.getId());
            voli.add(v.getVoloPerId(idVolo));
            idPasseggero=p.getIdPasseggero(prenotazione.getId());
            passeggeri.add(pas.getPerId(idPasseggero));
        }
    }

//SIGN IN UTENTE GENERICO - //TESTATO -COMPLETO

    /**
     * Registra un nuovo utente generico nel sistema.
     *
     * @param login nome utente scelto
     * @param password password associata all'account
     * @param email indirizzo email dell'utente
     */
    public void inizializzaUtenteGenerico(String login, String password, String email) {

        UtenteGenericoDAO u = new ImplementazioneUtenteGenericoDAO();
        u.signIn(email, login, password);
        u.closeConnection();
    }

//SIGN IN AMMINISTRATORE - //TESTATO -COMPLETO

    /**
     * Registra un nuovo amministratore se il codice di sicurezza è corretto.
     *
     * @param codice codice di sicurezza fornito
     * @param login nome utente scelto
     * @param password password dell'amministratore
     * @param email indirizzo email
     * @return {@code true} se il codice era valido e l'inserimento ha avuto successo, {@code false} altrimenti
     */
    public boolean inizializzaAmministratore(int codice, String login, String password, String email) {

        if(codice==this.codice) {

            AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
            a.signIn(email, login, password);
            a.closeConnection();

            return true;
        }

        else
            return false;
    }

//CONTROLLI SU DATA E ORARI -TESTATO -COMPLETO


    /**
     * Verifica se una stringa rappresenta una data valida.
     *
     * @param date stringa in formato ISO (es. "2025-06-15")
     * @return {@code true} se la stringa è una data valida, {@code false} in caso di errore
     */
    public boolean isDate(String date){

        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Errore sul controllo della data");
            return false;
        }
    }


    /**
     * Verifica se una stringa rappresenta un orario valido.
     *
     * @param time stringa nel formato "HH:mm"
     * @return {@code true} se il formato dell'orario è valido, {@code false} altrimenti
     */
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

    /**
     * Esegue il login dell’utente.
     * <p>
     * Tenta prima l'autenticazione come amministratore e, in caso fallisca, come utente generico.
     * In caso di successo, imposta l’attributo {@code user}.
     * </p>
     *
     * @param login nome utente
     * @param password password utente
     * @return {@code true} se l’autenticazione ha avuto successo, altrimenti {@code false}
     */
    public boolean logIn(String login, String password) {

        AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
        user = a.logIn(login, password);
        a.closeConnection();

        if(user==null){

            UtenteGenericoDAO ug = new ImplementazioneUtenteGenericoDAO();
            user = ug.logIn(login, password);
            ug.closeConnection();
        }

        if(user!=null)
            return true;

        else
            return false;
    }

//LOG-OUT - TESTATO -COMPLETO

    /**
     * Esegue il logout dell’utente.
     * <p>
     * Annulla l’utente autenticato rimuovendolo dal campo {@code user}.
     * </p>
     */
    public void logOut(){ //TESTATO COMPLETO

        user=null;
    }

//RICERCA VOLO - TESTATO -COMPLETO

    /**
     * Filtra i voli in base ai parametri specificati.
     *
     * @param voliTrovati lista in cui verranno inseriti i voli che soddisfano i criteri
     * @param tipo tipo di volo (es. {@code VoloInPartenza}, {@code VoloInArrivo})
     * @param compagniaAerea nome della compagnia aerea
     * @param codice codice identificativo del volo
     * @param dataPartenza data di partenza
     * @param destinazione destinazione del volo
     * @return la lista {@code voliTrovati} filtrata
     */
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

        voloDAO.closeConnection();
        return voliTrovati;
    }

//MONITORA BAGAGLIO -

    /**
     * Permette a un utente generico di monitorare lo stato di un proprio bagaglio.
     *
     * @param codice codice identificativo del bagaglio
     * @return l'oggetto {@link Bagaglio} se trovato, altrimenti {@code null}
     */
    public Bagaglio monitoraBagaglioUtenteGenerico(int codice){ //UTENTE GENERICO

        Bagaglio bagaglio=null;

        BagaglioDAO b = new ImplementazioneBagaglioDAO();
        bagaglio = b.getBagagliPerUtenteGenerico(user.getId(), codice);

        b.closeConnection();

        return bagaglio;
    }

    /**
     * Permette a un amministratore di accedere a qualsiasi bagaglio.
     *
     * @param codice codice identificativo del bagaglio
     * @return oggetto {@link Bagaglio} corrispondente, oppure {@code null}
     */
    public Bagaglio monitoraBagaglioAmministratore(int codice){

        Bagaglio bagaglio=null;

        BagaglioDAO b = new ImplementazioneBagaglioDAO();
        bagaglio = b.getBagaglioPerAmministratore(codice);

        b.closeConnection();

        return bagaglio;
    }

//CERCA PRENOTAZIONE -

    /**
     * Cerca le prenotazioni corrispondenti ai parametri specificati.
     * I risultati trovati vengono aggiunti alle liste fornite:
     * - {@code listaPrenotazioni} contiene le prenotazioni trovate
     * - {@code listaVoli} contiene i voli associati
     * - {@code listaPasseggeri} contiene i passeggeri legati alle prenotazioni
     *
     * @param listaPrenotazioni lista risultante di prenotazioni corrispondenti
     * @param listaVoli lista risultante dei voli corrispondenti
     * @param listaPasseggeri lista risultante dei passeggeri associati
     * @param codiceVolo codice del volo cercato
     * @param dataVolo data di partenza del volo
     * @param orarioPartenza orario di partenza previsto
     */
    public void cercaPrenotazione(ArrayList<Prenotazione> listaPrenotazioni, ArrayList<Volo> listaVoli, ArrayList<Passeggero> listaPasseggeri, String codiceVolo, String dataVolo, String orarioPartenza){

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

            if(!volo.getCodice().equals(codiceVolo) && !codiceVolo.isEmpty())
                continue;
            if(!volo.getDataPartenza().equals(dataVolo) && !dataVolo.isEmpty())
                continue;
            if(!volo.getOrarioPartenza().equals(orarioPartenza) && !orarioPartenza.isEmpty())
                continue;

            voliTrovati.add(volo);
        }

        for(Volo volo : voliTrovati){

            for(Prenotazione prenotazione : prenotazioni){

                if(p.getIdVolo(prenotazione.getId()).equals(volo.getCodice())) {
                    listaPrenotazioni.add(prenotazione);
                    listaVoli.add(v.getVoloPerId(volo.getCodice()));

                    idPasseggro=p.getIdPasseggero(prenotazione.getId());
                    listaPasseggeri.add(pas.getPerId(idPasseggro));
                }
            }
        }

        p.closeConnection();
        v.closeConnection();
        pas.closeConnection();
    }

//UTENTE GENERICO

//PRENOTA UN VOLO -

    /**
     * Prenota un volo per l’utente attualmente autenticato.
     * <p>
     * Crea un nuovo passeggero, una prenotazione associata e genera i bagagli legati alla prenotazione.
     * Se il volo non è programmato o non esiste, restituisce un codice di errore.
     * </p>
     *
     * @param codiceVolo codice del volo da prenotare
     * @param posto posto selezionato
     * @param classe classe di volo (es. Economy, Business)
     * @param nome nome del passeggero
     * @param cognome cognome del passeggero
     * @param numTelefono numero di telefono
     * @param numDocumento documento identificativo del passeggero
     * @param sesso genere ('M', 'F'...)
     * @param dataNascita data di nascita
     * @param numBagagli numero di bagagli da associare
     * @return ID della prenotazione se completata, 0 se il volo non esiste, -1 se lo stato del volo non è programmato
     */
    public int prenotaVolo(String codiceVolo, String posto, String classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita, int numBagagli) {

        Volo volo = null;
        int codice;

        VoloDAO v= new ImplementazioneVoloDAO();
        volo = v.getVoloPerId(codiceVolo);


        if (volo == null)

            codice = 0;

        else if (!volo.getStato().equals(StatoVolo.Programmato) || volo.getDestinazione().equals("Napoli"))

            codice = -1;

        else if(this.isDate(dataNascita)){

            int id_passeggero;
            int id_prenotazione;

            PasseggeroDAO passeggero = new ImplementazionePasseggeroDAO();
            id_passeggero = passeggero.create(nome, cognome, numTelefono, numDocumento, sesso, dataNascita);


            PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
            p.create(posto, classe, id_passeggero, codiceVolo, user.getId(), numBagagli);

            Prenotazione prenotazione =  p.getPerIdPasseggero(id_passeggero);

            BagaglioDAO bagaglio = new ImplementazioneBagaglioDAO();

            for (int i = 0; i < numBagagli; i++){

                bagaglio.create(prenotazione.getId());
            }

            codice = prenotazione.getId();

            p.closeConnection();
            bagaglio.closeConnection();
            passeggero.closeConnection();
        }

        else
            codice=-2;

        v.closeConnection();

        return codice;
    }

//CHECK IN -

    /**
     * Esegue il check-in per una prenotazione associata all’utente autenticato.
     * <p>
     * Assegna un numero di biglietto, aggiorna lo stato della prenotazione e restituisce un riepilogo.
     * </p>
     *
     * @param codicePrenotazione codice identificativo della prenotazione
     * @return stringa contenente i dettagli del passeggero, della prenotazione e del volo, oppure stringa vuota se non valida
     */
    public String checkIn(int codicePrenotazione) {

        Prenotazione prenotazione = null;
        Volo volo = null;
        Passeggero passeggero = null;

        String idVolo="";
        int idPasseggero=0;

        PrenotazioneDAO p = new ImplementazionePrenotazioneDAO();
        prenotazione= p.getPerIdUtenteGenerico(user.getId(), codicePrenotazione);

        if(prenotazione!=null) {
            p.checkIn(codicePrenotazione, num_biglietto);
            prenotazione.setNumBiglietto(num_biglietto);
            prenotazione.setStato(StatoPrenotazione.Confermata);

            num_biglietto++;

            idVolo=p.getIdVolo(prenotazione.getId());

            idPasseggero=p.getIdPasseggero(prenotazione.getId());

            VoloDAO v = new ImplementazioneVoloDAO();
            volo = v.getVoloPerId(idVolo);

            PasseggeroDAO pas = new ImplementazionePasseggeroDAO();
            passeggero = pas.getPerId(idPasseggero);


            p.closeConnection();
            v.closeConnection();
            pas.closeConnection();

            return passeggero.toString() + prenotazione.toString() + volo.toString() ;
        }

        else{

            p.closeConnection();
            return "";
        }
    }

//SEGNALA SMARRIMENTO -


    /**
     * Permette di segnalare lo smarrimento di un bagaglio.
     *
     * @param codice codice identificativo del bagaglio
     * @return {@code true} se l’operazione ha avuto successo, {@code false} in caso contrario
     */
    public boolean segnalaSmarrimento(int codice) {

        BagaglioDAO b = new ImplementazioneBagaglioDAO();

        if(b.segnalaSmarrimento(user.getId(), codice)) {
            b.closeConnection();
            return true;
        }

        else {
            b.closeConnection();
            return false;
        }
    }

//MODIFICA PRENOTAZIONE -

    /**
     * Modifica una prenotazione esistente con i nuovi dati forniti.
     * <p>
     * Aggiorna il posto, la classe e i dati del passeggero. Genera eventuali nuovi bagagli.
     * </p>
     *
     * @param codicePrenotazione identificativo della prenotazione da modificare
     * @param posto nuovo posto assegnato
     * @param classeVolo nuova classe di volo
     * @param nomePasseggero nuovo nome del passeggero
     * @param cognomePasseggero nuovo cognome
     * @param numDocumentoPasseggero nuovo numero documento
     * @param sessoPasseggero nuovo valore del sesso
     * @param numbagagli nuovo numero di bagagli
     * @return {@code true} se l’operazione ha avuto successo, altrimenti {@code false}
     */
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

            p.closeConnection();
            passeggero.closeConnection();
            bagaglio.closeConnection();

            return true;
        }

        else {
            p.closeConnection();
            return false;
        }
    }


//AMMINISTRATORE

//CERCA PASSEGGERO -

    /**
     * Cerca un passeggero nel sistema in base ai parametri anagrafici forniti.
     * <p>
     * Recupera i passeggeri che corrispondono ai criteri e associa automaticamente
     * le relative prenotazioni e voli. Richiede privilegi da amministratore.
     * </p>
     *
     * @param passeggeri lista da riempire con i passeggeri trovati
     * @param prenotazioni lista da riempire con le prenotazioni associate
     * @param nome nome del passeggero
     * @param cognome cognome del passeggero
     * @param numDocumento numero documento del passeggero
     * @param sesso genere del passeggero (es. 'M', 'F')
     */
    public void cercaPasseggero(ArrayList<Passeggero> passeggeri, ArrayList<Prenotazione> prenotazioni, String nome, String cognome, String numDocumento, char sesso) {

        ArrayList<Passeggero> passeggeriTotali = new ArrayList<>();

        PrenotazioneDAO prenotazione = new ImplementazionePrenotazioneDAO();
        PasseggeroDAO pas = new ImplementazionePasseggeroDAO();

        pas.getAll(passeggeriTotali);

        for (Passeggero p : passeggeriTotali) {
            if (!nome.isEmpty() && !p.getNome().equals(nome))
                continue;
            if (!cognome.isEmpty() && !p.getCognome().equals(cognome))
                continue;
            if (!numDocumento.isEmpty() && !p.getNumDocumento().equals(numDocumento))
                continue;
            if (sesso != ' ' && p.getSesso() != sesso)
                continue;

            passeggeri.add(p);
        }

        for(Passeggero passeggero: passeggeri)

            prenotazioni.add(prenotazione.getPerIdPasseggero(passeggero.getId()));

        System.out.println("DIMNESIONE ARRAY PASSEGGERI "+ passeggeri.size()+ "\nDIMENSIONE ARRAY PRENOTAZIONI "+prenotazioni.size());

        prenotazione.closeConnection();
        pas.closeConnection();
    }

//INSERISCI VOLO -
    /**
     * Inserisce un nuovo volo nel sistema se i parametri forniti risultano validi.
     * <p>
     * La creazione del volo avviene solo se:
     * - la stringa {@code dataPartenza} rappresenta una data valida nel formato {@code yyyy-MM-dd};
     * - le stringhe {@code orarioPartenza} e {@code orarioArrivo} sono orari validi nel formato {@code HH:mm};
     * - almeno uno tra {@code origine} o {@code destinazione} è uguale a "Napoli".
     * <p>
     * Se tutte le condizioni sono rispettate, il volo viene registrato nel database
     * e associato all'amministratore attualmente autenticato.
     *
     * @param compagniaAerea la compagnia aerea che opera il volo
     * @param codice codice identificativo univoco del volo
     * @param origine aeroporto o città di partenza
     * @param destinazione aeroporto o città di arrivo
     * @param orarioPartenza orario previsto di partenza (formato {@code HH:mm})
     * @param orarioArrivo orario previsto di arrivo (formato {@code HH:mm})
     * @param dataPartenza data di partenza del volo (formato {@code yyyy-MM-dd})
     * @param durata durata complessiva del volo (es. "2h30m")
     * @param ritardo ritardo previsto in minuti (0 se puntuale)
     * @param stato stato iniziale del volo (es. "Programmato", "Decollato", "Cancellato")
     * @return {@code true} se l’inserimento è andato a buon fine, {@code false} altrimenti
     */

    public boolean inserisciVolo(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String stato) {

        if(this.isDate(dataPartenza) && this.isTime(orarioPartenza) && this.isTime(orarioArrivo) && (origine.equals("Napoli") || destinazione.equals("Napoli"))){

            VoloDAO v = new ImplementazioneVoloDAO();

            v.create(compagniaAerea,  codice,  origine,  destinazione,  orarioPartenza,  orarioArrivo,  dataPartenza,  durata,  ritardo,  stato);
            v.closeConnection();

            AmministratoreDAO a = new ImplementazioneAmministratoreDAO();
            a.inserisciVolo(user.getId(), codice);
            a.closeConnection();

            return true;
        }

        return false;
    }

//MODIFICA VOLO - TESTATO COMPLETO

    /**
     * Aggiorna i dati di un volo esistente, se è stato inserito dall'amministratore attuale.
     * <p>
     * Ricerca il volo tra quelli creati dall’amministratore autenticato,
     * poi aggiorna i campi forniti: orari, data, durata, ritardo e stato.
     * Rileva anche se il volo è un {@code VoloInPartenza} o {@code VoloInArrivo}
     * per passare correttamente la tipologia al DAO.
     * </p>
     *
     * @param codiceVolo codice identificativo del volo
     * @param luogo nuova località (origine/destinazione) in base alla tipologia
     * @param orarioPartenza nuovo orario di partenza
     * @param orarioArrivo nuovo orario di arrivo
     * @param dataPartenza nuova data del volo
     * @param durata nuova durata
     * @param ritardo nuovo ritardo
     * @param statoDelVolo nuovo stato del volo
     * @return {@code true} se il volo è stato trovato e aggiornato, {@code false} altrimenti
     */
    public boolean aggiornaVolo(String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoDelVolo) {

        ArrayList<Volo> voli= new ArrayList<>();

        String tipologia;

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getVoliPerAmministratore(user.getId(), voli);

        for(Volo volo: voli){

            if(volo.getCodice().equals(codiceVolo)) {

                tipologia= volo.getClass().getSimpleName();

                v.aggiornaVolo(tipologia, codiceVolo, luogo, orarioPartenza, orarioArrivo, dataPartenza, durata, ritardo, statoDelVolo);

                v.closeConnection();

                return true;

            }
        }

        v.closeConnection();

        return false;
    }

//ASSEGNA GATE -


    /**
     * Assegna un gate fisico a un volo in partenza.
     *
     * @param codiceVolo codice del volo
     * @param gate numero del gate da assegnare
     * @return {@code true} se l’assegnazione ha avuto successo, altrimenti {@code false}
     */
    public boolean assegnaGate(String codiceVolo, int gate) {

        ArrayList<Volo> voli = new ArrayList<>();

        VoloDAO v = new ImplementazioneVoloDAO();
        v.getVoliPerAmministratore(user.getId(), voli);

        for (Volo volo : voli) {

            if (volo.getCodice().trim().equalsIgnoreCase(codiceVolo.trim())) {
                v.setGate(codiceVolo, gate);
                v.closeConnection();
                return true;
            }
        }

        v.closeConnection();

        return false;
    }

//AGGIORNA STATO BAGAGLIO - TESTATO COMPLETO

    /**
     * Aggiorna manualmente lo stato di un bagaglio specifico.
     * <p>
     * Disponibile solo per amministratori che abbiano accesso a quel bagaglio.
     * La modifica viene effettuata solo se il bagaglio appartiene a un volo gestito dall’amministratore corrente.
     * </p>
     *
     * @param codice codice identificativo del bagaglio da aggiornare
     * @param statoBagaglio nuovo stato da assegnare al bagaglio (es. "SMARRITO", "RITIRATO")
     */
    public boolean aggiornaStatoBagaglio(int codice, String statoBagaglio) {

        ArrayList<Bagaglio> bagagli = new ArrayList<>();

        BagaglioDAO b = new ImplementazioneBagaglioDAO();

        b.getBagagliPerAmministratore(user.getId(), bagagli);

        for(Bagaglio bagaglio : bagagli){

            if(bagaglio.getCodice()==codice) {
                b.setStato(codice, statoBagaglio);
                return true;
            }
        }

        b.closeConnection();

        return false;
    }

//VISUALIZZA SMARRIMENTI -

    /**
     * Restituisce la lista di tutti i bagagli attualmente smarriti.
     *
     * @return lista di oggetti {@link Bagaglio} in stato "Smarrito"
     */
    public ArrayList<Bagaglio> visualizzaBagagliSmarriti() {

        ArrayList<Bagaglio> bagagliSmarriti = new ArrayList<>();

        BagaglioDAO b = new ImplementazioneBagaglioDAO();

        b.getSmarriti(bagagliSmarriti);

        b.closeConnection();

        return bagagliSmarriti;
    }

    /**
     * Restituisce l’utente attualmente autenticato nel sistema.
     *
     * @return oggetto {@link Utente} attivo oppure {@code null}
     */
    public Utente getUser() {
        return user;
    }

    /**
     * Imposta l’utente attualmente attivo nel sistema.
     *
     * @param user oggetto {@link Utente} da registrare
     */
    public void setUser(Utente user) {
        this.user = user;
    }

}
