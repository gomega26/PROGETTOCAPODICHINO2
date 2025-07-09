package ImplementazioneDAO;

import dao.VoloDAO;
import db.ConnessioneDatabase;
import model.Volo;
import model.StatoVolo;
import model.VoloInArrivo;
import model.VoloInPartenza;

import java.sql.*;
import java.util.ArrayList;

/**
 * Costruisce un'istanza dell'implementazione DAO per i voli e stabilisce
 * una connessione con il database dell'applicazione aeroportuale.
 * <p>
 * La connessione è ottenuta tramite il singleton {@link ConnessioneDatabase},
 * garantendo un accesso condiviso e centralizzato alla risorsa JDBC.
 * Questo costruttore è fondamentale per abilitare tutte le operazioni
 * di lettura, scrittura e aggiornamento sui dati relativi ai voli.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */

public class ImplementazioneVoloDAO implements VoloDAO {

    private Connection connection;

    /**
     * Instantiates a new Implementazione volo dao.
     */
    public ImplementazioneVoloDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Recupera tutti i voli presenti nel sistema e li aggiunge alla lista specificata.
     * <p>
     * I voli vengono istanziati come {@link VoloInPartenza} o {@link VoloInArrivo}
     * in base alla tipologia e alla città di riferimento.
     *
     * @param voli lista da riempire con i voli recuperati
     */

