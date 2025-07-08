package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface PasseggeroDAO {

    int create(String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita);
    void modifica(int codicePrenotazione, String nome, String cognome, String numDocumentoPasseggero, char sesso);
    void getAll(ArrayList<Passeggero> passeggeri);
    Passeggero getPerId(int id);
}