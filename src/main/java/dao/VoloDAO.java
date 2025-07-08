package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface VoloDAO {

    //SELECT
    void getAll(ArrayList<Volo> voli);
    void getVoliInPartenza(ArrayList<VoloInPartenza> voli);
    Volo getVoloPerId(String codiceVolo);
    void getVoliPerAmministratore(int idUser, ArrayList<Volo> voli);

    //INSERT
    void create(String compagniaAerea, String codice, String origine, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String stato);

    //UPDATE
    void setGate(String codiceVolo, int gate);
    void aggiornaVolo(String tipologia, String codiceVolo, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, String statoDelVolo);
}