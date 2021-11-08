package no.uib.info233.Oblig1;

import java.lang.reflect.Array;
/**
 *Klassen TwostackArray implementerer et array med to stabeler
 *
 *
 * @author Cecilie Lyshoel
 * @version oblig1 v 1
 *
 *
 * */

public class TwostackArray<E> implements Twostack<E>{

    public TwostackArray (int size){
        this.size = size;
        this.twoStackArray = (E[]) new Object[size];//unchecked, E vil være et objekt uannsett
        this.rightSize = 0;
        this.leftSize = 0;

    }
    public TwostackArray(){
        this.size = 100;
        this.twoStackArray = (E[]) new Object[size];//unchecked, E vil være et objekt uannsett
        this.rightSize = 0;
        this.leftSize = 0;
    }

    /**
     * felt hvor variabler blir deklarert
     */
    private E[] twoStackArray;
    private int size;
    private int rightSize;
    private int leftSize;


    /**
     * Retunerer hvor mange elementer angitt stabel inneholder.
     *
     * @param right sann hvis høyre stabel og usann hvis venstre stabel
     * @return Størrelsen
     */
    @Override
    public Integer size(Boolean right) {
        if(right){
            return rightSize;

        }else{
            return leftSize;
        }
    }

    /**
     * Legger et element i angitt stabel.
     *
     * @param right   sann hvis høyre stabel og usann hvis venstre stabel
     * @param element Elementet som skal stables.
     * @throws TwostackFullException når det ikke er plass til elementet.
     */
    @Override
    public void push(Boolean right, E element) throws TwostackFullException {
       if(rightSize + leftSize >= size){
           throw new TwostackFullException();
        }

        if (right){
            int nextFreePos = size - rightSize - 1; //neste posisjon er lik størrelse på array minus størrelse på høyrestabel minus 1
            twoStackArray[nextFreePos] = element;
            rightSize++;
        }
        else{
            int nextFreePos = leftSize; //neste posisjon er alltid lik som størrelsen på venstrestabel.
            twoStackArray[nextFreePos] = element;
            leftSize++;
        }
    }

    /**
     * Stabler av topp-elementet av angitt stabel og returnerer det.
     *
     * @param right sann hvis høyre stabel og usann hvis venstre stabel
     * @return Topp-elementet i samlingen.
     * @throws TwostackEmptyException hvis stabelen er tom
     */
    @Override
    public E pop(Boolean right) throws TwostackEmptyException {

        if(right){
            if(rightSize == 0){ //sjekker om stabel er tom
                throw new TwostackEmptyException();
            }else {
                int topOfStack = size - rightSize;//finner toppen av stabelen
                E temp = twoStackArray[topOfStack];
                twoStackArray[topOfStack] = null;
                rightSize--;
                return temp;
            }
        }else{//hvis venstre (ikke høyre)
            if(leftSize == 0){//sjekker om stabel er tom
                throw new TwostackEmptyException();
            }else{//hvis ikke tom
                int topOfStack = leftSize - 1;//finner toppen av stabel
                E temp = twoStackArray[topOfStack];
                twoStackArray[topOfStack] = null;
                leftSize--;
                return temp;
            }
        }
    }

    /**
     * Retunerer topp-elementet av angitt stabel, men fjerner det ikke.
     *
     * @param right sann hvis høyre stabel og usann hvis venstre stabel
     * @return Det første elementet i samlingen.
     * @throws TwostackEmptyException hvis samlingen er tom.
     */
    @Override
    public E peek(Boolean right) throws TwostackEmptyException {
        if(right){//hvis høyre
            if(rightSize == 0){ //sjekker om stabel er tom
                throw new TwostackEmptyException();//hvis tom så kaster unntak
            }else {//hvis ikke
                int topOfStack = size - rightSize;//finner toppen av stabelen
                return twoStackArray[topOfStack];//returnerer verdien til toppen av stabelen
            }
        }else{//hvis venstre (ikke høyre)
            if(leftSize == 0){//sjekker om stabel er tom
                throw new TwostackEmptyException();//hvis tom så kaster unnatk
            }else{//hvis ikke tom
                int topOfStack = leftSize - 1;//finner toppen av stabel
                return twoStackArray[topOfStack];//returnerer verdien til toppen av stabelen
            }
        }
    }

    /**
     * Sjekker om et element er i samlingen.
     *
     * @param element Elementet som kanskje er i samlingen.
     * @return retunerer true hvis elementet er i samlingen.
     */
    @Override
    public Boolean contains(E element) {
        for (int i = 0; i < leftSize; i++) {
            if (element == twoStackArray[i]) {
                return true;
            }
        }
        for (int i = rightSize; i < size; i++) {
            if (element == twoStackArray[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fjerner alle elementene fra samlingen.
     */
    @Override
    public void clear() {
        this.twoStackArray = (E[]) new Object[size];
        this.rightSize = 0;
        this.leftSize = 0;
    }

    /**
     * Retunerer en tabell med alle elementene i samlingen.
     *
     * @param a
     * @return En tabell med alle elementene i samlingen.
     */
    @Override
    public <T> T[] toArray(T[] a) {
        int tempSize = leftSize;
        T[] tempArray = (T[]) Array.newInstance(a.getClass().getComponentType(), leftSize+rightSize);

        for (int i = 0 ; i < leftSize; i++){
            tempArray[i] = (T) twoStackArray[i];
        }
        for (int i = size - rightSize; i<size; i++){
            tempArray[tempSize] = (T) twoStackArray[i];
            tempSize++;
        }

        return tempArray;
    }

}
