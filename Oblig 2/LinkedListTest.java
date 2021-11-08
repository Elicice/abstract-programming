package no.uib.info233.oblig2;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testklasse for enhetstester av metoder for å legge til venn, slette venn og sortere venneliste
 *
 * @author Cecilie Lyshoel
 * @version Oblig 2, 1.0
 *
 */

class LinkedListTest {
    /**
     * @test addFriend
     * Tester om metode for å legge til venn virker.
     * Sjekker om forventet denn dukker opp i listen etter den er lagt til
     */
    @org.junit.jupiter.api.Test
    void addFriend() {
        LinkedList<Friend> friendList = new LinkedList<Friend>();
        friendList.addFriend(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"));
        Iterator<Friend> i = friendList.iterator();

        assertTrue(i.hasNext());
        assertEquals(i.next(), new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"));

        assertFalse(i.hasNext());
    }

    /**
     * @test enFriendEmptyList
     *
     * tester om det er mulig å slette venner fra listen når listen er tom.
     * Det er forventet at dette ikke skal være mulig
     */
    @org.junit.jupiter.api.Test
    void unFriendEpmtyList() {
        LinkedList<Friend> friendList = new LinkedList<Friend>();
        assertThrows(noFriendsException.class,
                () -> friendList.unFriend(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300")));

    }

    /**
     * @test unFriend
     *
     * tester om det er mulig å slette en venn midt i listen
     *
     * @throws noFriendsException
     */
    @org.junit.jupiter.api.Test
    void unFriend() throws noFriendsException {
        LinkedList<Friend> friendList = new LinkedList<Friend>();

        friendList.addFriend(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"));
        friendList.addFriend(new Friend("Andersen", "Anders", "oiu@hsi.com", "98765432"));
        friendList.addFriend(new Friend("Davidsen", "David", "ghj@jkl.vi", "67867800"));
        friendList.addFriend(new Friend("Corneliussen", "Cornelius", "gja@lku.at", "12309866"));

        friendList.unFriend(new Friend("Andersen", "Anders", "oiu@hsi.com", "98765432"));

        Iterator<Friend> i = friendList.iterator();

        assertTrue(i.hasNext());
        assertEquals(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"), i.next());

        assertTrue(i.hasNext());
        assertEquals(new Friend("Davidsen", "David", "ghj@jkl.vi", "67867800"), i.next());

        assertTrue(i.hasNext());
        assertEquals(new Friend("Corneliussen", "Cornelius", "gja@lku.at", "12309866"), i.next());

        assertFalse(i.hasNext());
    }

    /**
     * @test unFriendTail
     *
     * Tester om det er mulig å slette en venn som er siste element i listen
     *
     * @throws noFriendsException
     */
    @org.junit.jupiter.api.Test
    void unFriendTail() throws noFriendsException {
        LinkedList<Friend> friendList = new LinkedList<Friend>();

        friendList.addFriend(new Friend("Davidsen", "David", "ghj@jkl.vi", "67867800"));
        friendList.addFriend(new Friend("Corneliussen", "Cornelius", "gja@lku.at", "12309866"));

        friendList.unFriend(new Friend("Corneliussen", "Cornelius", "gja@lku.at", "12309866"));

        Iterator<Friend> i = friendList.iterator();

        assertTrue(i.hasNext());
        assertEquals(new Friend("Davidsen", "David", "ghj@jkl.vi", "67867800"), i.next());

        assertFalse(i.hasNext());
    }

    /**
     * @test inFriendHead
     *
     * Tester om det er mulig å slette første element i listen
     * @throws noFriendsException
     */
    @org.junit.jupiter.api.Test
    void unFriendHead() throws noFriendsException{
        LinkedList<Friend> friendList = new LinkedList<Friend>();

        friendList.addFriend(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"));
        friendList.addFriend(new Friend("Andersen", "Anders", "oiu@hsi.com", "98765432"));

        friendList.unFriend(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"));

        Iterator<Friend> i = friendList.iterator();

        assertTrue(i.hasNext());
        assertEquals(new Friend("Andersen", "Anders", "oiu@hsi.com", "98765432"), i.next());

        assertFalse(i.hasNext());
    }

    /**
     * @test sort
     *
     * tester om det er mulig å sortere en usortert liste.
     */
    @org.junit.jupiter.api.Test
    void sort() {
        LinkedList<Friend> friendList = new LinkedList<Friend>();

        friendList.addFriend(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"));
        friendList.addFriend(new Friend("Andersen", "Anders", "oiu@hsi.com", "98765432"));
        friendList.addFriend(new Friend("Davidsen", "David", "ghj@jkl.vi", "67867800"));
        friendList.addFriend(new Friend("Corneliussen", "Cornelius", "gja@lku.at", "12309866"));

        friendList.sort();

        Iterator<Friend> i = friendList.iterator();

        assertTrue(i.hasNext());
        assertEquals(new Friend("Andersen", "Anders", "oiu@hsi.com", "98765432"), i.next());

        assertTrue(i.hasNext());
        assertEquals(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"), i.next());


        assertTrue(i.hasNext());
        assertEquals(new Friend("Corneliussen", "Cornelius", "gja@lku.at", "12309866"), i.next());

        assertTrue(i.hasNext());
        assertEquals(new Friend("Davidsen", "David", "ghj@jkl.vi", "67867800"), i.next());

        assertFalse(i.hasNext());

    }
}