package model;

import java.util.ArrayList;
import java.util.Random;

public class UtenteGenerico extends Utente {

    private ArrayList<Prenotazione> prenotazioniEffetuate;
    private static int count=101;

    public UtenteGenerico(String login, String password, String email) {

        super(login, password, email);
        this.prenotazioniEffetuate = new ArrayList<Prenotazione>();
    }

    //PRENOTA UN VOLO
    public int prenotaVolo(Volo v, String posto, ClasseVolo classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita, int NumBagagli) {

        int codice= -1;

        if (v.getStato().equals(StatoVolo.Programmato)) {

            Prenotazione p = new Prenotazione(posto, classe);

            Passeggero passeggero = new Passeggero(nome, cognome, numTelefono, numDocumento, sesso, dataNascita);

            p.setPasseggero(passeggero);

            p.setVolo(v);

            for(int i=0; i<NumBagagli; i++)
                p.getBagagli().add(new Bagaglio());

            prenotazioniEffetuate.add(p);

            v.getPrenotazioni().add(p);

            codice=p.getId();

            }

        return codice;
    }

    //MODIFICA UNA PRENOTAZIONE GIA' EFFETTUATA
    public void modificaPrenotazione(Prenotazione p, String posto, ClasseVolo classeVolo, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero, char sessoPasseggero, ArrayList<Bagaglio> bagagli ) {

        if (!posto.isEmpty())
            p.setPosto(posto);
        if (!classeVolo.toString().isEmpty())
            p.setClasseVolo(classeVolo);
        if (!nomePasseggero.isEmpty())
            p.getPasseggero().setNome(nomePasseggero);
        if (!cognomePasseggero.isEmpty())
            p.getPasseggero().setCognome(cognomePasseggero);
        if (!numDocumentoPasseggero.isEmpty())
            p.getPasseggero().setNumDocumento(numDocumentoPasseggero);
        if (sessoPasseggero != ' ')
            p.getPasseggero().setSesso(sessoPasseggero);
        if (!bagagli.isEmpty()){

            p.getBagagli().clear();

            for(Bagaglio b: bagagli)
                p.getBagagli().add(b);
        }
    }

    //SEGNALA LO SMARRIMENTO DI UN BAGAGLIO
    public void segnalaSmarrimento(Bagaglio b) {

        b.setStato(StatoBagaglio.Smarrito);

    }

    //EFFETTUAT L'OPERAZIONE DI CHECK-IN
    public String checkIn(Prenotazione p) {

            p.setNumBiglietto(count);
            p.setStato(StatoPrenotazione.Confermata);
            count++;

            return p.toString();
    }

    //GETTERS E SETTERS
    public ArrayList<Prenotazione> getPrenotazioniEffetuate() {
        return prenotazioniEffetuate;
    }

    public void setPrenotazioniEffetuate(ArrayList<Prenotazione> prenotazioniEffetuate) {
        this.prenotazioniEffetuate = prenotazioniEffetuate;
    }
}

