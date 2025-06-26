package dao;

import java.util.List;
import model.*;

public interface UtenteGenericoDAO {

    UtenteGenerico findById(int id);
    List<UtenteGenerico> findAll();
    void save(UtenteGenerico utente);
    void update(UtenteGenerico utente);
    void delete(int id);
}
