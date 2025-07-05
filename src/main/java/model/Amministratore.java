package model;

import java.util.ArrayList;

public class Amministratore extends Utente {

    private ArrayList<Volo> voliGestiti;

    //COSTRUTTORE
    public Amministratore(String login, String password, String email){

        super(login, password, email);
        this.voliGestiti = new ArrayList<Volo>();
    }

    //CERCA PASSEGGERO
    public ArrayList<Passeggero> cercaPasseggero(ArrayList<Passeggero> passeggeri, String nome, String cognome, String numDocumento, char sesso){

        ArrayList<Passeggero> passeggeriTrovati = new ArrayList<>();

        for (Passeggero p : passeggeri) {

            if (!nome.isEmpty() && !p.getNome().equals(nome))
                continue;
            if (!cognome.isEmpty() && !p.getCognome().equals(cognome))
                continue;
            if (!numDocumento.isEmpty() && !p.getNumDocumento().equals(numDocumento))
                continue;
            if (sesso != '-' && p.getSesso() != sesso)
                continue;

            passeggeriTrovati.add(p);
        }

        return passeggeriTrovati;
    }

    //INSERISCE UN NUOVO VOLO NELLA LISTA DEI VOLI
    public void inserisciVolo(Volo v){
        voliGestiti.add(v);
    }

    //MODIFICA UN VOLO
    public void aggiornaVolo(Volo v, String luogo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo) {

        if (!orarioPartenza.isEmpty())
            v.setOrarioPartenza(orarioPartenza);
        if (!orarioArrivo.isEmpty())
            v.setOrarioArrivo(orarioArrivo);
        if (!durata.isEmpty())
            v.setDurata(durata);
        if (ritardo != 0)
            v.setRitardo(ritardo);
        if (!statoDelVolo.toString().isEmpty())
            v.setStato(statoDelVolo);

        if(v.getClass().getSimpleName().equals("VoloInPartenza"))
            v.setDestinazione(luogo);

        else
            v.setOrigine(luogo);
    }

    //ASSEGNA UN GATE AD UN VOLO IN PARTENZA
    public void assegnaGate(int gate, VoloInPartenza volo) {


        for (Volo v : voliGestiti) {
            if (v.equals(volo))
                ((VoloInPartenza) v).setNumGate(gate);
        }
    }

    //AGGIORNA LO STATO DI UN BAGAGLIO
    public void aggiornaStatoBagaglio(Bagaglio bagaglio, StatoBagaglio stato){

        bagaglio.setStatoBagaglio(stato);
    }

    //VISUALIZZA LA LISTA DEI BAGAGLI SMARRITI
    public ArrayList<Bagaglio> visualizzaSmarrimenti(){

        ArrayList<Bagaglio> bagagliSmarriti = new ArrayList<>();

        for (Volo v: voliGestiti) {
            for(Prenotazione p : v.getPrenotazioni()) {
                for(Bagaglio b: p.getBagagli()) {
                    if (b.getStato() == StatoBagaglio.Smarrito)
                        bagagliSmarriti.add(b);
                }
            }
        }

        return bagagliSmarriti;
    }

    //CERCA PRENOTAZIONE
    public ArrayList<Prenotazione> cercaPrenotazione(String codiceVolo, String dataVolo, String orarioPartenza, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero) {

        ArrayList<Prenotazione> prenotazioniTrovate = new ArrayList<Prenotazione>();


        for (Volo v : voliGestiti) {
            for(Prenotazione p : v.getPrenotazioni()) {
                if (!codiceVolo.isEmpty() && !p.getVolo().getCodice().equals(codiceVolo))
                    continue;
                if (!dataVolo.isEmpty() && !p.getVolo().getDataPartenza().equals(dataVolo))
                    continue;
                if (!orarioPartenza.isEmpty() && !p.getVolo().getOrarioPartenza().equals(orarioPartenza))
                    continue;
                if (!nomePasseggero.isEmpty() && !p.getPasseggero().getNome().equals(nomePasseggero))
                    continue;
                if (!cognomePasseggero.isEmpty() && !p.getPasseggero().getCognome().equals(cognomePasseggero))
                    continue;
                if (!numDocumentoPasseggero.isEmpty() && !p.getPasseggero().getNumDocumento().equals(numDocumentoPasseggero))
                    continue;

                prenotazioniTrovate.add(p);
            }
        }

        return prenotazioniTrovate;
    }

    //GETTERS

    public ArrayList<Volo> getVoliGestiti() {
        return voliGestiti;
    }

    public void setVoliGestiti(ArrayList<Volo> voliGestiti) {
        this.voliGestiti = voliGestiti;
    }
}
