package model;

import java.util.ArrayList;

public class VoloInPartenza extends Volo {

    private int numGate;

    public VoloInPartenza(){};

    public VoloInPartenza(String compagniaAerea, String codice, String destinazione, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo, int numGate) {

        super(compagniaAerea,codice,orarioPartenza,orarioArrivo,dataPartenza,durata,ritardo,statoDelVolo);
        this.origine = "Napoli";
        this.numGate = numGate;
        this.destinazione = destinazione;
    }

    public int getNumGate() {
        return numGate;
    }

    public void setNumGate(int numGate) {
        this.numGate = numGate;
    }
}