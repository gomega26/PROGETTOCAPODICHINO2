package model;

/**
 * Rappresenta lo stato corrente di un volo.
 * <p>
 * Gli stati possono essere aggiornati nel tempo per indicare
 * il progresso del volo, eventuali anomalie o la sua conclusione.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */

public enum StatoVolo {

    /**
     * Il volo è stato programmato ma non è ancora decollato.
     */
    Programmato,

    /**
     * Il volo è decollato ed è attualmente in corso.
     */
    Decollato,

    /**
     * Il volo è in ritardo rispetto all'orario previsto.
     */
    InRitardo,

    /**
     * Il volo è atterrato con successo.
     */
    Atterrato,

    /**
     * Il volo è stato cancellato e non verrà effettuato.
     */
    Cancellato;
}
