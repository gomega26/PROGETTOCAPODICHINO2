package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface VoloDAO {

    void getAll(ArrayList<Volo> voli);
    Volo getVoloPerId(String codiceVolo);
    void setPrenotazione(int id_prenotazione);

}