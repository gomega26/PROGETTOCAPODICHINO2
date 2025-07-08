package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;

public interface BagaglioDAO {

    Bagaglio getBagagliPerUtenteGenerico(int idUser, int codiceBagaglio);
    Bagaglio getBagagliPerAmministartore(int codiceBagaglio);
    void setStato(int codiceBagaglio, String stato);
    void create(int id_prenotazione);
    void getSmarriti(ArrayList<Bagaglio> bagagliSmarriti);
    boolean segnalaSmarrimento(int idAmministratore, int codice); //BISOGNA VERIFICARE CHE IL BAGAGLIO SIA IN UN VOLO MONITORATO DA QUELL'AMMINISTRATORE
    void getBagagliPerAmministratore(int idAmministratore, ArrayList<Bagaglio> bagagli);
}