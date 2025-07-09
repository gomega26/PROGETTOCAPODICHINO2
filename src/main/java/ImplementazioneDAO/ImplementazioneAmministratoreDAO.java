package ImplementazioneDAO;

import dao.AmministratoreDAO;
import db.ConnessioneDatabase;
import model.Amministratore;
import model.Bagaglio;
import model.StatoBagaglio;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementazione dell'interfaccia {@link AmministratoreDAO} per la gestione
 * degli amministratori tramite JDBC.
 * <p>
 * Questa classe consente di registrare un amministratore, autenticarlo
 * e associarlo a un volo da gestire nel database PostgresSQL.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class ImplementazioneAmministratoreDAO implements AmministratoreDAO {

    /**
     * Connessione attiva al database ottenuta tramite {@link ConnessioneDatabase}.
     */
    private Connection connection;

    /**
     * Costruttore della classe.
     * <p>
     * Inizializza la connessione al database usando il singleton {@code ConnessioneDatabase}.
     * </p>
     */
    public ImplementazioneAmministratoreDAO() {
        try {
            connection=ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Registra un nuovo amministratore nel sistema.
     * <p>
     * Inserisce le credenziali dell'amministratore nella tabella {@code amministratori}.
     * </p>
     *
     * @param login nome utente scelto per l'accesso
     * @param password password associata all'amministratore
     * @param email indirizzo email dell'amministratore
     */
    @Override
    public void signIn(String login, String password, String email) {

        try {
            PreparedStatement saveAmministratorePS = connection.prepareStatement(
                    "INSERT INTO amministratori " +
                            "(\"login\", \"email\", \"password\") " +
                            "VALUES ('" +
                            login + "', '" +
                            email + "', '" +
                            password + "');"
            );
            saveAmministratorePS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Autentica un amministratore tramite login e password.
     *
     * @param login nome utente dell'amministratore
     * @param password password fornita per l'autenticazione
     * @return oggetto {@link Amministratore} se l'autenticazione è riuscita, altrimenti {@code null}
     */
    public Amministratore logIn(String login, String password){

        Amministratore user=null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM amministratori " +
                            "WHERE \"login\" = '" + login + "' " +
                            "AND \"password\" = '" + password + "';"
            );

            if (rs.next())
                user = new Amministratore(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("email"));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return user;
    }
    /**
     * Associa un volo a un amministratore nel database.
     * <p>
     * Inserisce nella tabella {@code gestione} una nuova riga che collega l'amministratore a un volo.
     * </p>
     *
     * @param idUser identificativo dell'amministratore
     * @param idVolo codice identificativo del volo da assegnare
     */
    @Override
    public void inserisciVolo(int idUser, String idVolo) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO gestione " +
                            "(\"id_amministratore\", \"id_volo\") " +
                            "VALUES (" + idUser + ", '" +
                            idVolo + "');"
            );
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
}
