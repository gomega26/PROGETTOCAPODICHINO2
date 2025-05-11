package model;

import java.util.ArrayList;
import java.util.Random;

public class UtenteGenerico extends Utente {

    private ArrayList<Prenotazione> prenotazioniEffetuate;

    public UtenteGenerico(String login, String password, String email) {

        super(login, password, email);
        this.prenotazioniEffetuate = new ArrayList<Prenotazione>();
    }

    //PRENOTA UN VOLO
    public Prenotazione prenotaVolo(Volo v, String posto, ClasseVolo classe, String nome, String cognome, String numTelefono, String numDocumento, char sesso, String dataNascita) throws NonAutenticato{

        if(this.autenticato) {

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

        else
            throw new NonAutenticato("Eseguire log-in");
    }

    //CERCA UNA PRENOTAZIONE GIA' EFFETTUATA
    public ArrayList<Prenotazione> cercaPrenotazione(String codiceVolo, String dataVolo, String orarioPartenza) throws NonAutenticato{

        ArrayList<Prenotazione> prenotazioniTrovate = new ArrayList<Prenotazione>();

        if (this.autenticato) {

            for (Prenotazione p : prenotazioniEffetuate) {
                if (!codiceVolo.isEmpty() && !p.getVolo().codice.equals(codiceVolo))
                    continue;
                if(!dataVolo.isEmpty() && !p.getVolo().getDataPartenza().equals(dataVolo))
                    continue;
                if(!orarioPartenza.isEmpty() && !p.getVolo().getOrarioPartenza().equals(orarioPartenza))
                    continue;

                prenotazioniTrovate.add(p);
            }

            return prenotazioniTrovate;
        }

        else
            throw new NonAutenticato("Eseguire log-in");
    }

    //MODIFICA UNA PRENOTAZIONE GIA' EFFETTUATA
    public void modificaPrenotazione(Prenotazione prenotazione, String posto, ClasseVolo classeVolo, String nomePasseggero, String cognomePasseggero, String numDocumentoPasseggero, char sessoPasseggero, Bagaglio bagaglio ) throws NonAutenticato {

        if (this.autenticato) {

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

        else
            throw new NonAutenticato("Eseguire log-in");


    }

    //SEGNALA LO SMARRIMENTO DI UN BAGAGLIO
    public void segnalaSmarrimento(Bagaglio b) throws NonAutenticato{

        if (this.autenticato) {

            for (Prenotazione p : prenotazioniEffetuate) {

                if (p.getBagaglio().equals(b))
                    p.getBagaglio().setStato(StatoBagaglio.Smarrito);

            }
        }

        else
            throw new NonAutenticato("Eseguire log-in");

    }

    //EFFETTUAT L'OPERAZIONE DI CHECK-IN
    public void chekIn(Prenotazione prenotazione, Bagaglio bagaglio) throws NonAutenticato{

        if(autenticato) {
            Random r = new Random();

            if (this.autenticato) {
                for (Prenotazione p : prenotazioniEffetuate) {
                    if (p.equals(prenotazione)) {

                        p.setNumBiglietto(r.nextInt(100) + 1);
                        p.setStato(StatoPrenotazione.Confermata);

                        if (!bagaglio.toString().isEmpty())
                            p.setBagaglio(bagaglio);
                    }
                }
            }
        }

        else
            throw new NonAutenticato("Eseguire log-in");


    }

    //GETTERS E SETTERS
    public ArrayList<Prenotazione> getPrenotazioniEffetuate() {
        return prenotazioniEffetuate;
    }

    public void setPrenotazioniEffetuate(ArrayList<Prenotazione> prenotazioniEffetuate) {
        this.prenotazioniEffetuate = prenotazioniEffetuate;
    }
}

