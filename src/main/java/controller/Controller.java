package controller;

import model.*;

import java.util.ArrayList;

public class Controller {
    // Ho inserito tutte le classi dentro l'entità Controller
    private ArrayList<Volo> voli;
    private Utente user;

    public Controller () {
        // ISTANZIO I MODELLI(ENTITA')
        voli = new ArrayList<Volo>();
    }

    public void inizializzaUtenteGenerico(String login, String password, String email) {

        user = new UtenteGenerico(login, password, email);
    }

    public void inizializzaAmministratore(String login, String password, String email, String nome, String cognome) {

        user = new Amministratore(login, password, email, nome, cognome);
    }

    // Cerca voli disponibili tra due città
    public ArrayList<Volo> ricercaVoli(String tipo, String compagniaAerea, String codice, String dataPartenza, String destinazione) {
        return user.ricercaVolo(this.voli, tipo, compagniaAerea, codice, dataPartenza, destinazione);
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



    //  UTENTE
    /*
    // Restituisce i voli prenotati da un determinato utente
    public ArrayList<Volo> visualizzaVolo(String loginUtente) {
        return Utente.getVoloPrenotato(loginUtente);
    }

    // Cerca voli disponibili tra due città
    public ArrayList<Volo> ricercaVolo(String origine, String compagniaAerea, String codice, String dataPartenza, String destinazione) {
        return Utente(origine, destinazione, compagniaAerea, codice);
    }

    // Restituisce lo stato attuale di un bagaglio
    public StatoDelBagaglio monitoraBagaglio(String codiceBagaglio) {
        return Bagaglio.getStato(codiceBagaglio);
    }

    // Verifica le credenziali dell'utente per il login
    public boolean login(String email, String password) {
        return Utente.autentica(email, password);
    }

    // Cerca i passeggeri in base al nome e cognome
    public ArrayList<Passeggero> cercaPasseggero(String nome, String cognome) {
        return Passeggero.cerca(nome, cognome);
    }

    //  UTENTE GENERICO

    // Effettua una prenotazione per un volo
    public void prenotaVolo(String loginUtente, String codiceVolo, int numeroPosto, ClasseVolo classe) {
        UtenteGenerico.creaPrenotazione(loginUtente, codiceVolo, numeroPosto, classe);
    }

    // Cerca una prenotazione tramite il numero di biglietto
    public Prenotazione cercaPrenotazione(int numBiglietto) {
        return Prenotazione.getPrenotazione(numBiglietto);
    }

    // Modifica il posto di una prenotazione esistente
    public void modificaPrenotazione(int numBiglietto, int nuovoPosto) {
        Prenotazione.modifica(numBiglietto, nuovoPosto);
    }

    // Segnala un bagaglio come smarrito
    public void segnalaSmarrimento(String codiceBagaglio) {
        Bagaglio.segnalaSmarrimento(codiceBagaglio);
    }

    // Esegue il check-in per un biglietto
    public void checkIn(int numBiglietto) {
        Prenotazione.checkIn(numBiglietto);
    }

    // AMMINISTRATORE

    private static ArrayList<Volo> voli = new ArrayList<>();
    private static ArrayList<Gate> gates = new ArrayList<>();

    // Inserisce un volo
    public static void inserisciVolo(Volo volo) {
        voli.add(volo);
    }

    // Aggiorna un volo cercandolo per codice
    public static void aggiornaVolo(String codiceVolo, Volo nuovoVolo) {
        Volo daSostituire = null;

        for (Volo v : voli) {
            if (v.getCodice().equals(codiceVolo)) {
                daSostituire = v;
                break;
            }
        }

        if (daSostituire != null) {
            voli.remove(daSostituire);
            voli.add(nuovoVolo);
        }
    }

    // Assegna un gate (lo aggiunge solo se non è già presente)
    public static void assegnaGate(Gate gate) {
        boolean trovato = false;

        for (Gate g : gates) {
            if (g.equals(gate)) {
                trovato = true;
                break;
            }
        }

        if (!trovato) {
            gates.add(gate);
        }
    }

    // Aggiorna lo stato del bagaglio
    public static void aggiornaStatoBagaglio(Bagaglio bagaglio, StatoBagaglio nuovoStato) {
        bagaglio.setStato(nuovoStato);
    }

    // Restituisce l'elenco dei bagagli smarriti
    public static ArrayList<Bagaglio> visualizzaSmarrimenti(ArrayList<Bagaglio> tuttiIBagagli) {
        ArrayList<Bagaglio> smarriti = new ArrayList<>();

        for (Bagaglio b : tuttiIBagagli) {
            if (b.getStato() == StatoDelBagaglio.smarrito) {
                smarriti.add(b);
            }
        }

        return smarriti;
    }
}
*/