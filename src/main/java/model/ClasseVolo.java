package model;

/**
 * Rappresenta la classe di viaggio associata a un posto su un volo.
 * <p>
 * Ogni classe offre livelli diversi di comfort, servizi e tariffe.
 * Questi valori possono essere utilizzati per la gestione delle prenotazioni,
 * l’assegnazione dei posti e la visualizzazione delle opzioni al passeggero.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public enum ClasseVolo {

    /**
     * Classe economica, offre una sistemazione di base a tariffa ridotta.
     */
    Economy,

    /**
     * Classe business, con comfort e servizi aggiuntivi rispetto alla economy.
     */
    Business,

    /**
     * Prima classe, con il massimo livello di lusso e servizi personalizzati.
     */
    FirstClass;
}

