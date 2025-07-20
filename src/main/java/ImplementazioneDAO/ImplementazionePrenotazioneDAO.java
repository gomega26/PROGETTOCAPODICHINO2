package ImplementazioneDAO;

import dao.PrenotazioneDAO;
import db.ConnessioneDatabase;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementazione concreta dell'interfaccia {@link PrenotazioneDAO}
 * per la gestione delle prenotazioni nel sistema aeroportuale.
 * <p>
 * Fornisce funzionalità C.R.U.D. = Create, Read, Update, Delete per la tabella {@code prenotazioni},
 * interagendo con il database PostgresSQL tramite JDBC.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class ImplementazionePrenotazioneDAO implements PrenotazioneDAO {

    private Connection connection;

    /**
     * Instantiates a new Implementazione prenotazione dao.
     */
    public ImplementazionePrenotazioneDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Recupera tutte le prenotazioni associate a un utente generico.
     *
     * @param idUser identificativo dell’utente generico
     * @param prenotazioni lista da riempire con le prenotazioni trovate
     */
    @Override
    public void getPrenotazioniPerUtenteGenerico(int idUser, ArrayList<Prenotazione> prenotazioni) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id_utente_generico = " + idUser + ";"
            );

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
                prenotazioni.add(p);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Recupera tutte le prenotazioni presenti nel sistema.
     *
     * @param prenotazioni lista da riempire con tutte le prenotazioni trovate
     */
    @Override
    public void getAll(ArrayList<Prenotazione> prenotazioni) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prenotazioni;");

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
                prenotazioni.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Crea una nuova prenotazione per un passeggero e un volo specifico.
     * Lo stato iniziale è impostato su "IN ATTESA".
     *
     * @param posto posto assegnato
     * @param classe classe del volo
     * @param id_passeggero identificativo del passeggero
     * @param id_volo codice volo
     * @param idUser identificativo dell’utente generico
     * @param numBagagli numero di bagagli associati
     */
    @Override
    public void create(String posto, String classe, int id_passeggero, String id_volo, int idUser, int numBagagli) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO prenotazioni " +
                            "(\"posto\", \"classe_volo\", \"id_passeggero\", \"codice_volo\", \"id_utente_generico\", \"stato\", \"num_bagagli\") " +
                            "VALUES ('" + posto + "', '" + classe + "', " +
                            id_passeggero + ", '" + id_volo + "', " + idUser + ", 'InAttesa', "+numBagagli+");"
            );
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Recupera una prenotazione di uno specifico utente generico in base al codice fornito.
     *
     * @param idUser identificativo dell’utente generico
     * @param codicePrenotazione codice identificativo della prenotazione
     * @return oggetto {@link Prenotazione} se trovato, altrimenti {@code null}
     */
    @Override
    public Prenotazione getPerIdUtenteGenerico(int idUser, int codicePrenotazione) {
        Prenotazione p = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id_utente_generico = " + idUser +
                            " AND id = " + codicePrenotazione + ";"
            );

            if (rs.next()) {
                p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return p;
    }

    /**
     * Restituisce una prenotazione tramite il suo ID.
     *
     * @param codicePrenotazione codice identificativo della prenotazione
     * @return oggetto {@link Prenotazione} se trovato, altrimenti {@code null}
     */
    @Override
    public Prenotazione getPerId(int codicePrenotazione) {
        Prenotazione p = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id = " + codicePrenotazione + ";"
            );

            if (rs.next()) {
                p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_bagagli"),
                        rs.getString("posto"),
                        rs.getString("classe_volo"),
                        rs.getString("stato")
                );
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return p;
    }
    /**
     * Restituisce il codice volo associato a una prenotazione.
     *
     * @param idPrenotazione identificativo della prenotazione
     * @return codice del volo corrispondente
     */
    @Override
    public String getIdVolo(int idPrenotazione) {
        String idVolo="";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT codice_volo FROM prenotazioni WHERE id = " + idPrenotazione + ";"
            );

            if (rs.next())
                idVolo = rs.getString("codice_volo");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return idVolo;
    }
    /**
     * Restituisce l'ID del passeggero associato a una prenotazione.
     *
     * @param idPrenotazione identificativo della prenotazione
     * @return ID del passeggero corrispondente
     */
    @Override
    public int getIdPasseggero(int idPrenotazione) {

        int idPasseggero=0;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id_passeggero FROM prenotazioni WHERE id = " + idPrenotazione + ";"
            );

            if (rs.next())
                idPasseggero = rs.getInt("id_passeggero");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return idPasseggero;
    }
    /**
     * Esegue il check-in per una prenotazione, aggiornando lo stato a "CONFERMATA"
     * e assegnando un numero di biglietto.
     *
     * @param idPrenotazione identificativo della prenotazione
     * @param numBiglietto numero del biglietto assegnato
     */
    @Override
    public void checkIn(int idPrenotazione, int numBiglietto) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    "UPDATE prenotazioni SET stato = 'Confermata', num_biglietto = " + numBiglietto +
                            " WHERE id = " + idPrenotazione + ";"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Modifica i dati di una prenotazione esistente nel sistema.
     *
     * @param codicePrenotazione identificativo della prenotazione da aggiornare
     * @param posto nuovo posto assegnato
     * @param classeVolo nuova classe del volo (es. ECONOMY, BUSINESS)
     * @param numBagagli nuovo numero di bagagli da registrare
     */
    @Override
    public void modifica(int codicePrenotazione, String posto, String classeVolo, int numBagagli) {
        try {

            Statement stmt = connection.createStatement();

            StringBuilder query = new StringBuilder("UPDATE prenotazioni SET ");

            boolean first = true;

            if (posto != null && !posto.isEmpty()) {
                query.append("posto = '").append(posto).append("'");
                first = false;
            }
            if (classeVolo != null && !classeVolo.isEmpty()) {
                if (!first) query.append(", ");
                query.append("classe_volo = '").append(classeVolo).append("'");
                first = false;
            }
            if (numBagagli >= 0) {
                if (!first) query.append(", ");
                query.append("num_bagagli = ").append(numBagagli);
                first = false;
            }

            query.append(" WHERE id = ").append(codicePrenotazione).append(";");

            stmt.executeUpdate(query.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Restituisce la prenotazione associata a un determinato passeggero.
     *
     * @param idPasseggero identificativo del passeggero
     * @return  prenotazione corrispondente, oppure null se non trovata
     */
    public Prenotazione getPerIdPasseggero(int idPasseggero){

        Prenotazione p =null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM prenotazioni WHERE id_passeggero = " + idPasseggero + ";"
            );

            if (rs.next()) {
                p = new Prenotazione(rs.getInt("id"),
                                                    rs.getInt("num_bagagli"),
                                                    rs.getString("posto"),
                                                    rs.getString("classe_volo"),
                                                    rs.getString("stato"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return p;
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
                System.err.println("Errore durante la chiusura della connessione:");
                e.printStackTrace();
            }
        }
    }
}
