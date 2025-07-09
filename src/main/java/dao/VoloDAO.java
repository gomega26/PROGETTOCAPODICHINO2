package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;
import java.sql.SQLException;
/**
 * Interfaccia DAO per la gestione dei dati relativi ai voli.
 * <p>
 * Fornisce metodi per recuperare, creare e aggiornare informazioni sui voli in partenza, in arrivo o associati a un amministratore.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see model.Volo
 */

public interface VoloDAO {

    /**
     * Recupera tutti i voli disponibili nel sistema.
     *
     * @param voli lista da popolare con gli oggetti {@link Volo} recuperati

     */
//SELECT
    void getAll(ArrayList<Volo> voli);

    /**
     * Recupera tutti i voli disponibili nel sistema.
     *
     * @param voli lista da popolare con gli oggetti {@link Volo} recuperati
     */
    void getVoliInPartenza(ArrayList<VoloInPartenza> voli);

    /**
     * Recupera i dettagli di un volo specifico tramite il codice identificativo.
     *
     * @param codiceVolo il codice identificativo del volo (es. "AZ1234")
     * @return l’oggetto {@link Volo} corrispondente al codice, oppure {@code null} se non trovato
     */
    Volo getVoloPerId(String codiceVolo);

    /**
     * Recupera tutti i voli associati a un amministratore specifico.
     *
     * @param idUser identificativo dell’amministratore
     * @param voli lista da popolare con i voli gestiti dall’amministratore
     */
    void getVoliPerAmministratore(int idUser, ArrayList<Volo> voli);

    /**
     * Crea un nuovo volo con i dati forniti.
     *
     * @param compagniaAerea il nome della compagnia aerea
     * @param codice il codice identificativo del volo
     * @param origine l’aeroporto di partenza
     * @param destinazione l’aeroporto di arrivo
     * @param orarioPartenza orario di partenza (HH:mm)
     * @param orarioArrivo orario di arrivo (HH:mm)
     * @param dataPartenza data del volo (yyyy-MM-dd)
     * @param durata durata prevista del volo (es. "2h15m")
     * @param ritardo ritardo in minuti rispetto all’orario previsto
     * @param stato stato attuale del volo (es. "In orario", "Cancellato")
     * @throws IllegalArgumentException se uno dei parametri è invalido
     */
//INSERT
    void create(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String stato);

    /**
     * Imposta o aggiorna il gate associato a un volo.
     *
     * @param codiceVolo codice del volo da aggiornare
     * @param gate numero del gate assegnato (es. 12)
     */
//UPDATE
    void setGate(String codiceVolo, int gate);

    /**
     * Aggiorna i dati di un volo esistente.
     *
     * @param tipologia "Partenza" o "Arrivo"
     * @param codiceVolo codice del volo da modificare
     * @param luogo nuovo aeroporto di origine o destinazione
     * @param orarioPartenza orario aggiornato di partenza (HH:mm)
     * @param orarioArrivo orario aggiornato di arrivo (HH:mm)
     * @param dataPartenza data aggiornata del volo (yyyy-MM-dd)
     * @param durata durata totale aggiornata
     * @param ritardo ritardo attuale in minuti
     * @param statoDelVolo stato aggiornato (es. "In ritardo", "Cancellato")
     * @throws IllegalArgumentException se i parametri sono incoerenti
     */
    void aggiornaVolo(String tipologia, String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoDelVolo);
}