package AVL;

public class Node {
    Node leftNode;
    Node rightNode;
    Node parent;
    int value;

    Node(int value){
        leftNode = null;
        rightNode = null;
        parent = null;
        this.value = value;
    }
}
