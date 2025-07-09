package ImplementazioneDAO;

import dao.PasseggeroDAO;
import db.ConnessioneDatabase;
import model.Passeggero;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementazione concreta dell'interfaccia {@link PasseggeroDAO} per la gestione dei passeggeri.
 * <p>
 * Fornisce metodi per la creazione, modifica e recupero dei dati anagrafici dei passeggeri
 * tramite interazione con il database PostgreSQL.
 * </p>
 *  @author Gianmarco Minei
 *  @author Stefano Luongo
 *  @author Alessandro Esposito
 */
public class ImplementazionePasseggeroDAO implements PasseggeroDAO {

    private Connection connection;

    /**
     * Inizializza un'istanza del DAO stabilendo la connessione al database.
     * La connessione è gestita tramite il singleton {@link ConnessioneDatabase}.
     */
    public ImplementazionePasseggeroDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Inserisce un nuovo passeggero nel database.
     *
     * @param nome nome del passeggero
     * @param cognome cognome del passeggero
     * @param numTelefono numero di telefono del passeggero
     * @param numDocumento numero documento identificativo
     * @param sesso sesso del passeggero (M o F)
     * @param dataNascita data di nascita del passeggero
     * @return ID del passeggero appena creato, oppure {@code -1} se si verifica un errore
     */
    @Override
    public int create(String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO passeggeri " +
                            "(\"nome\", \"cognome\", \"num_telefono\", \"num_documento\", \"sesso\", \"data_nascita\") " +
                            "VALUES ('" + nome + "', '" + cognome + "', '" + numTelefono + "', '" +
                            numDocumento + "', '" + sesso + "', '" + dataNascita + "');",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt("id");
                rs.close();
                ps.close();
                return id;
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Modifica i dati anagrafici di un passeggero associato a una prenotazione esistente.
     *
     * @param codicePrenotazione ID della prenotazione collegata al passeggero
     * @param nome nuovo nome da assegnare
     * @param cognome nuovo cognome da assegnare
     * @param numDocumentoPasseggero nuovo numero di documento
     * @param sesso nuovo sesso del passeggero
     */
    @Override
    public void modifica(int codicePrenotazione, String nome, String cognome, String numDocumentoPasseggero, char sesso) {
        try {

            Statement stmt = connection.createStatement();

            StringBuilder query = new StringBuilder("UPDATE passeggeri SET ");

            boolean first = true;

            if (nome != null && !nome.isEmpty()) {
                query.append("nome = '").append(nome).append("'");
                first = false;
            }
            if (cognome != null && !cognome.isEmpty()) {
                if (!first) query.append(", ");
                query.append("cognome = '").append(cognome).append("'");
                first = false;
            }
            if (numDocumentoPasseggero != null && !numDocumentoPasseggero.isEmpty()) {
                if (!first) query.append(", ");
                query.append("num_documento = '").append(numDocumentoPasseggero).append("'");
                first = false;
            }
            if (sesso != '\0') {
                if (!first) query.append(", ");
                query.append("sesso = '").append(sesso).append("'");
            }

            query.append(" WHERE id IN (SELECT id_passeggero FROM prenotazioni WHERE id = ")
                    .append(codicePrenotazione).append(");");

            stmt.executeUpdate(query.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recupera tutti i passeggeri presenti nel database e li aggiunge alla lista fornita.
     *
     * @param passeggeri lista da riempire con i passeggeri recuperati
     */
    @Override
    public void getAll(ArrayList<Passeggero> passeggeri) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM passeggeri;");

            while (rs.next()) {
                Passeggero p = new Passeggero(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("num_telefono"),
                        rs.getString("num_documento"),
                        rs.getString("sesso").charAt(0),
                        rs.getString("data_nascita")
                );

                passeggeri.add(p);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recupera un passeggero specifico sulla base dell'ID indicato.
     *
     * @param id identificativo del passeggero da recuperare
     * @return oggetto {@link Passeggero} se trovato, altrimenti {@code null}
     */
    @Override
    public Passeggero getPerId(int id) {
        Passeggero p = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM passeggeri WHERE id = " + id + ";"
            );

            if (rs.next()) {
                p = new Passeggero(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("num_telefono"),
                        rs.getString("num_documento"),
                        rs.getString("sesso").charAt(0),
                        rs.getString("data_nascita")
                );
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
     * Utile per liberare risorse manualmente in fase di terminazione o errore.
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
