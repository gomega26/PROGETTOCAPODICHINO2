package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;
/**
 * Interfaccia per la gestione delle operazioni disponibili agli amministratori.
 * <p>
 * Consente l'autenticazione e la gestione dei voli associati a ciascun amministratore.
 * Implementazioni concrete dovranno occuparsi dell'accesso al database o altre logiche applicative.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 * @see model.Amministratore
 */


public interface AmministratoreDAO {

    /**
     * Registra un nuovo amministratore nel sistema con le credenziali specificate.
     *
     * @param email indirizzo email dell'amministratore
     * @param login nome utente scelto per l'accesso
     * @param password password associata al nuovo account
     */

    void signIn(String email, String login, String password);

    /**
     * Esegue il login di un amministratore sulla base delle credenziali fornite.
     *
     * @param login nome utente dell'amministratore
     * @param password password associata all'account
     * @return oggetto {@link Amministratore} se le credenziali sono corrette, altrimenti {@code null}
     */

    Amministratore logIn(String login, String password);

    /**
     * Collega un volo a un amministratore, indicandone la responsabilità di gestione.
     *
     * @param idUser identificativo univoco dell'amministratore
     * @param idVolo codice identificativo del volo da associare
     */

    void inserisciVolo(int idUser, String idVolo);
    void closeConnection();
}