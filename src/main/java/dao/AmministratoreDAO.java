package dao;

import java.util.List;
import model.*;

public interface AmministratoreDAO {

    void signIn(String email, String login, String password);
}