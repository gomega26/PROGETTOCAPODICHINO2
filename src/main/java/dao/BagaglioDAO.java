package dao;

import java.util.List;
import model.*;

public interface BagaglioDAO {

    Bagaglio findById(int id);
    List<Bagaglio> findAll();
    void save(Bagaglio bagaglio);
    void update(Bagaglio bagaglio);
    void delete(int id);
}