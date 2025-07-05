package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface VoloDAO {

    void getAll(ArrayList<Volo> voli); /*PRIMA DI CREARE IL VOLO E INSERIRLO NELL ARRAY FARE UN CONTROLLO SULLA COLONNA TIPOLOGIA,
    SE è IN PARTENZA CREAARE UN VOLO IN PARTENZA, SE è IN ARRIVO CREARE UN VOLO IN ARRIVO*/
    Volo getVoloPerId(String codiceVolo);
    void setPrenotazione(int id_prenotazione);

    void create(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoVoloString);
    //SE ORIGINE = NAPOLI LA COLONNA TIPOLOGIA = IN PARTENZA, SE DESTINAZIONE = NAPOLI, LA COLONNA TIPOLOGIA = IN ARRIVO

    void getVoloPerAmministratore(int idUser, ArrayList<Volo> voli);
    void setGate(String codiceVolo, int gate);

    void aggiornaVolo(String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo);
    //SE LA TIPOLOGIA è IN PARTENZA IL LUOGO VA NELLA COLONNA DESTINAZIONE, SE NO NELLA COLONNA ARRIVO
}