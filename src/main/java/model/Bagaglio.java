package model;

import java.util.Random;

public class Bagaglio {

    private static int cont=101;
    private int codice;
    private StatoBagaglio statoBagaglio;

    public Bagaglio() {

        this.codice = cont;
        this.statoBagaglio = StatoBagaglio.Ritirabile;

        cont++;
    }

    public void setStato(StatoBagaglio stato) {
        this.statoBagaglio = stato;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public int getCodice() {
        return codice;
    }

    public StatoBagaglio getStato() {
        return statoBagaglio;
    }

    public StatoBagaglio getStatoBagaglio() {
        return statoBagaglio;
    }

    public void setStatoBagaglio(StatoBagaglio statoBagaglio) {
        this.statoBagaglio = statoBagaglio;
    }
}
