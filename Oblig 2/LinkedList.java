package no.uib.info233.oblig2;

import java.util.Iterator;

/**
 * Klasse for en kjedet liste
 * @author Cecilie Lyshoel
 * @version Oblig 2, 1.0
 * @param <E>
 */

public class LinkedList<E extends Comparable> implements LinkedListInterface<E>, Iterable<E> //extends noFriendsExeption
  {

      /**
       * Nøstet klasse for node
       * @author Cecilie Lyshoel
       * @version Oblig2, 1.0
       * @param <T>
       */
      private class Node <T>{
        private T data;
        public Node next;
        public Node previous;

        public Node(T data) {
            this.data = data;
        };

    }

    private Node head;
    private Node tail;




    /**
     * Metode for å legge til en venn
     *
     * @param friend
     */
    @Override
    public void addFriend(E friend) {
        if(head == null){//når head er tom
            this.head = new Node<E>(friend);//legger venn-objekt inn i node, og lar denne være head
            tail = head;//tail er head fordi det abre er ett element
        }else{
            Node<E> n = new Node<E>(friend);//legger venn-objekt inn i node.
            n.previous = tail;//setter forrige node
            this.tail.next = n;//setter node som neste etter tail
            tail = n;
        }

    }

    /**
     * Metode for å fjerne venn fra vennelisten
     *
     * @param friend
     * @thorws noFriendsException
     */
    @Override
    public void unFriend(E friend) throws noFriendsException{
        if(head == null){
            throw new noFriendsException();
        }
        Node<E> n = head;
        while(n != null){
            if(friend.equals(n.data)){ //hvis venn er n
                if (n.previous != null) { //hvis n ikke er head
                    n.previous.next = n.next;//n's forrige node lenkes til n's neste node
                }else{
                    head = n.next;
                }
                if(n.next != null){//n er ikke tail
                    n.next.previous = n.previous;//n's neste kobles til n's forrige
                }else{
                    tail= n.previous;
                }
                return;
            }
            n = n.next;
        }

    }

    /**
     * Metode for å skrive ut vennelisten
     */
    @Override
    public void printFriendList() {
        for (E e : this){
            System.out.println(e);
        }

    }

    /**
     * Metode for å sortere venner basert på etternavn
     * Bubble sort fordi den kan brukes med kjedet liste og fungerer bra når en liste med tilfeldig ordning.
     *
     * Kjøretidsanalyse: Worst case er bubble sort O(n^2), man må gjennom hele listen og sammenligne to og to samt gjøre endringer om nødvendig.
     *
     */
    @Override
    public void sort() {
        boolean hasChanged;
        do {

            Node<E> n = head;
            hasChanged = false;

            while (n != null) {
                if (n.next != null) {
                    if (n.data.compareTo(n.next.data) > 0) {//sammenligne etternavn i venneobjetene
                        //gir nodene midlertidige identiteter
                        Node<E> a = n.previous;
                        Node<E> b = n;
                        Node<E> c = n.next;
                        Node<E> d = n.next.next;

                        //Flytter referanser
                        if (a != null) {//Hvis b ikke er head
                            a.next = c;
                        }

                        b.next = d;
                        c.next = b;
                        b.previous = c;
                        c.previous = a;
                        if (d != null) {//Hvis c ikke er tail
                            d.previous = b;
                        }
                        if(b == head){
                            head = c;
                        }
                        if(c == tail){
                            tail = b;
                        }
                        hasChanged = true;

                    }
                }
                n = n.next; //går videre

            }
        } while (hasChanged);
    }
      /**
       * returnerer iterartor over elementer
       *
       * @return an Iterator.
       */
      @Override
      public Iterator<E> iterator() {
          return new Iterator<E>() {
              private Node<E> current;


              @Override
              public boolean hasNext() {//sjekker om det finnes et neste element
                  if(current == null){
                      return head != null;
                  }
                  return current.next != null; //true så lenge elementet har et neste element
              }

              @Override
              public E next() { //Ønsker neste elemnetet i listen
                  if(!hasNext()){
                      return null;
                  }
                  if(current == null){
                      current = head;
                  }else{
                      current = current.next;
                  }
                  return current.data;

              }
          };
      }
}
