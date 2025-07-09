package ImplementazioneDAO;

import dao.UtenteGenericoDAO;
import db.ConnessioneDatabase;

import model.Utente;
import model.UtenteGenerico;

import java.sql.*;

/**
 * Implementazione dell'interfaccia {@link UtenteGenericoDAO} per la gestione
 * degli utenti generici tramite JDBC e connessione a database PostgreSQL.
 * <p>
 * Permette l'inserimento e l'autenticazione di utenti attraverso operazioni dirette sul database.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class ImplementazioneUtenteGenericoDAO implements UtenteGenericoDAO {
    /**
     * Connessione attiva al database ottenuta tramite {@link ConnessioneDatabase}.
     */
    private Connection connection;

    /**
     * Costruttore della classe.
     * <p>
     * Inizializza la connessione al database tramite l'istanza singleton.
     * </p>
     */
    public ImplementazioneUtenteGenericoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Registra un nuovo utente nel sistema con i dati forniti.
     * <p>
     * Inserisce una nuova riga nella tabella {@code utenti_generici} contenente login, password ed email.
     * </p>
     *
     * @param login nome utente scelto per l'accesso
     * @param password password associata all'account
     * @param email indirizzo email dell'utente
     */
    @Override
    public void signIn(String login, String password, String email) {
        try {
            PreparedStatement saveUtentePS = connection.prepareStatement(
                    "INSERT INTO \"utenti_generici\" " +
                            "(\"login\", \"email\", \"password\") " +
                            "VALUES ('" +
                            login + "', '" +
                            email + "', '" +
                            password + "');"
            );
            saveUtentePS.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Autentica un utente tramite login e password.
     * <p>
     * Se le credenziali sono corrette, restituisce l'oggetto {@link UtenteGenerico} associato,
     * altrimenti restituisce {@code null}.
     * </p>
     *
     * @param login nome utente utilizzato per l'accesso
     * @param password password fornita in fase di login
     * @return utente autenticato oppure {@code null} se le credenziali non corrispondono
     */
    @Override
    public UtenteGenerico logIn(String login, String password){

        UtenteGenerico user=null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM utenti_generici " +
                            "WHERE \"login\" = '" + login + "' " +
                            "AND \"password\" = '" + password + "';"
            );

            if (rs.next())
                user = new UtenteGenerico(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("email"));

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return user;
    }
    /**
     * Chiude la connessione al database, se ancora attiva.
     * <p>
     * Utile per liberare risorse manualmente, se necessario.
     * </p>
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Connessione chiusa correttamente.");
                }
            } catch (SQLException e) {
                System.err.println("Errore durante la chiusura della connessione:");
                e.printStackTrace();
            }
        }
    }
}
