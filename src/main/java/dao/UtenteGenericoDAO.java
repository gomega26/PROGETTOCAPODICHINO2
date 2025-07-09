package dao;

import model.UtenteGenerico;


/**
 * Interfaccia DAO per la gestione degli utenti generici.
 * <p>
 * Fornisce metodi per la registrazione (sign-in) e l'autenticazione (login) degli utenti.
 * Implementazioni concrete possono utilizzare un database relazionale o altri sistemi di persistenza.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see model.UtenteGenerico
 */
public interface UtenteGenericoDAO {

    /**
     * Registra un nuovo utente nel sistema con le credenziali fornite.
     * <p>
     * Il metodo verifica che l'indirizzo email e il nome utente non siano già presenti nel database.
     * Se uno dei due è già utilizzato, l'inserimento viene annullato e viene sollevata un'eccezione.
     * </p>
     *
     * @param email    l'indirizzo email dell'utente da registrare
     * @param login    il nome utente scelto per l'accesso
     * @param password la password associata all'account
     * @throws IllegalArgumentException se uno dei parametri è {@code null}, vuoto o invalido
     * @throws RuntimeException se l’utente con lo stesso email o login esiste già
     */
    void signIn(String email, String login, String password);

    /**
     * Autentica un utente tramite il nome utente e la password forniti.
     *
     * @param login    il nome utente
     * @param password la password associata
     * @return un oggetto {@link UtenteGenerico} se le credenziali sono corrette, altrimenti {@code null}
     * @throws IllegalArgumentException se i parametri forniti sono {@code null} o vuoti
     */
    UtenteGenerico logIn(String login, String password);

    void closeConnection();
}
