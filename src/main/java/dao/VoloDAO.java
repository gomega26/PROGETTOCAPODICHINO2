package dao;

import java.util.List;
import model.*;

public interface VoloDAO {

    Volo findById(int id);
    List<Volo> findAll();
    void save(Volo volo);
    void update(Volo volo);
    void delete(int id);
}