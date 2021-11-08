package no.uib.info233.oblig4.heap;

/**
 * Et grensesnitt for ADT-en haug (heap). Dette er en maks-haug, det betyr at
 * det er det st?rste elementet som til ehver tid ligger p? toppen av haugen.
 *
 * @author Atle Geitung
 * @version 1.0
 */
public interface Heap<T extends Comparable<? super T>> {
    /**
     * Legg et nytt element p? haugen.
     *
     * @param element Elementet som skal legges p? haugen
     */
    void add(T element);

    /**
     * Fjern topp-elementet fra haugen (det st?rste elementet).
     *
     * @return topp-element eller null hvis haugen er tom
     */
    T remove();

    /**
     * Ser p? topp-elementet i haugen (det st?rste elementet).
     *
     * @return topp-elementet eller null hvis haugen er tom.
     */
    T peek();

    /**
     * Er haugen tom eller ikke.
     *
     * @return sann hvis haugen er tom.
     */
    Boolean isEmpty();

    /**
     * Returnerer st?rrelsen av haugen.
     *
     * @return Antall element i haugen
     */
    Integer getSize();

    /**
     * Fjerner alle elementene fra haugen.
     */
    void clear();
}
