package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Classe singleton che gestisce la connessione al database PostgreSQL
 * <p>
 * Utilizza il pattern Singleton per garantire una singola istanza attiva della connessione
 * durante il ciclo di vita dell'applicazione. Si connette al database "aeroporto" locale.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */


public class ConnessioneDatabase {

    private static ConnessioneDatabase instance;


    /**
     * Connessione JDBC {@link Connection} attiva per l'accesso al database PostgreSQL.
     * Viene inizializzato nel costruttore privato della classe Singleton.
     */
    public Connection connection = null;

    /**
     * Nome utente utilizzato per l'autenticazione al database PostgreSQL.
     */
    private String nome = "postgres";

    /**
     * Password associata all'utente del database.
     * ⚠️ Nota: per motivi di sicurezza, è preferibile non hardcodare le credenziali,
     * ma caricarle da variabili d'ambiente o da un file esterno.
     */
    private String password = "FedericoII04!";

    /**
     * URL JDBC utilizzato per la connessione al database PostgreSQL.
     * Include l'host locale, la porta e il nome del database ("aeroporto").
     */
    private String url = "jdbc:postgresql://localhost:5432/aeroporto";

    /**
     * Nome completo della classe del driver JDBC per PostgreSQL.
     * Utilizzato dalla chiamata {@code Class.forName(...)} per caricare il driver.
     */
    private String driver = "org.postgresql.Driver";

    private ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Restituisce l'istanza Singleton di {@code ConnessioneDatabase}.
     * <p>
     * Se la connessione non esiste o è chiusa, ne crea una nuova.
     * </p>
     *
     * @return istanza della classe {@code ConnessioneDatabase}
     * @throws SQLException in caso di errore nella connessione al database
     */

    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnessioneDatabase();
        } else if (instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }
}