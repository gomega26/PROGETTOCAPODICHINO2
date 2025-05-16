package interfaces;

import model.Prenotazione;
import model.StatoVolo;

import java.util.ArrayList;

public interface Volo {

    String getCodice();
    void setCodice(String codice);

    String getOrigine();
    void setOrigine(String origine);

    String getDestinazione();
    void setDestinazione(String destinazione);

    String getOrarioPartenza();
    void setOrarioPartenza(String orarioPartenza);

    String getOrarioArrivo();
    void setOrarioArrivo(String orarioArrivo);

    String getDataPartenza();
    void setDataPartenza(String dataPartenza);

    String getDurata();
    void setDurata(String durata);

    int getRitardo();
    void setRitardo(int ritardo);

    StatoVolo getStatoDelVolo();
    void setStatoDelVolo(StatoVolo statoDelVolo);

    String getCompagniaAerea();
    void setCompagniaAerea(String compagniaAerea);

    StatoVolo getStato();
    void setStato(StatoVolo stato);

    ArrayList<Prenotazione> getPrenotazioni();
    void setPrenotazioni(ArrayList<Prenotazione> prenotazioni);

}

