package dao;

import java.util.List;
import model.*;

public interface UtenteGenericoDAO {

    void signIn(String email, String login, String password);
    UtenteGenerico logIn(String login, String password);
}
