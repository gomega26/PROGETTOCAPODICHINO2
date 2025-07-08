package model;

import java.util.ArrayList;

public class Amministratore extends Utente {

    //COSTRUTTORE
    public Amministratore(int id, String login, String password, String email){

        super(id, login, password, email);
    }

    //CERCA PASSEGGERO
    public ArrayList<Passeggero> cercaPasseggero(ArrayList<Passeggero> passeggeri, String nome, String cognome, String numDocumento, char sesso){

        ArrayList<Passeggero> passeggeriTrovati = new ArrayList<>();

        for (Passeggero p : passeggeri) {

            if (!nome.isEmpty() && !p.getNome().equals(nome))
                continue;
            if (!cognome.isEmpty() && !p.getCognome().equals(cognome))
                continue;
            if (!numDocumento.isEmpty() && !p.getNumDocumento().equals(numDocumento))
                continue;
            if (sesso != '-' && p.getSesso() != sesso)
                continue;

            passeggeriTrovati.add(p);
        }

        return passeggeriTrovati;
    }
}
