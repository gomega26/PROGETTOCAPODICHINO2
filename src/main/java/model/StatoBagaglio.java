package model;

/**
 * Rappresenta lo stato corrente di un bagaglio associato a un volo.
 * <p>
 * Lo stato del bagaglio viene aggiornato durante il processo aeroportuale,
 * dal caricamento sull’aereo fino all’eventuale recupero o smarrimento.
 * </p>
 *
 * @author Gianmarco Minei
 * @author Stefano Luongo
 * @author Alessandro Esposito
 */
public enum StatoBagaglio {

    /**
     * Il bagaglio è stato caricato sull'aereo ed è in transito.
     */
    Caricato,

    /**
     * Il bagaglio è disponibile al ritiro presso il nastro bagagli.
     */
    Ritirabile,

    /**
     * Il bagaglio è stato smarrito e non è attualmente rintracciabile.
     */
    Smarrito;
}
