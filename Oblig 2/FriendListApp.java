package no.uib.info233.oblig2;

/**
 * Klasse for progrem for å lage en venneliste
 * @author Cecilie Lyshoel
 * @version Oblig 2, 1.0
 */

public class FriendListApp {
    /**
     * Program for å lage en venneliste
     * @param args
     */

    public static void main(String [] args){
        LinkedList<Friend> friendList = new LinkedList<Friend>();

        friendList.addFriend(new Friend("Bertilsen", "Bertil", "cvb.mn@aks.hd", "81549300"));
        friendList.addFriend(new Friend("Andersen", "Anders", "oiu@hsi.com", "98765432"));
        friendList.addFriend(new Friend("Davidsen", "David", "ghj@jkl.vi", "67867800"));
        friendList.addFriend(new Friend("Corneliussen", "Cornelius", "gja@lku.at", "12309866"));
        System.out.println("Usortert liste: ");
        friendList.printFriendList();
        System.out.println();
        System.out.println("Sortert liste: ");
        friendList.sort();

        friendList.printFriendList();
    }
}
