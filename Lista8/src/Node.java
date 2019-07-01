public class Node {
    Node leftNode;
    Node rightNode;
    Node parent;
    int value;

    Node(int value, Node parent){
        leftNode = null;
        rightNode = null;
        this.parent = parent;
        this.value = value;
    }
}
