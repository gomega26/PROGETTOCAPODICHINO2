package model;

import java.util.ArrayList;

public class Utente {

        protected String login;
        protected String password;
        protected String email;

        //COSTRUTTORE
        public Utente(String login, String password, String email){

                this.login = login;
                this.password = password;
                this.email = email;
        }

        //LOG-IN
        public boolean logIn(String login, String password) {

                if ((this.login.equals(login) || this.email.equals(login)) && this.password.equals(password))
                      return true;

                else
                        return false;
        }

        //RICERCA VOLO
        public ArrayList<Volo> ricercaVolo(ArrayList<Volo> voli, String tipo, String compagniaAerea, String codice, String dataPartenza, String destinazione){

                ArrayList<Volo> voliTrovati = new ArrayList<Volo>();

                for(Volo v : voli){

                        if(!tipo.isEmpty() && !v.getClass().getSimpleName().equals(tipo))
                                continue;
                        if(!compagniaAerea.isEmpty() && !v.getCompagniaAerea().equals(compagniaAerea))
                                continue;
                        if(!codice.isEmpty() && !v.getCodice().equals(codice))
                                continue;
                        if(!dataPartenza.isEmpty() && !v.getDataPartenza().equals(dataPartenza))
                                continue;
                        if(!destinazione.isEmpty() && !v.getDestinazione().equals(destinazione))
                                continue;

                        voliTrovati.add(v);
                }

                return voliTrovati;
        }

        //MONITORA LO STATO DEL BAGAGLIO
        public Bagaglio monitoraBagaglio(ArrayList<Bagaglio> bagagli, int codice) {

                for (Bagaglio b : bagagli) {

                        if (b.getCodice() == codice) {

                                return b;
                        }
                }

                return null;
        }

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
}

