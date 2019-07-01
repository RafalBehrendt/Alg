import java.util.Random;

public class BinarySearchTree {

    Random rand = new Random();
    Node root;


    private Node addElement(Node current, int value){

        if(current == null){
            return new Node(value);
        }

        if(value < current.value){
            current.leftNode = addElement(current.leftNode, value);
        }

        else if(value > current.value){
            current.rightNode = addElement(current.rightNode, value);
        }

        return current;
    }

    void add(int value){
        root = addElement(root, value);
    }

    private boolean contain(Node current, int value) {
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
                return current.leftNode;
            }

            if (current.leftNode == null) {
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

    public static void main(String[] args){

        BinarySearchTree tree = new BinarySearchTree();

//        for(int i = 0; i < 10; i++) {
//            tree.add(tree.rand.nextInt(10));
//        }

        tree.add(35);
        tree.add(11);
        tree.add(9);
        tree.add(10);
        tree.add(17);
        tree.add(26);
        tree.add(38);
        tree.add(42);

        tree.delete(11);

        System.out.println(tree.contains(10));
        System.out.println(tree.contains(11));

        System.out.println(tree.toString());

    }

}
