package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;
/**
 * Interfaccia per la gestione delle operazioni relative ai bagagli nel sistema aeroportuale.
 * <p>
 * Include metodi per la creazione, il recupero, l’aggiornamento dello stato e la segnalazione
 * dello smarrimento dei bagagli associati a prenotazioni o voli monitorati.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see model.Bagaglio
 */

public interface BagaglioDAO {
    /**
     * Restituisce il bagaglio associato a un utente generico e al codice fornito.
     *
     * @param idUser ID dell'utente che ha effettuato la prenotazione
     * @param codiceBagaglio codice identificativo del bagaglio
     * @return oggetto {@link Bagaglio} corrispondente, oppure {@code null} se non trovato
     */
    Bagaglio getBagagliPerUtenteGenerico(int idUser, int codiceBagaglio);

    /**
     * Restituisce un bagaglio specificato, accessibile da un amministratore.
     *
     * @param codiceBagaglio codice identificativo del bagaglio
     * @return oggetto {@link Bagaglio} trovato, oppure {@code null}
     */
    Bagaglio getBagagliPerAmministartore(int codiceBagaglio);

    /**
     * Imposta lo stato attuale del bagaglio.
     *
     * @param codiceBagaglio codice identificativo del bagaglio
     * @param stato nuovo stato da assegnare (es. Caricato, Ritirabile, Smarrito)
     */
    void setStato(int codiceBagaglio, String stato);

    /**
     * Crea un nuovo bagaglio associato a una prenotazione esistente.
     *
     * @param id_prenotazione ID della prenotazione a cui il bagaglio è legato
     */
    void create(int id_prenotazione);

    /**
     * Aggiunge alla lista fornita tutti i bagagli attualmente contrassegnati come smarriti.
     *
     * @param bagagliSmarriti lista da riempire con i bagagli smarriti
     */
    void getSmarriti(ArrayList<Bagaglio> bagagliSmarriti);

    /**
     * Segnala lo smarrimento di un bagaglio da parte di un amministratore.
     * <p>
     * L'operazione ha successo solo se l'amministratore è autorizzato a gestire il volo associato.
     * </p>
     *
     * @param idAmministratore ID dell'amministratore che segnala lo smarrimento
     * @param codice codice del bagaglio
     * @return {@code true} se la segnalazione ha avuto successo, altrimenti {@code false}
     */
    boolean segnalaSmarrimento(int idAmministratore, int codice);

    /**
     * Recupera tutti i bagagli gestiti da un determinato amministratore.
     *
     * @param idAmministratore ID dell'amministratore
     * @param bagagli lista da riempire con i bagagli trovati
     */
    void getBagagliPerAmministratore(int idAmministratore, ArrayList<Bagaglio> bagagli);

    /**
     *
     * Chiude la connessione al database
     */
    void closeConnection();
}