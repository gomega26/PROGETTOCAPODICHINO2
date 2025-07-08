package dao;

import java.util.ArrayList;

import model.*;

public interface PrenotazioneDAO {

    //INSERT

    void create(String posto, String classe, int id_passeggero, String id_volo, int idUser, int numBagagli);

    //SELECT

    void getPrenotazioniPerUtenteGenerico(int idUser, ArrayList<Prenotazione> prenotazioni);
    void getAll(ArrayList<Prenotazione> prenotazioni);
    Prenotazione getPerIdUtenteGenerico(int idUser, int codicePrenotazione);
    Prenotazione getPerId(int codicePrenotazione);
    int getIdPerPasseggero(int idPasseggero);
    String getIdVolo(int idPrenotazione);
    int getIdPasseggero(int idPrenotazione);

    //UPDATE

    void checkIn(int idPrenotazione, int numBiglietto);
    void modifica(int codicePrenotazione, String posto, String classeVolo, int numBagagli);
}