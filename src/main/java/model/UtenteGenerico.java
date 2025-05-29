package model;

import java.util.ArrayList;
import java.util.Random;

public class UtenteGenerico extends Utente {

    private ArrayList<Prenotazione> prenotazioniEffetuate;
    private static int count;

    public UtenteGenerico(String login, String password, String email) {

        super(login, password, email);
        this.prenotazioniEffetuate = new ArrayList<Prenotazione>();
        count=101;
    }

    //PRENOTA UN VOLO
    public Prenotazione prenotaVolo(Volo v, String posto, ClasseVolo classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita) {

        if (v.getStato().equals(StatoVolo.Programmato)) {

            Prenotazione p = new Prenotazione(posto, classe);

            Passeggero passeggero = new Passeggero(nome, cognome, numTelefono, numDocumento, sesso, dataNascita);

            p.setPasseggero(passeggero);

            p.setVolo(v);

            prenotazioniEffetuate.add(p);

            v.getPrenotazioni().add(p);

            return p;

            }

        return null;
    }

    //CERCA UNA PRENOTAZIONE GIA' EFFETTUATA
    public ArrayList<Prenotazione> cercaPrenotazione(String codiceVolo, String dataVolo, String orarioPartenza) {

        ArrayList<Prenotazione> prenotazioniTrovate = new ArrayList<Prenotazione>();

        for (Prenotazione p : prenotazioniEffetuate) {
            if (!codiceVolo.isEmpty() && !p.getVolo().getCodice().equals(codiceVolo))
                continue;
            if(!dataVolo.isEmpty() && !p.getVolo().getDataPartenza().equals(dataVolo))
                continue;
            if(!orarioPartenza.isEmpty() && !p.getVolo().getOrarioPartenza().equals(orarioPartenza))
                continue;

            prenotazioniTrovate.add(p);
        }

        return prenotazioniTrovate;
    }

    //MODIFICA UNA PRENOTAZIONE GIA' EFFETTUATA
    public void modificaPrenotazione(Prenotazione prenotazione, String posto, ClasseVolo classeVolo, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero, char sessoPasseggero, Bagaglio bagaglio ) {



        for(Prenotazione p : prenotazioniEffetuate) {

            if (p.equals(prenotazione)) {
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
                if (!bagaglio.toString().isEmpty())
                    p.setBagaglio(bagaglio);
            }
        }
    }

    //SEGNALA LO SMARRIMENTO DI UN BAGAGLIO
    public void segnalaSmarrimento(Bagaglio b) {

        for (Prenotazione p : prenotazioniEffetuate) {

            if (p.getBagaglio().equals(b))
                p.getBagaglio().setStato(StatoBagaglio.Smarrito);

        }
    }

    //EFFETTUAT L'OPERAZIONE DI CHECK-IN
    public void chekIn(Prenotazione prenotazione, boolean bagaglio) {

        for (Prenotazione p : prenotazioniEffetuate) {
            if (p.equals(prenotazione)) {

                p.setNumBiglietto(count);
                p.setStato(StatoPrenotazione.Confermata);
                count++;

                if(bagaglio) {
                    p.setBagaglio(new Bagaglio(count+1000, StatoBagaglio.Caricato));
                    count++;
                }
            }
        }
    }


    //GETTERS E SETTERS
    public ArrayList<Prenotazione> getPrenotazioniEffetuate() {
        return prenotazioniEffetuate;
    }

    public void setPrenotazioniEffetuate(ArrayList<Prenotazione> prenotazioniEffetuate) {
        this.prenotazioniEffetuate = prenotazioniEffetuate;
    }
}

