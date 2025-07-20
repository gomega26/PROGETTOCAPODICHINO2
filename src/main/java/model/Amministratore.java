package model;

import java.util.ArrayList;

/**
 * Rappresenta un amministratore del sistema.
 * <p>
 * L'amministratore è un {@link Utente} con privilegi avanzati,
 * come la possibilità di cercare passeggeri all'interno del sistema
 * in base a criteri specifici.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class Amministratore extends Utente {

    /**
     * Costruisce un nuovo oggetto {@code Amministratore} con le credenziali fornite.
     *
     * @param id identificativo numerico dell'amministratore
     * @param login nome utente per l'autenticazione
     * @param password password associata all'account
     * @param email indirizzo email dell'amministratore
     */
    public Amministratore(int id, String login, String password, String email) {
        super(id, login, password, email);
    }

    /**
     * Cerca e restituisce una lista di passeggeri che corrispondono ai criteri specificati.
     * <p>
     * Il confronto è case-sensitive e ignora i parametri vuoti o neutri ("-" per il sesso).
     * Ogni parametro non vuoto viene usato come criterio di filtraggio.
     * </p>
     *
     * @param passeggeri lista completa di passeggeri da filtrare
     * @param nome nome del passeggero da cercare; ignorato se vuoto
     * @param cognome cognome del passeggero da cercare; ignorato se vuoto
     * @param numDocumento numero del documento del passeggero; ignorato se vuoto
     * @param sesso sesso del passeggero da cercare (es. 'M' o 'F'); se {@code '-'} viene ignorato
     * @return lista di {@link Passeggero} che soddisfano i criteri specificati
     */
}
