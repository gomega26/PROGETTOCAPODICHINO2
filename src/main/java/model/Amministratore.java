package model;

import java.util.ArrayList;

public class Amministratore extends Utente {

    private String nome;
    private String cognome;
    private ArrayList<Volo> voliGestiti;


    //COSTRUTTORE
    public Amministratore(String login, String password, String email, String nome, String cognome){

        super(login, password, email);
        this.nome = nome;
        this.cognome = cognome;
        this.voliGestiti = new ArrayList<Volo>();
    }

    //CERCA PASSEGGERO
    public ArrayList<Passeggero> cercaPasseggero(String nome, String cognome, String numDocumento, char sesso){



        ArrayList<Passeggero> passeggeriTrovati = new ArrayList<Passeggero>();

        for (Volo v : voliGestiti) {

            for (Prenotazione p : v.getPrenotazioni()) {

                if (!nome.isEmpty() && !p.getPasseggero().getNome().equals(nome))
                    continue;
                if (!cognome.isEmpty() && !p.getPasseggero().getCognome().equals(cognome))
                    continue;
                if (!numDocumento.isEmpty() && !p.getPasseggero().getNumDocumento().equals(numDocumento))
                    continue;
                if (sesso != '-' && p.getPasseggero().getSesso() != sesso)
                    continue;

                passeggeriTrovati.add(p.getPasseggero());
            }
        }


        return passeggeriTrovati;
    }

    //INSERISCE UN NUOVO VOLO NELLA LISTA DEI VOLI
    public void inserisciVolo(Volo v){
        voliGestiti.add(v);
    }

    //MODIFICA UN VOLO
    public void aggiornaVolo(Volo volo, String orarioPartenza, String orarioArrivo, String dataPartenza, String durata, int ritardo, StatoVolo statoDelVolo) {

        for (Volo v : voliGestiti) {

            if (v.equals(volo)) {

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
            }
        }
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

        for (Volo v : voliGestiti) {
            for (Prenotazione p : v.getPrenotazioni()) {
                if (p.getBagaglio().equals(bagaglio))
                    p.getBagaglio().setStatoBagaglio(stato);
            }
        }
    }

    //VISUALIZZA LA LISTA DEI BAGAGLI SMARRITI
    public ArrayList<Bagaglio> visualizzaSmarrimenti(){

        ArrayList<Bagaglio> bagagliSmarriti = new ArrayList<Bagaglio>();

        for (Volo v: voliGestiti) {
            for(Prenotazione p : v.getPrenotazioni()) {
                if (p.getBagaglio().getStato() == StatoBagaglio.Smarrito)
                    bagagliSmarriti.add(p.getBagaglio());
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
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public ArrayList<Volo> getVoliGestiti() {
        return voliGestiti;
    }

    public void setVoliGestiti(ArrayList<Volo> voliGestiti) {
        this.voliGestiti = voliGestiti;
    }
}
