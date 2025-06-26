package dao;

import java.util.List;
import model.*;

public interface PrenotazioneDAO {

    Prenotazione findById(int id);
    List<Prenotazione> findAll();
    void save(Prenotazione prenotazione);
    void update(Prenotazione prenotazione);
    void delete(int id);
}