package ImplementazioneDAO;

import dao.BagaglioDAO;
import db.ConnessioneDatabase;
import model.Bagaglio;
import model.StatoBagaglio;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementazione dell'interfaccia {@link BagaglioDAO} per la gestione dei bagagli tramite JDBC.
 * <p>
 * Consente operazioni CRUD e operazioni specifiche legate al tracciamento dei bagagli
 * nel contesto aeroportuale.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class ImplementazioneBagaglioDAO implements BagaglioDAO {

    private Connection connection;

    /**
     * Costruisce un'istanza dell'implementazione DAO e inizializza la connessione al database.
     * La connessione è ottenuta tramite il singleton {@link ConnessioneDatabase}.
     */
    public ImplementazioneBagaglioDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Restituisce un bagaglio associato a un utente generico, se presente nel database.
     *
     * @param idUser identificativo dell'utente generico
     * @param codiceBagaglio codice identificativo del bagaglio
     * @return bagaglio trovato oppure {@code null} se non esiste corrispondenza
     */
    @Override
    public Bagaglio getBagagliPerUtenteGenerico(int idUser, int codiceBagaglio) {
        Bagaglio b = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.* " +
                            "FROM bagagli b " +
                            "JOIN prenotazioni p ON b.id_prenotazione = p.id " +
                            "WHERE p.id_utente_generico = " + idUser + " AND b.id = " + codiceBagaglio + ";"
            );

            if (rs.next()) {
                b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return b;
    }

    /**
     * Recupera un bagaglio specifico sulla base del codice, accessibile da un amministratore.
     *
     * @param codiceBagaglio codice univoco del bagaglio
     * @return oggetto {@link Bagaglio} se presente, altrimenti {@code null}
     */
    @Override
    public Bagaglio getBagagliPerAmministartore(int codiceBagaglio) {
        Bagaglio b = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM bagagli WHERE id = " + codiceBagaglio + ";"
            );

            if (rs.next()) {
                b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return b;
    }

    /**
     * Aggiorna lo stato di un determinato bagaglio.
     *
     * @param codiceBagaglio identificativo del bagaglio da aggiornare
     * @param stato nuovo stato da assegnare (es. "RITIRABILE", "SMARRITO")
     */
    @Override
    public void setStato(int codiceBagaglio, String stato) {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "UPDATE bagagli SET stato = '" + stato + "' WHERE id = " + codiceBagaglio + ";"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Crea un nuovo bagaglio associato a una prenotazione.
     * Il bagaglio viene inizializzato con stato "RITIRABILE".
     *
     * @param id_prenotazione ID della prenotazione a cui associare il bagaglio
     */
    @Override
    public void create(int id_prenotazione) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO bagagli (id_prenotazione, stato) VALUES (" + id_prenotazione + ", 'Ritirabile');"
            );
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recupera tutti i bagagli attualmente smarriti (stato "SMARRITO")
     * e li inserisce nella lista fornita.
     *
     * @param bagagliSmarriti lista da riempire con i bagagli smarriti trovati
     */
    @Override
    public void getSmarriti(ArrayList<Bagaglio> bagagliSmarriti) {

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM bagagli WHERE stato = 'Smarrito';";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Bagaglio b = new Bagaglio();
                b.setCodice(rs.getInt("id"));

                bagagliSmarriti.add(b);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Permette a un amministratore di segnalare come smarrito un bagaglio,
     * solo se il volo associato è sotto la sua gestione.
     *
     * @param idAmministratore ID dell’amministratore che segnala lo smarrimento
     * @param codice ID del bagaglio da aggiornare
     * @return {@code true} se l’operazione ha avuto successo, altrimenti {@code false}
     */
    @Override
    public boolean segnalaSmarrimento(int idAmministratore, int codice) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.id " +
                            "FROM bagagli b " +
                            "JOIN prenotazioni p ON b.id_prenotazione = p.id " +
                            "JOIN voli v ON p.codice_volo = v.codice " +
                            "JOIN gestione g ON g.id_volo = v.codice " +
                            "WHERE b.id = " + codice + " AND g.id_amministratore = " + idAmministratore + ";"
            );

            if (rs.next()) {
                stmt.executeUpdate(
                        "UPDATE bagagli SET stato = 'SMARRITO' WHERE id = " + codice + ";"
                );

                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Recupera tutti i bagagli relativi ai voli gestiti da un amministratore
     * e li aggiunge alla lista specificata.
     *
     * @param idAmministratore ID dell’amministratore
     * @param bagagli lista da riempire con i bagagli trovati
     */
    public void getBagagliPerAmministratore(int idAmministratore, ArrayList<Bagaglio> bagagli) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.* " +
                            "FROM bagagli b " +
                            "JOIN prenotazioni p ON b.id_prenotazione = p.id " +
                            "JOIN voli v ON p.codice_volo = v.codice " +
                            "JOIN gestione g ON g.id_volo = v.codice " +
                            "WHERE g.id_amministratore = " + idAmministratore + ";"
            );

            while (rs.next()) {
                Bagaglio b = new Bagaglio();
                b.setCodice(rs.getInt("id"));
                b.setStatoBagaglio(StatoBagaglio.valueOf(rs.getString("stato")));
                bagagli.add(b);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    /**
     * Chiude la connessione al database se ancora attiva.
     * <p>
     * Utile per liberare risorse manualmente o in fase di terminazione applicazione.
     * </p>
     */

    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Errore durante la chiusura della connessione:");
                e.printStackTrace();
            }
        }
    }


}