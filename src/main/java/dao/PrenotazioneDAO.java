package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface PrenotazioneDAO {

    void getPrenotazioniPerUtenteGenerico(int idUser, ArrayList<Prenotazione> prenotazioni);
    void getAll(ArrayList<Prenotazione> prenotazioni);
    void create(String posto, String classe, int id_passeggero, String id_volo, int idUser);
    Prenotazione getPerId(int idUser, int codicePrenotazione);
    int getIdVolo(int idPrenotazione);
    void checkIn(int numBiglietto); //CAMBIA ANCHE LO STATO IN CONFERMATA
    void modifica(int codicePrenotazione, String posto, String classeVolo); //SI DEVE RESETTARE IL NUMERO DEI BAGAGLI, IL CODICE SERVE PER CERCARE LA PRENOTAZIONE
}