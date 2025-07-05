package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface PasseggeroDAO {

    void create(String nome, String cognome, String numTelefono, String numDocumento, String sesso, String dataNascita);
    void modifica(int codicePrenotazione, String nome, String cognome, String numDocumentoPasseggero, char sesso);
    void getAll(ArrayList<Passeggero> passeggeri);
    int getIdPrenotazione(int idPasseggero);
}