package no.uib.info233.oblig4.binarysearchstree;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testklasse for binært søketre for å teste om de fem elementene i kulelisten i oppgave b stemmer med svaret gitt.
 *
 * @author Cecilie Lyshoel
 *
 * @version Oblig 4 v1
 *
 */

public class BinarySearchTreeTest {

    /**
     * Alle testene skal innehodle det orginale binære søketreet
     */
    private BinarySearchTree bst;
    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
        bst.insertNode(8);
        bst.insertNode(3);
        bst.insertNode(10);
        bst.insertNode(1);
        bst.insertNode(6);
        bst.insertNode(14);
        bst.insertNode(4);
        bst.insertNode(7);
        bst.insertNode(13);
    }

    /**
     * alle tester skal opphøre etter gjennomgang
     */
    @AfterEach
    void tearDown() {
    }


    /**
     *Testmetode for å teste at 15 blir innsatt korrekt
     */
    @Test
    public void testInsertFemten(){


        bst.insertNode(15);

        // søk opp 15 og se om den finnes i treet nå
        Assert.assertTrue("Number 15 is not found", bst.searchNode(15));

        //sjekke at nodene før og node med verdi 15 er på rett plass i treet
        Assert.assertEquals("Root node has not correct value", 8, bst.getRootNode().data);
        Assert.assertEquals("First right node has not correct value", 10, bst.getRootNode().right.data);
        Assert.assertEquals("First right node has not correct value", 14, bst.getRootNode().right.right.data);
        Assert.assertEquals("First right node has not correct value", 15, bst.getRootNode().right.right.right.data);

        //sjekke at 15 ikke har et barn
        Assert.assertNull("Last right node has unexpected child",  bst.getRootNode().right.right.right.right);

        //sjekke at innsettingen ikke har gjort noe merkelig
        Assert.assertFalse("Number 150 was found", bst.searchNode(150));

    }
    /**
     *Testmetode for å sjekke innsetting av node med verdi 2
     */
    @Test
    public void testInsertTo(){

        bst.insertNode(2);

        //sjekket at den finner innsatt node
        Assert.assertTrue("Number 2 is not found", bst.searchNode(2));

        //sjekke at noder er på rett plass
        Assert.assertEquals("Root node has not correct value", 8, bst.getRootNode().data);
        Assert.assertEquals("First left node has not correct value", 3, bst.getRootNode().left.data);
        Assert.assertEquals("First left node has not correct value", 1, bst.getRootNode().left.left.data);
        Assert.assertEquals("First left node has not correct value", 2, bst.getRootNode().left.left.right.data);


        //sjekke at node ikke har barn
        Assert.assertNull("Last right node has unexpected child",  bst.getRootNode().left.left.right.right);

        // sjekke at innsetting ikke har gjort noe rart
        Assert.assertFalse("Number 22 was found", bst.searchNode(22));
    }
    /**
     *Test for innsetting av node med verdi 15
     */
    @Test
    public void testInsertFem(){
        bst.insertNode(5);

        // sjekke at endring er utført
        Assert.assertTrue("Number 5 is not found", bst.searchNode(5));


        //sjekke at noder er på rett plass
        Assert.assertEquals("Root node has not correct value", 8, bst.getRootNode().data);
        Assert.assertEquals("First left node has not correct value", 3, bst.getRootNode().left.data);
        Assert.assertEquals("First left node has not correct value", 6, bst.getRootNode().left.right.data);
        Assert.assertEquals("First left node has not correct value", 7, bst.getRootNode().left.right.right.data);
        Assert.assertEquals("First left node has not correct value", 4, bst.getRootNode().left.right.left.data);
        Assert.assertEquals("First left node has not correct value", 5, bst.getRootNode().left.right.left.right.data);

        //Sjekke at node ikke har barn
        Assert.assertNull("Last right node has unexpected child",  bst.getRootNode().left.right.left.right.right);

        // sjekke at innsetting ikke har gjort noe rart
        Assert.assertFalse("Number 55 was found", bst.searchNode(55));
    }
    /**
     *Testmetode for sletting av node med verdi 13, node uten barn
     */
    @Test
    public void testDeletTretten(){
        bst.deleteNode(13);

        // Sjekke at endring er utført
        Assert.assertFalse("Number 13 is not found", bst.searchNode(13));

        //sjekke at noder er på rett plass
        Assert.assertEquals("Root node has not correct value", 8, bst.getRootNode().data);
        Assert.assertEquals("First right node has not correct value", 10, bst.getRootNode().right.data);
        Assert.assertEquals("First right node has not correct value", 14, bst.getRootNode().right.right.data);


        //Sjekke at node faktisk er null og ikke bare endret verdi
        Assert.assertNull("node was not deleted",  bst.getRootNode().right.right.left);

    }
    /**
     *Testmetode for sletting av node med verdi 6, noe med to barn
     */
    @Test
    public void testDeletSeks(){
        bst.deleteNode(6);
        //sjekke at man ikke finner verdien 6
        Assert.assertFalse("Number 6 is not found", bst.searchNode(6));

        //sjekke at alle nodene er på rett plass
        Assert.assertEquals("Root node has not correct value", 8, bst.getRootNode().data);
        Assert.assertEquals("First left node has not correct value", 3, bst.getRootNode().left.data);
        Assert.assertEquals("First left node has not correct value", 7, bst.getRootNode().left.right.data);
        Assert.assertEquals("First left node has not correct value", 4, bst.getRootNode().left.right.left.data);

        //sjekke at rett node nå har verdi null
        Assert.assertNull("node was not deleted",  bst.getRootNode().left.right.right);

    }

}