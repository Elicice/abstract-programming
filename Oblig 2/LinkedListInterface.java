package no.uib.info233.oblig2;

/**
 * Interface for en venneliste
 * @author Cecilie Lyshoel
 * @version Oblig2, 1.0
 */
public interface LinkedListInterface<E> {

    /**
     * Metode for å sortere venner basert på etternavn
     */
    public void sort();

    /**
     * Metode for å legge til en venn
     */
    public void addFriend(E friend);

    /**
     * Metode for å fjerne venn fra vennelisten
     */
    public void unFriend(E friend) throws noFriendsException;


    /**
     * Metode for å skrive ut vennelisten
     */
    public void printFriendList();


}
