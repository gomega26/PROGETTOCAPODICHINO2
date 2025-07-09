package model;

/**
 * Rappresenta un utente generico del sistema.
 * <p>
 * Ogni utente è identificato da credenziali di accesso (login e password),
 * un indirizzo email e un identificativo numerico univoco.
 * Questa classe può essere estesa per realizzare tipologie specifiche di utente
 * come {@link Amministratore} o altri ruoli.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public class Utente {

        /**
         * Nome utente utilizzato per l'accesso al sistema.
         */
        protected String login;

        /**
         * Password associata all'account dell'utente.
         */
        protected String password;

        /**
         * Indirizzo email di contatto dell'utente.
         */
        protected String email;

        /**
         * Identificativo numerico univoco dell'utente.
         */
        protected int id;

        /**
         * Costruisce un nuovo utente con i parametri specificati.
         *
         * @param id identificativo dell’utente
         * @param login nome utente per l'autenticazione
         * @param password password associata all'account
         * @param email indirizzo email dell’utente
         */
        public Utente(int id, String login, String password, String email) {
                this.id = id;
                this.login = login;
                this.password = password;
                this.email = email;
        }

        /**
         * Costruttore vuoto per utilizzi alternativi. //ESEMPIO NON LO SO
         */
        public Utente() {
        }

        /**
         * Restituisce l'indirizzo email dell'utente.
         *
         * @return email dell'utente
         */
        public String getEmail() {
                return email;
        }

        /**
         * Imposta l'indirizzo email dell'utente.
         *
         * @param email nuovo indirizzo email
         */
        public void setEmail(String email) {
                this.email = email;
        }

        /**
         * Restituisce il nome utente dell'account.
         *
         * @return login dell'utente
         */
        public String getLogin() {
                return login;
        }

        /**
         * Imposta il nome utente per l'accesso.
         *
         * @param login nuovo nome utente
         */
        public void setLogin(String login) {
                this.login = login;
        }

        /**
         * Restituisce la password associata all'account.
         *
         * @return la password
         */
        public String getPassword() {
                return password;
        }

        /**
         * Imposta la password per l'autenticazione.
         *
         * @param password nuova password
         */
        public void setPassword(String password) {
                this.password = password;
        }

        /**
         * Restituisce l'identificativo univoco dell'utente.
         *
         * @return id dell’utente
         */
        public int getId() {
                return id;
        }

        /**
         * Imposta l'identificativo univoco dell’utente.
         *
         * @param id nuovo identificativo
         */
        public void setId(int id) {
                this.id = id;
        }
}


