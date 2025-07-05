package dao;

import java.util.ArrayList;

import model.*;

public interface PrenotazioneDAO {

    void getPrenotazioniPerUtenteGenerico(int idUser, ArrayList<Prenotazione> prenotazioni);
    void getAll(ArrayList<Prenotazione> prenotazioni);
    int create(String posto, String classe, int id_passeggero, String id_volo, int idUser);
    Prenotazione getPerIdUtenteGenerico(int idUser, int codicePrenotazione);
    Prenotazione getPerIdAmministratore(int codicePrenotazione);
    int getIdVolo(int idPrenotazione);
    void checkIn(int idPrenotazione, int numBiglietto); //CAMBIA ANCHE LO STATO IN CONFERMATA
    void modifica(int codicePrenotazione, String posto, String classeVolo); //SI DEVE RESETTARE IL NUMERO DEI BAGAGLI, IL CODICE SERVE PER CERCARE LA PRENOTAZIONE
}