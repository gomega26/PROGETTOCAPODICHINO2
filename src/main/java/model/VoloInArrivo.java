package model;

import java.util.ArrayList;

public class VoloInArrivo extends Volo {

    public VoloInArrivo(String compagniaAerea, String codice, String origine, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo){

        super(compagniaAerea,codice,orarioPartenza,orarioArrivo,dataPartenza,durata, ritardo, statoDelVolo);
        this.origine = origine;
        this.destinazione = "Napoli";
    }
}

