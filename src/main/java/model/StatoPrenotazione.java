package model;

/**
 * Rappresenta lo stato attuale di una prenotazione effettuata su un volo.
 * <p>
 * Gli stati aiutano a monitorare il ciclo di vita della prenotazione: dalla sua creazione
 * alla conferma o eventuale cancellazione.
 * </p>
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public enum StatoPrenotazione {

    /**
     * La prenotazione è stata confermata con successo ed è attiva.
     */
    Confermata,

    /**
     * La prenotazione è in attesa di approvazione o conferma.
     */
    InAttesa,

    /**
     * La prenotazione è stata annullata e non è più valida.
     */
    Cancellata;
}

