package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;
/**
 * Interfaccia per la gestione dei dati relativi ai passeggeri.
 * <p>
 * Definisce operazioni per la creazione, modifica, ricerca e recupero
 * di passeggeri nel sistema aeroportuale.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see model.Passeggero
 */


public interface PasseggeroDAO {

    /**
     * Crea un nuovo passeggero con i dati forniti.
     *
     * @param nome nome del passeggero
     * @param cognome cognome del passeggero
     * @param numTelefono numero di telefono del passeggero
     * @param numDocumento numero del documento identificativo
     * @param sesso sesso del passeggero (M/F)
     * @param dataNascita data di nascita in formato "dd/MM/yyyy"
     * @return ID univoco assegnato al nuovo passeggero nel database
     */

    int create(String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita);

    /**
     * Modifica i dati di un passeggero associato a una prenotazione specifica.
     *
     * @param codicePrenotazione codice identificativo della prenotazione
     * @param nome nuovo nome del passeggero
     * @param cognome nuovo cognome del passeggero
     * @param numDocumentoPasseggero nuovo numero documento del passeggero
     * @param sesso nuovo sesso (M/F)
     */

    void modifica(int codicePrenotazione, String nome, String cognome, String numDocumentoPasseggero, char sesso);

    /**
     * Recupera l'elenco completo dei passeggeri registrati nel sistema.
     *
     * @param passeggeri lista da riempire con i passeggeri trovati
     */

    void getAll(ArrayList<Passeggero> passeggeri);

    /**
     * Restituisce il passeggero associato all'ID specificato.
     *
     * @param id ID univoco del passeggero
     * @return oggetto {@link Passeggero} corrispondente, oppure {@code null} se non trovato
     */

    Passeggero getPerId(int id);
}