package no.uib.info233.oblig2;


/**
 * klasse for sjekket unntakt om man ikke har venner
 *
 * @author Cecilie Lyshoel
 *
 * @version Oblig2, 1.0.
 */


public class noFriendsException extends Exception {

    /**
     * skriver ut feilmelding
     */
    public noFriendsException()
    {
        super("Du har ingen venner...:(");
    }

}
