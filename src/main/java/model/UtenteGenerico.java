package model;
/**
 * Rappresenta un utente generico del sistema, che non possiede privilegi amministrativi.
 * <p>
 * Estende la classe {@link Utente}, ereditando le informazioni di base come ID, credenziali e email.
 * Può essere utilizzato per identificare clienti, visitatori o utenti non autenticati con ruoli speciali.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see Utente
 */


public class UtenteGenerico extends Utente {

    /**
     * Instantiates a new Utente generico.
     /**
     * Costruisce un oggetto {@code UtenteGenerico} con i dati utente forniti.
     * I dati vengono inoltrati al costruttore della superclasse {@link Utente}.
     *
     * @param id identificativo univoco dell'utente
     * @param login nome utente per l'autenticazione
     * @param password password associata
     * @param email indirizzo email dell'utente
     */

    public UtenteGenerico(int id, String login, String password, String email) {

        super(id, login, password, email);
    }
}

