public class DSW{

    Node root;

    public void createPerfectTree() {
        if (null != root) {
            createBackbone();
            createPerfectBST();
        }
    }

    private void createBackbone() {
        Node grandParent = null;
        Node parent = root;
        Node leftChild;

        while (null != parent) {
            leftChild = parent.leftNode;
            if (null != leftChild) {
                grandParent = rotateRight(grandParent, parent, leftChild);
                parent = leftChild;
            } else {
                grandParent = parent;
                parent = parent.rightNode;
            }
        }
    }

    private Node rotateRight(Node grandParent, Node parent, Node leftChild) {
        if (null != grandParent) {
            grandParent.rightNode = leftChild;
        } else {
            root = leftChild;
        }
        parent.leftNode = leftChild.rightNode;
        leftChild.rightNode = parent;
        return grandParent;
    }


    private void createPerfectBST() {
        int n = 0;
        for (Node tmp = root; null != tmp; tmp = tmp.rightNode) {
            n++;
        }

        int m = greatestPowerOf2LessThanN(n + 1) - 1;
        makeRotations(n - m);

        while (m > 1) {
            makeRotations(m /= 2);
        }
    }

    int greatestPowerOf2LessThanN(int n) {
        int x = MSB(n);
        return (1 << x); // 2^x
    }

    public int MSB(int n) {
        int ndx = 0;
        while (1 < n) {
            n = (n >> 1);
            ndx++;
        }
        return ndx;
    }

    private void makeRotations(int bound) {
        Node grandParent = null;
        Node parent = root;
        Node child = root.rightNode;
        for (; bound > 0; bound--) {
            try {
                if (null != child) {
                    rotateLeft(grandParent, parent, child);
                    grandParent = child;
                    parent = grandParent.rightNode;
                    child = parent.rightNode;
                } else {
                    break;
                }
            } catch (NullPointerException e) {
                break;
            }
        }
    }

    private void rotateLeft(Node grandParent, Node parent, Node rightChild) {
        if (null != grandParent) {
            grandParent.rightNode = rightChild;
        } else {
            root = rightChild;
        }
        parent.rightNode = rightChild.leftNode;
        rightChild.leftNode = parent;
    }

}
