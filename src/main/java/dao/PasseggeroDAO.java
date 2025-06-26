package dao;

import java.util.List;
import model.*;

public interface PasseggeroDAO {

    Passeggero findById(int id);
    List<Passeggero> findAll();
    void save(Passeggero passeggero);
    void update(Passeggero passeggero);
    void delete(int id);
}