package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface AmministratoreDAO {

    void signIn(String email, String login, String password);
    Amministratore logIn(String login, String password);
    void inserisciVolo(int idUser, String idVolo);
}