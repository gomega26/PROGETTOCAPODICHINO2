package model;

import java.util.ArrayList;

public class Utente {

        protected String login;
        protected String password;
        protected String email;
        protected int id;

        //COSTRUTTORE
        public Utente(int id, String login, String password, String email){

                this.id=id;
                this.login = login;
                this.password = password;
                this.email = email;
        }

        public Utente(){};

        //SETTERS E GETTERS
        public void setEmail(String email) {
                this.email = email;
        }

        public void setLogin(String login) {
                this.login = login;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public String getPassword() {
                return password;
        }

        public String getLogin() {
                return login;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getId() {
                return id;
        }
}

