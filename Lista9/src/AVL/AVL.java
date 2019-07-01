package AVL;

public class AVL{

    Node root;

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

    private Node rotateRight(Node parent, Node leftChild) {

        parent.leftNode = leftChild.rightNode;
        leftChild.rightNode = parent;

        return leftChild;

    }

    private Node rotateLeft(Node parent, Node rightChild) {

        parent.rightNode = rightChild.leftNode;
        rightChild.leftNode = parent;

        return rightChild;

    }

    int getBalance(Node node){
        if(node == null)
            return 0;

        return height(node.rightNode) - height(node.leftNode);

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

    Node insertion (Node node, int value){

        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.leftNode = insertion(node.leftNode, value);
            node.leftNode.parent = node;
        }
        else if (value > node.value) {
            node.rightNode = insertion(node.rightNode, value);
            node.rightNode.parent = node;
        }
        else
            return node;

        return balancing(node, value);
    }

    void insert(int value){
        root = insertion(root, value);
    }

    Node balancing(Node node, int value){

        int balance = getBalance(node);

        if (balance > 1 && value > node.rightNode.value)
            return rotateLeft(node, node.rightNode);

        if (balance < -1 && value < node.leftNode.value)
            return rotateRight(node, node.leftNode);

        if (balance > 1 && value < node.rightNode.value) {
            node.rightNode = rotateRight(node.rightNode, node.rightNode.leftNode);
            return rotateLeft(node, node.rightNode);
        }

        if (balance < -1 && value > node.leftNode.value) {
            node.leftNode = rotateLeft(node.leftNode, node.leftNode.rightNode);
            return rotateRight(node, node.leftNode);
        }

        return node;
    }

    Node balancing2(Node node) {

        int balance = getBalance(node);

        if(balance > 1 && getBalance(node.rightNode) < 0){
            node.rightNode = rotateRight(node.rightNode, node.rightNode.leftNode);
            return rotateLeft(node, node.rightNode);
        }

        if(balance > 1 && getBalance(node.rightNode) >= 0){
            return rotateLeft(node, node.rightNode);
        }

        if(balance < -1 && getBalance(node.leftNode) > 0){
            node.leftNode = rotateLeft(node.leftNode, node.leftNode.rightNode);
            return rotateRight(node, node.rightNode);
        }

        if(balance < -1 && getBalance(node.leftNode) <= 0){
            return rotateRight(node, node.leftNode);
        }

        return node;

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
            } else {
                int smallestValue = findSmallestValue(current.rightNode);
                current.value = smallestValue;
                current.rightNode = deletion(current.rightNode, smallestValue);
                return current;
            }


        }
        if (value < current.value) {
            current.leftNode = deletion(current.leftNode, value);
            return balancing2(current);
        }
        current.rightNode = deletion(current.rightNode, value);

        return balancing2(current);
    }

    private int findSmallestValue(Node root) {
        return root.leftNode == null ? root.value : findSmallestValue(root.leftNode);
    }

    public void delete(int value) {
        root = deletion(root, value);
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

    boolean contains(int value) {
        return contain(root, value);
    }

    public static void main(String[] args){
        AVL tree = new AVL();

        tree.insert(5);
        tree.printTree();
        System.out.println("###########################");
        tree.insert(8);
        tree.printTree();
        System.out.println("###########################");
        tree.insert(10);
        tree.printTree();
        System.out.println("###########################");
        tree.insert(2);
        tree.printTree();
        System.out.println("###########################");
        tree.insert(3);
        tree.printTree();
        System.out.println("###########################");
        tree.insert(7);
        tree.printTree();
        System.out.println("###########################");
        tree.insert(1);
        tree.printTree();
        System.out.println("###########################");
        tree.insert(9);
        tree.printTree();
        System.out.println("###########################");
        tree.delete(7);
        tree.printTree();

    }

}
