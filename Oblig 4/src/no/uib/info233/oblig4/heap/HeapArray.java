package no.uib.info233.oblig4.heap;

import java.util.Arrays;

/**
 * Klasse for HeapArray
 *
 * @author Cecilie Lyshoel
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 *
 * @version oblig 4 v1
 */
public class HeapArray<T> implements Heap{

    private int lastIndex;
    private T[] heap;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public HeapArray(){
        this (DEFAULT_CAPACITY);
    }

    public HeapArray(int initialCapasity){
        if(initialCapasity < DEFAULT_CAPACITY)
            initialCapasity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapasity);

        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapasity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    }

    /**
     * Legg et nytt element p? haugen.
     *
     * @param element Elementet som skal legges p? haugen
     */
    @Override
    public void add(Comparable element) {

        checkInitialization();
        int newIndex = lastIndex+1;
        int parentIndex = newIndex/2;

        while((parentIndex>0)&& element.compareTo(heap[parentIndex]) > 0) {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        heap[newIndex] = heap[parentIndex];
        lastIndex++;
        ensureCapacity();
        }



    /**
     * Fjern topp-elementet fra haugen (det st?rste elementet).
     *
     * @return topp-element eller null hvis haugen er tom
     */
    @Override
    public Comparable remove() {
        checkInitialization();
        T root = null;

        if (!isEmpty())
        {
            root = heap[1];               // Return value
            heap[1] = heap[lastIndex]; // Form a semiheap
            lastIndex--;                  // Decrease size
            reheap(1);                    // Transform to a heap
        }

        return (Comparable) root;
    }

    /**
     * Ser p? topp-elementet i haugen (det st?rste elementet).
     *
     * @return topp-elementet eller null hvis haugen er tom.
     */
    @Override
    public Comparable peek() {
        if(lastIndex <= 0){
            return Heap.length;
        }else
            return null;
    }

    /**
     * Er haugen tom eller ikke.
     *
     * @return sann hvis haugen er tom.
     */
    @Override
    public Boolean isEmpty() {
        return lastIndex < 1;
    }

    /**
     * Returnerer st?rrelsen av haugen.
     *
     * @return Antall element i haugen
     */
    @Override
    public Integer getSize() {
        return lastIndex;
    }

    /**
     * Fjerner alle elementene fra haugen.
     */
    @Override
    public void clear() {
        checkInitialization();
        while (lastIndex >-1){
            heap[lastIndex] = null;
            lastIndex--;
        }
    }

    /**
     * Privat metode for å
     * @param rootIndex
     */
    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while (!done && (leftChildIndex <= lastIndex) )
        {
            int largerChildIndex = leftChildIndex; // assume larger
            int rightChildIndex = leftChildIndex + 1;

            if ( (rightChildIndex <= lastIndex) &&
                    heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            }

            if (orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
            {
                done = true;
            }
        }

        heap[rootIndex] = orphan;
    }

    /**
     * privat metode for å sjekke at man ikke ber om større kapsitet enn det som er tilgjengelig.
     * @param capacity
     */
    private void checkCapacity(int capacity)
    {
        if (capacity < DEFAULT_CAPACITY)
        {
            capacity = DEFAULT_CAPACITY;
        }
        else if (capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException("Attempt to create a heap " +
                    "whose capacity is larger than " +
                    MAX_CAPACITY);
        }
    }

    /**
     * Privalt metode for å doble kapaisteten hvis arrayet er fult
     */
    private void ensureCapacity()
    {
        if (lastIndex >= heap.length)
        {
            int newCapacity = 2 * heap.length;
            checkCapacity(newCapacity);
            heap = Arrays.copyOf(heap, newCapacity);
        }
    }

    /**
     * Privat metode for å sjekke at det er blitt initialisert, hvis ikke kaster den unntal
     */
    private void checkInitialization()
    {
        if (!initialized)
        {
            throw new SecurityException ("MaxHeap object is not initialized properly.");
        }
    }
}
