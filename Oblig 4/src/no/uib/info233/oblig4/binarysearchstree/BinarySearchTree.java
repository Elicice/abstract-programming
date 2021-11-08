package no.uib.info233.oblig4.binarysearchstree;

/**
 *klasse for binært søketre.
 *
 *
 * @author algorithms.tutorialhorizon.com
 * @author Cecilie Lyshoel
 *
 * @version Oblig 4 v1
 */
public class BinarySearchTree {
    public static  Node root;
    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * Metode for å søke opp en node og se om den finnes
     *
     * @param id
     * @return om man finner noden
     */
    public boolean searchNode(int id){
        Node current = root;
        while(current!= null){
            if(current.data == id){
                return true;
            }else if(current.data>id){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return false;
    }

    /**
     * Metode for å slette en node
     *
     *
     * @param id
     * @return om node blir slettet eller ikke
     */
    public boolean deleteNode(int id){


        //finner node som skal slettes
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while(current.data != id){
            parent = current;
            if(current.data>id){
                isLeftChild = true;
                current = current.left;
            }else{
                isLeftChild = false;
                current = current.right;
            }
            if(current == null){
                return false;
            }
        }

        //Hvis node som skal slettes ikke har barn setter man verdien til null
        if(current.left == null && current.right == null){
            if(current == root){
                root = null;
            }
            if(isLeftChild == true){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //Hvis node som skal slettes kun har ett barn skal verdien til barnet erstette verdien til foreldernoden og man setter så verdien i opprinnelig barenode til null
        else if(current.right == null){
            if(current == root){
                root = current.left;
            }else if(isLeftChild){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
        }
        else if(current.left == null){
            if(current == root){
                root = current.right;
            }else if(isLeftChild){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
        }
        //hvis node som skal slettes har to barn, erstatt verdien i noden med successor-noden og sett noden successor opprinnelig var i til null.
        else if(current.left != null && current.right != null){

            Node successor	 = getSuccessorNode(current);
            if(current == root){
                root = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    /**
     * Metode for å finne successor til noden som skal slettes
     * @param deleleNode
     * @return successor til node som skal slettes
     */
    private Node getSuccessorNode(Node deleleNode){
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.right;
        while(current != null){
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        if(successsor != deleleNode.right){
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    /**
     * Metode for å sette inn node i det binære søketreet
     * @param id
     */
    public void insertNode(int id){
        Node newNode = new Node(id);
        if(root == null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent;

        while(true){
            parent = current;
            //om ny nodes verdi er mindre enn eksisterende node så går vi til venstre.
            if(id<current.data){
                current = current.left;
                if(current == null){
                    parent.left = newNode;
                    return;
                }
            //Om ny nodes verdi er større enn eksisternde node gå til høyre
            }else{
                current = current.right;
                if(current == null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    /**
     * Metode for å hente rotnoden
     * @return rotnode
     */
    public Node getRootNode() {
        return root;
    }
}

/**
 * Nøstet klasse for en node
 */
class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
        left = null;
        right = null;
    }

}