    @Override
    public void getAll(ArrayList<Volo> voli) { //FATTO

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli;");

            while (rs.next()) {

                String tipologia = rs.getString("tipologia");
                String origine = rs.getString("origine");
                String destinazione = rs.getString("destinazione");

                Volo v = null;

                if ("InPartenza".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(origine)) {
                    v = new VoloInPartenza();
                } else if ("InArrivo".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(destinazione)) {
                    v = new VoloInArrivo();
                }

                if (v != null) {
                    v.setCodice(rs.getString("codice"));
                    v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                    v.setOrigine(origine);
                    v.setDestinazione(destinazione);
                    v.setDataPartenza(rs.getString("data"));
                    v.setOrarioPartenza(rs.getString("orario_partenza"));
                    v.setOrarioArrivo(rs.getString("orario_arrivo"));
                    v.setDurata(rs.getString("durata"));
                    v.setRitardo(rs.getInt("ritardo"));
                    v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                    voli.add(v);
                }
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Recupera tutti i voli in partenza presenti nel database.
     *
     * @param voli lista da riempire con i voli in partenza
     */

    @Override
    public void getVoliInPartenza(ArrayList<VoloInPartenza> voli) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli WHERE tipologia = 'InPartenza';");

            while (rs.next()) {

                VoloInPartenza v=new VoloInPartenza();

                v.setCodice(rs.getString("codice"));
                v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                v.setOrigine(rs.getString("origine"));
                v.setDestinazione(rs.getString("destinazione"));
                v.setDataPartenza(rs.getString("data"));
                v.setOrarioPartenza(rs.getString("orario_partenza"));
                v.setOrarioArrivo(rs.getString("orario_arrivo"));
                v.setDurata(rs.getString("durata"));
                v.setRitardo(rs.getInt("ritardo"));
                v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                voli.add(v);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Recupera un volo specifico in base al suo codice identificativo.
     * <p>
     * Il volo restituito può essere una sottoclasse {@link VoloInPartenza} o {@link VoloInArrivo}.
     *
     * @param codiceVolo codice univoco del volo
     * @return oggetto {@link Volo} se trovato, altrimenti {@code null}
     */

    @Override
    public Volo getVoloPerId(String codiceVolo) { //FATTO
        Volo v = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM voli WHERE codice = '" + codiceVolo + "';");

            if (rs.next()) {
                String tipologia = rs.getString("tipologia");
                String origine = rs.getString("origine");
                String destinazione = rs.getString("destinazione");

                if ("IN PARTENZA".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(origine)) {
                    v = new VoloInPartenza();
                } else if ("IN ARRIVO".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(destinazione)) {
                    v = new VoloInArrivo();
                }

                if (v != null) {
                    v.setCodice(rs.getString("codice"));
                    v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                    v.setOrigine(origine);
                    v.setDestinazione(destinazione);
                    v.setDataPartenza(rs.getString("data"));
                    v.setOrarioPartenza(rs.getString("orario_partenza"));
                    v.setOrarioArrivo(rs.getString("orario_arrivo"));
                    v.setDurata(rs.getString("durata"));
                    v.setRitardo(rs.getInt("ritardo"));
                    v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                }
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return v;
    }
    /**
     * Crea un nuovo volo nel database, assegnandogli automaticamente la tipologia
     * in base alla città di origine o destinazione.
     *
     * @param compagniaAerea compagnia aerea del volo
     * @param codice codice identificativo del volo
     * @param origine città di partenza
     * @param destinazione città di arrivo
     * @param orarioPartenza orario previsto per la partenza
     * @param orarioArrivo orario previsto per l’arrivo
     * @param dataPartenza data del volo
     * @param durata durata del volo
     * @param ritardo minuti di ritardo
     * @param statoVoloString stato attuale del volo (es. "IN ORARIO", "IN RITARDO")
     */

    @Override
    public void create(String compagniaAerea, String codice, String origine, String destinazione,
                       String orarioPartenza, String orarioArrivo, String dataPartenza,
                       String durata, int ritardo, String statoVoloString) { //FATTO


        try {
            Statement stmt = connection.createStatement();
            String tipologia = "";

            if (origine.equalsIgnoreCase("Napoli")) {
                tipologia = "InPartenza";
            } else if (destinazione.equalsIgnoreCase("Napoli")) {
                tipologia = "InArrivo";
            }

            String query = "INSERT INTO voli (codice, compagnia_aerea, origine, destinazione, orario_partenza, orario_arrivo, data, durata, ritardo, stato, tipologia) " +
                    "VALUES ('" + codice + "', '" + compagniaAerea + "', '" + origine + "', '" + destinazione + "', '" + orarioPartenza + "', '" + orarioArrivo + "', '" + dataPartenza + "', '" + durata + "', " + ritardo + ", '" + statoVoloString + "', '" + tipologia + "');";

            stmt.executeUpdate(query);
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Recupera tutti i voli gestiti da un amministratore specifico.
     *
     * @param idUser identificativo dell’amministratore
     * @param voli lista da riempire con i voli gestiti
     */

    @Override
    public void getVoliPerAmministratore(int idUser, ArrayList<Volo> voli) { //FATTO
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM voli WHERE codice IN (SELECT id_volo FROM gestione WHERE id_amministratore = " + idUser + ");"
            );

            while (rs.next()) {
                String tipologia = rs.getString("tipologia");
                String origine = rs.getString("origine");
                String destinazione = rs.getString("destinazione");

                Volo v = null;

                if ("IN PARTENZA".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(origine)) {
                    v = new VoloInPartenza();
                } else if ("IN ARRIVO".equalsIgnoreCase(tipologia) && "Napoli".equalsIgnoreCase(destinazione)) {
                    v = new VoloInArrivo();
                }

                if (v != null) {
                    v.setCodice(rs.getString("codice"));
                    v.setCompagniaAerea(rs.getString("compagnia_aerea"));
                    v.setOrigine(origine);
                    v.setDestinazione(destinazione);
                    v.setDataPartenza(rs.getString("data"));
                    v.setOrarioPartenza(rs.getString("orario_partenza"));
                    v.setOrarioArrivo(rs.getString("orario_arrivo"));
                    v.setDurata(rs.getString("durata"));
                    v.setRitardo(rs.getInt("ritardo"));
                    v.setStato(StatoVolo.valueOf(rs.getString("stato")));
                    voli.add(v);
                }
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Imposta il numero del gate associato a un volo.
     *
     * @param codiceVolo codice identificativo del volo
     * @param gate numero del gate da assegnare
     */

    @Override
    public void setGate(String codiceVolo, int gate) { //FATTO
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE voli SET gate = " + gate + " WHERE codice = '" + codiceVolo + "';");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Aggiorna i dati principali di un volo esistente, inclusi luogo, orari, data,
     * ritardo e stato corrente. L’attributo modificato per il luogo dipende dalla tipologia del volo.
     *
     * @param tipologia tipo di volo ("InPartenza" o "InArrivo")
     * @param codiceVolo codice del volo da aggiornare
     * @param luogo nuova città di destinazione (se in partenza) o origine (se in arrivo)
     * @param orarioPartenza nuovo orario di partenza
     * @param orarioArrivo nuovo orario di arrivo
     * @param dataPartenza nuova data di partenza
     * @param durata nuova durata prevista del volo
     * @param ritardo nuovo valore del ritardo in minuti
     * @param statoDelVolo nuovo stato del volo (es. "IN RITARDO", "CANCELLATO")
     */

    @Override
    public void aggiornaVolo(String tipologia, String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo,
                             String dataPartenza, String durata, int ritardo, String statoDelVolo) {
        try {

            Statement stmt = connection.createStatement();

            String colonna;

            if(tipologia.equals("InPartenza"))
                colonna = "destinazione";

            else
                colonna = "origine";

            stmt.executeQuery("UPDATE voli SET "
                    + colonna + " = '" + luogo + "', "
                    + "orario_partenza = '" + orarioPartenza + "', "
                    + "orario_arrivo = '" + orarioArrivo + "', "
                    + "data= '" + dataPartenza + "', "
                    + "durata = '" + durata + "', "
                    + "ritardo = " + ritardo + ", "
                    + "stato = '" + statoDelVolo+ "' "
                    + "WHERE codice = '" + codiceVolo + "';");

            connection.close();

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
                    System.out.println("Connessione chiusa correttamente.");
                }
            } catch (SQLException e) {
                System.err.println("Errore durante la chiusura della connessione:");
                e.printStackTrace();
            }
        }
    }
}