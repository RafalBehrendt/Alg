import java.util.ArrayList;
import java.util.Random;

public class BinarySearchTree extends DSW{


    Random rand = new Random();
    int searching = -1;


    private Node addElement(Node current, int value, Node parent){

        if(current == null){
            return new Node(value, parent);
        }

        if(value < current.value){
            current.leftNode = addElement(current.leftNode, value, current);
        }

        else if(value > current.value){
            current.rightNode = addElement(current.rightNode, value, current);
        }

        return current;
    }

    void add(int value){
        root = addElement(root, value, null);
    }

    private boolean contain(Node current, int value) {
        searching++;
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? contain(current.leftNode, value)
                : contain(current.rightNode, value);
    }

    boolean contains(int value){
        return contain(root, value);
    }

    private Node deletion(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            if (current.leftNode == null && current.rightNode == null) {
                return null;
            }

            if (current.rightNode == null) {
                current.leftNode.parent = current.parent;
                return current.leftNode;
            }

            if (current.leftNode == null) {
                current.rightNode.parent = current.parent;
                return current.rightNode;
            }

            else{
                int smallestValue = findSmallestValue(current.rightNode);
                current.value = smallestValue;
                current.rightNode = deletion(current.rightNode, smallestValue);
                return current;
            }


        }
        if (value < current.value) {
            current.leftNode = deletion(current.leftNode, value);
            return current;
        }
        current.rightNode = deletion(current.rightNode, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.leftNode == null ? root.value : findSmallestValue(root.leftNode);
    }

    public void delete(int value) {
        root = deletion(root, value);
    }

    private String write(Node current){

        String wynik = Integer.toString(current.value);

        if(current.leftNode != null){
            wynik += "\n" + write(current.leftNode);
        }

        if(current.rightNode != null){
            wynik += "\n" + write(current.rightNode);
        }

        return wynik;

    }

    public String toString(){

        String wynik = write(root);

        return wynik;

    }

    public void test(int quantity, int bound){

        ArrayList<Integer> balancedTreeResults = new ArrayList<Integer>();

        for(int i = 1; i <= quantity; i++){

            BinarySearchTree tmpTree = new BinarySearchTree();

            for(int j = 0; j < bound*i; j++){
                tmpTree.add(rand.nextInt(bound*i));
            }

            int seek = rand.nextInt(bound*i);

            tmpTree.contains(seek);
            System.out.print(tmpTree.searching + " ");
            tmpTree.searching = -1;

            tmpTree.createPerfectTree();

            tmpTree.contains(seek);
            balancedTreeResults.add(tmpTree.searching);
            tmpTree.searching = -1;
        }

        System.out.println();

        for(int i = 0; i < balancedTreeResults.size(); i++){
            System.out.print(balancedTreeResults.get(i) + " ");
        }



    }

    public void preorder(Node current){

    if(current != null) {
        System.out.print(current.value + " ");

        preorder(current.leftNode);
        preorder(current.rightNode);
        }
    }

    public void postorder(Node current){
        if(current != null) {
             postorder(current.leftNode);
             postorder(current.rightNode);

             System.out.print(current.value + " ");
        }
    }

    public void inorder(Node current){
        if(current != null) {
            inorder(current.leftNode);
            System.out.print(current.value + " ");
            inorder(current.rightNode);
        }
    }


    void levelOrder() {

    for (int i = 1; i<=height(root); i++) {

            printGivenLevel(root, i);

        }
    }

    int height(Node current)
    {
        if (current == null)
            return 0;
        else
        {
            int lheight = height(current.leftNode);
            int rheight = height(current.rightNode);

            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }

    void printGivenLevel (Node current, int level)
    {
        if (current == null) {
            return;
        }
        if (level == 1)
            System.out.print(current.value + " ");
        else if (level > 1)
        {
            printGivenLevel(current.leftNode, level-1);
            printGivenLevel(current.rightNode, level-1);
        }
    }

    void printTree(){

            boolean[] ReqBind = new boolean[height(root)+1];
            for(int i = 1; i <= height(root); i++){
                ReqBind[i] = false;
            }
            printTreeElement(root, 1, true, ReqBind);

    }

    void printTreeElement(Node current, int level, boolean isRight, boolean RB[]) {

        if(current.rightNode != null){
            printTreeElement(current.rightNode, level+1, true, RB);
        }

        for (int i = 1; i <= level - 2; i++) {
            if(RB[i]) System.out.print("|   ");
            else System.out.print("    ");
        }

        if(isRight) {

            RB[level] = false;

            if (current == root) System.out.println(current.value);

            else {
                System.out.println("/---" + current.value);
                RB[level-1] = true;
            }
        }

        if(!isRight){
            RB[level] = false;
            System.out.println("\\---" + current.value);
            RB[level-1] = false;
        }

        if(current.leftNode != null){
            RB[level] = true;
            printTreeElement(current.leftNode, level+1, false, RB);
        }

    }

    public static void main(String[] args){

        BinarySearchTree tree = new BinarySearchTree();

        tree.add(9);
        tree.add(5);
        tree.add(4);
        tree.add(3);
        tree.add(8);
        tree.add(7);
        tree.add(6);
        tree.add(2);
        tree.add(1);


        System.out.print("Pre-order: ");
        tree.preorder(tree.root);
        System.out.println();
        System.out.print("Post-order: ");
        tree.postorder(tree.root);
        System.out.println();
        System.out.print("In-order: ");
        tree.inorder(tree.root);
        System.out.println();
        System.out.print("Level-order: ");
        tree.levelOrder();
        System.out.println();

        tree.printTree();
        System.out.println();
        tree.createPerfectTree();
        tree.printTree();

        System.out.println();
        System.out.println("Test - wyniki wyszukiwania tej samej liczby w drzewie zbalansowanym i niezbalansowanym. Testy od od 10000 co 10000 aż do miliona liści");
        System.out.println("Wyniki w pierwszym wierszu to ilość iteracji dla drzewa niezbalansowanego, a w drugim dla drzewa zbalansowanego");
        tree.test(100, 10000);


    }

}

