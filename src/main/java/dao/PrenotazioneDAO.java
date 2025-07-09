package dao;

import model.Prenotazione;
import java.util.List;
import java.util.ArrayList;

/**
 * Interfaccia DAO per la gestione delle prenotazioni.
 * <p>
 * Fornisce operazioni per creare, recuperare, modificare e gestire prenotazioni associate a voli e utenti.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see model.Prenotazione
 */
public interface PrenotazioneDAO {

    // INSERT

    /**
     * Crea una nuova prenotazione per un volo specifico.
     *
     * @param posto il posto assegnato (es. "12A")
     * @param classe la classe del volo (es. "Economy", "Business")
     * @param id_passeggero l'identificativo del passeggero associato
     * @param id_volo l'identificativo del volo
     * @param idUser l'identificativo dell'utente che effettua la prenotazione
     * @param numBagagli numero di bagagli inclusi nella prenotazione
     * @throws IllegalArgumentException se uno dei parametri forniti è nullo o invalido
     */
    void create(String posto, String classe, int id_passeggero, String id_volo, int idUser, int numBagagli);

    // SELECT

    /**
     * Recupera tutte le prenotazioni associate a un dato utente.
     *
     * @param idUser l'identificativo dell’utente
     * @param prenotazioni lista da popolare con le prenotazioni recuperate
     * @throws NullPointerException se la lista fornita è {@code null}
     */
    void getPrenotazioniPerUtenteGenerico(int idUser, List<Prenotazione> prenotazioni);

    /**
     * Recupera tutte le prenotazioni presenti nel sistema.
     *
     * @param prenotazioni lista da popolare con tutte le prenotazioni disponibili
     */
    void getAll(ArrayList<Prenotazione> prenotazioni);

    /**
     * Recupera una prenotazione a partire dall'id utente e dal codice prenotazione.
     *
     * @param idUser identificativo dell’utente
     * @param codicePrenotazione codice identificativo della prenotazione
     * @return l’oggetto {@link Prenotazione} corrispondente, oppure {@code null} se non trovato
     */
    Prenotazione getPerIdUtenteGenerico(int idUser, int codicePrenotazione);

    /**
     * Recupera una prenotazione conoscendone solo il codice.
     *
     * @param codicePrenotazione codice identificativo della prenotazione
     * @return la prenotazione corrispondente, oppure {@code null} se non trovata
     */
    Prenotazione getPerId(int codicePrenotazione);

    /**
     * Restituisce l'id della prenotazione associata a un passeggero.
     *
     * @param idPasseggero identificativo del passeggero
     * @return l'identificativo della prenotazione
     */
    int getIdPerPasseggero(int idPasseggero);

    /**
     * Restituisce l'identificativo del volo associato alla prenotazione.
     *
     * @param idPrenotazione identificativo della prenotazione
     * @return l’id del volo
     */
    String getIdVolo(int idPrenotazione);

    /**
     * Restituisce l'id del passeggero associato alla prenotazione.
     *
     * @param idPrenotazione identificativo della prenotazione
     * @return l'id del passeggero
     */
    int getIdPasseggero(int idPrenotazione);

    // UPDATE

    /**
     * Registra il check-in di una prenotazione assegnando il numero di biglietto.
     *
     * @param idPrenotazione identificativo della prenotazione
     * @param numBiglietto numero del biglietto assegnato al check-in
     * @throws IllegalStateException se la prenotazione non è valida per il check-in
     */
    void checkIn(int idPrenotazione, int numBiglietto);

    /**
     * Modifica una prenotazione esistente aggiornando posto, classe e numero di bagagli.
     *
     * @param codicePrenotazione codice della prenotazione da modificare
     * @param posto nuovo posto da assegnare
     * @param classeVolo nuova classe di volo
     * @param numBagagli numero aggiornato di bagagli
     * @throws IllegalArgumentException se uno dei parametri è invalido o nullo
     */
    void modifica(int codicePrenotazione, String posto, String classeVolo, int numBagagli);
}
